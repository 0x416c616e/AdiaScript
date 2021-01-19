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
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.MouseInfo;
import java.util.Optional;
import java.util.Set;


public class Main extends Application {

    public static void click(int x, int y) throws AWTException{
        Robot bot = new Robot();
        bot.mouseMove(x, y);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public static void rightClick(int x, int y) throws AWTException {
        Robot bot = new Robot();
        bot.mouseMove(x, y);
        bot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
    }



    public static void clickAndDrag(int x_start, int y_start, int x_end, int y_end) throws AWTException {
        Robot bot = new Robot();
        bot.mouseMove(x_start, y_start);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseMove(x_end, y_end);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("AutoInput");
        primaryStage.setWidth(360);
        primaryStage.setMinWidth(360);
        primaryStage.setHeight(360);
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

        MenuItem fileItem1 = new MenuItem("Open");
        MenuItem fileItem2 = new MenuItem("Save as");
        MenuItem fileItem3 = new MenuItem("Quit");

        Menu fileMenu = new Menu("File", null, fileItem1, fileItem2, fileItem3);

        MenuItem optionsItem1 = new MenuItem("Always on top");
        MenuItem optionsItem2 = new MenuItem("Reset");

        Menu optionsMenu = new Menu("Options", null, optionsItem1, optionsItem2);
        MenuItem helpItem1 = new MenuItem("About");
        Alert aboutAlert = new Alert(Alert.AlertType.INFORMATION);
        aboutAlert.setTitle("About");
        aboutAlert.setHeaderText("About AutoInput");
        aboutAlert.setContentText("This is an input automation scripting language and editor made by 0x416c616e. You can use it to write keyboard/mouse macros" +
                " in order to automate repetitive tasks that require using a GUI rather than something command line-based that can be automated with a shell script.");


        helpItem1.setOnAction(e -> {
            //primaryStage is temporarily set to not be always on top
            //otherwise an alert would be behind it
            primaryStage.setAlwaysOnTop(false);
            Optional<ButtonType> aboutAlertResult = aboutAlert.showAndWait();
            if(aboutAlertResult.get() == ButtonType.OK) {
                //once the alert is closed, set the primaryStage (main program window) to be always on top again
                primaryStage.setAlwaysOnTop(true);
            }
        });

        MenuItem helpItem2 = new Menu("Website");
        Alert websiteAlert = new Alert(Alert.AlertType.CONFIRMATION);
        websiteAlert.setTitle("Website");
        websiteAlert.setHeaderText("would you like to open the developer's website (saintlouissoftware.com) in a browser?");

        helpItem2.setOnAction(e -> {
            primaryStage.setAlwaysOnTop(false);
            Optional<ButtonType> websiteAlertResult = websiteAlert.showAndWait();
            if (websiteAlertResult.get() == ButtonType.OK) {
                System.out.println("You want to open the website in a browser");
                getHostServices().showDocument("https://saintlouissoftware.com/");
                primaryStage.setAlwaysOnTop(true);
            } else if (websiteAlertResult.get() == ButtonType.CANCEL || websiteAlertResult.get() == ButtonType.CLOSE) {
                System.out.println("You do not want to open the website.");
                primaryStage.setAlwaysOnTop(true);
            }
        });

        MenuItem helpItem3 = new Menu("Git repo");
        Menu helpMenu = new Menu("Help", null, helpItem1, helpItem2, helpItem3);
        MenuBar menuBar = new MenuBar(fileMenu, optionsMenu, helpMenu);
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
                    System.out.println("Number of lines in the text area: " + String.valueOf(textArea.getText().split("\n").length));

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
                                        } catch (NumberFormatException numException) {
                                            loadingLabel.setText("Macro error on line " + lineNumber + ": click args must be ints");
                                            scriptingError = true;
                                            break;
                                        }

                                    }
                                    break;
                                case "wait":
                                    scriptIsEmpty = false;
                                    System.out.println("you want to wait on line " + j);
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
                        //!!!!!!!!!!!!!!!!!!!!!!WGERE I LEFT OFF!!!!!!!!!!!!!!!!!!!!!!!!!!
                    }



                    click(1110, 345);
                    rightClick(1110, 345);
                    //wait
                    Thread.sleep(1000);

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
        //need to add runMacroButton, repeatLabel, minusButton, numTimes, plusButton to an HBox

        //and put the vbox in said hbox
        //aaaaaaaaaaaaaaaaaaaaaaaa
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



        mainPane.setCenter(textArea);
        mainPane.setStyle(("-fx-focus-color: transparent!important;"));
        Pane bottomPane = new Pane();
        loadingLabel.setMinHeight(30);
        VBox bottomBox = new VBox();
        Label closeToEndScriptLabel = new Label("To halt a running script, close the AutoInput window.");
        closeToEndScriptLabel.setMinHeight(30);
        bottomBox.getChildren().addAll(loadingLabel, closeToEndScriptLabel);
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
