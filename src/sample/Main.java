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
        primaryStage.setWidth(300);
        primaryStage.setHeight(360);
        BorderPane mainPane = new BorderPane();
        VBox mybox = new VBox();
        mainPane.setTop(mybox);
        Scene scene = new Scene(mainPane);
        Button button2 = new Button("Warp2");
        button2.setOnAction( e -> {
            try {
                clickAndDrag(400,400,500,500);
            } catch (AWTException a) {
                a.printStackTrace();
            }
        });
        Button testButton = new Button("Get coords");
        Label coordsLabel = new Label("(x, y)");
        testButton.setOnAction( e-> {
            //MouseInfo.getPointerInfo().getLocation().getX()
            //coordsLabel.setText(MouseInfo.getPointerInfo().getLocation().toString());
            coordsLabel.setText((int)MouseInfo.getPointerInfo().getLocation().getX() + ", " + (int)MouseInfo.getPointerInfo().getLocation().getY());
        });

        TextArea textArea = new TextArea();
        textArea.setStyle(("height: 100%; -fx-focus-color: transparent;"));


        Label warpTransform1Label = new Label("Macro:");
        Button runMacroButton = new Button("Run Macro");
        Label repeatLabel = new Label("Number of times to repeat:");
        TextField numTimes = new TextField();
        numTimes.setText("1");
        numTimes.setMaxWidth(100);
        //vbox is called mybox
        //need to add a status label that the multi button changes
        Label loadingLabel = new Label("Status: OK");

        MenuItem fileItem1 = new MenuItem("Open");
        MenuItem fileItem2 = new MenuItem("Save");

        Menu fileMenu = new Menu("File", null, fileItem1, fileItem2);

        MenuItem optionsItem1 = new MenuItem("Always on top");
        MenuItem optionsItem2 = new MenuItem("Reset");
        MenuItem optionsItem3 = new Menu("Quit");

        Menu optionsMenu = new Menu("Options", null, optionsItem1, optionsItem2, optionsItem3);
        MenuItem helpItem1 = new MenuItem("About");
        MenuItem helpItem2 = new Menu("Website");
        MenuItem helpItem3 = new Menu("Git repo");
        MenuItem helpItem4 = new Menu("How to extend");
        Menu helpMenu = new Menu("Help", null, helpItem1, helpItem2, helpItem3, helpItem4);
        MenuBar menuBar = new MenuBar(fileMenu, optionsMenu, helpMenu);
        Label info = new Label("To change the functionality of this program, change the lambda expressions for 'runMacroButton' and 'button'");
        info.setWrapText(true);
        Label info2 = new Label("And use the 'get coords' button to get the current mouse x,y");
        info2.setWrapText(true);


        mybox.getChildren().addAll(menuBar);
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
                                        allKeys += "enter,space,backspace,up,down,left,right";
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
                        loadingLabel.setText("Script contains no errors!");
                        //to-do: implement actually parsing script and then running the commands, such as click, rightClick, etc.
                        
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
        Label divider2 = new Label("-----------------------------------------------------------");
        mybox.getChildren().addAll(testButton, coordsLabel, divider2, warpTransform1Label, runMacroButton);
        mybox.getChildren().addAll(repeatLabel);
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
        nestedBox.getChildren().addAll(minusButton, numTimes, plusButton);
        mainPane.setCenter(textArea);
        mainPane.setStyle(("-fx-focus-color: transparent!important;"));
        Pane bottomPane = new Pane();
        loadingLabel.setMinHeight(30);
        mainPane.setBottom(loadingLabel);
        mybox.getChildren().addAll(nestedBox);
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
