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
            coordsLabel.setText(MouseInfo.getPointerInfo().getLocation().toString());
        });
        Label warpTransform1Label = new Label("Macro 1:");
        Button warpMutliButton = new Button("Run1");
        Label repeatLabel = new Label("Number of times to repeat:");
        TextField numTimes = new TextField();
        numTimes.setText("1");
        numTimes.setMaxWidth(40);
        //vbox is called mybox
        //need to add a status label that the multi button changes
        Label loadingLabel = new Label("OK");

        MenuItem optionsItem1 = new MenuItem("Always on top");
        MenuItem optionsItem2 = new MenuItem("Reset");
        MenuItem optionsItem3 = new Menu("Quit");
        Menu optionsMenu = new Menu("Options", null, optionsItem1, optionsItem2, optionsItem3);
        MenuItem helpItem1 = new MenuItem("About");
        MenuItem helpItem2 = new Menu("Website");
        MenuItem helpItem3 = new Menu("Git repo");
        MenuItem helpItem4 = new Menu("How to extend");
        Menu helpMenu = new Menu("Help", null, helpItem1, helpItem2, helpItem3, helpItem4);
        MenuBar menuBar = new MenuBar(optionsMenu, helpMenu);
        Label info = new Label("To change the functionality of this program, change the lambda expressions for 'warpMutliButton' and 'button'");
        info.setWrapText(true);
        Label info2 = new Label("And use the 'get coords' button to get the current mouse x,y");
        info2.setWrapText(true);


        mybox.getChildren().addAll(menuBar, loadingLabel);
        warpMutliButton.setOnAction( e -> {
            try {
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        loadingLabel.setText("LOADING!!!!!!!!!!!!!!!!!");
                    }
                });
                scene.setCursor(Cursor.WAIT);
                int timesToRepeat = Integer.parseInt(numTimes.getText());
                for (int i = 0; i < timesToRepeat; i++) {
                    clickAndDrag(671, 330, 671, 770);
                    clickAndDrag(841,765, 841, 324);
                    clickAndDrag(1053,329, 1053, 750);
                    clickAndDrag(1214,763, 1214, 334);
                }

                scene.setCursor(Cursor.DEFAULT);
            } catch (AWTException a) {
                a.printStackTrace();
            }
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    loadingLabel.setText("OK");
                }
            });
        });
        Label divider2 = new Label("-----------------------------------------------------------");
        mybox.getChildren().addAll(testButton, coordsLabel, divider2, warpTransform1Label, warpMutliButton);
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
        Label divider = new Label("-----------------------------------------------------------");
        Label warp2Label = new Label("Macro 2: ");
        Label repeatLabel2 = new Label("Number of times to repeat:");
        TextField numTimes2 = new TextField();
        numTimes2.setText("1");
        numTimes2.setMaxWidth(40);

        Button button = new Button("Run2");
        button.setOnAction( e -> {
            try {
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        loadingLabel.setText("LOADING!!!!!!!!!!!!!!!!!");
                    }
                });
                scene.setCursor(Cursor.WAIT);
                int timesToRepeat = Integer.parseInt(numTimes2.getText());
                for (int i = 0; i < timesToRepeat; i++) {
                    click(906, 539);
                }

            } catch (AWTException a) {
                a.printStackTrace();
            }
            scene.setCursor(Cursor.DEFAULT);
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    loadingLabel.setText("OK");
                }
            });
        });


        mybox.getChildren().addAll(divider, warp2Label, button, repeatLabel2);
        HBox h2 = new HBox();
        Button minusButton2 = new Button("-");
        Button plusButton2 = new Button("+");
        minusButton2.setOnAction( e -> {
            int newValue = Integer.parseInt(numTimes2.getText()) - 1;
            numTimes2.setText(Integer.toString(newValue));
        });
        plusButton2.setOnAction( e -> {
            int newValue = Integer.parseInt(numTimes2.getText()) + 1;
            numTimes2.setText(Integer.toString(newValue));
        });
        h2.getChildren().addAll(minusButton2, numTimes2, plusButton2);
        mybox.getChildren().add(h2);
        primaryStage.setScene(scene);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("file:icon.png"));
        mybox.requestFocus();
        scene.getStylesheets().add("css/style.css");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
