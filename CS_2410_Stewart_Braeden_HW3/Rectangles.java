package CS_2410_Stewart_Braeden_HW3;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Scanner;

public class Rectangles extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {

        double[] storeList = setUp();

        double rec1CX = storeList[0];
        double rec1CY = storeList[1];
        double rec1Width = storeList[2];
        double rec1Height = storeList[3];
        double rec2CX = storeList[4];
        double rec2CY = storeList[5];
        double rec2Width = storeList[6];
        double rec2Height = storeList[7];

        double rec1RCX = rec1CX + rec1Width;
        double rec1RCY = rec1CY + rec1Height;
        double rec2RCX = rec2CX + rec2Width;
        double rec2RCY = rec2CY + rec2Height;

        boolean isOver = true;
        boolean isInside = false;

        if (rec1RCX <= rec2CX || rec2RCX <= rec1CX){
            isOver = false;
        }
        else if (rec1RCY <= rec2CY || rec2RCY <= rec1CY){
            isOver = false;
        }
        if (isOver){
            if (rec1CX >= rec2CX && rec1CY >= rec2CY){
                if (rec1RCX <= rec2RCX && rec1RCY <= rec2RCY){
                    isInside = true;
                }
            }
            else if (rec2CX >= rec1CX && rec2CY >= rec1CY){
                if (rec2RCX <= rec1RCX && rec2RCY <= rec1RCY){
                    isInside = true;
                }
            }
        }
        Label result = new Label();

        Rectangle rec1 = new Rectangle(rec1CX, rec1CY, rec1Width, rec1Height);
        rec1.setFill(Color.TRANSPARENT);
        rec1.setStroke(Color.BLUE);

        Rectangle rec2 = new Rectangle(rec2CX, rec2CY, rec2Width, rec2Height);
        rec2.setFill(Color.TRANSPARENT);
        rec2.setStroke(Color.BLACK);

        Group group = new Group();
        group.getChildren().addAll(rec1, rec2);

        if (isOver){
            result.setText("The rectangles overlap");

            if (isInside){
                result.setText("One rectangle is contained in another");
            }
        }
        else{
            result.setText("The rectangles do not overlap");
        }
        result.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.REGULAR, 24));

        BorderPane layout = new BorderPane();
        layout.setCenter(group);
        layout.setBottom(result);

        Scene scene = new Scene(layout,500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private double[] setUp(){
        Scanner input = new Scanner(System.in);

        String currRec;
        String attribute = "";

        double[] storeList = new double[8];

        for (int i = 0; i < 8; i++){
            if (i > 3){
                currRec = "Rectangle 2";
            }
            else{
                currRec = "Rectangle 1";
            }
            if (i == 0 || i== 4){
                attribute = "center X";
            }
            if (i == 1 || i == 5){
                attribute = "center Y";
            }
            if (i == 2 || i == 6){
                attribute = "width";
            }
            if (i == 3 || i == 7){
                attribute = "height";
            }
            String prompt = "Enter the " + attribute + " of " + currRec;
            System.out.println(prompt);
            storeList[i] = input.nextDouble();
        }

        return storeList;
    }
}
