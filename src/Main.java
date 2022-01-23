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
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;


public class Main extends Application {

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static void click(int x, int y, Robot bot) throws AWTException{
        bot.mouseMove(x, y);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public static HashMap<String, Variable> createNewInt(String name, int intValue, HashMap<String, Variable> variables) {
        Variable newIntVar = new Variable(intValue);
        variables.put(name, newIntVar);
        System.out.println("new variable being added to variables: " + newIntVar.toString());
        System.out.println("variables: " + variables.toString());
        return variables;
    }

    public static void rightClickAndHold(int x, int y, Robot bot) throws AWTException {
        bot.mouseMove(x, y);
        bot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
    }

    public static void middleClickAndHold(int x, int y, Robot bot) throws AWTException {
        bot.mouseMove(x, y);
        bot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
    }

    public static void clickAndHold(int x, int y, Robot bot) throws AWTException {
        bot.mouseMove(x, y);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    }

    public static void releaseClick(Robot bot) throws AWTException {
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public static void releaseRightClick(Robot bot) throws AWTException {
        bot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
    }

    public static void releaseMiddleClick(Robot bot) throws AWTException {
        bot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
    }

    public static void rightClick(int x, int y, Robot bot) throws AWTException {
        bot.mouseMove(x, y);
        bot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
    }

    public static void middleClick(int x, int y, Robot bot) throws AWTException {
        bot.mouseMove(x, y);
        bot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
    }


    public static void move(int x, int y, Robot bot) throws AWTException {
        bot.mouseMove(x, y);
    }

    public static void moveRandom(int minX, int maxX, int minY, int maxY, Robot bot) throws AWTException {
        int randX = getRandomNumber(minX, maxX);
        int randY = getRandomNumber(minY, maxY);
        bot.mouseMove(randX, randY);
    }

    public static void clickRandom(int minX, int maxX, int minY, int maxY, Robot bot) throws AWTException {
        int randX = getRandomNumber(minX, maxX);
        int randY = getRandomNumber(minY, maxY);
        bot.mouseMove(randX, randY);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }



    public static void clickAndDrag(int x_start, int y_start, int x_end, int y_end, Robot bot) throws AWTException {
        bot.mouseMove(x_start, y_start);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseMove(x_end, y_end);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

    }

    public static void press(String key, Robot bot) {
        switch (key) {
            case "1":
                bot.keyPress(KeyEvent.VK_1);
                bot.keyRelease(KeyEvent.VK_1);
                break;
            case "2":
                bot.keyPress(KeyEvent.VK_2);
                bot.keyRelease(KeyEvent.VK_2);
                break;
            case "3":
                bot.keyPress(KeyEvent.VK_3);
                bot.keyRelease(KeyEvent.VK_3);
                break;
            case "4":
                bot.keyPress(KeyEvent.VK_4);
                bot.keyRelease(KeyEvent.VK_4);
                break;
            case "5":
                bot.keyPress(KeyEvent.VK_5);
                bot.keyRelease(KeyEvent.VK_5);
                break;
            case "6":
                bot.keyPress(KeyEvent.VK_6);
                bot.keyRelease(KeyEvent.VK_6);
                break;
            case "7":
                bot.keyPress(KeyEvent.VK_7);
                bot.keyRelease(KeyEvent.VK_7);
                break;
            case "8":
                bot.keyPress(KeyEvent.VK_8);
                bot.keyRelease(KeyEvent.VK_8);
                break;
            case "9":
                bot.keyPress(KeyEvent.VK_9);
                bot.keyRelease(KeyEvent.VK_9);
                break;
            case "0":
                bot.keyPress(KeyEvent.VK_0);
                bot.keyRelease(KeyEvent.VK_0);
                break;
            case "a":
                bot.keyPress(KeyEvent.VK_A);
                bot.keyRelease(KeyEvent.VK_A);
                break;
            case "b":
                bot.keyPress(KeyEvent.VK_B);
                bot.keyRelease(KeyEvent.VK_B);
                break;
            case "c":
                bot.keyPress(KeyEvent.VK_C);
                bot.keyRelease(KeyEvent.VK_C);
                break;
            case "d":
                bot.keyPress(KeyEvent.VK_D);
                bot.keyRelease(KeyEvent.VK_D);
                break;
            case "e":
                bot.keyPress(KeyEvent.VK_E);
                bot.keyRelease(KeyEvent.VK_E);
                break;
            case "f":
                bot.keyPress(KeyEvent.VK_F);
                bot.keyRelease(KeyEvent.VK_F);
                break;
            case "g":
                bot.keyPress(KeyEvent.VK_G);
                bot.keyRelease(KeyEvent.VK_G);
                break;
            case "h":
                bot.keyPress(KeyEvent.VK_H);
                bot.keyRelease(KeyEvent.VK_H);
                break;
            case "i":
                bot.keyPress(KeyEvent.VK_I);
                bot.keyRelease(KeyEvent.VK_I);
                break;
            case "j":
                bot.keyPress(KeyEvent.VK_J);
                bot.keyRelease(KeyEvent.VK_J);
                break;
            case "k":
                bot.keyPress(KeyEvent.VK_K);
                bot.keyRelease(KeyEvent.VK_K);
                break;
            case "l":
                bot.keyPress(KeyEvent.VK_L);
                bot.keyRelease(KeyEvent.VK_L);
                break;
            case "m":
                bot.keyPress(KeyEvent.VK_M);
                bot.keyRelease(KeyEvent.VK_M);
                break;
            case "n":
                bot.keyPress(KeyEvent.VK_N);
                bot.keyRelease(KeyEvent.VK_N);
                break;
            case "o":
                bot.keyPress(KeyEvent.VK_O);
                bot.keyRelease(KeyEvent.VK_O);
                break;
            case "p":
                bot.keyPress(KeyEvent.VK_P);
                bot.keyRelease(KeyEvent.VK_P);
                break;
            case "q":
                bot.keyPress(KeyEvent.VK_Q);
                bot.keyRelease(KeyEvent.VK_Q);
                break;
            case "r":
                bot.keyPress(KeyEvent.VK_R);
                bot.keyRelease(KeyEvent.VK_R);
                break;
            case "s":
                bot.keyPress(KeyEvent.VK_S);
                bot.keyRelease(KeyEvent.VK_S);
                break;
            case "t":
                bot.keyPress(KeyEvent.VK_T);
                bot.keyRelease(KeyEvent.VK_T);
                break;
            case "u":
                bot.keyPress(KeyEvent.VK_U);
                bot.keyRelease(KeyEvent.VK_U);
                break;
            case "v":
                bot.keyPress(KeyEvent.VK_V);
                bot.keyRelease(KeyEvent.VK_V);
                break;
            case "w":
                bot.keyPress(KeyEvent.VK_W);
                bot.keyRelease(KeyEvent.VK_W);
                break;
            case "x":
                bot.keyPress(KeyEvent.VK_X);
                bot.keyRelease(KeyEvent.VK_X);
                break;
            case "y":
                bot.keyPress(KeyEvent.VK_Y);
                bot.keyRelease(KeyEvent.VK_Y);
                break;
            case "z":
                bot.keyPress(KeyEvent.VK_Z);
                bot.keyRelease(KeyEvent.VK_Z);
                break;
            case "A":
                bot.keyPress(KeyEvent.VK_SHIFT);
                bot.keyPress(KeyEvent.VK_A);
                bot.keyRelease(KeyEvent.VK_A);
                bot.keyRelease(KeyEvent.VK_SHIFT);
                break;
            case "B":
                bot.keyPress(KeyEvent.VK_SHIFT);
                bot.keyPress(KeyEvent.VK_B);
                bot.keyRelease(KeyEvent.VK_B);
                bot.keyRelease(KeyEvent.VK_SHIFT);
                break;
            case "C":
                bot.keyPress(KeyEvent.VK_SHIFT);
                bot.keyPress(KeyEvent.VK_C);
                bot.keyRelease(KeyEvent.VK_C);
                bot.keyRelease(KeyEvent.VK_SHIFT);
                break;
            case "D":
                bot.keyPress(KeyEvent.VK_SHIFT);
                bot.keyPress(KeyEvent.VK_D);
                bot.keyRelease(KeyEvent.VK_D);
                bot.keyRelease(KeyEvent.VK_SHIFT);
                break;
            case "E":
                bot.keyPress(KeyEvent.VK_SHIFT);
                bot.keyPress(KeyEvent.VK_E);
                bot.keyRelease(KeyEvent.VK_E);
                bot.keyRelease(KeyEvent.VK_SHIFT);
                break;
            case "F":
                bot.keyPress(KeyEvent.VK_SHIFT);
                bot.keyPress(KeyEvent.VK_F);
                bot.keyRelease(KeyEvent.VK_F);
                bot.keyRelease(KeyEvent.VK_SHIFT);
                break;
            case "G":
                bot.keyPress(KeyEvent.VK_SHIFT);
                bot.keyPress(KeyEvent.VK_G);
                bot.keyRelease(KeyEvent.VK_G);
                bot.keyRelease(KeyEvent.VK_SHIFT);
                break;
            case "H":
                bot.keyPress(KeyEvent.VK_SHIFT);
                bot.keyPress(KeyEvent.VK_H);
                bot.keyRelease(KeyEvent.VK_H);
                bot.keyRelease(KeyEvent.VK_SHIFT);
                break;
            case "I":
                bot.keyPress(KeyEvent.VK_SHIFT);
                bot.keyPress(KeyEvent.VK_I);
                bot.keyRelease(KeyEvent.VK_I);
                bot.keyRelease(KeyEvent.VK_SHIFT);
                break;
            case "J":
                bot.keyPress(KeyEvent.VK_SHIFT);
                bot.keyPress(KeyEvent.VK_J);
                bot.keyRelease(KeyEvent.VK_J);
                bot.keyRelease(KeyEvent.VK_SHIFT);
                break;
            case "K":
                bot.keyPress(KeyEvent.VK_SHIFT);
                bot.keyPress(KeyEvent.VK_K);
                bot.keyRelease(KeyEvent.VK_K);
                bot.keyRelease(KeyEvent.VK_SHIFT);
                break;
            case "L":
                bot.keyPress(KeyEvent.VK_SHIFT);
                bot.keyPress(KeyEvent.VK_L);
                bot.keyRelease(KeyEvent.VK_L);
                bot.keyRelease(KeyEvent.VK_SHIFT);
                break;
            case "M":
                bot.keyPress(KeyEvent.VK_SHIFT);
                bot.keyPress(KeyEvent.VK_M);
                bot.keyRelease(KeyEvent.VK_M);
                bot.keyRelease(KeyEvent.VK_SHIFT);
                break;
            case "N":
                bot.keyPress(KeyEvent.VK_SHIFT);
                bot.keyPress(KeyEvent.VK_N);
                bot.keyRelease(KeyEvent.VK_N);
                bot.keyRelease(KeyEvent.VK_SHIFT);
                break;
            case "O":
                bot.keyPress(KeyEvent.VK_SHIFT);
                bot.keyPress(KeyEvent.VK_O);
                bot.keyRelease(KeyEvent.VK_O);
                bot.keyRelease(KeyEvent.VK_SHIFT);
                break;
            case "P":
                bot.keyPress(KeyEvent.VK_SHIFT);
                bot.keyPress(KeyEvent.VK_P);
                bot.keyRelease(KeyEvent.VK_P);
                bot.keyRelease(KeyEvent.VK_SHIFT);
                break;
            case "Q":
                bot.keyPress(KeyEvent.VK_SHIFT);
                bot.keyPress(KeyEvent.VK_Q);
                bot.keyRelease(KeyEvent.VK_Q);
                bot.keyRelease(KeyEvent.VK_SHIFT);
                break;
            case "R":
                bot.keyPress(KeyEvent.VK_SHIFT);
                bot.keyPress(KeyEvent.VK_R);
                bot.keyRelease(KeyEvent.VK_R);
                bot.keyRelease(KeyEvent.VK_SHIFT);
                break;
            case "S":
                bot.keyPress(KeyEvent.VK_SHIFT);
                bot.keyPress(KeyEvent.VK_S);
                bot.keyRelease(KeyEvent.VK_S);
                bot.keyRelease(KeyEvent.VK_SHIFT);
                break;
            case "T":
                bot.keyPress(KeyEvent.VK_SHIFT);
                bot.keyPress(KeyEvent.VK_T);
                bot.keyRelease(KeyEvent.VK_T);
                bot.keyRelease(KeyEvent.VK_SHIFT);
                break;
            case "U":
                bot.keyPress(KeyEvent.VK_SHIFT);
                bot.keyPress(KeyEvent.VK_U);
                bot.keyRelease(KeyEvent.VK_U);
                bot.keyRelease(KeyEvent.VK_SHIFT);
                break;
            case "V":
                bot.keyPress(KeyEvent.VK_SHIFT);
                bot.keyPress(KeyEvent.VK_V);
                bot.keyRelease(KeyEvent.VK_V);
                bot.keyRelease(KeyEvent.VK_SHIFT);
                break;
            case "W":
                bot.keyPress(KeyEvent.VK_SHIFT);
                bot.keyPress(KeyEvent.VK_W);
                bot.keyRelease(KeyEvent.VK_W);
                bot.keyRelease(KeyEvent.VK_SHIFT);
                break;
            case "X":
                bot.keyPress(KeyEvent.VK_SHIFT);
                bot.keyPress(KeyEvent.VK_X);
                bot.keyRelease(KeyEvent.VK_X);
                bot.keyRelease(KeyEvent.VK_SHIFT);
                break;
            case "Y":
                bot.keyPress(KeyEvent.VK_SHIFT);
                bot.keyPress(KeyEvent.VK_Y);
                bot.keyRelease(KeyEvent.VK_Y);
                bot.keyRelease(KeyEvent.VK_SHIFT);
                break;
            case "Z":
                bot.keyPress(KeyEvent.VK_SHIFT);
                bot.keyPress(KeyEvent.VK_Z);
                bot.keyRelease(KeyEvent.VK_Z);
                bot.keyRelease(KeyEvent.VK_SHIFT);
                break;

            //"enter,space,backspace,up,down,left,right,escape";

            case "tab":
                bot.keyPress(KeyEvent.VK_TAB);
                bot.keyRelease(KeyEvent.VK_TAB);
                break;
            case "enter":
                bot.keyPress(KeyEvent.VK_ENTER);
                bot.keyRelease(KeyEvent.VK_ENTER);
                break;
            case "space":
                bot.keyPress(KeyEvent.VK_SPACE);
                bot.keyRelease(KeyEvent.VK_SPACE);
                break;
            case "backspace":
                bot.keyPress(KeyEvent.VK_BACK_SPACE);
                bot.keyRelease(KeyEvent.VK_BACK_SPACE);
                break;
            case "up":
                bot.keyPress(KeyEvent.VK_UP);
                bot.keyRelease(KeyEvent.VK_UP);
                break;
            case "down":
                bot.keyPress(KeyEvent.VK_DOWN);
                bot.keyRelease(KeyEvent.VK_DOWN);
                break;
            case "left":
                bot.keyPress(KeyEvent.VK_LEFT);
                bot.keyRelease(KeyEvent.VK_LEFT);
                break;
            case "right":
                bot.keyPress(KeyEvent.VK_RIGHT);
                bot.keyRelease(KeyEvent.VK_RIGHT);
                break;
            case "escape":
                bot.keyPress(KeyEvent.VK_ESCAPE);
                bot.keyRelease(KeyEvent.VK_ESCAPE);
                break;
            default:
                System.out.println("error with press(): invalid key: " + key);
                break;
        }
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
    public static void parseAndRunScript(boolean isInfinite, int timesToRepeat, TextArea textArea, TextArea consoleTextArea, Label loadingLabel, TotalLoopTime totalLoopTime, ScriptHalter scriptHalter, Robot bot, Button runMacroButton, Stage primaryStage) throws AWTException {
        new Thread(()->{ //use another thread so long process does not block gui
                //update gui using fx thread
                //Platform.runLater(() -> label.setText(text));
            textArea.setEditable(false);


            for (int i = 0; i < timesToRepeat || isInfinite; i++) {
                //this is where the macro stuff happens
                //System.out.println("Number of lines in the text area: " + String.valueOf(textArea.getText().split("\n").length));

                //reset the console every run
                consoleTextArea.setText("\n\n\nAutoInput Script Console\n");

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

                //String = name, Variable = type and value (just int for now)
                HashMap<String, Variable> variables = new HashMap<>();

                for (int j = 0; j < numberOfLines; j++) {
                    /*Platform.runLater(new Runnable(){
                        @Override public void run() {
                            loadingLabel.setText("Parsing script");
                        }
                    });*/




                    if (scriptingError) {
                        runMacroButton.setDisable(false);
                        textArea.setEditable(true);
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
                            textArea.setEditable(true);
                            break;
                        }


                        switch (scriptLine[0]) {
                            //if/elseif/else/endif
                            //need to keep track of stuff like nextElseifSameIfLevel
                            //nextElseSameIfLevel, and nextEndifSameIfLevel
                            case "if":
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                }
                                scriptIsEmpty = false;
                                //if $x == 4
                                //can use ints or vars for either arg
                                //and can be any comparison operator
                                boolean expressionEval = false;
                                if (scriptLine.length == 4) {
                                    Variable var1 = new Variable();
                                    Variable var2 = new Variable();
                                    if (scriptLine[1].startsWith("$")) {
                                        //try to get and parse variable
                                        // then figure out its data type
                                        // then set dataType to it
                                        if (variables.get(scriptLine[1]) != null) {
                                            switch (variables.get(scriptLine[1]).getType()) {
                                                case INT:
                                                    var1.setType(Variable.Type.INT);
                                                    var1.setIntValue(variables.get(scriptLine[1]).getIntValue());
                                                    break;
                                                case STR:
                                                    //not implemented yet
                                                    break;
                                                default:
                                                    //todo: throw error
                                                    break;
                                            }
                                        } else {
                                            //todo: throw error, invalid variable reference
                                        }
                                    } else {
                                        try {
                                            var1.setIntValue(Integer.parseInt(scriptLine[1]));
                                            var1.setType(Variable.Type.INT);
                                        } catch (NumberFormatException e) {

                                            var1.setStrValue(String.valueOf(scriptLine[1]));
                                            var1.setType(Variable.Type.STR);
                                        }
                                        //else: in the future, will I want stuff aside from int and str?
                                    }

                                    //at this point, you know that this part is fine: "if $x" or "if 5"
                                    //but now you need to parse the second operand, and see if it matches the
                                    //data type of the first one
                                    //and then need to switch on the operator

                                    //parsing the second operand
                                    //i.e. if it's "if $x == 5"
                                    //then this part is parsing 5
                                    if (scriptLine[3].startsWith("$")) {
                                        //try to get and parse variable
                                        // then figure out its data type
                                        // then set dataType to it
                                        if (variables.get(scriptLine[3]) != null) {
                                            switch (variables.get(scriptLine[3]).getType()) {
                                                case INT:
                                                    var2.setType(Variable.Type.INT);
                                                    var2.setIntValue(variables.get(scriptLine[3]).getIntValue());
                                                    break;
                                                case STR:
                                                    //not implemented yet
                                                    break;
                                                default:
                                                    //todo: throw error
                                                    break;
                                            }
                                        } else {
                                            //todo: throw error, invalid variable reference
                                        }
                                    } else {
                                        try {
                                            var2.setIntValue(Integer.parseInt(scriptLine[3]));
                                            var2.setType(Variable.Type.INT);
                                        } catch (NumberFormatException e) {
                                            var2.setStrValue(String.valueOf(scriptLine[3]));
                                            var2.setType(Variable.Type.STR);
                                        }


                                    }

                                    //now you know the operands are fine, now switch on the operator
                                    if (((var1.getType() == var2.getType())) && !scriptingError) {
                                        if (var1.getType() == Variable.Type.INT) {
                                            switch (scriptLine[2]) {
                                                //todo: implement
                                                case "==":
                                                    expressionEval = (var1.getIntValue() == var2.getIntValue());
                                                    System.out.println("equals");
                                                    break;
                                                case "!=":
                                                    expressionEval = (var1.getIntValue() != var2.getIntValue());
                                                    System.out.println("not");
                                                    break;
                                                case ">=":
                                                    expressionEval = (var1.getIntValue() >= var2.getIntValue());
                                                    System.out.println("greater than or equal to");
                                                    break;
                                                case "<=":
                                                    expressionEval = (var1.getIntValue() <= var2.getIntValue());
                                                    System.out.println("less than or equal to");
                                                    break;
                                                case ">":
                                                    expressionEval = (var1.getIntValue() > var2.getIntValue());
                                                    System.out.println("greater than");
                                                    break;
                                                case "<":
                                                    expressionEval = (var1.getIntValue() < var2.getIntValue());
                                                    System.out.println("less than");
                                                    break;
                                                default:
                                                    int finalLengthDifference2 = lengthDifference;
                                                    Platform.runLater(new Runnable(){
                                                        @Override public void run() {
                                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid operator for if");
                                                            highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference2, lengthDifferenceSingleLine);
                                                        }
                                                    });
                                                    scriptingError = true;
                                                    runMacroButton.setDisable(false);
                                                    textArea.setEditable(true);
                                                    break;
                                            }
                                        } else if (var1.getType() == Variable.Type.STR) {
                                            //not yet implemented
                                        }
                                    } else {
                                        //throw error: the operands are not the same type
                                        int finalLengthDifference2 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": operand type mismatch for if");
                                                highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference2, lengthDifferenceSingleLine);
                                            }
                                        });
                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }


                                } else if (scriptLine.length == 3) {
                                    //if $x exists
                                    //todo: implement
                                    if (scriptLine[2].equalsIgnoreCase("exists")) {
                                        if (scriptLine[1].startsWith("$")) {
                                            if (variables.get(scriptLine[1]) != null) {
                                                expressionEval = true;
                                            } else {
                                                expressionEval = false;
                                            }
                                        } else {
                                            //todo: throw error, invalid args
                                        }
                                    } else if (scriptLine[2].equalsIgnoreCase("doesnotexist")) {
                                        //todo: implement
                                    } else {
                                        //todo: throw error, invalid args
                                    }
                                } else {
                                    int finalLengthDifference2 = lengthDifference;
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for if");
                                            highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference2, lengthDifferenceSingleLine);
                                        }
                                    });
                                    scriptingError = true;
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                }

                                //finally
                                if (!scriptingError) {
                                    System.out.println("expressionEval: " + expressionEval);
                                    //todo: iflevel stuff etc.
                                    // if expressionEval == true, then you proceed to the stuff before any
                                    // elseif or else blocks


                                    //todo: but if it's false, then immediately go to the next endif
                                    // **on the same ifLevel**
                                }

                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                }
                                break;
                            case "elseif":
                                //todo
                                break;
                            case "else":
                                //todo
                                break;
                            case "endif":
                                //todo
                                break;
                            case "loop":
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                }
                                scriptIsEmpty = false;
                                //check if it has an int arg i.e. loop 5
                                if (scriptLine.length != 2) {
                                    int finalLengthDifference2 = lengthDifference;
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid arg for loop");
                                            highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference2, lengthDifferenceSingleLine);
                                        }
                                    });

                                    scriptingError = true;
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                } else {
                                    try {

                                        int x;
                                        boolean varError = false;
                                        if (scriptLine[1].startsWith("$") && scriptLine[1].length() > 1) {
                                            if (variables.get(scriptLine[1]) == null) {
                                                varError = true;
                                            }

                                        } else {
                                            x = Integer.parseInt(scriptLine[1]);
                                        }

                                        if (varError) {
                                            int finalLengthDifference1 = lengthDifference;
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": move args must be ints or int vars");
                                                    highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                                }
                                            });
                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
                                            return;
                                        }

                                        //parse number after wait, i.e. loop 5
                                    } catch (NumberFormatException nfe) {
                                        //nfe.printStackTrace();
                                        int finalLengthDifference3 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": loop arg must be int");
                                                highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference3, lengthDifferenceSingleLine);
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }

                                }
                                break;
                            //end a loop
                            //example:
                            //loop 5
                            //  press a
                            //end
                            case "endloop":
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                }
                                scriptIsEmpty = false;
                                //check if it has an int arg i.e. loop 5
                                if (scriptLine.length != 1) {
                                    int finalLengthDifference2 = lengthDifference;
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid arg for end");
                                            //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference2, lengthDifferenceSingleLine);
                                        }
                                    });

                                    scriptingError = true;
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                }
                                break;
                            case "move":
                                //move 500 500
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
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
                                    textArea.setEditable(true);
                                    return;
                                } else {
                                    try {
                                        int x;
                                        int y;
                                        boolean varError = false;
                                        if (scriptLine[1].startsWith("$") && scriptLine[1].length() > 1) {
                                            if (variables.get(scriptLine[1]) == null) {
                                                varError = true;
                                            }

                                        } else {
                                            x = Integer.parseInt(scriptLine[1]);
                                        }

                                        if (scriptLine[2].startsWith("$") && scriptLine[2].length() > 1) {
                                            if (variables.get(scriptLine[2]) == null) {
                                                varError = true;
                                            }
                                        } else {
                                            y = Integer.parseInt(scriptLine[2]);
                                        }
                                        if (varError) {
                                            int finalLengthDifference1 = lengthDifference;
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": move args must be ints or int vars");
                                                    highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                                }
                                            });
                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
                                            return;
                                        }

                                        //parse number after wait, i.e. move 400 400
                                    } catch (NumberFormatException nfe) {
                                        //nfe.printStackTrace();
                                        int finalLengthDifference1 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": move args must be ints or int vars");
                                                highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        return;
                                    }
                                }
                                break;
                            case "moverandom":
                                //moverandom 400 425 600 625
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                }
                                scriptIsEmpty = false;
                                if (scriptLine.length != 5) {
                                    int finalLengthDifference = lengthDifference;
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid arg for moverandom");
                                            highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference, lengthDifferenceSingleLine);
                                        }
                                    });
                                    scriptingError = true;
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    return;
                                } else {
                                    try {
                                        int minX;
                                        int maxX;
                                        int minY;
                                        int maxY;
                                        boolean varError = false;
                                        if (scriptLine[1].startsWith("$") && scriptLine[1].length() > 1) {
                                            if (variables.get(scriptLine[1]) == null) {
                                                varError = true;
                                            }

                                        } else {
                                            minX = Integer.parseInt(scriptLine[1]);
                                        }

                                        if (scriptLine[2].startsWith("$") && scriptLine[2].length() > 1) {
                                            if (variables.get(scriptLine[2]) == null) {
                                                varError = true;
                                            }
                                        } else {
                                            maxX = Integer.parseInt(scriptLine[2]);
                                        }

                                        if (scriptLine[3].startsWith("$") && scriptLine[3].length() > 1) {
                                            if (variables.get(scriptLine[3]) == null) {
                                                varError = true;
                                            }
                                        } else {
                                            minY = Integer.parseInt(scriptLine[3]);
                                        }

                                        if (scriptLine[4].startsWith("$") && scriptLine[4].length() > 1) {
                                            if (variables.get(scriptLine[4]) == null) {
                                                varError = true;
                                            }
                                        } else {
                                            maxY = Integer.parseInt(scriptLine[4]);
                                        }
                                        if (varError) {
                                            int finalLengthDifference1 = lengthDifference;
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": moverandom args must be ints or int vars");
                                                    highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                                }
                                            });
                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
                                            return;
                                        }

                                        //parse number after wait, i.e. moverandom 400 450 600 650
                                    } catch (NumberFormatException nfe) {
                                        //nfe.printStackTrace();
                                        int finalLengthDifference1 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": moverandom args must be ints or int vars");
                                                highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
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
                                    textArea.setEditable(true);
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
                                    textArea.setEditable(true);
                                    break;
                                } else {
                                    try {

                                        int x;
                                        boolean varError = false;
                                        if (scriptLine[1].startsWith("$") && scriptLine[1].length() > 1) {
                                            if (variables.get(scriptLine[1]) == null) {
                                                varError = true;
                                            }

                                        } else {
                                            x = Integer.parseInt(scriptLine[1]);
                                        }

                                        if (varError) {
                                            int finalLengthDifference1 = lengthDifference;
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": wait args must be ints or int vars");
                                                    highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                                }
                                            });
                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
                                            return;
                                        }

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
                                        textArea.setEditable(true);
                                        break;
                                    }

                                }
                                break;
                            case "clickrandom":
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                }
                                scriptIsEmpty = false;
                                //System.out.println("you want to click on line " + j);
                                //now need to check if it has proper int args i.e. click 400 500
                                if (scriptLine.length != 5) {
                                    int finalLengthDifference4 = lengthDifference;
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for clickrandom");
                                            highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference4, lengthDifferenceSingleLine);
                                        }
                                    });

                                    scriptingError = true;
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                } else {
                                    try {
                                        int minX;
                                        int maxX;
                                        int minY;
                                        int maxY;
                                        boolean varError = false;
                                        if (scriptLine[1].startsWith("$") && scriptLine[1].length() > 1) {
                                            if (variables.get(scriptLine[1]) == null) {
                                                varError = true;
                                            }

                                        } else {
                                            minX = Integer.parseInt(scriptLine[1]);
                                        }

                                        if (scriptLine[2].startsWith("$") && scriptLine[2].length() > 1) {
                                            if (variables.get(scriptLine[2]) == null) {
                                                varError = true;
                                            }
                                        } else {
                                            maxX = Integer.parseInt(scriptLine[2]);
                                        }

                                        if (scriptLine[3].startsWith("$") && scriptLine[3].length() > 1) {
                                            if (variables.get(scriptLine[3]) == null) {
                                                varError = true;
                                            }
                                        } else {
                                            minY = Integer.parseInt(scriptLine[3]);
                                        }

                                        if (scriptLine[4].startsWith("$") && scriptLine[4].length() > 1) {
                                            if (variables.get(scriptLine[4]) == null) {
                                                varError = true;
                                            }
                                        } else {
                                            maxY = Integer.parseInt(scriptLine[4]);
                                        }
                                        if (varError) {
                                            int finalLengthDifference1 = lengthDifference;
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": clickrandom args must be ints or int vars");
                                                    highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                                }
                                            });
                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
                                            return;
                                        }
                                    } catch (NumberFormatException numException) {
                                        int finalLengthDifference5 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": clickrandom args must be ints");
                                                highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }

                                }
                                break;
                            case "releasemiddleclick":
                                System.out.println("releasemiddleclick");
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                }
                                scriptIsEmpty = false;
                                //System.out.println("you want to click on line " + j);
                                //now need to check if it has proper int args i.e. click 400 500
                                if (scriptLine.length != 1) {
                                    int finalLengthDifference4 = lengthDifference;
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for releasemiddleclick");
                                            highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference4, lengthDifferenceSingleLine);
                                        }
                                    });

                                    scriptingError = true;
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                } else {
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Script halted");
                                            }
                                        });
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }
                                }
                                break;
                            case "releaserightclick":
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                }
                                scriptIsEmpty = false;
                                //System.out.println("you want to click on line " + j);
                                //now need to check if it has proper int args i.e. click 400 500
                                if (scriptLine.length != 1) {
                                    int finalLengthDifference4 = lengthDifference;
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for releaserightclick");
                                            highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference4, lengthDifferenceSingleLine);
                                        }
                                    });

                                    scriptingError = true;
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                } else {
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Script halted");
                                            }
                                        });
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }
                                }
                                break;
                            case "releaseclick":
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                }
                                scriptIsEmpty = false;
                                //System.out.println("you want to click on line " + j);
                                //now need to check if it has proper int args i.e. click 400 500
                                if (scriptLine.length != 1) {
                                    int finalLengthDifference4 = lengthDifference;
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for releaseclick");
                                            highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference4, lengthDifferenceSingleLine);
                                        }
                                    });

                                    scriptingError = true;
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                } else {
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Script halted");
                                            }
                                        });
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }
                                }
                                break;
                            case "middleclickandhold":
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                }
                                scriptIsEmpty = false;
                                //System.out.println("you want to click on line " + j);
                                //now need to check if it has proper int args i.e. click 400 500
                                if (scriptLine.length != 3) {
                                    int finalLengthDifference4 = lengthDifference;
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for middleclickandhold");
                                            highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference4, lengthDifferenceSingleLine);
                                        }
                                    });

                                    scriptingError = true;
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                } else {
                                    try {
                                        int x;
                                        int y;
                                        boolean varError = false;
                                        if (scriptLine[1].startsWith("$") && scriptLine[1].length() > 1) {
                                            if (variables.get(scriptLine[1]) == null) {
                                                varError = true;
                                            }

                                        } else {
                                            x = Integer.parseInt(scriptLine[1]);
                                        }

                                        if (scriptLine[2].startsWith("$") && scriptLine[2].length() > 1) {
                                            if (variables.get(scriptLine[2]) == null) {
                                                varError = true;
                                            }
                                        } else {
                                            y = Integer.parseInt(scriptLine[2]);
                                        }
                                        if (varError) {
                                            int finalLengthDifference1 = lengthDifference;
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": middleclickandhold args must be ints or int vars");
                                                    highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                                }
                                            });
                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
                                            return;
                                        }
                                    } catch (NumberFormatException numException) {
                                        int finalLengthDifference5 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": middleclickandhold args must be ints");
                                                highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }

                                }
                                break;
                            case "rightclickandhold":
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                }
                                scriptIsEmpty = false;
                                //System.out.println("you want to click on line " + j);
                                //now need to check if it has proper int args i.e. click 400 500
                                if (scriptLine.length != 3) {
                                    int finalLengthDifference4 = lengthDifference;
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for rightclickandhold");
                                            highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference4, lengthDifferenceSingleLine);
                                        }
                                    });

                                    scriptingError = true;
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                } else {
                                    try {
                                        int x;
                                        int y;
                                        boolean varError = false;
                                        if (scriptLine[1].startsWith("$") && scriptLine[1].length() > 1) {
                                            if (variables.get(scriptLine[1]) == null) {
                                                varError = true;
                                            }

                                        } else {
                                            x = Integer.parseInt(scriptLine[1]);
                                        }

                                        if (scriptLine[2].startsWith("$") && scriptLine[2].length() > 1) {
                                            if (variables.get(scriptLine[2]) == null) {
                                                varError = true;
                                            }
                                        } else {
                                            y = Integer.parseInt(scriptLine[2]);
                                        }
                                        if (varError) {
                                            int finalLengthDifference1 = lengthDifference;
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": rightclickandhold args must be ints or int vars");
                                                    highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                                }
                                            });
                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
                                            return;
                                        }
                                    } catch (NumberFormatException numException) {
                                        int finalLengthDifference5 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": rightclickandhold args must be ints");
                                                highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }

                                }
                                break;
                            case "clickandhold":
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                }
                                scriptIsEmpty = false;
                                //System.out.println("you want to click on line " + j);
                                //now need to check if it has proper int args i.e. click 400 500
                                if (scriptLine.length != 3) {
                                    int finalLengthDifference4 = lengthDifference;
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for clickandhold");
                                            highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference4, lengthDifferenceSingleLine);
                                        }
                                    });

                                    scriptingError = true;
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                } else {
                                    try {
                                        int x;
                                        int y;
                                        boolean varError = false;
                                        if (scriptLine[1].startsWith("$") && scriptLine[1].length() > 1) {
                                            if (variables.get(scriptLine[1]) == null) {
                                                varError = true;
                                            }

                                        } else {
                                            x = Integer.parseInt(scriptLine[1]);
                                        }

                                        if (scriptLine[2].startsWith("$") && scriptLine[2].length() > 1) {
                                            if (variables.get(scriptLine[2]) == null) {
                                                varError = true;
                                            }
                                        } else {
                                            y = Integer.parseInt(scriptLine[2]);
                                        }
                                        if (varError) {
                                            int finalLengthDifference1 = lengthDifference;
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": clickandhold args must be ints or int vars");
                                                    highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                                }
                                            });
                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
                                            return;
                                        }
                                    } catch (NumberFormatException numException) {
                                        int finalLengthDifference5 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": clickandhold args must be ints");
                                                highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }

                                }
                                break;
                            case "print":
                            case "println":
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                }
                                //System.out.println("print 1");
                                int finalLengthDifference4 = lengthDifference;

                                //used to be 2 instead of 1, but then i realized it can be useful to print a blank line
                                if (scriptLine.length < 1) {
                                    int finalLengthDifference5 = lengthDifference;
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for print");
                                            highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                        }
                                    });

                                    scriptingError = true;
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                } else {
                                    scriptIsEmpty = false;
                                    int numOfThingsToPrint = scriptLine.length - 1;
                                    String newTextAreaString = consoleTextArea.getText();
                                    for (int k = 0; k < numOfThingsToPrint; k++) {
                                        //System.out.println(scriptLine[k + 1]);
                                        String thingToPrint = scriptLine[k + 1];
                                        if (thingToPrint.startsWith("$")) {
                                            if (variables.get(thingToPrint) != null) {
                                                //thingToPrint = variables.get(thingToPrint).getValue();
                                            } else {
                                                //error
                                                int finalLengthDifference5 = lengthDifference;
                                                Platform.runLater(new Runnable(){
                                                    @Override public void run() {
                                                        loadingLabel.setText("Macro error on line " + lineNumber + ": referenced non-existent variable");
                                                        highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                                    }
                                                });

                                                scriptingError = true;
                                                runMacroButton.setDisable(false);
                                                textArea.setEditable(true);
                                                break;
                                            }
                                        }



                                    }
                                }
                                break;
                            case "update":
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                }
                                scriptIsEmpty = false;
                                System.out.println("todo: update $x += 5 etc");
                                if (scriptLine.length != 4) {
                                    int finalLengthDifference5 = lengthDifference;
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for update");
                                            highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                        }
                                    });

                                    scriptingError = true;
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                } else {
                                    System.out.println("got here else update 1");
                                    //check args for either literal values or variable references
                                    //0 = update
                                    //1 = var to be updated, i.e. $x (operand 1)
                                    //2 = operator, such as += or -= etc
                                    //3 = operand 2

                                    for (int k = 0; k < scriptLine.length; k++) {
                                        System.out.println("scriptLine[" + k + "]: " + scriptLine[k]);
                                    }

                                    Variable varForOp1 = variables.get(scriptLine[1]);


                                    if (scriptLine[1].startsWith("$") && varForOp1 != null && varForOp1.getType() != null) {
                                        System.out.println("got here 2 starts with $");


                                        switch (variables.get(scriptLine[1]).getType()) {
                                            case INT:

                                                //check if it's in the variables hashmap
                                                if (varForOp1 != null) {
                                                    //all good
                                                    int result = -2147483648;
                                                    int operand1 = variables.get(scriptLine[1]).getIntValue();
                                                    Variable varForOp2 = null;
                                                    int operand2 = -1; //-1 means uninitialized

                                                    if (scriptLine[3].startsWith("$")) {
                                                        //check if operand 2 is in the variables hashmap
                                                        //and it also needs to be an int
                                                        varForOp2 = variables.get(scriptLine[3]);
                                                        if (varForOp2 != null && varForOp2.getType() == Variable.Type.INT) {
                                                            operand2 = varForOp2.getIntValue();
                                                        } else {
                                                            int finalLengthDifference5 = lengthDifference;
                                                            Platform.runLater(new Runnable(){
                                                                @Override public void run() {
                                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for update");
                                                                    highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                                                }
                                                            });

                                                            scriptingError = true;
                                                            runMacroButton.setDisable(false);
                                                            textArea.setEditable(true);
                                                            break;
                                                        }
                                                    } else {
                                                        try {
                                                            operand2 = Integer.parseInt(scriptLine[3]);
                                                        } catch (NumberFormatException e) {
                                                            int finalLengthDifference5 = lengthDifference;
                                                            Platform.runLater(new Runnable(){
                                                                @Override public void run() {
                                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for update");
                                                                    highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                                                }
                                                            });

                                                            scriptingError = true;
                                                            runMacroButton.setDisable(false);
                                                            textArea.setEditable(true);
                                                            break;
                                                        }
                                                    }
                                                    switch (scriptLine[2]) {
                                                        case "+=":
                                                            result = operand1 + operand2;
                                                            break;
                                                        case "-=":
                                                            result = operand1 - operand2;
                                                            break;
                                                        case "*=":
                                                            result = operand1 * operand2;
                                                            break;
                                                        case "/=":
                                                            if (!scriptLine[2].equalsIgnoreCase("0")) {
                                                                result = operand1 / operand2;
                                                            } else {
                                                                //can't divide by zero
                                                                int finalLengthDifference5 = lengthDifference;
                                                                Platform.runLater(new Runnable(){
                                                                    @Override public void run() {
                                                                        loadingLabel.setText("Macro error on line " + lineNumber + ": can't divide by zero");
                                                                        highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                                                    }
                                                                });

                                                                scriptingError = true;
                                                                runMacroButton.setDisable(false);
                                                                textArea.setEditable(true);
                                                                break;
                                                            }
                                                            break;
                                                        case "^=":
                                                            result = (int) Math.pow(operand1, operand2);
                                                            break;
                                                        case "%=":
                                                            result = operand1 % operand2;
                                                            break;
                                                        case "=":
                                                            result = operand2;
                                                            break;
                                                        default:
                                                            int finalLengthDifference5 = lengthDifference;
                                                            Platform.runLater(new Runnable(){
                                                                @Override public void run() {
                                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": invalid arithmetic operator");
                                                                    highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                                                }
                                                            });

                                                            scriptingError = true;
                                                            runMacroButton.setDisable(false);
                                                            textArea.setEditable(true);
                                                            break;
                                                    }
                                                    System.out.println("result: " + result);

                                                } else {
                                                    //todo: throw error
                                                    int finalLengthDifference5 = lengthDifference;
                                                    Platform.runLater(new Runnable(){
                                                        @Override public void run() {
                                                            loadingLabel.setText("Macro error on line " + lineNumber + ": referenced non-existent variable");
                                                            highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                                        }
                                                    });

                                                    scriptingError = true;
                                                    runMacroButton.setDisable(false);
                                                    textArea.setEditable(true);
                                                    break;
                                                }



                                                break;
                                            case STR:
                                                System.out.println("not implemented yet");
                                            default:
                                                System.out.println("not supposed to get here");
                                                break;
                                        }



                                    } else {
                                        //todo: throw error
                                        int finalLengthDifference5 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for update");
                                                highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }
                                }
                                break;
                            //int $i = 0
                            //this means make a new one, not update an existing one
                            //"update" will be separate, and will have different things like + - / etc.
                            case "clear":
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                }
                                scriptIsEmpty = false;
                                if (scriptLine.length != 1) {
                                    int finalLengthDifference7 = lengthDifference;
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for clear");
                                        }
                                    });

                                    scriptingError = true;
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                } else {
                                    //it's fine, but don't clear it here just yet
                                    //consoleTextArea.setText("\n\n\nAutoInput Script Console\n");
                                }
                                break;
                            case "unminimize":
                                //primaryStage.setIconified(false);
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                }
                                scriptIsEmpty = false;
                                break;
                            case "minimize":
                                System.out.println("minimize");
                                //primaryStage.setIconified(true);
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                }
                                scriptIsEmpty = false;
                                break;
                            case "destroy":
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                }
                                scriptIsEmpty = false;
                                if (scriptLine.length == 2) {
                                    if (scriptLine[1].startsWith("$")) {
                                        if (variables.get(scriptLine[1]) != null) {
                                            //destroy it
                                        } else {
                                            int finalLengthDifference5 = lengthDifference;
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": can't destroy a non-existent variable");
                                                    highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                                }
                                            });

                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
                                        }
                                    } else {
                                        int finalLengthDifference5 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for destroy");
                                                highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                    }
                                } else {
                                    int finalLengthDifference5 = lengthDifference;
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for destroy");
                                            highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                        }
                                    });

                                    scriptingError = true;
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                }
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                }
                                break;
                            case "random":
                                System.out.println("got here random");
                                //random int -- example:  random int $x = 100 500
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                }
                                scriptIsEmpty = false;
                                //System.out.println("you want to click on line " + j);
                                //now need to check if it has proper int args i.e. click 400 500
                                if (scriptLine.length != 6) {
                                    int finalLengthDifference5 = lengthDifference;
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for random");
                                            highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                        }
                                    });
                                    scriptingError = true;
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                } else {
                                    System.out.println("syntax checking for random int, not actually running it (this is part 1 of 2)");
                                    System.out.println("got here proper number of args");
                                    switch (scriptLine[1]) {
                                        case "int":
                                        case "update":
                                            System.out.println("got here int");
                                            //random int $x = 200 300
                                            //or random int $x = $y $z
                                            if (scriptLine[2].startsWith("$")) {
                                                System.out.println("2 starts with $, good");
                                                Variable varToSeeIfExists = variables.get(scriptLine[2]);
                                                if ((scriptLine[1].equalsIgnoreCase("int") && varToSeeIfExists == null) || (scriptLine[1].equalsIgnoreCase("update") && varToSeeIfExists != null)) {
                                                    //either new (int) or existing (update)
                                                    if ((scriptLine[1].equalsIgnoreCase("int") && scriptLine[3].equalsIgnoreCase("=")) ||
                                                            (scriptLine[1].equalsIgnoreCase("update")
                                                                    && Arrays.asList(new String[]{"+=", "-=", "*=", "/=", "^=", "%=", "="}).contains(scriptLine[3]))) {
                                                        //check if 4 and 5 are ints or variables
                                                        int lowerBound = -2147483648;
                                                        int upperBound = -2147483648;
                                                        boolean parseError = false;
                                                        if (scriptLine[4].startsWith("$")) {
                                                            Variable var4 = variables.get(scriptLine[4]);
                                                            if (var4.getType() == Variable.Type.INT) {
                                                                lowerBound = var4.getIntValue();
                                                            } else {
                                                                int finalLengthDifference5 = lengthDifference;
                                                                Platform.runLater(new Runnable(){
                                                                    @Override public void run() {
                                                                        loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for random");
                                                                        highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                                                    }
                                                                });
                                                                scriptingError = true;
                                                                runMacroButton.setDisable(false);
                                                                textArea.setEditable(true);
                                                                break;
                                                            }
                                                        } else {
                                                            //try parseInt for var4, throw numexception if failed
                                                            try {
                                                                lowerBound = Integer.parseInt(scriptLine[4]);
                                                            } catch (NumberFormatException ne) {
                                                                //throw error, invalid args, not a number
                                                                int finalLengthDifference5 = lengthDifference;
                                                                Platform.runLater(new Runnable(){
                                                                    @Override public void run() {
                                                                        loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for random");
                                                                        highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                                                    }
                                                                });
                                                                scriptingError = true;
                                                                runMacroButton.setDisable(false);
                                                                textArea.setEditable(true);
                                                                break;
                                                            }
                                                        }

                                                        if (scriptLine[5].startsWith("$")) {
                                                            Variable var5 = variables.get(scriptLine[5]);
                                                            if (var5.getType() == Variable.Type.INT) {
                                                                upperBound = var5.getIntValue();
                                                            }
                                                        } else {
                                                            //try parseInt for var5, throw numexception if failed
                                                            try {
                                                                upperBound = Integer.parseInt(scriptLine[5]);
                                                            } catch (NumberFormatException ne) {
                                                                //throw error, invalid args, not a number
                                                                int finalLengthDifference5 = lengthDifference;
                                                                Platform.runLater(new Runnable(){
                                                                    @Override public void run() {
                                                                        loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for random");
                                                                        highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                                                    }
                                                                });
                                                                scriptingError = true;
                                                                runMacroButton.setDisable(false);
                                                                textArea.setEditable(true);
                                                                break;
                                                            }
                                                        }

                                                        if (!parseError) {
                                                            System.out.println("lowerBound: " + lowerBound);
                                                            System.out.println("upperBound: " + upperBound);
                                                            int newRandomValue = getRandomNumber(lowerBound, upperBound);
                                                            Variable newRandomVar = new Variable();
                                                            newRandomVar.setType(Variable.Type.INT);


                                                            boolean shouldUpdate = false;
                                                            int existingVarToUpdate = -2147483648;
                                                            if (scriptLine[1].equalsIgnoreCase("int") &&
                                                                    scriptLine[3].equalsIgnoreCase("=")) {
                                                                //if int:
                                                                System.out.println("got here int 3322");
                                                                //keep it the same as the random number
                                                                existingVarToUpdate = newRandomValue;
                                                                shouldUpdate = true;
                                                            } else if (scriptLine[1].equalsIgnoreCase("update") &&
                                                                    Arrays.asList(new String[]{"+=", "-=", "*=", "/=", "^=", "%=", "="}).contains(scriptLine[3])) {
                                                                //else if update:
                                                                System.out.println("got here update 3322");
                                                                //if it's random update $x = 100 200, then [2] is $x
                                                                Variable fromVariable = variables.get(scriptLine[2]);

                                                                if (fromVariable != null) {
                                                                    existingVarToUpdate = fromVariable.getIntValue();
                                                                } else {
                                                                    //todo: throw parse error
                                                                }

                                                                switch (scriptLine[3]) {
                                                                    case "+=":
                                                                        existingVarToUpdate += newRandomValue;
                                                                        shouldUpdate = true;
                                                                        break;
                                                                    case "-=":
                                                                        existingVarToUpdate -= newRandomValue;
                                                                        shouldUpdate = true;
                                                                        break;
                                                                    case "*=":
                                                                        existingVarToUpdate *= newRandomValue;
                                                                        shouldUpdate = true;
                                                                        break;
                                                                    case "/=":
                                                                        if (!(newRandomValue == 0)) {
                                                                            existingVarToUpdate /= newRandomValue;
                                                                            shouldUpdate = true;
                                                                        } else {
                                                                            //can't divide by zero
                                                                            int finalLengthDifference5 = lengthDifference;
                                                                            Platform.runLater(new Runnable(){
                                                                                @Override public void run() {
                                                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": can't divide by zero");
                                                                                    highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                                                                }
                                                                            });

                                                                            scriptingError = true;
                                                                            runMacroButton.setDisable(false);
                                                                            textArea.setEditable(true);
                                                                            break;
                                                                        }
                                                                        break;
                                                                    case "^=":
                                                                        existingVarToUpdate = (int) Math.pow(existingVarToUpdate, newRandomValue);
                                                                        shouldUpdate = true;
                                                                        break;
                                                                    case "%=":
                                                                        existingVarToUpdate %= newRandomValue;
                                                                        shouldUpdate = true;
                                                                        break;
                                                                    case "=":
                                                                        existingVarToUpdate = newRandomValue;
                                                                        shouldUpdate = true;
                                                                        break;
                                                                    default:
                                                                        System.out.println("default in switch/case for for random update int $x = 100 200");
                                                                        break;
                                                                }
                                                            } else {
                                                                System.out.println("not supposed to get here");
                                                            }
                                                            System.out.println(scriptLine[2] + ": " + existingVarToUpdate);
                                                            if (shouldUpdate) {
                                                                System.out.println("got here shouldUpdate 121233");
                                                                newRandomVar.setIntValue(existingVarToUpdate);
                                                                //variables.put(scriptLine[2], newRandomVar);
                                                            }
                                                        }


                                                    } else {
                                                        int finalLengthDifference5 = lengthDifference;
                                                        Platform.runLater(new Runnable(){
                                                            @Override public void run() {
                                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for random");
                                                                highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                                            }
                                                        });
                                                        scriptingError = true;
                                                        runMacroButton.setDisable(false);
                                                        textArea.setEditable(true);
                                                        break;
                                                    }

                                                } else {
                                                    int finalLengthDifference5 = lengthDifference;
                                                    Platform.runLater(new Runnable(){
                                                        @Override public void run() {
                                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for random");
                                                            highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                                        }
                                                    });
                                                    scriptingError = true;
                                                    runMacroButton.setDisable(false);
                                                    textArea.setEditable(true);
                                                    break;
                                                }

                                            } else {
                                                int finalLengthDifference5 = lengthDifference;
                                                Platform.runLater(new Runnable(){
                                                    @Override public void run() {
                                                        loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for random");
                                                        highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                                    }
                                                });
                                                scriptingError = true;
                                                runMacroButton.setDisable(false);
                                                textArea.setEditable(true);
                                                break;
                                            }
                                            break;
                                        case "str":
                                            System.out.println("not implemented yet");
                                            break;
                                        default:
                                            System.out.println("not supposed to get here");
                                            //todo: throw error
                                            break;
                                    }
                                }
                                break;
                            case "int":
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                }
                                scriptIsEmpty = false;
                                //System.out.println("you want to click on line " + j);
                                //now need to check if it has proper int args i.e. click 400 500
                                if (scriptLine.length != 4) {
                                    int finalLengthDifference5 = lengthDifference;
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for int declaration");
                                            highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                        }
                                    });

                                    scriptingError = true;
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                } else {
                                    if ( (!scriptLine[2].equalsIgnoreCase("=")) ||
                                            ((!scriptLine[1].startsWith("$"))) ||
                                            ((!(scriptLine[1].length() > 1)))
                                    ) {
                                        System.out.println("-------------------------------");
                                        System.out.println("scriptLine[0]: " + scriptLine[0]);
                                        System.out.println("scriptLine[1]: " + scriptLine[1]);
                                        System.out.println("scriptLine[2]: " + scriptLine[2]);
                                        System.out.println("scriptLine[3]: " + scriptLine[3]);

                                        System.out.println(!scriptLine[2].equalsIgnoreCase("="));
                                        System.out.println((!scriptLine[1].startsWith("$")));
                                        System.out.println((!(scriptLine[1].length() > 1)));
                                        System.out.println("-------------------------------");
                                        int finalLengthDifference5 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid new int args1");
                                                highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                            }
                                        });
                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }
                                    try {
                                        System.out.println("Try 1");
                                        int intValue = -2147483648;
                                        boolean varError = false;

                                        if (scriptLine[3].startsWith("$") && scriptLine[3].length() > 1) {

                                            if (variables.get(scriptLine[3]) == null) {
                                                varError = true;
                                            } else {
                                                intValue = variables.get(scriptLine[3]).getIntValue();
                                            }
                                        } else {
                                            intValue = Integer.parseInt(scriptLine[3]);
                                        }

                                        if (!(intValue == -2147483648)) {
                                            variables = createNewInt(scriptLine[1], intValue, variables);
                                        } else {
                                            System.out.println("hmmm int111");
                                        }

                                        if (varError) {
                                            int finalLengthDifference1 = lengthDifference;
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": int args must be ints or int vars");
                                                    highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                                }
                                            });
                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
                                            return;
                                        }

                                    } catch (NumberFormatException numException) {

                                        if (scriptLine[3].startsWith("$") && scriptLine[3].length() > 1) {
                                            System.out.println("made it here!!!!!!!!!!!!!!!!!!");
                                        } else {
                                            int finalLengthDifference5 = lengthDifference;
                                            Platform.runLater(new Runnable() {
                                                @Override
                                                public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": invalid new int args");
                                                    highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                                }
                                            });

                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
                                            break;
                                        }
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
                                    textArea.setEditable(true);
                                    break;
                                }
                                scriptIsEmpty = false;
                                //System.out.println("you want to click on line " + j);
                                //now need to check if it has proper int args i.e. click 400 500
                                if (scriptLine.length != 3) {
                                    int finalLengthDifference6 = lengthDifference;
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for click");
                                            highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference6, lengthDifferenceSingleLine);
                                        }
                                    });

                                    scriptingError = true;
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                } else {
                                    try {
                                        int x;
                                        int y;
                                        boolean varError = false;
                                        if (scriptLine[1].startsWith("$") && scriptLine[1].length() > 1) {
                                            if (variables.get(scriptLine[1]) == null) {
                                                varError = true;
                                            }

                                        } else {
                                            x = Integer.parseInt(scriptLine[1]);
                                        }

                                        if (scriptLine[2].startsWith("$") && scriptLine[2].length() > 1) {
                                            if (variables.get(scriptLine[2]) == null) {
                                                varError = true;
                                            }
                                        } else {
                                            y = Integer.parseInt(scriptLine[2]);
                                        }
                                        if (varError) {
                                            int finalLengthDifference1 = lengthDifference;
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": click args must be ints or int vars");
                                                    highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                                }
                                            });
                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
                                            return;
                                        }
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
                                        textArea.setEditable(true);
                                        break;
                                    }

                                }
                                break;
                            case "middleclick":
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                }
                                scriptIsEmpty = false;
                                //System.out.println("you want to middleclick on line " + j);
                                //now need to check if it has proper int args i.e. middleclick 400 500
                                if (scriptLine.length != 3) {
                                    int finalLengthDifference6 = lengthDifference;
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for middleclick");
                                            highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference6, lengthDifferenceSingleLine);
                                        }
                                    });

                                    scriptingError = true;
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                } else {
                                    try {
                                        int x;
                                        int y;
                                        boolean varError = false;
                                        if (scriptLine[1].startsWith("$") && scriptLine[1].length() > 1) {
                                            if (variables.get(scriptLine[1]) == null) {
                                                varError = true;
                                            }

                                        } else {
                                            x = Integer.parseInt(scriptLine[1]);
                                        }

                                        if (scriptLine[2].startsWith("$") && scriptLine[2].length() > 1) {
                                            if (variables.get(scriptLine[2]) == null) {
                                                varError = true;
                                            }
                                        } else {
                                            y = Integer.parseInt(scriptLine[2]);
                                        }
                                        if (varError) {
                                            int finalLengthDifference1 = lengthDifference;
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": middleclick args must be ints or int vars");
                                                    highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                                }
                                            });
                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
                                            return;
                                        }
                                    } catch (NumberFormatException numException) {
                                        int finalLengthDifference7 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": middleclick args must be ints");
                                                highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference7, lengthDifferenceSingleLine);
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
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
                                    textArea.setEditable(true);
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
                                    textArea.setEditable(true);
                                    break;
                                } else {
                                    try {
                                        int x;
                                        int y;
                                        boolean varError = false;
                                        if (scriptLine[1].startsWith("$") && scriptLine[1].length() > 1) {
                                            if (variables.get(scriptLine[1]) == null) {
                                                varError = true;
                                            }

                                        } else {
                                            x = Integer.parseInt(scriptLine[1]);
                                        }

                                        if (scriptLine[2].startsWith("$") && scriptLine[2].length() > 1) {
                                            if (variables.get(scriptLine[2]) == null) {
                                                varError = true;
                                            }
                                        } else {
                                            y = Integer.parseInt(scriptLine[2]);
                                        }
                                        if (varError) {
                                            int finalLengthDifference1 = lengthDifference;
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": rightclick args must be ints or int vars");
                                                    highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                                }
                                            });
                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
                                            return;
                                        }
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
                                        textArea.setEditable(true);
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
                                    textArea.setEditable(true);
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
                                    textArea.setEditable(true);
                                    break;
                                } else {
                                    try {
                                        //old
                                        /*
                                        int startx;
                                        int starty;
                                        int endx;
                                        int endy;
                                        startx = Integer.parseInt(scriptLine[1]);
                                        starty = Integer.parseInt(scriptLine[2]);
                                        endx = Integer.parseInt(scriptLine[3]);
                                        endy = Integer.parseInt(scriptLine[4]);
                                        */
                                        int minX;
                                        int maxX;
                                        int minY;
                                        int maxY;
                                        boolean varError = false;
                                        if (scriptLine[1].startsWith("$") && scriptLine[1].length() > 1) {
                                            if (variables.get(scriptLine[1]) == null) {
                                                varError = true;
                                            }

                                        } else {
                                            minX = Integer.parseInt(scriptLine[1]);
                                        }

                                        if (scriptLine[2].startsWith("$") && scriptLine[2].length() > 1) {
                                            if (variables.get(scriptLine[2]) == null) {
                                                varError = true;
                                            }
                                        } else {
                                            maxX = Integer.parseInt(scriptLine[2]);
                                        }

                                        if (scriptLine[3].startsWith("$") && scriptLine[3].length() > 1) {
                                            if (variables.get(scriptLine[3]) == null) {
                                                varError = true;
                                            }
                                        } else {
                                            minY = Integer.parseInt(scriptLine[3]);
                                        }

                                        if (scriptLine[4].startsWith("$") && scriptLine[4].length() > 1) {
                                            if (variables.get(scriptLine[4]) == null) {
                                                varError = true;
                                            }
                                        } else {
                                            maxY = Integer.parseInt(scriptLine[4]);
                                        }
                                        if (varError) {
                                            int finalLengthDifference1 = lengthDifference;
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": clickanddrag args must be ints or int vars");
                                                    highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                                }
                                            });
                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
                                            return;
                                        }

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
                                        textArea.setEditable(true);
                                        break;
                                    }

                                }
                                break;
                            case "alert":
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                }

                                scriptIsEmpty = false;
                                if (scriptLine.length != 1) {
                                    int finalLengthDifference2 = lengthDifference;
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid arg for alert");
                                            //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference2, lengthDifferenceSingleLine);
                                        }
                                    });

                                    scriptingError = true;
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                }
                                break;
                            case "clickanddragrandom":
                                if (scriptHalter.isUserWantsToHaltScript()) {
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Script halted");
                                        }
                                    });
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                }
                                scriptIsEmpty = false;
                                //System.out.println("you want to clickanddragrandom on line " + j);
                                //now need to check if it has proper int args i.e. clickanddragrandom 300 350 300 350 500 550 500 550
                                //startxrandlower, startxrandupper, startyrandlower, startyrandupper, endxrandlower, endxrandupper, endyrandlower, endyrandupper
                                if (scriptLine.length != 9) {
                                    int finalLengthDifference6 = lengthDifference;
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for clickanddragrandom");
                                            highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference6, lengthDifferenceSingleLine);
                                        }
                                    });

                                    scriptingError = true;
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                                } else {
                                    try {
                                        //old
                                        /*
                                        int startxrandlower  = Integer.parseInt(scriptLine[1]);
                                        int startxrandupper  = Integer.parseInt(scriptLine[2]);
                                        int startyrandlower  = Integer.parseInt(scriptLine[3]);
                                        int startyrandupper  = Integer.parseInt(scriptLine[4]);
                                        int endxrandlower  = Integer.parseInt(scriptLine[5]);
                                        int endxrandupper  = Integer.parseInt(scriptLine[6]);
                                        int endyrandlower  = Integer.parseInt(scriptLine[7]);
                                        int endyrandupper = Integer.parseInt(scriptLine[8]);
                                         */
                                        int startMinX;
                                        int startMaxX;
                                        int startMinY;
                                        int startMaxY;
                                        int endMinX;
                                        int endMaxX;
                                        int endMinY;
                                        int endMaxY;

                                        boolean varError = false;
                                        if (scriptLine[1].startsWith("$") && scriptLine[1].length() > 1) {
                                            if (variables.get(scriptLine[1]) == null) {
                                                varError = true;
                                            }

                                        } else {
                                            startMinX = Integer.parseInt(scriptLine[1]);
                                        }

                                        if (scriptLine[2].startsWith("$") && scriptLine[2].length() > 1) {
                                            if (variables.get(scriptLine[2]) == null) {
                                                varError = true;
                                            }
                                        } else {
                                            startMaxX = Integer.parseInt(scriptLine[2]);
                                        }

                                        if (scriptLine[3].startsWith("$") && scriptLine[3].length() > 1) {
                                            if (variables.get(scriptLine[3]) == null) {
                                                varError = true;
                                            }
                                        } else {
                                            startMinY = Integer.parseInt(scriptLine[3]);
                                        }

                                        if (scriptLine[4].startsWith("$") && scriptLine[4].length() > 1) {
                                            if (variables.get(scriptLine[4]) == null) {
                                                varError = true;
                                            }
                                        } else {
                                            startMaxY = Integer.parseInt(scriptLine[4]);
                                        }

                                        //===================================================

                                        if (scriptLine[5].startsWith("$") && scriptLine[5].length() > 1) {
                                            if (variables.get(scriptLine[5]) == null) {
                                                varError = true;
                                            }

                                        } else {
                                            endMinX = Integer.parseInt(scriptLine[5]);
                                        }

                                        if (scriptLine[6].startsWith("$") && scriptLine[6].length() > 1) {
                                            if (variables.get(scriptLine[6]) == null) {
                                                varError = true;
                                            }
                                        } else {
                                            endMaxX = Integer.parseInt(scriptLine[6]);
                                        }

                                        if (scriptLine[7].startsWith("$") && scriptLine[7].length() > 1) {
                                            if (variables.get(scriptLine[7]) == null) {
                                                varError = true;
                                            }
                                        } else {
                                            endMinY = Integer.parseInt(scriptLine[7]);
                                        }

                                        if (scriptLine[8].startsWith("$") && scriptLine[8].length() > 1) {
                                            if (variables.get(scriptLine[8]) == null) {
                                                varError = true;
                                            }
                                        } else {
                                            endMaxY = Integer.parseInt(scriptLine[8]);
                                        }

                                        if (varError) {
                                            int finalLengthDifference1 = lengthDifference;
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": clickanddragrandom args must be ints or int vars");
                                                    highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                                }
                                            });
                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
                                            return;
                                        }
                                    } catch (NumberFormatException numException) {
                                        int finalLengthDifference7 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": clickanddragrandom args must be ints or int vars");
                                                highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference7, lengthDifferenceSingleLine);
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
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
                                    textArea.setEditable(true);
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
                                    textArea.setEditable(true);
                                    break;
                                } else {
                                    String allKeys = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,";
                                    allKeys += allKeys.toUpperCase();
                                    allKeys += "0,1,2,3,4,5,6,7,8,9,";
                                    //for now, I'm not adding all keys, just basic ones
                                    allKeys += "enter,space,backspace,up,down,left,right,escape,tab";
                                    String keysArray[] = allKeys.split(",");

                                    Set<String> keySet = Set.of(keysArray);

                                    if (keySet.contains(scriptLine[1])) {
                                        //System.out.println("this is a valid key");
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
                                        textArea.setEditable(true);
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
                                    textArea.setEditable(true);
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
                                    textArea.setEditable(true);
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
                                textArea.setEditable(true);
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
                    textArea.setEditable(true);

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




                    //now it supports nested loops, with up to 100 in total
                    int[] timesToLoop = new int[100]; //by default, everyone will run once, but in a loop command block, this value will be changed
                    int[] startOfLoopLineNunber = new int[100]; //-1 means not initialized
                    for (int idx = 0; idx < 100; idx++) {
                        timesToLoop[idx] = 1;
                        startOfLoopLineNunber[idx] = -1;
                    }
                    int currentLoopLevel = 0;

                    for (int j = 0; j < numberOfLines; j++) {

                        //if there is an error or the user presses the button to halt the script, then stop the script
                        if (scriptingError || scriptHalter.isUserWantsToHaltScript()) {
                            //System.out.println("script has been halted");
                            //scriptHalter.setUserWantsToHaltScript(false);
                            runMacroButton.setDisable(false);
                            textArea.setEditable(true);
                            break;
                        }
                        String scriptLine[] = lines[j].split(" ");


                        int lineNumber = j + 1;

                        if (scriptLine.length != 0) {

                            switch (scriptLine[0]) {
                                //if/elseif/else/endif
                                case "if":
                                    //todo
                                    break;
                                case "elseif":
                                    //todo
                                    break;
                                case "else":
                                    break;
                                case "endif":
                                    //todo
                                    break;
                                case "loop":
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Script halted");
                                            }
                                        });
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }
                                    scriptIsEmpty = false;
                                    //check if it has an int arg i.e. loop 5
                                    if (scriptLine.length != 2) {
                                        int finalLengthDifference2 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid arg for loop");
                                                //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference2, lengthDifferenceSingleLine);
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    } else {
                                        try {

                                            boolean varError = false;
                                            if (scriptLine[1].startsWith("$") && scriptLine[1].length() > 1) {
                                                if (variables.get(scriptLine[1]) == null) {
                                                    varError = true;
                                                } else {
                                                    System.out.println("got here 1!!!!!!!!!!!!!!!!!");
                                                    System.out.println("scriptLine[1]: " + scriptLine[1]);
                                                    System.out.println("variables.get(scriptLine[1]).getIntValue(): " + variables.get(scriptLine[1]).getIntValue());
                                                    currentLoopLevel += 1;
                                                    timesToLoop[currentLoopLevel] = variables.get(scriptLine[1]).getIntValue();
                                                    startOfLoopLineNunber[currentLoopLevel] = j;
                                                }

                                            } else {
                                                System.out.println("got here 2!!!!!!!!!!!!!!");
                                                System.out.println("scriptLine[1]: " + scriptLine[1]);
                                                currentLoopLevel += 1;
                                                timesToLoop[currentLoopLevel] = Integer.parseInt(scriptLine[1]);
                                                startOfLoopLineNunber[currentLoopLevel] = j;
                                            }

                                            if (varError) {
                                                int finalLengthDifference1 = lengthDifference;
                                                Platform.runLater(new Runnable(){
                                                    @Override public void run() {
                                                        loadingLabel.setText("Macro error on line " + lineNumber + ": loop args must be ints or int vars");
                                                        //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                                    }
                                                });
                                                scriptingError = true;
                                                runMacroButton.setDisable(false);
                                                textArea.setEditable(true);
                                                return;
                                            }

                                            /*if (!(x == -2147483648)) {
                                                click(x, y, bot);
                                            } else {
                                                System.out.println("hmmm2");
                                            }*/




                                        } catch (NumberFormatException nfe) {
                                            //nfe.printStackTrace();
                                            int finalLengthDifference3 = lengthDifference;
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": loop arg must be int");
                                                    //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference3, lengthDifferenceSingleLine);
                                                }
                                            });

                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
                                            break;
                                        }

                                    }
                                    break;
                                //end a loop
                                case "endloop":
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Script halted");
                                            }
                                        });
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }
                                    scriptIsEmpty = false;
                                    //check if it has an int arg i.e. loop 5
                                    if (scriptLine.length != 1) {
                                        int finalLengthDifference2 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid arg for end");
                                                //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference2, lengthDifferenceSingleLine);
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    } else {
                                        //if there are any more times to loop, it will go back to the top and run the loop block code again
                                        if (timesToLoop[currentLoopLevel] > 1) {
                                            j = startOfLoopLineNunber[currentLoopLevel]; //go back to top of the loop
                                            timesToLoop[currentLoopLevel] -= 1; //one more loop has been done
                                        } else {
                                            //no more times to loop, therefore go back to the previous loop level
                                            currentLoopLevel--;
                                        }
                                    }
                                    break;
                                case "move":
                                    //move 500 500
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
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
                                        textArea.setEditable(true);
                                        break;
                                    } else {
                                        try {
                                            int x = -2147483648;
                                            int y = -2147483648;
                                            boolean varError = false;
                                            if (scriptLine[1].startsWith("$") && scriptLine[1].length() > 1) {
                                                if (variables.get(scriptLine[1]) == null) {
                                                    varError = true;
                                                } else {
                                                    x = variables.get(scriptLine[1]).getIntValue();
                                                }

                                            } else {
                                                x = Integer.parseInt(scriptLine[1]);
                                            }

                                            if (scriptLine[2].startsWith("$") && scriptLine[2].length() > 1) {
                                                if (variables.get(scriptLine[2]) == null) {
                                                    varError = true;
                                                } else {
                                                    y = variables.get(scriptLine[2]).getIntValue();
                                                }
                                            } else {
                                                y = Integer.parseInt(scriptLine[2]);
                                            }
                                            if (varError) {
                                                int finalLengthDifference1 = lengthDifference;
                                                Platform.runLater(new Runnable(){
                                                    @Override public void run() {
                                                        loadingLabel.setText("Macro error on line " + lineNumber + ": move args must be ints or int vars");
                                                        //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                                    }
                                                });
                                                scriptingError = true;
                                                runMacroButton.setDisable(false);
                                                textArea.setEditable(true);
                                                return;
                                            }
                                            //for (int k = 0; k < timesToLoop; k++) {
                                            if (!(x == -2147483648 || y == -2147483648)) {
                                                move(x, y, bot);
                                            } else {
                                                System.out.println("hmmm");
                                            }

                                                if (scriptHalter.isUserWantsToHaltScript()) {
                                                    Platform.runLater(new Runnable(){
                                                        @Override public void run() {
                                                            loadingLabel.setText("Script halted");
                                                        }
                                                    });
                                                    runMacroButton.setDisable(false);
                                                    textArea.setEditable(true);
                                                    break;
                                                }
                                            //}
                                            //parse number after wait, i.e. move 400 400
                                        } catch (NumberFormatException nfe) {
                                            nfe.printStackTrace();
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": move args must be ints or int vars");
                                                }
                                            });

                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
                                            break;
                                        } catch (AWTException awtE) {
                                            awtE.printStackTrace();
                                        }
                                    }
                                    break;
                                case "moverandom":
                                    //moverandom 400 425 600 625
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }
                                    scriptIsEmpty = false;
                                    if (scriptLine.length != 5) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid arg for moverandom");
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    } else {
                                        try {
                                            int minX = -2147483648;
                                            int maxX = -2147483648;
                                            int minY = -2147483648;
                                            int maxY = -2147483648;
                                            boolean varError = false;
                                            if (scriptLine[1].startsWith("$") && scriptLine[1].length() > 1) {
                                                if (variables.get(scriptLine[1]) == null) {
                                                    varError = true;
                                                } else {
                                                    minX = variables.get(scriptLine[1]).getIntValue();
                                                }

                                            } else {
                                                minX = Integer.parseInt(scriptLine[1]);
                                            }

                                            if (scriptLine[2].startsWith("$") && scriptLine[2].length() > 1) {
                                                if (variables.get(scriptLine[2]) == null) {
                                                    varError = true;
                                                } else {
                                                    maxX = variables.get(scriptLine[2]).getIntValue();
                                                }
                                            } else {
                                                maxX = Integer.parseInt(scriptLine[2]);
                                            }

                                            if (scriptLine[3].startsWith("$") && scriptLine[3].length() > 1) {
                                                if (variables.get(scriptLine[3]) == null) {
                                                    varError = true;
                                                } else {
                                                    minY = variables.get(scriptLine[3]).getIntValue();
                                                }
                                            } else {
                                                minY = Integer.parseInt(scriptLine[3]);
                                            }

                                            if (scriptLine[4].startsWith("$") && scriptLine[4].length() > 1) {
                                                if (variables.get(scriptLine[4]) == null) {
                                                    varError = true;
                                                } else {
                                                    maxY = variables.get(scriptLine[4]).getIntValue();
                                                }
                                            } else {
                                                maxY = Integer.parseInt(scriptLine[4]);
                                            }

                                            if (varError) {
                                                int finalLengthDifference1 = lengthDifference;
                                                Platform.runLater(new Runnable(){
                                                    @Override public void run() {
                                                        loadingLabel.setText("Macro error on line " + lineNumber + ": moverandom args must be ints or int vars");
                                                        //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                                    }
                                                });
                                                scriptingError = true;
                                                runMacroButton.setDisable(false);
                                                textArea.setEditable(true);
                                                return;
                                            }
                                            //for (int k = 0; k < timesToLoop; k++) {
                                            if (!(minX == -2147483648 || maxX == -2147483648 || minY == -2147483648 || maxY == -2147483648)) {
                                                moveRandom(minX, maxX, minY, maxY, bot);
                                            } else {
                                                System.out.println("hmmm");
                                            }
                                            //}
                                            //parse number after wait, i.e. move 400 400
                                        } catch (NumberFormatException nfe) {
                                            nfe.printStackTrace();
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": moverandom args must be ints");
                                                }
                                            });

                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
                                            break;
                                        } catch (AWTException awtE) {
                                            awtE.printStackTrace();
                                        }
                                    }
                                    break;
                                case "wait":
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
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
                                        textArea.setEditable(true);
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
                                            //for (int k = 0; k < timesToLoop; k++) {
                                                //int waitDuration; // = Integer.parseInt(scriptLine[1]);

                                            int waitDuration = -2147483648;
                                            boolean varError = false;
                                            if (scriptLine[1].startsWith("$") && scriptLine[1].length() > 1) {
                                                if (variables.get(scriptLine[1]) == null) {
                                                    varError = true;
                                                } else {
                                                    waitDuration = variables.get(scriptLine[1]).getIntValue();
                                                }

                                            } else {
                                                waitDuration = Integer.parseInt(scriptLine[1]);
                                            }

                                            if (varError) {
                                                int finalLengthDifference1 = lengthDifference;
                                                Platform.runLater(new Runnable(){
                                                    @Override public void run() {
                                                        loadingLabel.setText("Macro error on line " + lineNumber + ": move args must be ints or int vars");
                                                        //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                                    }
                                                });
                                                scriptingError = true;
                                                runMacroButton.setDisable(false);
                                                textArea.setEditable(true);
                                                return;
                                            }
                                            if (!(waitDuration == -2147483648)) {

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
                                                        textArea.setEditable(true);
                                                        Thread.currentThread().interrupt();

                                                        return; //end the thread

                                                    }
                                                }
                                                if (scriptHalter.isUserWantsToHaltScript()) {
                                                    Platform.runLater(new Runnable(){
                                                        @Override public void run() {
                                                            loadingLabel.setText("Script halted");
                                                        }
                                                    });
                                                    runMacroButton.setDisable(false);
                                                    textArea.setEditable(true);
                                                    break;
                                                }

                                            } else {
                                                System.out.println("hmmm wait");
                                            }

                                            if (scriptHalter.isUserWantsToHaltScript()) {
                                                Platform.runLater(new Runnable(){
                                                    @Override public void run() {
                                                        loadingLabel.setText("Script halted");
                                                    }
                                                });
                                                runMacroButton.setDisable(false);
                                                textArea.setEditable(true);
                                                break;
                                            }

                                            //}

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
                                            textArea.setEditable(true);
                                            break;
                                        }

                                    }
                                    break;
                                case "clickrandom":
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }
                                    scriptIsEmpty = false;
                                    //System.out.println("you want to click on line " + j);
                                    //now need to check if it has proper int args i.e. click 400 500
                                    if (scriptLine.length != 5) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for clickrandom");
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    } else {
                                        try {
                                            int minX = -2147483648;
                                            int maxX = -2147483648;
                                            int minY = -2147483648;
                                            int maxY = -2147483648;
                                            boolean varError = false;
                                            if (scriptLine[1].startsWith("$") && scriptLine[1].length() > 1) {
                                                if (variables.get(scriptLine[1]) == null) {
                                                    varError = true;
                                                } else {
                                                    minX = variables.get(scriptLine[1]).getIntValue();
                                                }

                                            } else {
                                                minX = Integer.parseInt(scriptLine[1]);
                                            }

                                            if (scriptLine[2].startsWith("$") && scriptLine[2].length() > 1) {
                                                if (variables.get(scriptLine[2]) == null) {
                                                    varError = true;
                                                } else {
                                                    maxX = variables.get(scriptLine[2]).getIntValue();
                                                }
                                            } else {
                                                maxX = Integer.parseInt(scriptLine[2]);
                                            }

                                            if (scriptLine[3].startsWith("$") && scriptLine[3].length() > 1) {
                                                if (variables.get(scriptLine[3]) == null) {
                                                    varError = true;
                                                } else {
                                                    minY = variables.get(scriptLine[3]).getIntValue();
                                                }
                                            } else {
                                                minY = Integer.parseInt(scriptLine[3]);
                                            }

                                            if (scriptLine[4].startsWith("$") && scriptLine[4].length() > 1) {
                                                if (variables.get(scriptLine[4]) == null) {
                                                    varError = true;
                                                } else {
                                                    maxY = variables.get(scriptLine[4]).getIntValue();
                                                }
                                            } else {
                                                maxY = Integer.parseInt(scriptLine[4]);
                                            }

                                            if (varError) {
                                                int finalLengthDifference1 = lengthDifference;
                                                Platform.runLater(new Runnable(){
                                                    @Override public void run() {
                                                        loadingLabel.setText("Macro error on line " + lineNumber + ": clickrandom args must be ints or int vars");
                                                        //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                                    }
                                                });
                                                scriptingError = true;
                                                runMacroButton.setDisable(false);
                                                textArea.setEditable(true);
                                                return;
                                            }
                                            //for (int k = 0; k < timesToLoop; k++) {
                                            if (!(minX == -2147483648 || maxX == -2147483648 || minY == -2147483648 || maxY == -2147483648)) {
                                                clickRandom(minX, maxX, minY, maxY, bot);
                                            } else {
                                                System.out.println("hmmm");
                                            }
                                            //}

                                        } catch (NumberFormatException numException) {
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": clickrandom args must be ints");
                                                }
                                            });

                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
                                            break;
                                        } catch (AWTException awtE) {
                                            awtE.printStackTrace();
                                        }

                                    }
                                    break;
                                case "releasemiddleclick":
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }
                                    scriptIsEmpty = false;
                                    //System.out.println("you want to click on line " + j);
                                    //now need to check if it has proper int args i.e. click 400 500
                                    if (scriptLine.length != 1) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for releasemiddleclick");
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    } else {
                                        try {
                                            releaseMiddleClick(bot);
                                            if (scriptHalter.isUserWantsToHaltScript()) {
                                                Platform.runLater(new Runnable(){
                                                    @Override public void run() {
                                                        loadingLabel.setText("Script halted");
                                                    }
                                                });
                                                runMacroButton.setDisable(false);
                                                textArea.setEditable(true);
                                                break;
                                            }
                                            //}

                                        } catch (AWTException awtE) {
                                            awtE.printStackTrace();
                                        }

                                    }
                                    break;
                                case "releaserightclick":
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }
                                    scriptIsEmpty = false;
                                    //System.out.println("you want to click on line " + j);
                                    //now need to check if it has proper int args i.e. click 400 500
                                    if (scriptLine.length != 1) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for releaserightclick");
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    } else {
                                        try {
                                            releaseRightClick(bot);
                                            if (scriptHalter.isUserWantsToHaltScript()) {
                                                Platform.runLater(new Runnable(){
                                                    @Override public void run() {
                                                        loadingLabel.setText("Script halted");
                                                    }
                                                });
                                                runMacroButton.setDisable(false);
                                                textArea.setEditable(true);
                                                break;
                                            }
                                            //}

                                        } catch (AWTException awtE) {
                                            awtE.printStackTrace();
                                        }

                                    }
                                    break;
                                case "releaseclick":
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }
                                    scriptIsEmpty = false;
                                    //System.out.println("you want to click on line " + j);
                                    //now need to check if it has proper int args i.e. click 400 500
                                    if (scriptLine.length != 1) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for releaseclick");
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    } else {
                                        try {
                                            releaseClick(bot);
                                            if (scriptHalter.isUserWantsToHaltScript()) {
                                                Platform.runLater(new Runnable(){
                                                    @Override public void run() {
                                                        loadingLabel.setText("Script halted");
                                                    }
                                                });
                                                runMacroButton.setDisable(false);
                                                textArea.setEditable(true);
                                                break;
                                            }
                                            //}

                                        } catch (AWTException awtE) {
                                            awtE.printStackTrace();
                                        }

                                    }
                                    break;
                                case "middleclickandhold":
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }
                                    scriptIsEmpty = false;
                                    //System.out.println("you want to click on line " + j);
                                    //now need to check if it has proper int args i.e. click 400 500
                                    if (scriptLine.length != 3) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for middleclickandhold");
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    } else {
                                        try {
                                            int x = -2147483648;
                                            int y = -2147483648;
                                            boolean varError = false;
                                            if (scriptLine[1].startsWith("$") && scriptLine[1].length() > 1) {
                                                if (variables.get(scriptLine[1]) == null) {
                                                    varError = true;
                                                } else {
                                                    x = variables.get(scriptLine[1]).getIntValue();
                                                }

                                            } else {
                                                x = Integer.parseInt(scriptLine[1]);
                                            }

                                            if (scriptLine[2].startsWith("$") && scriptLine[2].length() > 1) {
                                                if (variables.get(scriptLine[2]) == null) {
                                                    varError = true;
                                                } else {
                                                    y = variables.get(scriptLine[2]).getIntValue();
                                                }
                                            } else {
                                                y = Integer.parseInt(scriptLine[2]);
                                            }
                                            if (varError) {
                                                int finalLengthDifference1 = lengthDifference;
                                                Platform.runLater(new Runnable(){
                                                    @Override public void run() {
                                                        loadingLabel.setText("Macro error on line " + lineNumber + ": middleclickandhold args must be ints or int vars");
                                                        //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                                    }
                                                });
                                                scriptingError = true;
                                                runMacroButton.setDisable(false);
                                                textArea.setEditable(true);
                                                return;
                                            }
                                            //for (int k = 0; k < timesToLoop; k++) {
                                            if (!(x == -2147483648 || y == -2147483648)) {
                                                middleClickAndHold(x, y, bot);
                                            } else {
                                                System.out.println("hmmm2");
                                            }

                                        } catch (NumberFormatException numException) {
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": middleclickandhold args must be ints");
                                                }
                                            });

                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
                                            break;
                                        } catch (AWTException awtE) {
                                            awtE.printStackTrace();
                                        }

                                    }
                                    break;
                                case "rightclickandhold":
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }
                                    scriptIsEmpty = false;
                                    //System.out.println("you want to click on line " + j);
                                    //now need to check if it has proper int args i.e. click 400 500
                                    if (scriptLine.length != 3) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for rightclickandhold");
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    } else {
                                        try {
                                            int x = -2147483648;
                                            int y = -2147483648;
                                            boolean varError = false;
                                            if (scriptLine[1].startsWith("$") && scriptLine[1].length() > 1) {
                                                if (variables.get(scriptLine[1]) == null) {
                                                    varError = true;
                                                } else {
                                                    x = variables.get(scriptLine[1]).getIntValue();
                                                }

                                            } else {
                                                x = Integer.parseInt(scriptLine[1]);
                                            }

                                            if (scriptLine[2].startsWith("$") && scriptLine[2].length() > 1) {
                                                if (variables.get(scriptLine[2]) == null) {
                                                    varError = true;
                                                } else {
                                                    y = variables.get(scriptLine[2]).getIntValue();
                                                }
                                            } else {
                                                y = Integer.parseInt(scriptLine[2]);
                                            }
                                            if (varError) {
                                                int finalLengthDifference1 = lengthDifference;
                                                Platform.runLater(new Runnable(){
                                                    @Override public void run() {
                                                        loadingLabel.setText("Macro error on line " + lineNumber + ": rightclickandhold args must be ints or int vars");
                                                        //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                                    }
                                                });
                                                scriptingError = true;
                                                runMacroButton.setDisable(false);
                                                textArea.setEditable(true);
                                                return;
                                            }
                                            //for (int k = 0; k < timesToLoop; k++) {
                                            if (!(x == -2147483648 || y == -2147483648)) {
                                                rightClickAndHold(x, y, bot);
                                            } else {
                                                System.out.println("hmmm2");
                                            }

                                        } catch (NumberFormatException numException) {
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": rightclickandhold args must be ints");
                                                }
                                            });

                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
                                            break;
                                        } catch (AWTException awtE) {
                                            awtE.printStackTrace();
                                        }

                                    }
                                    break;
                                case "clickandhold":
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }
                                    scriptIsEmpty = false;
                                    //System.out.println("you want to click on line " + j);
                                    //now need to check if it has proper int args i.e. click 400 500
                                    if (scriptLine.length != 3) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for clickandhold");
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    } else {
                                        try {
                                            int x = -2147483648;
                                            int y = -2147483648;
                                            boolean varError = false;
                                            if (scriptLine[1].startsWith("$") && scriptLine[1].length() > 1) {
                                                if (variables.get(scriptLine[1]) == null) {
                                                    varError = true;
                                                } else {
                                                    x = variables.get(scriptLine[1]).getIntValue();
                                                }

                                            } else {
                                                x = Integer.parseInt(scriptLine[1]);
                                            }

                                            if (scriptLine[2].startsWith("$") && scriptLine[2].length() > 1) {
                                                if (variables.get(scriptLine[2]) == null) {
                                                    varError = true;
                                                } else {
                                                    y = variables.get(scriptLine[2]).getIntValue();
                                                }
                                            } else {
                                                y = Integer.parseInt(scriptLine[2]);
                                            }
                                            if (varError) {
                                                int finalLengthDifference1 = lengthDifference;
                                                Platform.runLater(new Runnable(){
                                                    @Override public void run() {
                                                        loadingLabel.setText("Macro error on line " + lineNumber + ": clickandhold args must be ints or int vars");
                                                        //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                                    }
                                                });
                                                scriptingError = true;
                                                runMacroButton.setDisable(false);
                                                textArea.setEditable(true);
                                                return;
                                            }
                                            //for (int k = 0; k < timesToLoop; k++) {
                                            if (!(x == -2147483648 || y == -2147483648)) {
                                                clickAndHold(x, y, bot);
                                            } else {
                                                System.out.println("hmmm2");
                                            }

                                        } catch (NumberFormatException numException) {
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": clickandhold args must be ints");
                                                }
                                            });

                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
                                            break;
                                        } catch (AWTException awtE) {
                                            awtE.printStackTrace();
                                        }

                                    }
                                    break;
                                case "print":
                                case "println":
                                    //System.out.println("print 2");
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Script halted");
                                            }
                                        });
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }
                                    int finalLengthDifference4 = lengthDifference;


                                    if (scriptLine.length < 1) {
                                        finalLengthDifference4 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for print");
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    } else {
                                        scriptIsEmpty = false;
                                        int numOfThingsToPrint = scriptLine.length - 1;
                                        String newTextAreaString = consoleTextArea.getText();
                                        for (int k = 0; k < numOfThingsToPrint; k++) {
                                            //System.out.println(scriptLine[k + 1]);
                                            String thingToPrint = scriptLine[k + 1];
                                            if (thingToPrint.startsWith("$")) {
                                                if (variables.get(thingToPrint) != null) {
                                                    thingToPrint = variables.get(thingToPrint).getValue();
                                                }
                                            }
                                            newTextAreaString += thingToPrint + " ";


                                        }
                                        if (scriptLine[0].equalsIgnoreCase("println")) {
                                            newTextAreaString += "\n";

                                        }

                                        //count the number of new lines

                                        String findStr = "\n";
                                        int lastIndex = 0;
                                        int lineCount = 0;
                                        while (lastIndex != -1) {
                                            lastIndex = newTextAreaString.indexOf(findStr, lastIndex);
                                            if (lastIndex != -1) {
                                                lineCount++;
                                                lastIndex += findStr.length();
                                            }
                                        }
                                        System.out.println("number of lines in the console: " + lineCount);
                                        System.out.println("newTextAreaString.length(): " + newTextAreaString.length());

                                        //todo: change back to 5000
                                        if (lineCount > 5000) {
                                            int linesToRemove = lineCount - 5000; //todo: change back to 5000
                                            System.out.println("linesToRemove: " + linesToRemove);
                                            for (int k = 0; k < linesToRemove; k++) {
                                                //+2 because \n is two characters, and you want to remove that too
                                                int placeOfFirstLineToRemove = newTextAreaString.indexOf("\n");
                                                newTextAreaString = newTextAreaString.substring(placeOfFirstLineToRemove + 1);
                                            }
                                            consoleTextArea.appendText("");
                                        }
                                        //todo: change back to 120000
                                        if (newTextAreaString.length() > 120000) {
                                            System.out.println("too long, need to trim");                           //todo: change back to 120000
                                            newTextAreaString = newTextAreaString.substring(newTextAreaString.length() - 120000, newTextAreaString.length() - 1);
                                            if (scriptLine[1].equalsIgnoreCase("println")) {
                                                newTextAreaString += "\n";
                                            }
                                            consoleTextArea.appendText("");
                                        }


                                        try {
                                            consoleTextArea.setText(newTextAreaString);
                                            consoleTextArea.appendText("");
                                            consoleTextArea.setScrollTop(Double.MAX_VALUE);
                                            consoleTextArea.setScrollLeft(Double.MAX_VALUE);
                                            Thread.sleep(50); //could go as low as 25ms but want to make sure it works even on slower processors
                                            //consoleTextArea.appendText("");

                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                    break;
                                case "update":
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Script halted");
                                            }
                                        });
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }
                                    scriptIsEmpty = false;
                                    System.out.println("todo: update $x += 5 etc");
                                    if (scriptLine.length != 4) {
                                        int finalLengthDifference5 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for update");
                                                //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    } else {
                                        System.out.println("got here else update 1");
                                        //check args for either literal values or variable references
                                        //0 = update
                                        //1 = var to be updated, i.e. $x (operand 1)
                                        //2 = operator, such as += or -= etc
                                        //3 = operand 2

                                        for (int k = 0; k < scriptLine.length; k++) {
                                            System.out.println("scriptLine[" + k + "]: " + scriptLine[k]);
                                        }

                                        Variable varForOp1 = variables.get(scriptLine[1]);


                                        if (scriptLine[1].startsWith("$") && varForOp1 != null && varForOp1.getType() != null) {
                                            System.out.println("got here 2 starts with $");


                                            switch (variables.get(scriptLine[1]).getType()) {
                                                case INT:

                                                    //check if it's in the variables hashmap
                                                    if (varForOp1 != null) {
                                                        //all good
                                                        int result = -2147483648;
                                                        int operand1 = variables.get(scriptLine[1]).getIntValue();
                                                        Variable varForOp2 = null;
                                                        int operand2 = -1; //-1 means uninitialized

                                                        if (scriptLine[3].startsWith("$")) {
                                                            //check if operand 2 is in the variables hashmap
                                                            //and it also needs to be an int
                                                            varForOp2 = variables.get(scriptLine[3]);
                                                            if (varForOp2 != null && varForOp2.getType() == Variable.Type.INT) {
                                                                operand2 = varForOp2.getIntValue();
                                                            } else {
                                                                int finalLengthDifference5 = lengthDifference;
                                                                Platform.runLater(new Runnable(){
                                                                    @Override public void run() {
                                                                        loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for update");
                                                                        //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                                                    }
                                                                });

                                                                scriptingError = true;
                                                                runMacroButton.setDisable(false);
                                                                textArea.setEditable(true);
                                                                break;
                                                            }
                                                        } else {
                                                            try {
                                                                operand2 = Integer.parseInt(scriptLine[3]);
                                                            } catch (NumberFormatException e) {
                                                                int finalLengthDifference5 = lengthDifference;
                                                                Platform.runLater(new Runnable(){
                                                                    @Override public void run() {
                                                                        loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for update");
                                                                        //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                                                    }
                                                                });

                                                                scriptingError = true;
                                                                runMacroButton.setDisable(false);
                                                                textArea.setEditable(true);
                                                                break;
                                                            }
                                                        }
                                                        boolean shouldUpdate = false;
                                                        switch (scriptLine[2]) {
                                                            case "+=":
                                                                result = operand1 + operand2;
                                                                shouldUpdate = true;
                                                                break;
                                                            case "-=":
                                                                result = operand1 - operand2;
                                                                shouldUpdate = true;
                                                                break;
                                                            case "*=":
                                                                result = operand1 * operand2;
                                                                shouldUpdate = true;
                                                                break;
                                                            case "/=":
                                                                if (!scriptLine[2].equalsIgnoreCase("0")) {
                                                                    result = operand1 / operand2;
                                                                } else {
                                                                    //can't divide by zero
                                                                    int finalLengthDifference5 = lengthDifference;
                                                                    Platform.runLater(new Runnable(){
                                                                        @Override public void run() {
                                                                            loadingLabel.setText("Macro error on line " + lineNumber + ": can't divide by zero");
                                                                            //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                                                        }
                                                                    });

                                                                    scriptingError = true;
                                                                    runMacroButton.setDisable(false);
                                                                    textArea.setEditable(true);
                                                                    break;
                                                                }
                                                                break;
                                                            case "^=":
                                                                result = (int) Math.pow(operand1, operand2);
                                                                shouldUpdate = true;
                                                                break;
                                                            case "%=":
                                                                result = operand1 % operand2;
                                                                shouldUpdate = true;
                                                                break;
                                                            case "=":
                                                                result = operand2;
                                                                shouldUpdate = true;
                                                                break;
                                                            default:
                                                                int finalLengthDifference5 = lengthDifference;
                                                                Platform.runLater(new Runnable(){
                                                                    @Override public void run() {
                                                                        loadingLabel.setText("Macro error on line " + lineNumber + ": invalid arithmetic operator");
                                                                        //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                                                    }
                                                                });

                                                                scriptingError = true;
                                                                runMacroButton.setDisable(false);
                                                                textArea.setEditable(true);
                                                                break;
                                                        }
                                                        System.out.println("result: " + result);
                                                        if (shouldUpdate) {
                                                            Variable newVar = new Variable();
                                                            newVar.setType(Variable.Type.INT);
                                                            newVar.setIntValue(result);
                                                            variables.put(scriptLine[1], newVar);
                                                        }

                                                    } else {
                                                        //todo: throw error
                                                        int finalLengthDifference5 = lengthDifference;
                                                        Platform.runLater(new Runnable(){
                                                            @Override public void run() {
                                                                loadingLabel.setText("Macro error on line " + lineNumber + ": referenced non-existent variable");
                                                                //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                                            }
                                                        });

                                                        scriptingError = true;
                                                        runMacroButton.setDisable(false);
                                                        textArea.setEditable(true);
                                                        break;
                                                    }



                                                    break;
                                                case STR:
                                                    System.out.println("not implemented yet");
                                                default:
                                                    System.out.println("not supposed to get here");
                                                    break;
                                            }



                                        } else {
                                            //todo: throw error
                                            int finalLengthDifference5 = lengthDifference;
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for update");
                                                    //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                                }
                                            });

                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
                                            break;
                                        }
                                    }
                                    break;
                                //int $i = 0
                                //this means make a new one, not update an existing one
                                //"update" will be separate, and will have different things like + - / etc.
                                case "clear":
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Script halted");
                                            }
                                        });
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }
                                    scriptIsEmpty = false;
                                    if (scriptLine.length != 1) {
                                        int finalLengthDifference7 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for clear");
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    } else {
                                        consoleTextArea.setText("\n\n\nAutoInput Script Console\n");
                                    }
                                    break;
                                case "unminimize":
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Script halted");
                                            }
                                        });
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }
                                    scriptIsEmpty = false;
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            primaryStage.setIconified(false);
                                        }
                                    });
                                    break;
                                case "minimize":
                                    System.out.println("minimize");
                                    primaryStage.setIconified(true);
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Script halted");
                                            }
                                        });
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }
                                    scriptIsEmpty = false;
                                    break;
                                case "destroy":
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Script halted");
                                            }
                                        });
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }
                                    scriptIsEmpty = false;
                                    if (scriptLine.length == 2) {
                                        if (scriptLine[1].startsWith("$")) {
                                            if (variables.get(scriptLine[1]) != null) {
                                                //destroy it
                                                variables.remove(scriptLine[1]);
                                            } else {
                                                int finalLengthDifference5 = lengthDifference;
                                                Platform.runLater(new Runnable(){
                                                    @Override public void run() {
                                                        loadingLabel.setText("Macro error on line " + lineNumber + ": can't destroy a non-existent variable");
                                                    }
                                                });

                                                scriptingError = true;
                                                runMacroButton.setDisable(false);
                                                textArea.setEditable(true);
                                            }
                                        } else {
                                            int finalLengthDifference5 = lengthDifference;
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for destroy");
                                                }
                                            });

                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
                                        }
                                    } else {
                                        int finalLengthDifference5 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for destroy");
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                    }
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Script halted");
                                            }
                                        });
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }
                                    break;
                                case "random":
                                    System.out.println("got here random");
                                    //random int -- example:  random int $x = 100 500
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Script halted");
                                            }
                                        });
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }
                                    scriptIsEmpty = false;
                                    //System.out.println("you want to click on line " + j);
                                    //now need to check if it has proper int args i.e. click 400 500
                                    if (scriptLine.length != 6) {
                                        int finalLengthDifference5 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for random");
                                            }
                                        });
                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    } else {
                                        System.out.println("syntax checking for random int, not actually running it (this is part 1 of 2)");
                                        System.out.println("got here proper number of args");
                                        switch (scriptLine[1]) {
                                            case "int":
                                            case "update":
                                                System.out.println("got here int");
                                                //random int $x = 200 300
                                                //or random int $x = $y $z
                                                if (scriptLine[2].startsWith("$")) {
                                                    System.out.println("2 starts with $, good");
                                                    Variable varToSeeIfExists = variables.get(scriptLine[2]);
                                                    if ((scriptLine[1].equalsIgnoreCase("int") && varToSeeIfExists == null) || (scriptLine[1].equalsIgnoreCase("update") && varToSeeIfExists != null)) {
                                                        //either new (int) or existing (update)
                                                        if ((scriptLine[1].equalsIgnoreCase("int") && scriptLine[3].equalsIgnoreCase("=")) ||
                                                                (scriptLine[1].equalsIgnoreCase("update")
                                                                        && Arrays.asList(new String[]{"+=", "-=", "*=", "/=", "^=", "%=", "="}).contains(scriptLine[3]))) {
                                                            //check if 4 and 5 are ints or variables
                                                            int lowerBound = -2147483648;
                                                            int upperBound = -2147483648;
                                                            boolean parseError = false;
                                                            if (scriptLine[4].startsWith("$")) {
                                                                Variable var4 = variables.get(scriptLine[4]);
                                                                if (var4.getType() == Variable.Type.INT) {
                                                                    lowerBound = var4.getIntValue();
                                                                } else {
                                                                    int finalLengthDifference5 = lengthDifference;
                                                                    Platform.runLater(new Runnable(){
                                                                        @Override public void run() {
                                                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for random");
                                                                        }
                                                                    });
                                                                    scriptingError = true;
                                                                    runMacroButton.setDisable(false);
                                                                    textArea.setEditable(true);
                                                                    break;
                                                                }
                                                            } else {
                                                                //try parseInt for var4, throw numexception if failed
                                                                try {
                                                                    lowerBound = Integer.parseInt(scriptLine[4]);
                                                                } catch (NumberFormatException ne) {
                                                                    //throw error, invalid args, not a number
                                                                    int finalLengthDifference5 = lengthDifference;
                                                                    Platform.runLater(new Runnable(){
                                                                        @Override public void run() {
                                                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for random");
                                                                        }
                                                                    });
                                                                    scriptingError = true;
                                                                    runMacroButton.setDisable(false);
                                                                    textArea.setEditable(true);
                                                                    break;
                                                                }
                                                            }

                                                            if (scriptLine[5].startsWith("$")) {
                                                                Variable var5 = variables.get(scriptLine[5]);
                                                                if (var5.getType() == Variable.Type.INT) {
                                                                    upperBound = var5.getIntValue();
                                                                }
                                                            } else {
                                                                //try parseInt for var5, throw numexception if failed
                                                                try {
                                                                    upperBound = Integer.parseInt(scriptLine[5]);
                                                                } catch (NumberFormatException ne) {
                                                                    //throw error, invalid args, not a number
                                                                    int finalLengthDifference5 = lengthDifference;
                                                                    Platform.runLater(new Runnable(){
                                                                        @Override public void run() {
                                                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for random");
                                                                        }
                                                                    });
                                                                    scriptingError = true;
                                                                    runMacroButton.setDisable(false);
                                                                    textArea.setEditable(true);
                                                                    break;
                                                                }
                                                            }

                                                            if (!parseError) {
                                                                System.out.println("lowerBound: " + lowerBound);
                                                                System.out.println("upperBound: " + upperBound);
                                                                int newRandomValue = getRandomNumber(lowerBound, upperBound);
                                                                Variable newRandomVar = new Variable();
                                                                newRandomVar.setType(Variable.Type.INT);


                                                                boolean shouldUpdate = false;
                                                                int existingVarToUpdate = -2147483648;
                                                                if (scriptLine[1].equalsIgnoreCase("int") &&
                                                                        scriptLine[3].equalsIgnoreCase("=")) {
                                                                    //if int:
                                                                    System.out.println("got here int 3322");
                                                                    //keep it the same as the random number
                                                                    existingVarToUpdate = newRandomValue;
                                                                    shouldUpdate = true;
                                                                } else if (scriptLine[1].equalsIgnoreCase("update") &&
                                                                        Arrays.asList(new String[]{"+=", "-=", "*=", "/=", "^=", "%=", "="}).contains(scriptLine[3])) {
                                                                    //else if update:
                                                                    System.out.println("got here update 3322");
                                                                    //if it's random update $x = 100 200, then [2] is $x
                                                                    Variable fromVariable = variables.get(scriptLine[2]);

                                                                    if (fromVariable != null) {
                                                                        existingVarToUpdate = fromVariable.getIntValue();
                                                                    } else {
                                                                        //todo: throw parse error
                                                                    }

                                                                    switch (scriptLine[3]) {
                                                                        case "+=":
                                                                            existingVarToUpdate += newRandomValue;
                                                                            shouldUpdate = true;
                                                                            break;
                                                                        case "-=":
                                                                            existingVarToUpdate -= newRandomValue;
                                                                            shouldUpdate = true;
                                                                            break;
                                                                        case "*=":
                                                                            existingVarToUpdate *= newRandomValue;
                                                                            shouldUpdate = true;
                                                                            break;
                                                                        case "/=":
                                                                            if (!(newRandomValue == 0)) {
                                                                                existingVarToUpdate /= newRandomValue;
                                                                                shouldUpdate = true;
                                                                            } else {
                                                                                //can't divide by zero
                                                                                int finalLengthDifference5 = lengthDifference;
                                                                                Platform.runLater(new Runnable(){
                                                                                    @Override public void run() {
                                                                                        loadingLabel.setText("Macro error on line " + lineNumber + ": can't divide by zero");
                                                                                        //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference5, lengthDifferenceSingleLine);
                                                                                    }
                                                                                });

                                                                                scriptingError = true;
                                                                                runMacroButton.setDisable(false);
                                                                                textArea.setEditable(true);
                                                                                break;
                                                                            }
                                                                            break;
                                                                        case "^=":
                                                                            existingVarToUpdate = (int) Math.pow(existingVarToUpdate, newRandomValue);
                                                                            shouldUpdate = true;
                                                                            break;
                                                                        case "%=":
                                                                            existingVarToUpdate %= newRandomValue;
                                                                            shouldUpdate = true;
                                                                            break;
                                                                        case "=":
                                                                            existingVarToUpdate = newRandomValue;
                                                                            shouldUpdate = true;
                                                                            break;
                                                                        default:
                                                                            System.out.println("default in switch/case for for random update int $x = 100 200");
                                                                            break;
                                                                    }
                                                                } else {
                                                                    System.out.println("not supposed to get here");
                                                                }
                                                                System.out.println(scriptLine[2] + ": " + existingVarToUpdate);
                                                                if (shouldUpdate) {
                                                                    System.out.println("got here shouldUpdate 121233");
                                                                    newRandomVar.setIntValue(existingVarToUpdate);
                                                                    variables.put(scriptLine[2], newRandomVar);
                                                                }
                                                            }


                                                        } else {
                                                            int finalLengthDifference5 = lengthDifference;
                                                            Platform.runLater(new Runnable(){
                                                                @Override public void run() {
                                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for random");
                                                                }
                                                            });
                                                            scriptingError = true;
                                                            runMacroButton.setDisable(false);
                                                            textArea.setEditable(true);
                                                            break;
                                                        }

                                                    } else {
                                                        int finalLengthDifference5 = lengthDifference;
                                                        Platform.runLater(new Runnable(){
                                                            @Override public void run() {
                                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for random");
                                                            }
                                                        });
                                                        scriptingError = true;
                                                        runMacroButton.setDisable(false);
                                                        textArea.setEditable(true);
                                                        break;
                                                    }

                                                } else {
                                                    int finalLengthDifference5 = lengthDifference;
                                                    Platform.runLater(new Runnable(){
                                                        @Override public void run() {
                                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for random");
                                                        }
                                                    });
                                                    scriptingError = true;
                                                    runMacroButton.setDisable(false);
                                                    textArea.setEditable(true);
                                                    break;
                                                }
                                                break;
                                            case "str":
                                                System.out.println("not implemented yet");
                                                break;
                                            default:
                                                System.out.println("not supposed to get here");
                                                //todo: throw error
                                                break;
                                        }
                                    }
                                    break;
                                case "int":
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Script halted");
                                            }
                                        });
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }
                                    scriptIsEmpty = false;
                                    //System.out.println("you want to click on line " + j);
                                    //now need to check if it has proper int args i.e. click 400 500
                                    if (scriptLine.length != 4) {
                                        int finalLengthDifference7 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for int declaration");
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    } else {
                                        if ( (!scriptLine[2].equalsIgnoreCase("=")) ||
                                                ((!scriptLine[1].startsWith("$"))) ||
                                                ((!(scriptLine[1].length() > 1)))
                                        ) {
                                            int finalLengthDifference5 = lengthDifference;
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": invalid new int args3");
                                                }
                                            });
                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
                                            break;
                                        } else {
                                            System.out.println("got here 2 good");
                                        }
                                        try {

                                            System.out.println("Try 2");
                                            int intValue = -2147483648;
                                            boolean varError = false;

                                            if (scriptLine[3].startsWith("$") && scriptLine[3].length() > 1) {

                                                if (variables.get(scriptLine[3]) == null) {
                                                    varError = true;
                                                } else {
                                                    intValue = variables.get(scriptLine[3]).getIntValue();
                                                }
                                            } else {
                                                intValue = Integer.parseInt(scriptLine[3]);
                                            }

                                            if (!(intValue == -2147483648)) {
                                                variables = createNewInt(scriptLine[1], intValue, variables);
                                            } else {
                                                System.out.println("hmmm int111");
                                            }

                                            if (varError) {
                                                int finalLengthDifference1 = lengthDifference;
                                                Platform.runLater(new Runnable(){
                                                    @Override public void run() {
                                                        loadingLabel.setText("Macro error on line " + lineNumber + ": int args must be ints or int vars");
                                                        //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                                    }
                                                });
                                                scriptingError = true;
                                                runMacroButton.setDisable(false);
                                                textArea.setEditable(true);
                                                return;
                                            }
                                            /*
                                            int intValue = Integer.parseInt(scriptLine[3]);
                                            //setting an int
                                            // line:   int  $x  =  1
                                            // idx:    0    1   2  3
                                            //createNewInt(String name, int intValue, HashMap<String, Variable> variables)
                                            variables = createNewInt(scriptLine[1], intValue, variables);
                                            */

                                        } catch (NumberFormatException numException) {

                                            if (scriptLine[3].startsWith("$") && scriptLine[3].length() > 1) {
                                                System.out.println("made it here!!!!!!!!!!!!!!!!!!");
                                                //todo: implement setting a variable as a value for another variable (pass by value)
                                                // i.e. int $x2 = $y2
                                            } else {
                                                int finalLengthDifference5 = lengthDifference;
                                                Platform.runLater(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        loadingLabel.setText("Macro error on line " + lineNumber + ": invalid new int args4");
                                                    }
                                                });

                                                scriptingError = true;
                                                runMacroButton.setDisable(false);
                                                textArea.setEditable(true);
                                                break;
                                            }
                                        }
                                    }
                                    break;
                                case "click":
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
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
                                        textArea.setEditable(true);
                                        break;
                                    } else {
                                        try {
                                            //for (int k = 0; k < timesToLoop; k++) {
                                            int x = -2147483648;
                                            int y = -2147483648;
                                            boolean varError = false;
                                            if (scriptLine[1].startsWith("$") && scriptLine[1].length() > 1) {
                                                if (variables.get(scriptLine[1]) == null) {
                                                    varError = true;
                                                } else {
                                                    x = variables.get(scriptLine[1]).getIntValue();
                                                }

                                            } else {
                                                x = Integer.parseInt(scriptLine[1]);
                                            }

                                            if (scriptLine[2].startsWith("$") && scriptLine[2].length() > 1) {
                                                if (variables.get(scriptLine[2]) == null) {
                                                    varError = true;
                                                } else {
                                                    y = variables.get(scriptLine[2]).getIntValue();
                                                }
                                            } else {
                                                y = Integer.parseInt(scriptLine[2]);
                                            }
                                            if (varError) {
                                                int finalLengthDifference1 = lengthDifference;
                                                Platform.runLater(new Runnable(){
                                                    @Override public void run() {
                                                        loadingLabel.setText("Macro error on line " + lineNumber + ": click args must be ints or int vars");
                                                        //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                                    }
                                                });
                                                scriptingError = true;
                                                runMacroButton.setDisable(false);
                                                textArea.setEditable(true);
                                                return;
                                            }
                                            //for (int k = 0; k < timesToLoop; k++) {
                                            if (!(x == -2147483648 || y == -2147483648)) {
                                                click(x, y, bot);
                                            } else {
                                                System.out.println("hmmm2");
                                            }

                                        } catch (NumberFormatException numException) {
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": click args must be ints or int vars");
                                                }
                                            });

                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
                                            break;
                                        } catch (AWTException awtE) {
                                            awtE.printStackTrace();
                                        }

                                    }
                                    break;
                                case "middleclick":
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }
                                    scriptIsEmpty = false;
                                    //System.out.println("you want to middleclick on line " + j);
                                    //now need to check if it has proper int args i.e. middleclick 400 500
                                    if (scriptLine.length != 3) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for middleclick");
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    } else {
                                        try {
                                            //for (int k = 0; k < timesToLoop; k++) {
                                            int x = -2147483648;
                                            int y = -2147483648;
                                            boolean varError = false;
                                            if (scriptLine[1].startsWith("$") && scriptLine[1].length() > 1) {
                                                if (variables.get(scriptLine[1]) == null) {
                                                    varError = true;
                                                } else {
                                                    x = variables.get(scriptLine[1]).getIntValue();
                                                }

                                            } else {
                                                x = Integer.parseInt(scriptLine[1]);
                                            }

                                            if (scriptLine[2].startsWith("$") && scriptLine[2].length() > 1) {
                                                if (variables.get(scriptLine[2]) == null) {
                                                    varError = true;
                                                } else {
                                                    y = variables.get(scriptLine[2]).getIntValue();
                                                }
                                            } else {
                                                y = Integer.parseInt(scriptLine[2]);
                                            }
                                            if (varError) {
                                                int finalLengthDifference1 = lengthDifference;
                                                Platform.runLater(new Runnable(){
                                                    @Override public void run() {
                                                        loadingLabel.setText("Macro error on line " + lineNumber + ": middleclick args must be ints or int vars");
                                                        //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                                    }
                                                });
                                                scriptingError = true;
                                                runMacroButton.setDisable(false);
                                                textArea.setEditable(true);
                                                return;
                                            }
                                            //for (int k = 0; k < timesToLoop; k++) {
                                            if (!(x == -2147483648 || y == -2147483648)) {
                                                middleClick(x, y, bot);
                                            } else {
                                                System.out.println("hmmm2 middleclick");
                                            }
                                            //}

                                        } catch (NumberFormatException numException) {
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": middleclick args must be ints");
                                                }
                                            });

                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
                                            break;
                                        } catch (AWTException awtE) {
                                            awtE.printStackTrace();
                                        }

                                    }
                                    break;
                                case "rightclick":
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
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
                                        textArea.setEditable(true);
                                        break;
                                    } else {
                                        try {
                                            //for (int k = 0; k < timesToLoop; k++) {
                                            int x = -2147483648;
                                            int y = -2147483648;
                                            boolean varError = false;
                                            if (scriptLine[1].startsWith("$") && scriptLine[1].length() > 1) {
                                                if (variables.get(scriptLine[1]) == null) {
                                                    varError = true;
                                                } else {
                                                    x = variables.get(scriptLine[1]).getIntValue();
                                                }

                                            } else {
                                                x = Integer.parseInt(scriptLine[1]);
                                            }

                                            if (scriptLine[2].startsWith("$") && scriptLine[2].length() > 1) {
                                                if (variables.get(scriptLine[2]) == null) {
                                                    varError = true;
                                                } else {
                                                    y = variables.get(scriptLine[2]).getIntValue();
                                                }
                                            } else {
                                                y = Integer.parseInt(scriptLine[2]);
                                            }
                                            if (varError) {
                                                int finalLengthDifference1 = lengthDifference;
                                                Platform.runLater(new Runnable(){
                                                    @Override public void run() {
                                                        loadingLabel.setText("Macro error on line " + lineNumber + ": rightclick args must be ints or int vars");
                                                        //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                                    }
                                                });
                                                scriptingError = true;
                                                runMacroButton.setDisable(false);
                                                textArea.setEditable(true);
                                                return;
                                            }
                                            //for (int k = 0; k < timesToLoop; k++) {
                                            if (!(x == -2147483648 || y == -2147483648)) {
                                                rightClick(x, y, bot);
                                            } else {
                                                System.out.println("hmmm2");
                                            }
                                            //}

                                        } catch (NumberFormatException numException) {
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": rightclick args must be ints");
                                                }
                                            });

                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
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
                                        textArea.setEditable(true);
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
                                        textArea.setEditable(true);
                                        break;
                                    } else {
                                        try {


                                            //old
                                            /*
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
                                                if (scriptHalter.isUserWantsToHaltScript()) {
                                                    Platform.runLater(new Runnable(){
                                                        @Override public void run() {
                                                            loadingLabel.setText("Script halted");
                                                        }
                                                    });
                                                    runMacroButton.setDisable(false);
                                                    textArea.setEditable(true);
                                                    break;
                                                }
                                                */
                                            //}


                                            //new
                                            int minX = -2147483648;
                                            int maxX = -2147483648;
                                            int minY = -2147483648;
                                            int maxY = -2147483648;
                                            boolean varError = false;
                                            if (scriptLine[1].startsWith("$") && scriptLine[1].length() > 1) {
                                                if (variables.get(scriptLine[1]) == null) {
                                                    varError = true;
                                                } else {
                                                    minX = variables.get(scriptLine[1]).getIntValue();
                                                }

                                            } else {
                                                minX = Integer.parseInt(scriptLine[1]);
                                            }

                                            if (scriptLine[2].startsWith("$") && scriptLine[2].length() > 1) {
                                                if (variables.get(scriptLine[2]) == null) {
                                                    varError = true;
                                                } else {
                                                    maxX = variables.get(scriptLine[2]).getIntValue();
                                                }
                                            } else {
                                                maxX = Integer.parseInt(scriptLine[2]);
                                            }

                                            if (scriptLine[3].startsWith("$") && scriptLine[3].length() > 1) {
                                                if (variables.get(scriptLine[3]) == null) {
                                                    varError = true;
                                                } else {
                                                    minY = variables.get(scriptLine[3]).getIntValue();
                                                }
                                            } else {
                                                minY = Integer.parseInt(scriptLine[3]);
                                            }

                                            if (scriptLine[4].startsWith("$") && scriptLine[4].length() > 1) {
                                                if (variables.get(scriptLine[4]) == null) {
                                                    varError = true;
                                                } else {
                                                    maxY = variables.get(scriptLine[4]).getIntValue();
                                                }
                                            } else {
                                                maxY = Integer.parseInt(scriptLine[4]);
                                            }

                                            if (varError) {
                                                int finalLengthDifference1 = lengthDifference;
                                                Platform.runLater(new Runnable(){
                                                    @Override public void run() {
                                                        loadingLabel.setText("Macro error on line " + lineNumber + ": clickanddrag args must be ints or int vars");
                                                        //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                                    }
                                                });
                                                scriptingError = true;
                                                runMacroButton.setDisable(false);
                                                textArea.setEditable(true);
                                                return;
                                            }
                                            //for (int k = 0; k < timesToLoop; k++) {
                                            if (!(minX == -2147483648 || maxX == -2147483648 || minY == -2147483648 || maxY == -2147483648)) {
                                                clickAndDrag(minX, maxX, minY, maxY, bot);
                                            } else {
                                                System.out.println("hmmm");
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
                                            textArea.setEditable(true);
                                            break;
                                        } catch (AWTException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                    break;
                                case "alert":
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Script halted");
                                            }
                                        });
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }
                                    scriptIsEmpty = false;
                                    if (scriptLine.length != 1) {
                                        int finalLengthDifference2 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid arg for alert");
                                                //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference2, lengthDifferenceSingleLine);
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    } else {
                                        Toolkit.getDefaultToolkit().beep();
                                    }
                                    break;
                                case "clickanddragrandom":
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Script halted");
                                            }
                                        });
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    }
                                    scriptIsEmpty = false;
                                    //System.out.println("you want to clickanddragrandom on line " + j);
                                    //now need to check if it has proper int args i.e. clickanddragrandom 300 350 300 350 500 550 500 550
                                    //startxrandlower, startxrandupper, startyrandlower, startyrandupper, endxrandlower, endxrandupper, endyrandlower, endyrandupper
                                    if (scriptLine.length != 9) {
                                        int finalLengthDifference6 = lengthDifference;
                                        Platform.runLater(new Runnable(){
                                            @Override public void run() {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for clickanddragrandom");
                                                //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference6, lengthDifferenceSingleLine);
                                            }
                                        });

                                        scriptingError = true;
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
                                        break;
                                    } else {
                                        try {
                                            //old
                                            /*
                                                int startxrandlower  = Integer.parseInt(scriptLine[1]);;
                                                int startxrandupper  = Integer.parseInt(scriptLine[2]);;
                                                int startyrandlower  = Integer.parseInt(scriptLine[3]);;
                                                int startyrandupper  = Integer.parseInt(scriptLine[4]);;
                                                int endxrandlower  = Integer.parseInt(scriptLine[5]);;
                                                int endxrandupper  = Integer.parseInt(scriptLine[6]);;
                                                int endyrandlower  = Integer.parseInt(scriptLine[7]);;
                                                int endyrandupper = Integer.parseInt(scriptLine[8]);;
                                                System.out.println("this is where the clickanddragrandom implementation should go");
                                                //min, max
                                                int startx = getRandomNumber(startxrandlower, startxrandupper);
                                                int starty = getRandomNumber(startyrandlower, startyrandupper);
                                                int endx = getRandomNumber(endxrandlower, endxrandupper);
                                                int endy = getRandomNumber(endyrandlower, endyrandupper);
                                                System.out.println("calling clickanddrag " + startx + " " + starty + " " + endx + " " + endy);
                                                try {
                                                    clickAndDrag(startx, starty, endx, endy, bot);
                                                } catch (AWTException awte2) {
                                                    awte2.printStackTrace();
                                                }
                                                if (scriptHalter.isUserWantsToHaltScript()) {
                                                    Platform.runLater(new Runnable(){
                                                        @Override public void run() {
                                                            loadingLabel.setText("Script halted");
                                                        }
                                                    });
                                                    runMacroButton.setDisable(false);
                                                    textArea.setEditable(true);
                                                    break;
                                                }
                                                */

                                            //new
                                            int startMinX = -2147483648;
                                            int startMaxX = -2147483648;
                                            int startMinY = -2147483648;
                                            int startMaxY = -2147483648;

                                            int endMinX = -2147483648;
                                            int endMaxX = -2147483648;
                                            int endMinY = -2147483648;
                                            int endMaxY = -2147483648;

                                            boolean varError = false;
                                            if (scriptLine[1].startsWith("$") && scriptLine[1].length() > 1) {
                                                if (variables.get(scriptLine[1]) == null) {
                                                    varError = true;
                                                } else {
                                                    startMinX = variables.get(scriptLine[1]).getIntValue();
                                                }

                                            } else {
                                                startMinX = Integer.parseInt(scriptLine[1]);
                                            }

                                            if (scriptLine[2].startsWith("$") && scriptLine[2].length() > 1) {
                                                if (variables.get(scriptLine[2]) == null) {
                                                    varError = true;
                                                } else {
                                                    startMaxX = variables.get(scriptLine[2]).getIntValue();
                                                }
                                            } else {
                                                startMaxX = Integer.parseInt(scriptLine[2]);
                                            }

                                            if (scriptLine[3].startsWith("$") && scriptLine[3].length() > 1) {
                                                if (variables.get(scriptLine[3]) == null) {
                                                    varError = true;
                                                } else {
                                                    startMinY = variables.get(scriptLine[3]).getIntValue();
                                                }
                                            } else {
                                                startMinY = Integer.parseInt(scriptLine[3]);
                                            }

                                            if (scriptLine[4].startsWith("$") && scriptLine[4].length() > 1) {
                                                if (variables.get(scriptLine[4]) == null) {
                                                    varError = true;
                                                } else {
                                                    startMaxY = variables.get(scriptLine[4]).getIntValue();
                                                }
                                            } else {
                                                startMaxY = Integer.parseInt(scriptLine[4]);
                                            }

                                            //=====================================================

                                            if (scriptLine[5].startsWith("$") && scriptLine[5].length() > 1) {
                                                if (variables.get(scriptLine[5]) == null) {
                                                    varError = true;
                                                } else {
                                                    endMinX = variables.get(scriptLine[5]).getIntValue();
                                                }

                                            } else {
                                                endMinX = Integer.parseInt(scriptLine[5]);
                                            }

                                            if (scriptLine[6].startsWith("$") && scriptLine[6].length() > 1) {
                                                if (variables.get(scriptLine[6]) == null) {
                                                    varError = true;
                                                } else {
                                                    endMaxX = variables.get(scriptLine[6]).getIntValue();
                                                }
                                            } else {
                                                endMaxX = Integer.parseInt(scriptLine[6]);
                                            }

                                            if (scriptLine[7].startsWith("$") && scriptLine[7].length() > 1) {
                                                if (variables.get(scriptLine[7]) == null) {
                                                    varError = true;
                                                } else {
                                                    endMinY = variables.get(scriptLine[7]).getIntValue();
                                                }
                                            } else {
                                                endMinY = Integer.parseInt(scriptLine[7]);
                                            }

                                            if (scriptLine[8].startsWith("$") && scriptLine[8].length() > 1) {
                                                if (variables.get(scriptLine[8]) == null) {
                                                    varError = true;
                                                } else {
                                                    endMaxY = variables.get(scriptLine[8]).getIntValue();
                                                }
                                            } else {
                                                endMaxY = Integer.parseInt(scriptLine[8]);
                                            }

                                            //=====================================================

                                            if (varError) {
                                                int finalLengthDifference1 = lengthDifference;
                                                Platform.runLater(new Runnable(){
                                                    @Override public void run() {
                                                        loadingLabel.setText("Macro error on line " + lineNumber + ": clickanddrag args must be ints or int vars");
                                                        //highlightErrorLine(lineNumber, lines, textArea, scriptLine, finalLengthDifference1, lengthDifferenceSingleLine);
                                                    }
                                                });
                                                scriptingError = true;
                                                runMacroButton.setDisable(false);
                                                textArea.setEditable(true);
                                                return;
                                            }
                                            //for (int k = 0; k < timesToLoop; k++) {
                                            int startx = getRandomNumber(startMinX, startMaxX);
                                            int starty = getRandomNumber(startMinY, startMaxY);
                                            int endx = getRandomNumber(endMinX, endMaxX);
                                            int endy = getRandomNumber(endMinY, endMaxY);
                                            System.out.println("calling clickanddrag " + startx + " " + starty + " " + endx + " " + endy);
                                            if (!(startMinX == -2147483648 || startMaxX == -2147483648 || startMinY == -2147483648 || startMaxY == -2147483648
                                                    || endMinX == -2147483648 || endMaxX == -2147483648 || endMinY == -2147483648 || endMaxY == -2147483648
                                            )) {
                                                clickAndDrag(startx, starty, endx, endy, bot);
                                            } else {
                                                System.out.println("hmmm clickanddragrandom");

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
                                            textArea.setEditable(true);
                                            break;
                                        } catch (AWTException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                    break;
                                //press a key, i.e. press a
                                case "press":
                                    if (scriptHalter.isUserWantsToHaltScript()) {
                                        runMacroButton.setDisable(false);
                                        textArea.setEditable(true);
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
                                        textArea.setEditable(true);
                                        break;
                                    } else {
                                        String allKeys = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,";
                                        allKeys += allKeys.toUpperCase();
                                        allKeys += "0,1,2,3,4,5,6,7,8,9,";
                                        //for now, I'm not adding all keys, just basic ones
                                        allKeys += "enter,space,backspace,up,down,left,right,escape,tab";
                                        String keysArray[] = allKeys.split(",");

                                        Set<String> keySet = Set.of(keysArray);

                                        if (keySet.contains(scriptLine[1])) {
                                            //System.out.println("this is a valid key");
                                            //System.out.println("press feature is not yet implemented! implement it here!!!!!");
                                            //for (int k = 0; k < timesToLoop; k++) {
                                                press(scriptLine[1], bot);
                                                if (scriptHalter.isUserWantsToHaltScript()) {
                                                    Platform.runLater(new Runnable(){
                                                        @Override public void run() {
                                                            loadingLabel.setText("Script halted");
                                                        }
                                                    });
                                                    runMacroButton.setDisable(false);
                                                    textArea.setEditable(true);
                                                    break;
                                                }
                                            //}

                                        } else {

                                            System.out.print("invalid key for press command. arg: " + scriptLine[1]);
                                            Platform.runLater(new Runnable(){
                                                @Override public void run() {
                                                    loadingLabel.setText("Macro error on line " + lineNumber + ": invalid press arg");
                                                }
                                            });

                                            scriptingError = true;
                                            runMacroButton.setDisable(false);
                                            textArea.setEditable(true);
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
                                        textArea.setEditable(true);
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
                                        textArea.setEditable(true);
                                        break;
                                    }
                                    Platform.runLater(new Runnable(){
                                        @Override public void run() {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid syntax");
                                        }
                                    });

                                    scriptingError = true;
                                    runMacroButton.setDisable(false);
                                    textArea.setEditable(true);
                                    break;
                            }
                        }

                        //try {Thread.sleep(totalLoopTime.getTotalLoopTime());} catch (InterruptedException ex) { ex.printStackTrace();}


                    } //end of lines for a single main loop (the GUI number loop, not the "loop" command

                    //reset the variables each main loop
                    variables = new HashMap<>();

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
            //reset the console text, in case the program is being run multiple times
            runMacroButton.setDisable(false);
            textArea.setEditable(true);


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
        primaryStage.setWidth(450);
        primaryStage.setMinWidth(450);
        primaryStage.setHeight(450);
        primaryStage.setMinHeight(450);
        BorderPane mainPane = new BorderPane();
        VBox topMostContainerVBox = new VBox();
        HBox topButtonsHBox = new HBox();
        VBox mybox = new VBox();


        topMostContainerVBox.getChildren().add(topButtonsHBox);
        mainPane.setTop(topMostContainerVBox);
        Scene scene = new Scene(mainPane);
        Button testButton = new Button("Get Coords");
        Label coordsLabel = new Label("(x, y)");
        TabPane tabPane = new TabPane();
        Tab defaultTab = new Tab("Untitled Script");
        Tab newTab = new Tab("+");
        defaultTab.setClosable(false);
        newTab.setClosable(false);
        tabPane.getTabs().addAll(defaultTab, newTab);
        CheckBox wait5SecondsButton = new CheckBox("Wait 5s for x,y");
        testButton.setOnAction( e-> {
            //MouseInfo.getPointerInfo().getLocation().getX()
            //coordsLabel.setText(MouseInfo.getPointerInfo().getLocation().toString());
            System.out.println("got here testbutton");
            new Thread(()->{
                System.out.println("got here new thread");
                if (wait5SecondsButton.isSelected()) {
                    System.out.println("got here 5000 delay");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                Platform.runLater(new Runnable(){
                    @Override public void run() {
                        coordsLabel.setText((int)MouseInfo.getPointerInfo().getLocation().getX() + ", " + (int)MouseInfo.getPointerInfo().getLocation().getY());
                    }
                });

            }).start();
        });

        TextArea textArea = new TextArea();
        TextArea consoleTextArea = new TextArea();
        consoleTextArea.setStyle(("-fx-focus-color: transparent;"));
        consoleTextArea.setText("\n\n\nAutoInput Script Console\n");
        consoleTextArea.setEditable(false);
        consoleTextArea.setMinHeight(100);
        consoleTextArea.setMaxHeight(100);

        textArea.setStyle(("height: 100%; -fx-focus-color: transparent;"));


        Button runMacroButton = new Button("Run Script");

        Button debugButton = new Button("Debug");

        Button mouseRecordButton = new Button("Record Mouse");
        Button generateButton = new Button("Generate");
        Button getColorButton = new Button("Get Color");
        Button clearButton = new Button("Clear");
        //increase/decrease indent level of selected text in textarea
        Button increaseIndentButton = new Button("Increase Indent");
        Button decreaseIndentButton = new Button("Decrease Indent");


        Label repeatLabel = new Label("Times to run script:");
        TextField numTimes = new TextField();
        numTimes.setText("1");
        numTimes.setMaxWidth(100);
        //vbox is called mybox
        //need to add a status label that the multi button changes
        Label loadingLabel = new Label("Status: OK");

        MenuItem fileItemNew = new MenuItem("New (not done)");
        MenuItem fileItem1 = new MenuItem("Open (not done)");
        MenuItem fileItemSave = new MenuItem("Save (not done)");
        MenuItem fileItem2 = new MenuItem("Save as (not done)");
        MenuItem fileItem3 = new MenuItem("Quit");

        Alert quitAlert = new Alert(Alert.AlertType.CONFIRMATION);
        quitAlert.setTitle("Quit");
        quitAlert.setHeaderText("Quit AutoInput");
        quitAlert.setContentText("Are you sure you want to quit?\nAny unsaved changes will be lost.");

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

        Menu fileMenu = new Menu("File", null, fileItemNew, fileItem1, fileItemSave, fileItem2, fileItem3);

        MenuItem optionsItem1 = new MenuItem("Enable always on top");
        MenuItem optionsItem3 = new MenuItem("Enable dark mode");
        MenuItem optionsItem4 = new MenuItem("Disable dark mode");
        MenuItem optionsItem5 = new MenuItem("Disable always on top");
        MenuItem optionsItem6 = new MenuItem("Enable word wrap");
        MenuItem optionsItem7 = new MenuItem("Disable word wrap");
        MenuItem optionsItem8 = new MenuItem("Advanced options (not done)");


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



        MenuItem editItem[] = new MenuItem[12];
        editItem[8] = new MenuItem("Undo (not done)");
        editItem[9] = new MenuItem("Redo (not done)");
        editItem[10] = new Menu("Find (not done)");
        editItem[11] = new Menu("Replace (not done)");
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

        Menu editMenu = new Menu("Edit", null, editItem[8], editItem[9], editItem[10], editItem[11], editItem[0], editItem[7], editItem[1], editItem[2], editItem[3], editItem[5], editItem[6]);

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


        Menu optionsMenu = new Menu("Options", null, optionsItem1, optionsItem5, optionsItem3, optionsItem4, optionsItem6, optionsItem7, optionsItem8);
        MenuItem aboutItem1 = new MenuItem("About");
        Alert aboutAlert = new Alert(Alert.AlertType.INFORMATION);
        aboutAlert.setTitle("About");
        aboutAlert.setHeaderText("About AutoInput v0.0043");
        aboutAlert.setContentText("This is a programming language and IDE made by Alan");


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
        helpItem[1] = new MenuItem("Advanced usage (not done)");

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
                "press enter\n" +
                "loop 5\n" +
                "   press a\n" +
                "endloop\n" +
                "clickanddrag 300 300 600 500\n" +
                "clickanddragrandom 100 150 400 450 200 250 500 550\n" +
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



        Menu helpMenu = new Menu("Help", null, helpItem[0], helpItem[1]);



        MenuBar menuBar = new MenuBar(fileMenu, editMenu, optionsMenu, aboutMenu, helpMenu);
        Label info = new Label("To change the functionality of this program, change the lambda expressions for 'runMacroButton' and 'button'");
        info.setWrapText(true);
        Label info2 = new Label("And use the 'get coords' button to get the current mouse x,y");
        info2.setWrapText(true);


        topMostContainerVBox.getChildren().addAll(menuBar);
        //topMostContainerVBox.getChildren().add(mybox);
        //topMostContainerVBox.getChildren().add(topButtonsHBox);




        mybox.getChildren().addAll(/*testButton, */coordsLabel, wait5SecondsButton);
        runMacroButton.setOnAction( e -> {
            runMacroButton.setDisable(true);


            try {

                int timesToRepeat = 2;
                boolean isInfinite = false;
                //scene.setCursor(Cursor.WAIT);
                boolean error = false;
                if (numTimes.getText().equalsIgnoreCase("infinite")) {
                    isInfinite = true;
                } else {
                    try {
                        timesToRepeat = Integer.parseInt(numTimes.getText());
                    } catch (NumberFormatException ne) {
                        //ne.printStackTrace();
                        error = true;
                        runMacroButton.setDisable(false);
                        textArea.setEditable(true);
                        Platform.runLater(new Runnable(){
                            @Override public void run() {
                                loadingLabel.setText("Error: invalid num times to run script.");
                            }
                        });
                    }
                }
                if (timesToRepeat < 0) {
                    error = true;
                    runMacroButton.setDisable(false);
                    textArea.setEditable(true);
                    Platform.runLater(new Runnable(){
                        @Override public void run() {
                            loadingLabel.setText("Error: invalid num times to run script.");
                        }
                    });
                }

                consoleTextArea.setText("\n\n\nAutoInput Script Console\n");
                if (!error) {
                    parseAndRunScript(isInfinite, timesToRepeat, textArea, consoleTextArea, loadingLabel, totalLoopTime, scriptHalter, bot, runMacroButton, primaryStage);
                }


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
            if (numTimes.getText() != null && !numTimes.getText().equalsIgnoreCase("infinite")) {
                try {
                    Integer.parseInt(numTimes.getText());
                    if (Integer.parseInt(numTimes.getText()) > 0) {
                        int newValue = Integer.parseInt(numTimes.getText()) - 1;
                        numTimes.setText(Integer.toString(newValue));
                    }
                } catch (NumberFormatException ne) {
                    //ne.printStackTrace();
                }

            }


        });
        plusButton.setOnAction( e -> {
            if (numTimes.getText() != null && !numTimes.getText().equalsIgnoreCase("infinite")) {
                try {
                    Integer.parseInt(numTimes.getText());
                    if (Integer.parseInt(numTimes.getText()) < 2147473646) {
                        int newValue = Integer.parseInt(numTimes.getText()) + 1;
                        numTimes.setText(Integer.toString(newValue));
                    }
                } catch (NumberFormatException ne) {
                    //ne.printStackTrace();
                }

            }
        });
        BorderPane nestedBorderPane = new BorderPane();
        nestedBorderPane.setLeft(mybox);
        //need to add: tabPane, closeCurrentTabButton
        FlowPane buttonsPane = new FlowPane();
        Button haltScriptButton = new Button("Halt Script");
        //testButton, runMacroButton, haltScriptButton
        buttonsPane.getChildren().addAll(testButton, runMacroButton, haltScriptButton, debugButton, mouseRecordButton,
                generateButton, getColorButton, clearButton, increaseIndentButton, decreaseIndentButton);
        buttonsPane.setHgap(1);
        buttonsPane.setVgap(2);
        topMostContainerVBox.getChildren().addAll(buttonsPane, nestedBorderPane, tabPane);
        VBox rightTopVBox = new VBox();
        rightTopVBox.getChildren().addAll(/*runMacroButton, */repeatLabel);
        nestedBox.getChildren().addAll(minusButton, numTimes, plusButton);
        rightTopVBox.getChildren().addAll(nestedBox);
        nestedBorderPane.setCenter(rightTopVBox);

        Insets nestedBorderInsets = new Insets(0, 0, 0, 50);
        nestedBorderPane.setMargin(rightTopVBox, nestedBorderInsets);
        //Label closeToEndScriptLabel = new Label("To halt a running script, close the AutoInput window.");

        //enable dark mode

        //nestedBorderPane.setRight(haltScriptButton);

        optionsItem3.setOnAction(e -> {

            debugButton.setStyle("-fx-background-color: #676767; -fx-text-fill: #d2d2d2;");
            mouseRecordButton.setStyle("-fx-background-color: #676767; -fx-text-fill: #d2d2d2;");
            generateButton.setStyle("-fx-background-color: #676767; -fx-text-fill: #d2d2d2;");
            getColorButton.setStyle("-fx-background-color: #676767; -fx-text-fill: #d2d2d2;");
            clearButton.setStyle("-fx-background-color: #676767; -fx-text-fill: #d2d2d2;");
            increaseIndentButton.setStyle("-fx-background-color: #676767; -fx-text-fill: #d2d2d2;");
            decreaseIndentButton.setStyle("-fx-background-color: #676767; -fx-text-fill: #d2d2d2;");
            buttonsPane.lookup(".button:hover").setStyle("-fx-background-color: blue;");
            wait5SecondsButton.setStyle("-fx-text-fill: #d2d2d2;");
            tabPane.lookup(".tab:selected:top").lookup(".tab-container").lookup(".tab-close-button").setStyle("-fx-background-color: #d2d2d2;");
            tabPane.lookup(".tab-pane").lookup(".tab-header-area").lookup(".tab-header-background").setStyle("-fx-background-color: #474749;");
            defaultTab.setStyle("-fx-background-color: #676767; -fx-text-base-color: #d2d2d2; ");
            newTab.setStyle("-fx-background-color: #676767; -fx-text-base-color: #d2d2d2;");
            //.tab-pane:top *.tab-header-area

            consoleTextArea.lookup(".content").setStyle("-fx-background-color: #2a2a2e;");
            consoleTextArea.setStyle("-fx-text-fill: #499c54; -fx-font-family: monospace");
            //consoleTextArea.setStyle(("height: 100%; -fx-focus-color: transparent; -fx-text-fill: #384c38; -fx-background-color: #2a2a2e;"));

            textArea.lookup(".content").setStyle("-fx-background-color: #2a2a2e;");
            textArea.setStyle("-fx-text-fill: #d2d2d2; -fx-font-family: monospace");
            mainPane.setStyle("-fx-background-color: #474749;");
            numTimes.setStyle("-fx-background-color: #2a2a2e; -fx-text-fill: #d2d2d2;");
            loadingLabel.setStyle("-fx-text-fill: #d2d2d2;");
            //closeToEndScriptLabel.setStyle("-fx-text-fill: #d2d2d2;");
            coordsLabel.setStyle("-fx-text-fill: #d2d2d2;");
            repeatLabel.setStyle("-fx-text-fill: #d2d2d2;");
            testButton.setStyle("-fx-background-color: #676767; -fx-text-fill: #d2d2d2;");
            //runMacroButton.setStyle("-fx-background-color: #676767; -fx-text-fill: #d2d2d2;");
            runMacroButton.setStyle("-fx-background-color: #676767; -fx-text-fill: lightgreen;");
            plusButton.setStyle("-fx-background-color: #676767; -fx-text-fill: #d2d2d2;");
            minusButton.setStyle("-fx-background-color: #676767; -fx-text-fill: #d2d2d2;");
            menuBar.setStyle("-fx-background-color: darkgray; -fx-text-fill: #d2d2d2;");
            //haltScriptButton.setStyle("-fx-background-color: #676767; -fx-text-fill: #d2d2d2;");
            haltScriptButton.setStyle("-fx-background-color: #676767; -fx-text-fill: pink;");
        });

        //disable dark mode
        optionsItem4.setOnAction(e -> {

            debugButton.setStyle(null);
            mouseRecordButton.setStyle(null);
            generateButton.setStyle(null);
            getColorButton.setStyle(null);
            clearButton.setStyle(null);
            increaseIndentButton.setStyle(null);
            decreaseIndentButton.setStyle(null);
            buttonsPane.lookup(".button:hover").setStyle(null);
            wait5SecondsButton.setStyle(null);
            tabPane.setStyle(null);
            tabPane.lookup(".tab:selected:top").lookup(".tab-container").lookup(".tab-close-button").setStyle(null);
            tabPane.lookup(".tab-pane").lookup(".tab-header-area").lookup(".tab-header-background").setStyle(null);
            defaultTab.setStyle(null);
            newTab.setStyle(null);

            consoleTextArea.lookup(".content").setStyle(null);
            consoleTextArea.setStyle(null);

            textArea.lookup(".content").setStyle(null);
            textArea.setStyle(null);
            mainPane.setStyle(null);
            numTimes.setStyle(null);
            loadingLabel.setStyle(null);
            //closeToEndScriptLabel.setStyle(null);
            coordsLabel.setStyle(null);
            repeatLabel.setStyle(null);
            testButton.setStyle(null);
            runMacroButton.setStyle("-fx-text-fill: darkgreen;");
            plusButton.setStyle(null);
            minusButton.setStyle(null);
            menuBar.setStyle(null);
            haltScriptButton.setStyle("-fx-text-fill: darkred;");
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
        bottomBox.getChildren().addAll(consoleTextArea, loadingLabel, bottomBorderPane);
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
        optionsItem3.fire();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
