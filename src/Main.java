import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;


public class Main extends Application {

    public static void click(int x, int y, Robot bot) throws AWTException{
        bot.mouseMove(x, y);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public static void rightClick(int x, int y, Robot bot) throws AWTException {
        bot.mouseMove(x, y);
        bot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
    }


    public static void move(int x, int y, Robot bot) throws AWTException {
        bot.mouseMove(x, y);
    }





    public static void clickAndDrag(int x_start, int y_start, int x_end, int y_end, Robot bot) throws AWTException {
        bot.mouseMove(x_start, y_start);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseMove(x_end, y_end);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

    }

    public static void highlightErrorLine(int lineNumber, String[] lines, TextArea textArea, String[] scriptLine, int lengthDifference, int lengthDifferenceSingleLine) {
        int totalAfterEnd = 0;
        for (int i = 0; i < lineNumber; i++) {
            totalAfterEnd += lines[i].length();
            totalAfterEnd += 1;
        }
        //System.out.println("beginning: " + totalAfterEnd);
        //linePosition is the position at the beginning of the line where the error occured
        int lineEndPosition = totalAfterEnd - 1;
        //whitespace removal during parsing/running can lead to the position being different
        //so this accounts for that difference
        lineEndPosition += lengthDifference;
        //System.out.println(lineEndPosition);
        textArea.positionCaret(lineEndPosition);
        //get length of current line
        int totalAfterBeginning = 0;
        for (int i = 0; i < lineNumber - 1; i++) {
            totalAfterBeginning += lines[i].length();
            totalAfterBeginning += 1;
        }
        //System.out.println("end: " + totalAfterBeginning);
        int lineBeginningPosition = totalAfterBeginning - 1; //bad, need to fix

        lineBeginningPosition += lengthDifference;
        lineBeginningPosition -= lengthDifferenceSingleLine;
        textArea.selectRange(lineBeginningPosition, lineEndPosition);

        textArea.requestFocus();
    }

    //parses the script, runs the script if there were no errors detected during parsing, and creates threads for each click/rightclick/etc command
    public static void parseAndRunScript(int timesToRepeat, TextArea textArea, Label loadingLabel, TotalLoopTime totalLoopTime, ScriptHalter scriptHalter, Robot bot, Button runMacroButton) throws AWTException {
        new Thread(()->{ //use another thread so long process does not block gui
                //update gui using fx thread
                //Platform.runLater(() -> label.setText(text));
            for (int i = 0; i < timesToRepeat; i++) {
                //this is where the macro stuff happens
                //System.out.println("Number of lines in the text area: " + String.valueOf(textArea.getText().split("\n").length));

                int numberOfLines = textArea.getText().split("\n").length;
                String lines[] = textArea.getText().split("\n");


                //commands in AutoInputScript:
                //for now, just click and wait
                //example code:
                //click 300 400
                //wait 500

                //parse the textArea just to see if there are any problems
                //this parsing does NOT run the script, it just checks it for errors
                boolean scriptingError = false; //set to true if issue with parsing script
                boolean scriptIsEmpty = true;
                int lengthDifference = 0; //total difference between whitespace-removed and total script
                                        //removing whitespace helps with interpreting, but totals are important for highlighting error lines

                for (int j = 0; j < numberOfLines; j++) {
                    /*Platform.runLater(new Runnable(){
                        @Override public void run() {
                            loadingLabel.setText("Parsing script");
                        }
                    });*/




                    if (scriptingError) {
                        runMacroButton.setDisable(false);
                        break;
                    }

                    int oldLength = lines[j].length();

                    lines[j] = lines[j].strip();



                    lengthDifference += oldLength - lines[j].length(); //this is for the entire script
                    int lengthDifferenceSingleLine = oldLength - lines[j].length(); //difference for a single line

                    String scriptLine[] = lines[j].split(" ");


                    int lineNumber = j + 1;

                    if (scriptLine.length != 0) {
                        if (scriptHalter.isUserWantsToHaltScript()) {
                            Platform.runLater(new Runnable(){
                                @Override public void run() {
                                    loadingLabel.setText("Script halted");
                                }
                            });
                            runMacroButton.setDisable(false);
                            break;
                        }


                        switch (scriptLine[0]) {
                            case "move":
                                //move 500 500
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    break;
                                }
                                scriptIsEmpty = false;
                                if (scriptLine.length != 3) {
                                    int finalLengthDifference = lengthDifference;
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid arg for move");
                                            highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference, lengthDifferenceSingleLine);
                                        }
                                    });
                                    scriptingError = true;
                                    runMacroButton.setDisable(false);
                                    return;
                                } else {
                                    try {
                                        Integer.parseInt(scriptLine[1]);
                                        Integer.parseInt(scriptLine[2]);
                                        //parse number after wait, i.e. move 400 400
                                    } catch (NumberFormatException nfe) {
                                        //nfe.printStackTrace();
                                        int finalLengthDifference1 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": move args must be ints");
                                                highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        return;
                                    }
                                }
                                break;
                            case "wait":
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    break;
                                }
                                scriptIsEmpty = false;
                                //check if it has an int arg
                                if (scriptLine.length != 2) {
                                    int finalLengthDifference2 = lengthDifference;
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid arg for wait");
                                            highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference2, lengthDifferenceSingleLine);
                                        }
                                    });

                                    scriptingError = true;
                                    runMacroButton.setDisable(false);
                                    break;
                                } else {
                                    try {
                                        Integer.parseInt(scriptLine[1]);
                                        //parse number after wait, i.e. wait 5000
                                    } catch (NumberFormatException nfe) {
                                        //nfe.printStackTrace();
                                        int finalLengthDifference3 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": wait arg must be int");
                                                highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference3, lengthDifferenceSingleLine);
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        break;
                                    }

                                }
                                break;
                            case "click":
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    break;
                                }
                                scriptIsEmpty = false;
                                //System.out.println("you want to click on line " + j);
                                //now need to check if it has proper int args i.e. click 400 500
                                if (scriptLine.length != 3) {
                                    int finalLengthDifference4 = lengthDifference;
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for click");
                                            highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference4, lengthDifferenceSingleLine);
                                        }
                                    });

                                    scriptingError = true;
                                    runMacroButton.setDisable(false);
                                    break;
                                } else {
                                    try {
                                        int x;
                                        int y;
                                        x = Integer.parseInt(scriptLine[1]);
                                        y = Integer.parseInt(scriptLine[2]);
                                    } catch (NumberFormatException numException) {
                                        int finalLengthDifference5 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": click args must be ints");
                                                highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        break;
                                    }

                                }
                                break;
                            case "rightclick":
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    break;
                                }
                                scriptIsEmpty = false;
                                //System.out.println("you want to rightclick on line " + j);
                                //now need to check if it has proper int args i.e. rightclick 400 500
                                if (scriptLine.length != 3) {
                                    int finalLengthDifference6 = lengthDifference;
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for rightclick");
                                            highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference6, lengthDifferenceSingleLine);
                                        }
                                    });

                                    scriptingError = true;
                                    runMacroButton.setDisable(false);
                                    break;
                                } else {
                                    try {
                                        int x;
                                        int y;
                                        x = Integer.parseInt(scriptLine[1]);
                                        y = Integer.parseInt(scriptLine[2]);
                                    } catch (NumberFormatException numException) {
                                        int finalLengthDifference7 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": rightclick args must be ints");
                                                highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference7, lengthDifferenceSingleLine);
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        break;
                                    }

                                }
                                break;
                            //clickAndDrag(int x_start, int y_start, int x_end, int y_end, Robot bot)
                            case "clickanddrag":
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    break;
                                }
                                scriptIsEmpty = false;
                                //System.out.println("you want to clickanddrag on line " + j);
                                //now need to check if it has proper int args i.e. clickanddrag 300 300 400 400
                                if (scriptLine.length != 5) {
                                    int finalLengthDifference6 = lengthDifference;
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for clickanddrag");
                                            highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference6, lengthDifferenceSingleLine);
                                        }
                                    });

                                    scriptingError = true;
                                    runMacroButton.setDisable(false);
                                    break;
                                } else {
                                    try {
                                        int startx;
                                        int starty;
                                        int endx;
                                        int endy;
                                        startx = Integer.parseInt(scriptLine[1]);
                                        starty = Integer.parseInt(scriptLine[2]);
                                        endx = Integer.parseInt(scriptLine[3]);
                                        endy = Integer.parseInt(scriptLine[4]);
                                    } catch (NumberFormatException numException) {
                                        int finalLengthDifference7 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": rightclick args must be ints");
                                                highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference7, lengthDifferenceSingleLine);
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        break;
                                    }

                                }
                                break;
                            //press a key, i.e. press a
                            case "press":
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    break;
                                }
                                scriptIsEmpty = false;
                                System.out.println("you want to press on line " + j);
                                //now need to check if it has a proper string arg i.e. press a
                                if (scriptLine.length != 2) {
                                    int finalLengthDifference8 = lengthDifference;
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid arg for press");
                                            highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference8, lengthDifferenceSingleLine);
                                        }
                                    });

                                    scriptingError = true;
                                    runMacroButton.setDisable(false);
                                    break;
                                } else {
                                    String allKeys = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,";
                                    allKeys += allKeys.toUpperCase();
                                    allKeys += "0,1,2,3,4,5,6,7,8,9,";
                                    //for now, I'm not adding all keys, just basic ones
                                    allKeys += "enter,space,backspace,up,down,left,right,escape";
                                    String keysArray[] = allKeys.split(",");

                                    Set<String> keySet = Set.of(keysArray);

                                    if (keySet.contains(scriptLine[1])) {
                                        System.out.println("this is a valid key");
                                    } else {

                                        System.out.print("invalid key for press command. arg: " + scriptLine[1]);
                                        int finalLengthDifference9 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid press arg");
                                                highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference9, lengthDifferenceSingleLine);
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        break;
                                    }

                                }
                                break;
                            //case " ":
                            case "":
                                //blank lines are ok, just ignore them
                            case "#":
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    break;
                                }
                                //comments are ok, just don't do anything with the subsequent words in the line
                                //comments in AutoInputScript must be like this:
                                //# comment
                                //hash sign followed by a space and then the comment
                                break;
                            /*case "totallooptime":
                                //it was already handled earlier in the code
                                if (lineNumber != 1) {
                                    loadingLabel.setText("Macro error on " + lineNumber + ": invalid use of totallooptime");
                                    scriptingError = true;
                                    break;
                                }
                                break;*/
                            default:
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    break;
                                }
                                int finalLengthDifference10 = lengthDifference;
                                Platform.runLater(new Runnable(){
                                    @Override public void run() {
                                        loadingLabel.setText("Macro error on line " + lineNumber + ": invalid syntax");
                                        highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference10, lengthDifferenceSingleLine);
                                    }
                                });
                                scriptingError = true;
                                runMacroButton.setDisable(false);
                                break;
                        }
                    }


                }

                if (!scriptingError && scriptIsEmpty) {
                    Platform.runLater(new Runnable(){
                        @Override public void run() {
                            loadingLabel.setText("Macro error: cannot run blank script");

                        }

                    });
                    runMacroButton.setDisable(false);

                }

                //if no errors are found with the error checking, time to actually run the script
                if (!scriptingError && !scriptIsEmpty) {
                    Platform.runLater(new Runnable(){
                        @Override public void run() {
                            loadingLabel.setText("Running script");
                        }
                    });

                    //-------------------------------------------------------------------------------------------------
                    //the following loop actually runs the script!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    //the above stuff just checks it for errors
                    //that way, it will only run the script in its entirety when there are no errors
                    //it will not run a partially-working script
                    for (int j = 0; j < numberOfLines; j++) {

                        //if there is an error or the user presses the button to halt the script, then stop the script
                        if (scriptingError || scriptHalter.isUserWantsToHaltScript()) {
                            //System.out.println("script has been halted");
                            //scriptHalter.setUserWantsToHaltScript(false);
                            runMacroButton.setDisable(false);
                            break;
                        }
                        String scriptLine[] = lines[j].split(" ");


                        int lineNumber = j + 1;
                        if (scriptLine.length != 0) {

                            switch (scriptLine[0]) {
                                case "move":
                                    //move 500 500
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        runMacroButton.setDisable(false);
                                        break;
                                    }
                                    scriptIsEmpty = false;
                                    if (scriptLine.length != 3) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid arg for move");
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        break;
                                    } else {
                                        try {
                                            int x = Integer.parseInt(scriptLine[1]);
                                            int y = Integer.parseInt(scriptLine[2]);
                                            move(x, y, bot);
                                            //parse number after wait, i.e. move 400 400
                                        } catch (NumberFormatException nfe) {
                                            nfe.printStackTrace();
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": move args must be ints");
                                                }
                                            });

                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            break;
                                        } catch (AWTException awtE) {
                                            awtE.printStackTrace();
                                        }
                                    }
                                    break;
                                case "wait":
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        runMacroButton.setDisable(false);
                                        break;
                                    }
                                    scriptIsEmpty = false;
                                    //check if it has an int arg
                                    if (scriptLine.length != 2) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid arg for wait");
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        break;
                                    } else {
                                        //wait and check periodically if the script has been halted
                                        //wait 1000 will check 10 times per second
                                        //wait 100 will check 100 times per second
                                        //wait 1 will check 1000 times per second
                                        //before this change, I had it set to 100ms for checking for halt command
                                        //which would mess up fast delays, i.e. "wait 1" would take a minimum of 100 milliseconds
                                        //the reason for the slower stuff on higher wait durations is to reduce CPU usage
                                        //but faster stuff needs to check more frequently
                                        try {
                                            int waitDuration = Integer.parseInt(scriptLine[1]);
                                            int stepDuration;
                                            if (waitDuration >= 1000) {
                                                stepDuration = 100;
                                            } else if (waitDuration < 1000 && waitDuration > 100) {
                                                stepDuration = 10;
                                            } else {
                                                stepDuration = 1;
                                            }
                                            for (int x = 0; x < waitDuration; x += stepDuration) {
                                                try {Thread.sleep(stepDuration);} catch (InterruptedException ex) { ex.printStackTrace();}
                                                if (scriptHalter.isUserWantsToHaltScript()) {
                                                    //System.out.println("user wants to halt the script");
                                                    runMacroButton.setDisable(false);
                                                    Thread.currentThread().interrupt();

                                                    return; //end the thread

                                                }
                                            }
                                            //Thread.sleep(Integer.parseInt(scriptLine[1]));
                                        } catch (NumberFormatException nfe) {
                                            nfe.printStackTrace();
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": wait arg must be int");
                                                }
                                            });

                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            break;
                                        }

                                    }
                                    break;
                                case "click":
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        runMacroButton.setDisable(false);
                                        break;
                                    }
                                    scriptIsEmpty = false;
                                    //System.out.println("you want to click on line " + j);
                                    //now need to check if it has proper int args i.e. click 400 500
                                    if (scriptLine.length != 3) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for click");
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        break;
                                    } else {
                                        try {
                                            int x;
                                            int y;
                                            x = Integer.parseInt(scriptLine[1]);
                                            y = Integer.parseInt(scriptLine[2]);
                                            //scheduleEvent(x, y, waitDuration, bot, scriptHalter, "click"); //i is the loop #
                                            click(x, y, bot);
                                        } catch (NumberFormatException numException) {
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": click args must be ints");
                                                }
                                            });

                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            break;
                                        } catch (AWTException awtE) {
                                            awtE.printStackTrace();
                                        }

                                    }
                                    break;
                                case "rightclick":
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        runMacroButton.setDisable(false);
                                        break;
                                    }
                                    scriptIsEmpty = false;
                                    //System.out.println("you want to rightclick on line " + j);
                                    //now need to check if it has proper int args i.e. rightclick 400 500
                                    if (scriptLine.length != 3) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for rightclick");
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        break;
                                    } else {
                                        try {
                                            int x;
                                            int y;
                                            x = Integer.parseInt(scriptLine[1]);
                                            y = Integer.parseInt(scriptLine[2]);
                                            //scheduleEvent(x, y, waitDuration, bot, scriptHalter, "rightclick"); //i is the loop #
                                            rightClick(x, y, bot);
                                        } catch (NumberFormatException numException) {
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": rightclick args must be ints");
                                                }
                                            });

                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            break;
                                        } catch (AWTException awtE) {
                                            awtE.printStackTrace();
                                        }

                                    }
                                    break;
                                //clickAndDrag(int x_start, int y_start, int x_end, int y_end, Robot bot)
                                case "clickanddrag":
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Script halted");
                                            }
                                        });
                                        runMacroButton.setDisable(false);
                                        break;
                                    }
                                    scriptIsEmpty = false;
                                    //System.out.println("you want to clickanddrag on line " + j);
                                    //now need to check if it has proper int args i.e. clickanddrag 300 300 400 400
                                    if (scriptLine.length != 5) {
                                        int finalLengthDifference6 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for clickanddrag");
                                                //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference6, lengthDifferenceSingleLine);
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        break;
                                    } else {
                                        try {
                                            int startx;
                                            int starty;
                                            int endx;
                                            int endy;
                                            startx = Integer.parseInt(scriptLine[1]);
                                            starty = Integer.parseInt(scriptLine[2]);
                                            endx = Integer.parseInt(scriptLine[3]);
                                            endy = Integer.parseInt(scriptLine[4]);
                                            try {
                                                clickAndDrag(startx, starty, endx, endy, bot);
                                            } catch (AWTException awte1) {
                                                awte1.printStackTrace();
                                            }

                                        } catch (NumberFormatException numException) {
                                            int finalLengthDifference7 = lengthDifference;
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": rightclick args must be ints");
                                                    //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference7, lengthDifferenceSingleLine);
                                                }
                                            });

                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            break;
                                        }

                                    }
                                    break;
                                //press a key, i.e. press a
                                case "press":
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        runMacroButton.setDisable(false);
                                        break;
                                    }
                                    scriptIsEmpty = false;
                                    System.out.println("you want to press on line " + j);
                                    //now need to check if it has a proper string arg i.e. press a
                                    if (scriptLine.length != 2) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid arg for press");
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        break;
                                    } else {
                                        String allKeys = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,";
                                        allKeys += allKeys.toUpperCase();
                                        allKeys += "0,1,2,3,4,5,6,7,8,9,";
                                        //for now, I'm not adding all keys, just basic ones
                                        allKeys += "enter,space,backspace,up,down,left,right,escape";
                                        String keysArray[] = allKeys.split(",");

                                        Set<String> keySet = Set.of(keysArray);

                                        if (keySet.contains(scriptLine[1])) {
                                            System.out.println("this is a valid key");
                                            System.out.println("press feature is not yet implemented! implement it here!!!!!");
                                        } else {

                                            System.out.print("invalid key for press command. arg: " + scriptLine[1]);
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": invalid press arg");
                                                }
                                            });

                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            break;
                                        }

                                    }
                                    break;
                                //case " ":
                                case "":
                                    //blank lines are ok, just ignore them
                                case "#":
                                    //comments are ok, just don't do anything with the subsequent words in the line
                                    //comments in AutoInputScript must be like this:
                                    //# comment
                                    //hash sign followed by a space and then the comment
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        runMacroButton.setDisable(false);
                                        break;
                                    }
                                    break;
                                /*case "totallooptime":
                                    //totallooptime is used for the initial parsing, and for setting the totalLoopTime's int property
                                    //which is used for the arg that goes to scheduleEvent()
                                    break;*/
                                default:
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        runMacroButton.setDisable(false);
                                        break;
                                    }
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid syntax");
                                        }
                                    });

                                    scriptingError = true;
                                    runMacroButton.setDisable(false);
                                    break;
                            }
                        }

                        //try {Thread.sleep(totalLoopTime.getTotalLoopTime());} catch (InterruptedException ex) { ex.printStackTrace();}
                    }

                    Platform.runLater(new Runnable(){
                        @Override public void run() {
                            Platform.runLater(new Runnable(){
                                @Override public void run() {
                                    loadingLabel.setText("Finished running script");
                                }
                            });


                        }
                    });

                }





            }

            runMacroButton.setDisable(false);



        }).start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AlwaysOnTopStatus alwaysOnTopStatus = new AlwaysOnTopStatus();
        TotalLoopTime totalLoopTime = new TotalLoopTime();
        ScriptHalter scriptHalter = new ScriptHalter();
        Robot bot = new Robot();
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("AutoInput");
        primaryStage.setWidth(360);
        primaryStage.setMinWidth(360);
        primaryStage.setHeight(400);
        primaryStage.setMinHeight(360);
        BorderPane mainPane = new BorderPane();
        VBox topMostContainerVBox = new VBox();
        HBox topButtonsHBox = new HBox();
        VBox mybox = new VBox();

        topMostContainerVBox.getChildren().add(topButtonsHBox);
        mainPane.setTop(topMostContainerVBox);
        Scene scene = new Scene(mainPane);
        Button testButton = new Button("Get Coords");
        Label coordsLabel = new Label("(x, y)");
        testButton.setOnAction( e-> {
            //MouseInfo.getPointerInfo().getLocation().getX()
            //coordsLabel.setText(MouseInfo.getPointerInfo().getLocation().toString());
            coordsLabel.setText((int)MouseInfo.getPointerInfo().getLocation().getX() + ", " + (int)MouseInfo.getPointerInfo().getLocation().getY());
        });

        TextArea textArea = new TextArea();
        textArea.setStyle(("height: 100%; -fx-focus-color: transparent;"));


        Button runMacroButton = new Button("Run Macro");
        Label repeatLabel = new Label("Times to repeat:");
        TextField numTimes = new TextField();
        numTimes.setText("1");
        numTimes.setMaxWidth(100);
        //vbox is called mybox
        //need to add a status label that the multi button changes
        Label loadingLabel = new Label("Status: OK");

        MenuItem fileItem1 = new MenuItem("Open (not done)");
        MenuItem fileItem2 = new MenuItem("Save as (not done)");
        MenuItem fileItem3 = new MenuItem("Quit");

        Alert quitAlert = new Alert(Alert.AlertType.CONFIRMATION);
        quitAlert.setTitle("Quit");
        quitAlert.setHeaderText("Quit AutoInput");
        quitAlert.setContentText("Are you sure you want to quit? Any unsaved changes will be lost.");

        //quit the program
        fileItem3.setOnAction(e -> {
            if (alwaysOnTopStatus.isAlwaysOnTop()) {
                primaryStage.setAlwaysOnTop(false);
            }

            Optional<ButtonType> quitAlertResult = quitAlert.showAndWait();
            if (quitAlertResult.get() == ButtonType.OK) {
                //System.out.println("You want to quit AutoInput");
                scriptHalter.setUserWantsToHaltScript(true);
                try {
                    Thread.sleep(1200);
                    Platform.runLater(new Runnable(){
                        @Override public void run() {
                            loadingLabel.setText("Script halted");
                        }
                    });
                } catch (InterruptedException interruptedExcept) {
                    interruptedExcept.printStackTrace();
                }

                scriptHalter.setUserWantsToHaltScript(false);
                Platform.exit();
                if (alwaysOnTopStatus.isAlwaysOnTop()) {
                    primaryStage.setAlwaysOnTop(true);
                }

            } else if (quitAlertResult.get() == ButtonType.CANCEL || quitAlertResult.get() == ButtonType.CLOSE) {
                //System.out.println("You do not want to quit AutoInput");
                if (alwaysOnTopStatus.isAlwaysOnTop()) {
                    primaryStage.setAlwaysOnTop(true);
                }

            }
        });

        Platform.setImplicitExit(false);


        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                if (alwaysOnTopStatus.isAlwaysOnTop()) {
                    primaryStage.setAlwaysOnTop(false);
                }

                Optional<ButtonType> websiteAlertResult = quitAlert.showAndWait();
                if (websiteAlertResult.get() == ButtonType.OK) {
                    //System.out.println("You want to quit AutoInput");
                    scriptHalter.setUserWantsToHaltScript(true);
                    try {
                        Thread.sleep(1200);
                        Platform.runLater(new Runnable(){
                            @Override public void run() {
                                loadingLabel.setText("Script halted");
                            }
                        });
                    } catch (InterruptedException interruptedExcept) {
                        interruptedExcept.printStackTrace();
                    }

                    scriptHalter.setUserWantsToHaltScript(false);
                    Platform.exit();
                    if (alwaysOnTopStatus.isAlwaysOnTop()) {
                        primaryStage.setAlwaysOnTop(true);
                    }

                } else if (websiteAlertResult.get() == ButtonType.CANCEL || websiteAlertResult.get() == ButtonType.CLOSE) {
                    //System.out.println("You do not want to quit AutoInput");
                    if (alwaysOnTopStatus.isAlwaysOnTop()) {
                        primaryStage.setAlwaysOnTop(true);
                    }

                }
                event.consume();
            }
        });

        Menu fileMenu = new Menu("File", null, fileItem1, fileItem2, fileItem3);

        MenuItem optionsItem1 = new MenuItem("Enable always on top");
        MenuItem optionsItem3 = new MenuItem("Enable dark mode");
        MenuItem optionsItem4 = new MenuItem("Disable dark mode");
        MenuItem optionsItem5 = new MenuItem("Disable always on top");
        MenuItem optionsItem6 = new MenuItem("Enable word wrap");
        MenuItem optionsItem7 = new MenuItem("Disable word wrap");



        //enable always on top
        optionsItem1.setOnAction(e -> {
            primaryStage.setAlwaysOnTop(true);
            alwaysOnTopStatus.setAlwaysOnTop(true);

        });

        //disable always on top
        optionsItem5.setOnAction(e -> {
            primaryStage.setAlwaysOnTop(false);
            alwaysOnTopStatus.setAlwaysOnTop(false);
        });

        optionsItem6.setOnAction(e -> {
            textArea.setWrapText(true);
        });

        optionsItem7.setOnAction(e -> {
            textArea.setWrapText(false);
        });


        //text.setWrapText(true)

        MenuItem editItem[] = new MenuItem[8];
        editItem[0] = new MenuItem("Delete all text");
        editItem[1] = new MenuItem("Copy");
        editItem[2] = new MenuItem("Cut");
        editItem[3] = new MenuItem("Paste");
        //editItem[4] = new MenuItem("Delete (not done)");
        editItem[5] = new MenuItem("Select all");
        editItem[6] = new MenuItem("Select none");
        editItem[7] = new MenuItem("Delete selected text");

        //delete selected
        editItem[0].setOnAction(e -> {
            textArea.setText("");
        });


        //copy
        editItem[1].setOnAction(e -> {
            //System.out.println("textArea selected: " + textArea.getSelectedText());
            final StringSelection stringSelection = new StringSelection(textArea.getSelectedText());
            final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        });

        //cut
        editItem[2].setOnAction(e -> {
            final StringSelection stringSelection = new StringSelection(textArea.getSelectedText());
            final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            textArea.replaceSelection("");
        });

        //delete selected
        editItem[7].setOnAction(e -> {
            textArea.replaceSelection("");
        });

        Menu editMenu = new Menu("Edit", null, editItem[0], editItem[7], editItem[1], editItem[2], editItem[3], editItem[5], editItem[6]);

        //paste
        editItem[3].setOnAction(e-> {
            int textPosition = textArea.getCaretPosition();
            final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            try {
                textArea.insertText(textPosition, Toolkit.getDefaultToolkit()
                        .getSystemClipboard().getData(DataFlavor.stringFlavor).toString());
            } catch (UnsupportedFlavorException ex) {
                ex.printStackTrace();
                loadingLabel.setText("Error: only text can be pasted");
            } catch (IOException ioe) {
                ioe.printStackTrace();
                loadingLabel.setText("Error: only text can be pasted");
            }

        });

        //select all
        editItem[5].setOnAction(e -> {
            textArea.selectAll();
        });

        //select none
        editItem[6].setOnAction(e -> {
            int textPosition = textArea.getCaretPosition();
            textArea.selectRange(textPosition,textPosition);
        });


        Menu optionsMenu = new Menu("Options", null, optionsItem1, optionsItem5, optionsItem3, optionsItem4, optionsItem6, optionsItem7);
        MenuItem aboutItem1 = new MenuItem("About");
        Alert aboutAlert = new Alert(Alert.AlertType.INFORMATION);
        aboutAlert.setTitle("About");
        aboutAlert.setHeaderText("About AutoInput v0.0040");
        aboutAlert.setContentText("This is an input automation scripting language and editor made by 0x416c616e. You can use it to write keyboard/mouse macros" +
                " in order to automate repetitive tasks that require using a GUI rather than something command line-based that can be automated with a shell script.");


        aboutItem1.setOnAction(e -> {
            //primaryStage is temporarily set to not be always on top
            //otherwise an alert would be behind it
            if (alwaysOnTopStatus.isAlwaysOnTop()) {
                primaryStage.setAlwaysOnTop(false);
            }
            Optional<ButtonType> aboutAlertResult = aboutAlert.showAndWait();
            if (aboutAlertResult.isEmpty()) {
                //do nothing
                //without this part, it would throw an exception
                if (alwaysOnTopStatus.isAlwaysOnTop()) {
                    primaryStage.setAlwaysOnTop(true);
                }

            } else if (aboutAlertResult.get() == ButtonType.OK) {
                //once the alert is closed, set the primaryStage (main program window) to be always on top again
                if (alwaysOnTopStatus.isAlwaysOnTop()) {
                    primaryStage.setAlwaysOnTop(true);
                }

            } else if (aboutAlertResult.get() == ButtonType.CLOSE) {
                if (alwaysOnTopStatus.isAlwaysOnTop()) {
                    primaryStage.setAlwaysOnTop(true);
                }

            }
        });

        MenuItem aboutItem2 = new MenuItem("Website");
        Alert websiteAlert = new Alert(Alert.AlertType.CONFIRMATION);
        websiteAlert.setTitle("Website");
        websiteAlert.setHeaderText("Would you like to open the developer's website (saintlouissoftware.com) in a browser?");

        aboutItem2.setOnAction(e -> {
            if (alwaysOnTopStatus.isAlwaysOnTop()) {
                primaryStage.setAlwaysOnTop(false);
            }

            Optional<ButtonType> websiteAlertResult = websiteAlert.showAndWait();
            if (websiteAlertResult.get() == ButtonType.OK) {
                //System.out.println("You want to open the website in a browser");
                getHostServices().showDocument("https://saintlouissoftware.com/");
                if (alwaysOnTopStatus.isAlwaysOnTop()) {
                    primaryStage.setAlwaysOnTop(true);
                }

            } else if (websiteAlertResult.get() == ButtonType.CANCEL || websiteAlertResult.get() == ButtonType.CLOSE) {
                //System.out.println("You do not want to open the website.");
                if (alwaysOnTopStatus.isAlwaysOnTop()) {
                    primaryStage.setAlwaysOnTop(true);
                }

            }
        });

        MenuItem aboutItem3 = new MenuItem("Git repo");
        Menu aboutMenu = new Menu("About", null, aboutItem1, aboutItem2, aboutItem3);

        Alert gitRepoAlert = new Alert(Alert.AlertType.CONFIRMATION);
        gitRepoAlert.setTitle("Git Repo");
        gitRepoAlert.setHeaderText("Would you like to open the project's GitHub page in a browser?");

        aboutItem3.setOnAction(e -> {
            if (alwaysOnTopStatus.isAlwaysOnTop()) {
                primaryStage.setAlwaysOnTop(false);
            }

            Optional<ButtonType> gitRepoAlertResult = gitRepoAlert.showAndWait();
            if (gitRepoAlertResult.get() == ButtonType.OK) {
                //System.out.println("You want to open the git repo page in a browser");
                getHostServices().showDocument("https://github.com/0x416c616e/AutoInput");
                if (alwaysOnTopStatus.isAlwaysOnTop()) {
                    primaryStage.setAlwaysOnTop(true);
                }

            } else if (gitRepoAlertResult.get() == ButtonType.CANCEL || gitRepoAlertResult.get() == ButtonType.CLOSE) {
                //System.out.println("You do not want to open the git repo page in a browser.");
                if (alwaysOnTopStatus.isAlwaysOnTop()) {
                    primaryStage.setAlwaysOnTop(true);
                }

            }
        });

        MenuItem helpItem[] = new MenuItem[2];
        helpItem[0] = new MenuItem("Basic usage");

        Alert basicUsageAlert = new Alert(Alert.AlertType.INFORMATION);
        basicUsageAlert.setTitle("Basic Usage");
        basicUsageAlert.setHeaderText("How to use AutoInput");
        basicUsageAlert.setContentText("Type your commands in the editor, then hit run.\n" +
                "Here are some commands you can use:\n" +
                "move 300 700\n" +
                "click 400 500\n" +
                "rightclick 300 400\n" +
                "wait 1000\n" +
                "# this is a comment\n" +
                "press x\n" +
                "\n" +
                "It's recommended that you use a wait command between other commands.\n" +
                "wait 1000 means wait for 1,000 milliseconds, or one second.\n" +
                "\nIn order to prevent getting into a situation where a script is running but you can't get out of it due to " +
                "the mouse moving due to lots of loops of move commands, it's recommended that you put at least one long wait " +
                "in the script to give you enough time to be able to move the mouse to hit the halt button.");

        helpItem[0].setOnAction(e -> {
            //primaryStage is temporarily set to not be always on top
            //otherwise an alert would be behind it
            if (alwaysOnTopStatus.isAlwaysOnTop()) {
                primaryStage.setAlwaysOnTop(false);
            }

            Optional<ButtonType> basicUsageAlertResult = basicUsageAlert.showAndWait();
            if (basicUsageAlertResult.isEmpty()) {
                //do nothing
                //without this part, it would throw an exception
                if (alwaysOnTopStatus.isAlwaysOnTop()) {
                    primaryStage.setAlwaysOnTop(true);
                }

            } else if (basicUsageAlertResult.get() == ButtonType.OK) {
                //once the alert is closed, set the primaryStage (main program window) to be always on top again

                if (alwaysOnTopStatus.isAlwaysOnTop()) {
                    primaryStage.setAlwaysOnTop(true);
                }
            } else if (basicUsageAlertResult.get() == ButtonType.CLOSE) {
                if (alwaysOnTopStatus.isAlwaysOnTop()) {
                    primaryStage.setAlwaysOnTop(true);
                }

            }
        });



        Menu helpMenu = new Menu("Help", null, helpItem[0]);



        MenuBar menuBar = new MenuBar(fileMenu, editMenu, optionsMenu, aboutMenu, helpMenu);
        Label info = new Label("To change the functionality of this program, change the lambda expressions for 'runMacroButton' and 'button'");
        info.setWrapText(true);
        Label info2 = new Label("And use the 'get coords' button to get the current mouse x,y");
        info2.setWrapText(true);


        topMostContainerVBox.getChildren().addAll(menuBar);
        //topMostContainerVBox.getChildren().add(mybox);
        //topMostContainerVBox.getChildren().add(topButtonsHBox);

        mybox.getChildren().addAll(testButton, coordsLabel);
        runMacroButton.setOnAction( e -> {
            runMacroButton.setDisable(true);


            try {


                //scene.setCursor(Cursor.WAIT);
                int timesToRepeat = Integer.parseInt(numTimes.getText());

                parseAndRunScript(timesToRepeat, textArea, loadingLabel, totalLoopTime, scriptHalter, bot, runMacroButton);


                scene.setCursor(Cursor.DEFAULT);
            } catch (AWTException a) {
                a.printStackTrace();
            }
            /*
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    loadingLabel.setText("Status: OK");
                }
            });*/





        });

        //mybox.getChildren().addAll(runMacroButton, repeatLabel);
        //need to add numTimes, minusButton, and plusButton to an HBox
        HBox nestedBox = new HBox();
        Button minusButton = new Button("-");
        Button plusButton = new Button("+");
        minusButton.setOnAction( e -> {
            int newValue = Integer.parseInt(numTimes.getText()) - 1;
            numTimes.setText(Integer.toString(newValue));
        });
        plusButton.setOnAction( e -> {
            int newValue = Integer.parseInt(numTimes.getText()) + 1;
            numTimes.setText(Integer.toString(newValue));
        });
        BorderPane nestedBorderPane = new BorderPane();
        nestedBorderPane.setLeft(mybox);
        topMostContainerVBox.getChildren().add(nestedBorderPane);
        VBox rightTopVBox = new VBox();
        rightTopVBox.getChildren().addAll(runMacroButton, repeatLabel);
        nestedBox.getChildren().addAll(minusButton, numTimes, plusButton);
        rightTopVBox.getChildren().addAll(nestedBox);
        nestedBorderPane.setCenter(rightTopVBox);

        Insets nestedBorderInsets = new Insets(0, 0, 0, 50);
        nestedBorderPane.setMargin(rightTopVBox, nestedBorderInsets);
        //Label closeToEndScriptLabel = new Label("To halt a running script, close the AutoInput window.");

        //enable dark mode
        Button haltScriptButton = new Button("Halt Macro");
        nestedBorderPane.setRight(haltScriptButton);

        optionsItem3.setOnAction(e -> {
            textArea.lookup(".content").setStyle("-fx-background-color: #2a2a2e;");
            textArea.setStyle("-fx-text-fill: #d2d2d2;");
            mainPane.setStyle("-fx-background-color: #474749;");
            numTimes.setStyle("-fx-background-color: #2a2a2e; -fx-text-fill: #d2d2d2;");
            loadingLabel.setStyle("-fx-text-fill: #d2d2d2;");
            //closeToEndScriptLabel.setStyle("-fx-text-fill: #d2d2d2;");
            coordsLabel.setStyle("-fx-text-fill: #d2d2d2;");
            repeatLabel.setStyle("-fx-text-fill: #d2d2d2;");
            testButton.setStyle("-fx-background-color: #676767; -fx-text-fill: #d2d2d2;");
            runMacroButton.setStyle("-fx-background-color: #676767; -fx-text-fill: #d2d2d2;");
            plusButton.setStyle("-fx-background-color: #676767; -fx-text-fill: #d2d2d2;");
            minusButton.setStyle("-fx-background-color: #676767; -fx-text-fill: #d2d2d2;");
            menuBar.setStyle("-fx-background-color: darkgray; -fx-text-fill: #d2d2d2;");
            haltScriptButton.setStyle("-fx-background-color: #676767; -fx-text-fill: #d2d2d2;");

        });

        //disable dark mode
        optionsItem4.setOnAction(e -> {
            textArea.lookup(".content").setStyle(null);
            textArea.setStyle(null);
            mainPane.setStyle(null);
            numTimes.setStyle(null);
            loadingLabel.setStyle(null);
            //closeToEndScriptLabel.setStyle(null);
            coordsLabel.setStyle(null);
            repeatLabel.setStyle(null);
            testButton.setStyle(null);
            runMacroButton.setStyle(null);
            plusButton.setStyle(null);
            minusButton.setStyle(null);
            menuBar.setStyle(null);
            haltScriptButton.setStyle(null);
        });


        mainPane.setCenter(textArea);
        mainPane.setStyle(("-fx-focus-color: transparent!important;"));
        Pane bottomPane = new Pane();
        loadingLabel.setMinHeight(30);
        VBox bottomBox = new VBox();

        //closeToEndScriptLabel.setMinHeight(30);



        haltScriptButton.setOnAction(e -> {
            scriptHalter.setUserWantsToHaltScript(true);
            try {
                Thread.sleep(1200);
                Platform.runLater(new Runnable(){
                    @Override public void run() {
                        loadingLabel.setText("Script halted");
                    }
                });
            } catch (InterruptedException interruptedExcept) {
                interruptedExcept.printStackTrace();
            }

            scriptHalter.setUserWantsToHaltScript(false);
        });

        Button resetHaltScriptButton = new Button("Reset halt");
        resetHaltScriptButton.setOnAction(e -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException interruptedExcept) {
                interruptedExcept.printStackTrace();
            }

            scriptHalter.setUserWantsToHaltScript(false);
        });
        BorderPane bottomBorderPane = new BorderPane();
        //bottomBorderPane.setLeft(haltScriptButton);

        //bottomBorderPane.setCenter(resetHaltScriptButton);

        Button haltInfoButton = new Button("Important info about halting");


        Alert haltAlert = new Alert(Alert.AlertType.WARNING);
        haltAlert.setTitle("Halting");
        haltAlert.setHeaderText("Important info about halting/resetting");
        haltAlert.setContentText("If you use 'schedule' commands, such as scheduleclick, and your script takes 30 seconds to run, " +
                "with commands happening at different times, and you halt the program 10 seconds into the script, " +
                "then you will either need to wait 20 seconds before hitting 'reset halt' or just restart the program. " +
                "This is due to the technical limitations of this program. It's not ideal. " +
                "If you hit 'halt' then you will also not be able to run a script again until you hit 'reset halt'." +
                " But with schedule commands, it can cause problems if you 'reset halt' too early.");


        haltInfoButton.setOnAction(e -> {
            primaryStage.setAlwaysOnTop(false);
            Optional<ButtonType> haltAlertResult = haltAlert.showAndWait();
            if (haltAlertResult.isEmpty()) {
                //do nothing
                //without this part, it would throw an exception
                primaryStage.setAlwaysOnTop(true);
            } else if (haltAlertResult.get() == ButtonType.OK) {
                //once the alert is closed, set the primaryStage (main program window) to be always on top again
                primaryStage.setAlwaysOnTop(true);
            } else if (haltAlertResult.get() == ButtonType.CLOSE) {
                primaryStage.setAlwaysOnTop(true);
            }
        });

        Label blankLabelSpacer1 = new Label(" ");
        Label blankLabelSpacer2 = new Label(" ");
        bottomBox.getChildren().addAll(loadingLabel, bottomBorderPane);
        mainPane.setBottom(bottomBox);
        //mybox.getChildren().addAll(nestedBox);
        //clicking in center
        primaryStage.setScene(scene);
        primaryStage.setAlwaysOnTop(true);
        //primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("file:assets/icon.png"));
        mybox.requestFocus();
        scene.getStylesheets().add("file:css/style.css");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
