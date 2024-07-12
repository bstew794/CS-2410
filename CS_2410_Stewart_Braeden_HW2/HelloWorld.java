package CS_2410_Stewart_Braeden_HW2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloWorld extends Application {

    public void start(Stage primaryStage) throws Exception{
        Text text = new Text();
        text.setText("Hello");
        text.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 48));
        text.setFill(Color.DARKSEAGREEN);
        text.setX(25);
        text.setY(50);
        Text text1 = new Text();
        text1.setText("World");
        text1.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 48));
        text1.setFill(Color.CRIMSON);
        text1.setX(75);
        text1.setY(100);
        primaryStage.setTitle("Hello World");
        StackPane root = new StackPane();
        Pane pane = new Pane();
        pane.getChildren().add(text);
        Pane pane1 = new Pane();
        pane.getChildren().add(text1);
        root.getChildren().addAll(pane, pane1);
        Scene scene = new Scene(root, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
