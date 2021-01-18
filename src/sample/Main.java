package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.MouseInfo;


public class Main extends Application {

    public static void click(int x, int y) throws AWTException{
        Robot bot = new Robot();
        bot.mouseMove(x, y);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
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
        VBox mybox = new VBox();
        Scene scene = new Scene(mybox);
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

        Label warpTransform1Label = new Label("Macro:");
        Button WarpMultiButton = new Button("Run Macro");
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
        Label info = new Label("To change the functionality of this program, change the lambda expressions for 'WarpMultiButton' and 'button'");
        info.setWrapText(true);
        Label info2 = new Label("And use the 'get coords' button to get the current mouse x,y");
        info2.setWrapText(true);


        mybox.getChildren().addAll(menuBar, loadingLabel);
        WarpMultiButton.setOnAction( e -> {
            try {
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        loadingLabel.setText("LOADING!!!!!!!!!!!!!!!!!");
                    }
                });
                scene.setCursor(Cursor.WAIT);
                int timesToRepeat = Integer.parseInt(numTimes.getText());


                for (int i = 0; i < timesToRepeat; i++) {
                    //this is where the macro stuff happens
                    click(1110, 345);
                    //wait
                    Thread.sleep(2000);




                }

                scene.setCursor(Cursor.DEFAULT);
            } catch (AWTException a) {
                a.printStackTrace();
            } catch (InterruptedException asdf) {
                asdf.printStackTrace();
                System.out.println("asdasdasd");
            }
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    loadingLabel.setText("Status: OK");
                }
            });
        });
        Label divider2 = new Label("-----------------------------------------------------------");
        mybox.getChildren().addAll(testButton, coordsLabel, divider2, warpTransform1Label, textArea, WarpMultiButton);
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
        mybox.getChildren().add(nestedBox);
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
