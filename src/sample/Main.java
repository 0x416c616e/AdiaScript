package sample;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
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
import javafx.util.Duration;

import java.awt.*;
import java.awt.event.InputEvent;
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

    public static void scheduleEvent(int x, int y, int duration, Robot bot, ScriptHalter scriptHalter, String eventType, int offsetMultiplier, int totalLoopTime) throws AWTException {

        new Thread(()->{ //use another thread so long process does not block gui
            //update gui using fx thread
            if (scriptHalter.isUserWantsToHaltScript()) {
                System.out.println("thread is returning");
                Thread.currentThread().interrupt();
                return; //ends the thread
                //this needs to be checked before and after the wait duration

            } else {
                //I know this is bad but oh well lol
                //at least it works (for smaller durations, at least)
                if (duration <= 1000) {
                    System.out.println("not a very long duration so it doesn't really matter");
                    for (int i = 0; i < offsetMultiplier; i++) {
                        for (int j = 0; j < totalLoopTime; j += 1000) {
                            try {Thread.sleep(1000);} catch (InterruptedException ex) { ex.printStackTrace();}
                        }
                    }
                    try {Thread.sleep(duration);} catch (InterruptedException ex) { ex.printStackTrace();}
                    //all these "else if" things do is break the duration into 1 second chunks, and then checks
                    //every single second to see if it should quit the thread due to the user wanting to halt the program
                } else if (duration <= 10000) {
                    //longer duration
                    final int smallerDuration = duration / 10;
                    for (int i = 0; i < duration; i += smallerDuration) {
                        if (scriptHalter.isUserWantsToHaltScript()) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        try {Thread.sleep(smallerDuration);} catch (InterruptedException ex) { ex.printStackTrace();}
                    }
                } else if (duration <= 100000) {
                    final int smallerDuration = duration / 100;
                    for (int i = 0; i < duration; i += smallerDuration) {
                        if (scriptHalter.isUserWantsToHaltScript()) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        try {Thread.sleep(smallerDuration);} catch (InterruptedException ex) { ex.printStackTrace();}
                    }
                } else if (duration <= 1000000) {
                    final int smallerDuration = duration / 1000;
                    for (int i = 0; i < duration; i += smallerDuration) {
                        if (scriptHalter.isUserWantsToHaltScript()) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        try {Thread.sleep(smallerDuration);} catch (InterruptedException ex) { ex.printStackTrace();}
                    }
                } else if (duration <= 10000000) {
                    final int smallerDuration = duration / 10000;
                    for (int i = 0; i < duration; i += smallerDuration) {
                        if (scriptHalter.isUserWantsToHaltScript()) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        try {Thread.sleep(smallerDuration);} catch (InterruptedException ex) { ex.printStackTrace();}
                    }
                } else if (duration <= 100000000) {
                    final int smallerDuration = duration / 100000;
                    for (int i = 0; i < duration; i += smallerDuration) {
                        if (scriptHalter.isUserWantsToHaltScript()) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        try {Thread.sleep(smallerDuration);} catch (InterruptedException ex) { ex.printStackTrace();}
                    }
                } else if (duration <= 1000000000) {
                    final int smallerDuration = duration / 1000000;
                    for (int i = 0; i < duration; i += smallerDuration) {
                        if (scriptHalter.isUserWantsToHaltScript()) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        try {Thread.sleep(smallerDuration);} catch (InterruptedException ex) { ex.printStackTrace();}
                    }
                } else {
                    //probably should have just done this earlier but oh well
                    //I don't feel like refactoring
                    for (int i = 0; i < duration; i += 1000) {
                        if (scriptHalter.isUserWantsToHaltScript()) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        try {Thread.sleep(1000);} catch (InterruptedException ex) { ex.printStackTrace();}
                    }
                }
                if (scriptHalter.isUserWantsToHaltScript()) {
                    System.out.println("user wants to halt the script");
                    Thread.currentThread().interrupt();
                    return; //end the thread

                } else {
                    System.out.println("user wants to continue the script");
                    Platform.runLater(() -> {
                        try {
                            switch (eventType) {
                                case "click":
                                    click(x, y, bot);
                                    break;
                                case "move":
                                    move(x, y, bot);
                                //not fully implemented
                                //to-do
                                default:
                                    System.out.println("error with eventType in scheduleEvent");
                                    break;
                            }

                        } catch (AWTException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }


        }).start();

    }

    public void scheduleMultiple (int x, int y, int duration, Robot bot, ScriptHalter scriptHalter) throws AWTException {
        //if there's more than one schedule event, use this instead of scheduleclick
        //
    }

    public static void clickAndDrag(int x_start, int y_start, int x_end, int y_end, Robot bot) throws AWTException {
        bot.mouseMove(x_start, y_start);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseMove(x_end, y_end);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ScriptHalter scriptHalter = new ScriptHalter();
        Robot bot = new Robot();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
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
        Button testButton = new Button("Get coords");
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
        MenuItem fileItem3 = new MenuItem("Quit (not done)");

        Menu fileMenu = new Menu("File", null, fileItem1, fileItem2, fileItem3);

        MenuItem optionsItem1 = new MenuItem("Enable always on top (not done)");
        MenuItem optionsItem3 = new MenuItem("Enable dark mode");
        MenuItem optionsItem4 = new MenuItem("Disable dark mode");
        MenuItem optionsItem5 = new MenuItem("Disable always on top (not done)");
        MenuItem optionsItem6 = new MenuItem("Enable word wrap");
        MenuItem optionsItem7 = new MenuItem("Disable word wrap");

        optionsItem6.setOnAction(e -> {
            textArea.setWrapText(true);
        });

        optionsItem7.setOnAction(e -> {
            textArea.setWrapText(false);
        });


        //text.setWrapText(true)

        MenuItem editItem[] = new MenuItem[7];
        editItem[0] = new MenuItem("Clear text (not done)");
        editItem[1] = new MenuItem("Copy (not done)");
        editItem[2] = new Menu("Cut (not done)");
        editItem[3] = new MenuItem("Paste (not done)");
        editItem[4] = new MenuItem("Delete (not done)");
        editItem[5] = new MenuItem("Select all (not done)");
        editItem[6] = new MenuItem("Select none (not done)");


        Menu editMenu = new Menu("Edit", null, editItem[0], editItem[1], editItem[2], editItem[3], editItem[4], editItem[5], editItem[6]);


        Menu optionsMenu = new Menu("Options", null, optionsItem1, optionsItem5, optionsItem3, optionsItem4, optionsItem6, optionsItem7);
        MenuItem aboutItem1 = new MenuItem("About");
        Alert aboutAlert = new Alert(Alert.AlertType.INFORMATION);
        aboutAlert.setTitle("About");
        aboutAlert.setHeaderText("About AutoInput");
        aboutAlert.setContentText("This is an input automation scripting language and editor made by 0x416c616e. You can use it to write keyboard/mouse macros" +
                " in order to automate repetitive tasks that require using a GUI rather than something command line-based that can be automated with a shell script.");


        aboutItem1.setOnAction(e -> {
            //primaryStage is temporarily set to not be always on top
            //otherwise an alert would be behind it
            primaryStage.setAlwaysOnTop(false);
            Optional<ButtonType> aboutAlertResult = aboutAlert.showAndWait();
            if (aboutAlertResult.isEmpty()) {
                //do nothing
                //without this part, it would throw an exception
                primaryStage.setAlwaysOnTop(true);
            } else if (aboutAlertResult.get() == ButtonType.OK) {
                //once the alert is closed, set the primaryStage (main program window) to be always on top again
                primaryStage.setAlwaysOnTop(true);
            } else if (aboutAlertResult.get() == ButtonType.CLOSE) {
                primaryStage.setAlwaysOnTop(true);
            }
        });

        MenuItem aboutItem2 = new Menu("Website");
        Alert websiteAlert = new Alert(Alert.AlertType.CONFIRMATION);
        websiteAlert.setTitle("Website");
        websiteAlert.setHeaderText("Would you like to open the developer's website (saintlouissoftware.com) in a browser?");

        aboutItem2.setOnAction(e -> {
            primaryStage.setAlwaysOnTop(false);
            Optional<ButtonType> websiteAlertResult = websiteAlert.showAndWait();
            if (websiteAlertResult.get() == ButtonType.OK) {
                //System.out.println("You want to open the website in a browser");
                getHostServices().showDocument("https://saintlouissoftware.com/");
                primaryStage.setAlwaysOnTop(true);
            } else if (websiteAlertResult.get() == ButtonType.CANCEL || websiteAlertResult.get() == ButtonType.CLOSE) {
                //System.out.println("You do not want to open the website.");
                primaryStage.setAlwaysOnTop(true);
            }
        });

        MenuItem aboutItem3 = new Menu("Git repo");
        Menu aboutMenu = new Menu("About", null, aboutItem1, aboutItem2, aboutItem3);

        Alert gitRepoAlert = new Alert(Alert.AlertType.CONFIRMATION);
        gitRepoAlert.setTitle("Git Repo");
        gitRepoAlert.setHeaderText("Would you like to open the project's GitHub page in a browser?");

        aboutItem3.setOnAction(e -> {
            primaryStage.setAlwaysOnTop(false);
            Optional<ButtonType> gitRepoAlertResult = gitRepoAlert.showAndWait();
            if (gitRepoAlertResult.get() == ButtonType.OK) {
                //System.out.println("You want to open the git repo page in a browser");
                getHostServices().showDocument("https://github.com/0x416c616e/AutoInput");
                primaryStage.setAlwaysOnTop(true);
            } else if (gitRepoAlertResult.get() == ButtonType.CANCEL || gitRepoAlertResult.get() == ButtonType.CLOSE) {
                //System.out.println("You do not want to open the git repo page in a browser.");
                primaryStage.setAlwaysOnTop(true);
            }
        });

        MenuItem helpItem[] = new MenuItem[2];
        helpItem[0] = new MenuItem("Basic usage (not done)");


        helpItem[1] = new MenuItem("Common issues");
        Alert commonIssuesAlert = new Alert(Alert.AlertType.INFORMATION);
        commonIssuesAlert.setTitle("Common Issues");
        commonIssuesAlert.setHeaderText("Common issues when using AutoInput");
        commonIssuesAlert.setContentText("If the program crashes or stops before the macro is finished, try increasing the wait time between clicks or moves. \n\n" +
                "Here is an example of a good macro (for stability):\n\n" +
                "click 500 500\n" +
                "wait 4000\n" +
                "click 400 400\n" +
                "wait 6000\n\n" +
                "That being said, it's normal for the program to say 'not responding' due to how the wait command is coded. It's not perfect, but it works. " +
                "If AutoInput says 'not responding' but it doesn't crash, it's fine. But if it crashes, then you need to increase the duration of the wait commands.");

        helpItem[1].setOnAction(e -> {
            primaryStage.setAlwaysOnTop(false);
            Optional<ButtonType> commonIssuesAlertResult = commonIssuesAlert.showAndWait();
            if (commonIssuesAlertResult.isEmpty()) {
                //nothing
                primaryStage.setAlwaysOnTop(true);
            } else if (commonIssuesAlertResult.get() == ButtonType.OK || commonIssuesAlertResult.get() == ButtonType.CLOSE) {
                primaryStage.setAlwaysOnTop(true);
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

        mybox.getChildren().addAll(testButton, coordsLabel);
        runMacroButton.setOnAction( e -> {



            try {


                //scene.setCursor(Cursor.WAIT);
                int timesToRepeat = Integer.parseInt(numTimes.getText());


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
                    for (int j = 0; j < numberOfLines; j++) {
                        if (scriptingError) {
                            break;
                        }
                        String scriptLine[] = lines[j].split(" ");


                        int lineNumber = j + 1;
                        if (scriptLine.length != 0) {

                            switch (scriptLine[0]) {

                                case "click":
                                    scriptIsEmpty = false;
                                    //System.out.println("you want to click on line " + j);
                                    //now need to check if it has proper int args i.e. click 400 500
                                    if (scriptLine.length != 3) {
                                        loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for click");
                                        scriptingError = true;
                                        break;
                                    } else {
                                        try {
                                            int x;
                                            int y;
                                            x = Integer.parseInt(scriptLine[1]);
                                            y = Integer.parseInt(scriptLine[2]);
                                        } catch (NumberFormatException numException) {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": click args must be ints");
                                            scriptingError = true;
                                            break;
                                        }

                                    }
                                    break;
                                case "scheduleclick":
                                    scriptIsEmpty = false;
                                    //System.out.println("you want to scheduleclick on line " + j);
                                    //now need to check if it has proper int args i.e. scheduleclick 400 500 1000
                                    if (scriptLine.length != 4) {
                                        loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for scheduleclick");
                                        scriptingError = true;
                                        break;
                                    } else {
                                        try {
                                            int x;
                                            int y;
                                            int waitDuration;
                                            x = Integer.parseInt(scriptLine[1]);
                                            y = Integer.parseInt(scriptLine[2]);
                                            waitDuration = Integer.parseInt(scriptLine[3]);
                                        } catch (NumberFormatException numException) {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": scheduleclick args must be ints");
                                            scriptingError = true;
                                            break;
                                        }

                                    }
                                    break;
                                case "move":
                                    scriptIsEmpty = false;
                                    //System.out.println("you want to move on line " + j);
                                    //now need to check if it has proper int args i.e. move 400 500
                                    if (scriptLine.length != 3) {
                                        loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for move");
                                        scriptingError = true;
                                        break;
                                    } else {
                                        try {
                                            int x;
                                            int y;
                                            x = Integer.parseInt(scriptLine[1]);
                                            y = Integer.parseInt(scriptLine[2]);
                                        } catch (NumberFormatException numException) {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": move args must be ints");
                                            scriptingError = true;
                                            break;
                                        }

                                    }
                                    break;
                                case "wait":
                                    scriptIsEmpty = false;
                                    //System.out.println("you want to wait on line " + j);
                                    //now need to check if it has a proper int arg i.e. wait 500
                                    if (scriptLine.length != 2) {

                                        loadingLabel.setText("Macro error on line " + lineNumber + ": invalid arg for wait");
                                        scriptingError = true;
                                        break;
                                    } else {
                                        try {
                                            int duration = Integer.parseInt(scriptLine[1]);
                                        } catch (NumberFormatException numException) {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": wait arg must be int");
                                            scriptingError = true;
                                            break;
                                        }
                                    }
                                    break;
                                case "rightclick":
                                    scriptIsEmpty = false;
                                    //System.out.println("you want to rightclick on line " + j);
                                    //now need to check if it has proper int args i.e. rightclick 400 500
                                    if (scriptLine.length != 3) {
                                        loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for rightclick");
                                        scriptingError = true;
                                        break;
                                    } else {
                                        try {
                                            int x;
                                            int y;
                                            x = Integer.parseInt(scriptLine[1]);
                                            y = Integer.parseInt(scriptLine[2]);
                                        } catch (NumberFormatException numException) {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": rightclick args must be ints");
                                            scriptingError = true;
                                            break;
                                        }

                                    }
                                    break;
                                //press a key, i.e. press a
                                case "press":
                                    scriptIsEmpty = false;
                                    System.out.println("you want to press on line " + j);
                                    //now need to check if it has a proper string arg i.e. press a
                                    if (scriptLine.length != 2) {

                                        loadingLabel.setText("Macro error on line " + lineNumber + ": invalid arg for press");
                                        scriptingError = true;
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
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid press arg");
                                            scriptingError = true;
                                            break;
                                        }

                                    }
                                    break;
                                //case " ":
                                case "":
                                    //blank lines are ok, just ignore them
                                    break;
                                case "#":
                                    //comments are ok, just don't do anything with the subsequent words in the line
                                    //comments in AutoInputScript must be like this:
                                    //# comment
                                    //hash sign followed by a space and then the comment
                                    break;
                                default:
                                    loadingLabel.setText("Macro error on line " + lineNumber + ": invalid syntax");
                                    scriptingError = true;
                                    break;
                            }
                        }


                    }

                    if (!scriptingError && scriptIsEmpty) {
                        loadingLabel.setText("Macro error: cannot run blank script");
                    }

                    //if no errors are found with the error checking, time to actually run the script
                    if (!scriptingError && !scriptIsEmpty) {
                        loadingLabel.setText("Script check: OK");
                        //to-do: implement actually parsing script and then running the commands, such as click, rightClick, etc.
                        //!!!!!!!!!!!!!!!!!!!!!!WHERE I LEFT OFF!!!!!!!!!!!!!!!!!!!!!!!!!!


                        //-------------------------------------------------------------------------------------------------
                        //the following loop actually runs the script!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                        //the above stuff just checks it for errors
                        //that way, it will only run the script in its entirety when there are no errors
                        //it will not run a partially-working script
                        for (int j = 0; j < numberOfLines; j++) {
                            //if there is an error or the user presses the button to halt the script, then stop the script
                            if (scriptingError || scriptHalter.isUserWantsToHaltScript()) {
                                System.out.println("script has been halted");
                                //scriptHalter.setUserWantsToHaltScript(false);
                                break;
                            }
                            String scriptLine[] = lines[j].split(" ");


                            int lineNumber = j + 1;
                            if (scriptLine.length != 0) {

                                switch (scriptLine[0]) {

                                    case "click":
                                        scriptIsEmpty = false;
                                        System.out.println("you want to click on line " + j);
                                        //now need to check if it has proper int args i.e. click 400 500
                                        if (scriptLine.length != 3) {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for click");
                                            scriptingError = true;
                                            break;
                                        } else {
                                            try {
                                                int x;
                                                int y;
                                                x = Integer.parseInt(scriptLine[1]);
                                                y = Integer.parseInt(scriptLine[2]);
                                                click(x, y, bot);
                                            } catch (NumberFormatException numException) {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": click args must be ints");
                                                scriptingError = true;
                                                break;
                                            }

                                        }
                                        break;
                                    case "scheduleclick":
                                        scriptIsEmpty = false;
                                        //System.out.println("you want to scheduleclick on line " + j);
                                        //now need to check if it has proper int args i.e. scheduleclick 400 500 1000
                                        if (scriptLine.length != 4) {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for scheduleclick");
                                            scriptingError = true;
                                            break;
                                        } else {
                                            try {
                                                int x;
                                                int y;
                                                int waitDuration;
                                                x = Integer.parseInt(scriptLine[1]);
                                                y = Integer.parseInt(scriptLine[2]);
                                                waitDuration = Integer.parseInt(scriptLine[3]);
                                                scheduleEvent(x, y, waitDuration, bot, scriptHalter, "click", i, 82); //i is the loop #
                                            } catch (NumberFormatException numException) {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": scheduleclick args must be ints");
                                                scriptingError = true;
                                                break;
                                            }

                                        }
                                        break;
                                    case "move":
                                        scriptIsEmpty = false;
                                        //System.out.println("you want to move on line " + j);
                                        //now need to check if it has proper int args i.e. move 400 500
                                        if (scriptLine.length != 3) {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for move");
                                            scriptingError = true;
                                            break;
                                        } else {
                                            try {
                                                int x;
                                                int y;
                                                x = Integer.parseInt(scriptLine[1]);
                                                y = Integer.parseInt(scriptLine[2]);
                                                move(x, y, bot);
                                            } catch (NumberFormatException numException) {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": move args must be ints");
                                                scriptingError = true;
                                                break;
                                            }

                                        }
                                        break;
                                    case "wait":
                                        scriptIsEmpty = false;
                                        //System.out.println("you want to wait on line " + j);
                                        //now need to check if it has a proper int arg i.e. wait 500
                                        if (scriptLine.length != 2) {

                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid arg for wait");
                                            scriptingError = true;
                                            break;
                                        } else {
                                            try {
                                                int duration = Integer.parseInt(scriptLine[1]);
                                                Thread.sleep(duration);
                                            } catch (NumberFormatException numException) {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": wait arg must be int");
                                                scriptingError = true;
                                                break;
                                            }
                                        }
                                        break;
                                    case "rightclick":
                                        scriptIsEmpty = false;
                                        System.out.println("you want to rightclick on line " + j);
                                        //now need to check if it has proper int args i.e. rightclick 400 500
                                        if (scriptLine.length != 3) {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid args for rightclick");
                                            scriptingError = true;
                                            break;
                                        } else {
                                            try {
                                                int x;
                                                int y;
                                                x = Integer.parseInt(scriptLine[1]);
                                                y = Integer.parseInt(scriptLine[2]);
                                                rightClick(x, y, bot);
                                            } catch (NumberFormatException numException) {
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": rightclick args must be ints");
                                                scriptingError = true;
                                                break;
                                            }

                                        }
                                        break;
                                    //press a key, i.e. press a
                                    case "press":
                                        scriptIsEmpty = false;
                                        System.out.println("you want to press on line " + j);
                                        //now need to check if it has a proper string arg i.e. press a
                                        if (scriptLine.length != 2) {

                                            loadingLabel.setText("Macro error on line " + lineNumber + ": invalid arg for press");
                                            scriptingError = true;
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
                                                loadingLabel.setText("Macro error on line " + lineNumber + ": invalid press arg");
                                                scriptingError = true;
                                                break;
                                            }

                                        }
                                        break;
                                    //case " ":
                                    case "":
                                        //blank lines are ok, just ignore them
                                        break;
                                    case "#":
                                        //comments are ok, just don't do anything with the subsequent words in the line
                                        //comments in AutoInputScript must be like this:
                                        //# comment
                                        //hash sign followed by a space and then the comment
                                        break;
                                    default:
                                        loadingLabel.setText("Macro error on line " + lineNumber + ": invalid syntax");
                                        scriptingError = true;
                                        break;
                                }
                            }


                        }

                    }





                }

                scene.setCursor(Cursor.DEFAULT);
            } catch (AWTException a) {
                a.printStackTrace();
            } catch (InterruptedException asdf) {
                asdf.printStackTrace();
                System.out.println("asdasdasd");
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
        });


        mainPane.setCenter(textArea);
        mainPane.setStyle(("-fx-focus-color: transparent!important;"));
        Pane bottomPane = new Pane();
        loadingLabel.setMinHeight(30);
        VBox bottomBox = new VBox();

        //closeToEndScriptLabel.setMinHeight(30);


        Button haltScriptButton = new Button("Halt script");
        haltScriptButton.setOnAction(e -> {
            scriptHalter.setUserWantsToHaltScript(true);
            try {
                Thread.sleep(1200);
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
        bottomBorderPane.setCenter(resetHaltScriptButton);

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
        bottomBox.getChildren().addAll(loadingLabel, bottomBorderPane, blankLabelSpacer1, blankLabelSpacer2);
        mainPane.setBottom(bottomBox);
        //mybox.getChildren().addAll(nestedBox);
        //clicking in center
        primaryStage.setScene(scene);
        primaryStage.setAlwaysOnTop(true);
        //primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("file:icon.png"));
        mybox.requestFocus();
        scene.getStylesheets().add("css/style.css");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
