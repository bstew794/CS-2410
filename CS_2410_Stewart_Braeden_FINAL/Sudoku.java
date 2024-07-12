package CS_2410_Stewart_Braeden_FINAL;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sudoku extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        ChatBox chatBox = new ChatBox();
        Stage chatStage = new Stage();

        Grid grid = new Grid();

        Text status = new Text("Loading...");
        status.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 48));
        status.setFill(Color.BLUE);

        // loading screen
        FadeTransition statusFade = new FadeTransition(Duration.millis(1000), status);
        statusFade.setFromValue(1.0);
        statusFade.setToValue(0.1);
        statusFade.setCycleCount(Timeline.INDEFINITE);
        statusFade.setAutoReverse(true);
        statusFade.play();

        FlowPane statusPane = new FlowPane();
        statusPane.getChildren().add(status);

        Scene statusScene = new Scene(statusPane);

        Stage statusWindow = new Stage();
        statusWindow.setScene(statusScene);

        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(e ->{
            statusWindow.close();
            primaryStage.show();
            try {
                chatBox.start(chatStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Stage secondaryStage = new Stage();

        Stage winWindow = new Stage();
        winWindow.initModality(Modality.APPLICATION_MODAL);

        Label winLabel = new Label();
        winLabel.setText("You Won!");
        winLabel.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 24));
        winLabel.setTextFill(Color.BLUE);

        Stage loseWindow = new Stage();
        loseWindow.initModality(Modality.APPLICATION_MODAL);

        Button playAgain = new Button();
        playAgain.setText("Play Again?");
        playAgain.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 24));
        playAgain.setOnAction(e ->{
            secondaryStage.show();
            grid.getChildren().clear();
        });

        VBox winVBox = new VBox();
        winVBox.getChildren().addAll(winLabel, playAgain);

        Label loseLabel = new Label();
        loseLabel.setText("The current configuration is not a win state");
        loseLabel.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 24));
        loseLabel.setTextFill(Color.RED);

        VBox loseVBox = new VBox();
        loseVBox.getChildren().add(loseLabel);

        Scene loseScene = new Scene(loseVBox);

        Scene winScene = new Scene(winVBox);

        winWindow.setScene(winScene);

        loseWindow.setScene(loseScene);

        Label errorLabel = new Label();
        errorLabel.setText("ERROR: File not found");
        errorLabel.setTextFill(Color.RED);
        errorLabel.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 24));
        errorLabel.setVisible(false);


        Button testButt = new Button();
        testButt.setText("Check");
        testButt.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 24));
        testButt.setTextFill(Color.BLUE);
        testButt.setOnAction(e ->{
            if (grid.checkWin()){

                winWindow.show();

                primaryStage.close();
            }
            else{
                loseWindow.show();
            }
        });

        Button hintButt = new Button();
        hintButt.setText("Hint");
        hintButt.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 24));
        hintButt.setTextFill(Color.BLUE);
        hintButt.setOnAction(e -> grid.giveHints());

        HBox primaryHBox = new HBox();
        primaryHBox.getChildren().addAll(testButt, hintButt);

        VBox primaryVBox = new VBox(grid, primaryHBox);

        Scene scene1 = new Scene(primaryVBox);

        primaryStage.setScene(scene1);

        Label labFile = new Label();
        labFile.setText("Please enter the filename: ");
        labFile.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 24));

        TextField userFile = new TextField();
        userFile.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 24));

        Button subButt = new Button();
        subButt.setText("Submit");
        subButt.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 24));
        subButt.setOnMouseEntered(e -> subButt.setTextFill(Color.BLUE));
        subButt.setOnMouseExited(e -> subButt.setTextFill(Color.BLACK));
        subButt.setOnAction(e ->{
            try{
                winWindow.close();
                loseWindow.close();
                statusWindow.show();
                secondaryStage.close();

                grid.loadConfig(userFile.getText());

                delay.play();

                errorLabel.setVisible(false);
            }
            catch(FileNotFoundException ex) {
                winWindow.close();
                loseWindow.close();
                secondaryStage.show();
                statusWindow.close();

                errorLabel.setVisible(true);

                ex.printStackTrace();
            }
        });

        HBox hBox = new HBox();
        hBox.getChildren().addAll(userFile, subButt);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(labFile, hBox, errorLabel);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vBox);

        Scene scene = new Scene(borderPane);

        secondaryStage.setScene(scene);
        secondaryStage.show();
    }
}
class Grid extends GridPane{
    private int sol[][] = new int[9][9];

    Grid(){
        super();
        setUp();
    }

    public boolean checkWin(){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                GridEntry tempEntry = (GridEntry) getEntry(j, i);
                if (tempEntry.getValue() != sol[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    public void giveHints(){
        int hintCount = 0;

        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                GridEntry tempEntry = (GridEntry) getEntry(j, i);

                if(tempEntry.getValue() == 0){
                    tempEntry.setValue(sol[i][j]);

                    String tempText = Integer.toString(tempEntry.getValue());

                    tempEntry.setText(tempText);
                    tempEntry.setStyle("-fx-text-inner-color: blue");
                    tempEntry.setEditable(false);

                    hintCount++;

                    if (hintCount >= 2){
                        return;
                    }
                }
                else if(tempEntry.getValue() == sol[i][j]){
                    tempEntry.setFont(Font.font("Times New Roman", FontWeight.BOLD, 48));
                    tempEntry.setEditable(false);
                }
                else{
                    tempEntry.setStyle("-fx-text-inner-color: red");
                }
            }
        }
    }

    private Node getEntry(int col, int row){
        for (Node entry : super.getChildren()){
            if (super.getColumnIndex(entry) == col && super.getRowIndex(entry) == row){
                return entry;
            }
        }
        return null;
    }
    public void setUp(){
        this.setAlignment(Pos.CENTER);

        int smallH = 78;
        int smallW = 80;
        int largeH = smallH + 10;
        int largeW = smallW + 10;

        ColumnConstraints smallColCon = new ColumnConstraints(smallW, smallW, Double.MAX_VALUE);
        ColumnConstraints largeColCon = new ColumnConstraints(largeW, largeW, Double.MAX_VALUE);

        ColumnConstraints col = smallColCon;
        col.setHalignment(HPos.LEFT);
        ColumnConstraints col1 = smallColCon;
        col1.setHalignment(HPos.LEFT);
        ColumnConstraints col2 = largeColCon;
        col2.setHalignment(HPos.LEFT);
        ColumnConstraints col3 = smallColCon;
        col3.setHalignment(HPos.LEFT);
        ColumnConstraints col4 = smallColCon;
        col4.setHalignment(HPos.LEFT);
        ColumnConstraints col5 = largeColCon;
        col5.setHalignment(HPos.LEFT);
        ColumnConstraints col6 = smallColCon;
        col6.setHalignment(HPos.LEFT);
        ColumnConstraints col7 = smallColCon;
        col7.setHalignment(HPos.LEFT);
        ColumnConstraints col8 = smallColCon;
        col8.setHalignment(HPos.LEFT);

        this.getColumnConstraints().addAll(col, col1, col2, col3, col4, col5, col6, col7, col8);

        RowConstraints smallRowCon = new RowConstraints(smallH, smallH, Double.MAX_VALUE);
        RowConstraints largeRowCon = new RowConstraints(largeH, largeH, Double.MAX_VALUE);

        RowConstraints row = smallRowCon;
        row.setValignment(VPos.TOP);
        RowConstraints row1 = smallRowCon;
        row1.setValignment(VPos.TOP);
        RowConstraints row2 = largeRowCon;
        row2.setValignment(VPos.TOP);
        RowConstraints row3 = smallRowCon;
        row3.setValignment(VPos.TOP);
        RowConstraints row4 = smallRowCon;
        row4.setValignment(VPos.TOP);
        RowConstraints row5 = largeRowCon;
        row5.setValignment(VPos.TOP);
        RowConstraints row6 = smallRowCon;
        row6.setValignment(VPos.TOP);
        RowConstraints row7 = smallRowCon;
        row7.setValignment(VPos.TOP);
        RowConstraints row8 = largeRowCon;
        row8.setValignment(VPos.TOP);

        this.getRowConstraints().addAll(row, row1, row2, row3, row4, row5, row6, row7, row8);

        BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        this.setBackground(background);

    }

    public void loadConfig(String fileName) throws FileNotFoundException{
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        String line;
        String numbers[];
        int i = 0;

        while (scanner.hasNextLine()){
            line = scanner.nextLine();
            numbers = line.split(" ");

            for (int j = 0; j < numbers.length; j++){

                GridEntry tempEntry = new GridEntry();
                tempEntry.setX(i);
                tempEntry.setY(j);
                tempEntry.setValue(Integer.parseInt(numbers[j]));

                sol[i][j] = tempEntry.getValue();

                if (tempEntry.getValue() != 0){
                    tempEntry.setFont(Font.font("Times New Roman", FontWeight.BOLD, 48));
                    tempEntry.setText(numbers[j]);
                    tempEntry.setEditable(false);
                }
                else{
                    tempEntry.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 48));

                    sol[i][j] = 0;
                }
                tempEntry.setMaxWidth(86);

                tempEntry.textProperty().addListener((observable, oldValue, newValue) ->{
                    tempEntry.setValue(Integer.parseInt(tempEntry.getText()));
                    tempEntry.setStyle("-fx-text-inner-color: black");
                });

                super.add(tempEntry, j, i);
            }
            i++;
        }

        solve();
    }

    private void solve(){
        int[][] tempSol = SudokuSolver.solve(sol);

        sol = tempSol;
        System.out.println("solved");
    }
}


class GridEntry extends TextField{
    private int value;
    private int x;
    private int y;

    public void setValue(int num){
        this.value = num;
    }
    public int getValue(){
        return value;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getX() {
        return x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getY() {
        return y;
    }
}
