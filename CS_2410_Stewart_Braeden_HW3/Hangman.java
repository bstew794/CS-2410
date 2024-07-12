package CS_2410_Stewart_Braeden_HW3;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Hangman extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hangman");
        double sceneWidth = 500;
        double sceneHeight = 500;
        double MARGIN = 25;
        double starAng = 0;
        double endAng = 180;

        Group group = new Group();
        Scene scene = new Scene(group, sceneWidth, sceneHeight);
        Arc ground = new Arc();
        ground.setFill(Color.TRANSPARENT);
        ground.setStroke(Color.BLACK);
        ground.setStartAngle(starAng);
        ground.setLength(endAng);
        Line vPole = new Line();
        Line hPole = new Line();
        Line vPole1 = new Line();
        Circle head = new Circle();
        head.setFill(Color.TRANSPARENT);
        head.setStroke(Color.BLACK);
        Line torso = new Line();
        Line rArm = new Line();
        Line lArm = new Line();
        Line rLeg = new Line();
        Line lLeg = new Line();

        int groundRXInt = (int) scene.getWidth() / 5;
        double groundRX = groundRXInt;
        double groundCX = groundRX + MARGIN;
        int hPoleEndXInt = (int)(11 * (scene.getWidth()/ 16));
        double hPoleEndX = hPoleEndXInt;

        ground.setCenterX(groundCX);
        ground.setRadiusX(groundRX);
        vPole.setStartX(groundCX);
        vPole.setEndX(groundCX);
        hPole.setStartX(groundCX);
        hPole.setEndX(hPoleEndX);
        vPole1.setStartX(hPoleEndX);
        vPole1.setEndX(hPoleEndX);
        head.setCenterX(hPoleEndX);
        torso.setStartX(hPoleEndX);
        torso.setEndX(hPoleEndX);
        lArm.setStartX(hPoleEndX);
        lArm.setEndX(hPoleEndX + groundRX);
        rArm.setStartX(hPoleEndX);
        rArm.setEndX(hPoleEndX - groundRX);
        lLeg.setStartX(hPoleEndX);
        lLeg.setEndX(hPoleEndX + groundRX);
        rLeg.setStartX(hPoleEndX);
        rLeg.setEndX(hPoleEndX - groundRX);

        int groundRYInt = (int) scene.getHeight() / 10;
        double groundRY = groundRYInt;
        double groundCY = scene.getHeight() - MARGIN;
        double vPoleEndY = 2 * MARGIN;
        double vPole1EndY = vPoleEndY + MARGIN;
        int headRInt = (int) (3 * (groundRY / 4));
        double headR = headRInt;
        double headCY = vPole1EndY + headR;
        double torsoStartY = headCY + headR;
        double torsoEndY = torsoStartY + (2 * headR);

        ground.setCenterY(groundCY);
        ground.setRadiusY(groundRY);
        vPole.setStartY(groundCY - groundRY);
        vPole.setEndY(vPoleEndY);
        hPole.setStartY(vPoleEndY);
        hPole.setEndY(vPoleEndY);
        vPole1.setStartY(vPoleEndY);
        vPole1.setEndY(vPole1EndY);
        head.setCenterY(headCY);
        head.setRadius(headR);
        torso.setStartY(torsoStartY);
        lArm.setStartY(torsoStartY);
        lArm.setEndY(torsoEndY);
        rArm.setStartY(torsoStartY);
        rArm.setEndY(torsoEndY);
        torso.setEndY(torsoEndY);
        lLeg.setStartY(torsoEndY);
        lLeg.setEndY(torsoEndY + (2 * headR));
        rLeg.setStartY(torsoEndY);
        rLeg.setEndY(torsoEndY + (2 * headR));

        group.getChildren().addAll(ground, vPole, hPole, vPole1, head, torso, lArm, rArm, lLeg, rLeg);

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
