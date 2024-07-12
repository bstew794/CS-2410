package CS_2410_Stewart_Braeden_HW3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;

public class WelcomeToJava extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        String[] message = "Welcome to Java".split("");
        Font font = Font.font("Times New Roman", FontWeight.NORMAL, 24);

        Point center = new Point(200, 200);
        double radius = 100;
        double angle = 0;
        double rotate = 90;

        for (int i = 0; i < message.length; i++, angle += 22, rotate += 22){
            double x = center.getX() + (radius * Math.cos(Math.toRadians(angle)));
            double y = center.getX() + (radius * Math.sin(Math.toRadians(angle)));
            Text text = new Text(x, y, message[i]);

            text.setRotate(rotate);
            text.setFont(font);
            pane.getChildren().add(text);
        }

        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setTitle("Welcome to Java");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
