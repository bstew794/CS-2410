package CS_2410_Stewart_Braeden_HW4;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class ConnectFour extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public GridPane gridPaneSetUp(){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);

        ColumnConstraints col = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        col.setHalignment(HPos.CENTER);
        ColumnConstraints col1 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        col1.setHalignment(HPos.CENTER);
        ColumnConstraints col2 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        col2.setHalignment(HPos.CENTER);
        ColumnConstraints col3 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        col3.setHalignment(HPos.CENTER);
        ColumnConstraints col4 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        col4.setHalignment(HPos.CENTER);
        ColumnConstraints col5 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        col5.setHalignment(HPos.CENTER);

        grid.getColumnConstraints().addAll(col, col1, col2, col3, col4, col5);

        RowConstraints row = new RowConstraints(100, 100, Double.MAX_VALUE);
        row.setValignment(VPos.CENTER);
        RowConstraints row1 = new RowConstraints(100, 100, Double.MAX_VALUE);
        row1.setValignment(VPos.CENTER);
        RowConstraints row2 = new RowConstraints(100, 100, Double.MAX_VALUE);
        row2.setValignment(VPos.CENTER);
        RowConstraints row3 = new RowConstraints(100, 100, Double.MAX_VALUE);
        row3.setValignment(VPos.CENTER);
        RowConstraints row4 = new RowConstraints(100, 100, Double.MAX_VALUE);
        row4.setValignment(VPos.CENTER);
        RowConstraints row5 = new RowConstraints(100, 100, Double.MAX_VALUE);
        row5.setValignment(VPos.CENTER);

        grid.getRowConstraints().addAll(row, row1, row2, row3, row4, row5);

        BackgroundFill backgroundFill = new BackgroundFill(Color.NAVY, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        grid.setBackground(background);

        return grid;
    }

    @Override
    public void start(Stage primaryStage){
        Stage winWin = new Stage();
        Text winner = new Text();
        winner.setFont(Font.font("Times New Roman", 100));
        HBox hBox = new HBox();
        hBox.getChildren().add(winner);
        Scene scene2 = new Scene(hBox);
        winWin.setTitle("Results");
        winWin.setScene(scene2);

        GridPane gridPane = gridPaneSetUp();
        Grid grid = new Grid();
        primaryStage.setTitle("Connect Four");
        Player player = new Player(true);

        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 6; j++){
                GridEntry gridEntry = new GridEntry(50, Color.WHITE, i, j);
                grid.insert(gridEntry);

                gridEntry.setOnMouseEntered(e ->{
                    if (gridEntry.getIsClicked()){
                        gridEntry.setFill(player.getPlayFill());
                    }
                });
                gridEntry.setOnMouseExited(e -> {
                    if (gridEntry.getIsClicked()) {
                        gridEntry.setFill(Color.WHITE);
                    }
                });
                gridEntry.setOnMouseClicked(e -> {
                    if (grid.checkFail()){
                        winner.setText("No one wins, there are only survivors");
                        winner.setFill(Color.BLACK);
                        winWin.show();
                    }
                    grid.checkValid(gridEntry);

                    if (gridEntry.getIsClicked() && gridEntry.getValid()){
                        gridEntry.setFill(player.getPlayFill());
                        gridEntry.setPlayNum();

                        ArrayList<GridEntry> winResult = grid.returnWins(gridEntry);

                        if (winResult.size() >= 4){
                            for (int k = 0; k < 4; k++){
                                winResult.get(k).getFade().play();
                            }
                            winner.setText(player.getPlayColor() + " wins!");
                            winner.setFill(gridEntry.getFill());
                            winWin.show();
                        }
                        player.switchPlay();
                        gridEntry.setClick(false);
                    }
                });
                gridPane.add(gridEntry, i, j);
            }
        }

        winWin.setOnCloseRequest(e -> {
            primaryStage.close();
            winWin.close();
        });

        Scene scene = new Scene(gridPane, 700, 700);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
class Grid{
    private GridEntry list[][];
    private ArrayList<GridEntry> winList;

    Grid(){
        list = new GridEntry [6][6];
    }
    public void insert(GridEntry entry){
        list[entry.getColIndex()][entry.getRowIndex()] = entry;
    }
    public void checkValid(GridEntry entry){
        if (entry.getColIndex() == 5){
            entry.setValid(true);
        }
        else if (!list[entry.getColIndex() + 1][entry.getRowIndex()].getIsClicked()){
            entry.setValid(true);
        }
    }
    public boolean checkFail(){
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 6; j++){
                if (list[i][j].getIsClicked()){
                    return false;
                }
            }
        }
        return true;
    }
    public ArrayList<GridEntry> returnWins(GridEntry entry){
        winList = new ArrayList<>();
        winList.clear();
        winList = lookLeft(entry.getColIndex(), entry.getRowIndex(), entry.getPlayNum());

        if (winList.size() >= 4){
            return winList;
        }
        winList.clear();
        winList = lookLeftUp(entry.getColIndex(), entry.getRowIndex(), entry.getPlayNum());

        if (winList.size() >= 4){
            return winList;
        }
        winList.clear();
        winList = lookUp(entry.getColIndex(), entry.getRowIndex(), entry.getPlayNum());

        if (winList.size() >= 4){
            return winList;
        }
        winList.clear();
        winList = lookRightUp(entry.getColIndex(), entry.getRowIndex(), entry.getPlayNum());

        if (winList.size() >= 4){
            return winList;
        }
        winList.clear();
        winList = lookRight(entry.getColIndex(), entry.getRowIndex(), entry.getPlayNum());

        if (winList.size() >= 4){
            return winList;
        }
        winList.clear();
        winList = lookDownR(entry.getColIndex(), entry.getRowIndex(), entry.getPlayNum());

        if (winList.size() >= 4){
            return winList;
        }
        winList.clear();
        winList = lookDown(entry.getColIndex(), entry.getRowIndex(), entry.getPlayNum());

        if (winList.size() >= 4){
            return winList;
        }
        winList.clear();
        winList = lookDownL(entry.getColIndex(), entry.getRowIndex(), entry.getPlayNum());

        if (winList.size() >= 4){
            return winList;
        }
        return winList;
    }
    private ArrayList<GridEntry> lookLeft(int x, int y, int playNum){
        if (y < 0){
            return winList;
        }
        else if (list[x][y].getPlayNum() != playNum){
            return winList;
        }
        winList.add(list[x][y]);

        return lookLeft(x, y - 1, playNum);
    }
    private ArrayList<GridEntry> lookLeftUp(int x, int y, int playNum){
        if (y < 0 || x < 0){
            return winList;
        }
        else if (list[x][y].getPlayNum() != playNum){
            return winList;
        }
        winList.add(list[x][y]);

        return lookLeftUp(x - 1, y - 1, playNum);
    }
    private ArrayList<GridEntry> lookUp(int x, int y, int playNum){
        if (x < 0){
            return winList;
        }
        else if (list[x][y].getPlayNum() != playNum){
            return winList;
        }
        winList.add(list[x][y]);

        return lookUp(x - 1, y, playNum);
    }
    private ArrayList<GridEntry> lookRightUp(int x, int y, int playNum){
        if (y > 5 || x < 0){
            return winList;
        }
        else if (list[x][y].getPlayNum() != playNum){
            return winList;
        }
        winList.add(list[x][y]);

        return lookRightUp(x - 1, y + 1, playNum);
    }
    private ArrayList<GridEntry> lookRight(int x, int y, int playNum){
        if ( y > 5){
            return winList;
        }
        else if (list[x][y].getPlayNum() != playNum){
            return winList;
        }
        winList.add(list[x][y]);

        return lookRight(x, y + 1, playNum);
    }
    private ArrayList<GridEntry> lookDownR(int x, int y, int playNum){
        if (y > 5 || x > 5){
            return winList;
        }
        else if (list[x][y].getPlayNum() != playNum){
            return winList;
        }
        winList.add(list[x][y]);

        return lookDownR(x + 1, y + 1, playNum);
    }
    private ArrayList<GridEntry> lookDownL(int x, int y, int playNum){
        if (y < 0 || x > 5){
            return winList;
        }
        else if (list[x][y].getPlayNum() != playNum){
            return winList;
        }
        winList.add(list[x][y]);

        return lookDownL(x + 1, y - 1, playNum);
    }
    private ArrayList<GridEntry> lookDown(int x, int y, int playNum){
        if (x > 5){
            return winList;
        }
        else if (list[x][y].getPlayNum() != playNum){
            return winList;
        }
        winList.add(list[x][y]);

        return lookDown(x + 1, y, playNum);
    }
}
class Player{
    private boolean isPlay1;
    private Color playFill;
    private String playColor;

    Player(boolean isPlayer1){
        setIsPlay1(isPlayer1);
        setPlayFill();
        setPlayColor();
    }
    public void setIsPlay1(boolean state){
        isPlay1 = state;
    }
    public boolean getIsPlay1 (){
        return isPlay1;
    }
    public void setPlayFill(){
        if (getIsPlay1()){
            playFill = Color.RED;
        }
        else{
            playFill = Color.YELLOW;
        }
    }
    public void setPlayColor(){
        if (getIsPlay1()){
            playColor = "Red";
        }
        else{
            playColor = "Yellow";
        }
    }
    public String getPlayColor(){
        return playColor;
    }
    public Color getPlayFill(){
        return playFill;
    }
    public void switchPlay(){
        if(isPlay1){
            setIsPlay1(false);
            setPlayFill();
            setPlayColor();
        }
        else{
            setIsPlay1(true);
            setPlayFill();
            setPlayColor();
        }
    }
}
class GridEntry extends Circle{
    private FadeTransition fade;
    private boolean notClicked;
    private boolean isValid;
    private int rowIndex;
    private int colIndex;
    private int playNum;

    GridEntry(double cirRadius, Paint cirFill, int rIndex, int cIndex){
        super(cirRadius, cirFill);
        setPlayNum();
        setFade();
        setRowIndex(rIndex);
        setColIndex(cIndex);
        setClick(true);
        setValid(false);
    }
    public boolean getIsClicked(){
        return notClicked;
    }
    public void setClick(boolean state){
        notClicked = state;
    }
    private void setFade(){
        fade = new FadeTransition(Duration.millis(3000), this);
        fade.setFromValue(1.0);
        fade.setToValue(0.3);
        fade.setCycleCount(4);
        fade.setAutoReverse(true);
    }
    public FadeTransition getFade(){
        return fade;
    }
    public void setValid(boolean valid){
        isValid = valid;
    }
    public boolean getValid(){
        return isValid;
    }
    public void setPlayNum(){
        if (this.getFill() != Color.WHITE){
            if (this.getFill() == Color.RED){
                playNum = 1;
            }
            else{
                playNum = 2;
            }
        }
        else{
            playNum = 0;
        }
    }
    public int getPlayNum(){
        return playNum;
    }
    public int getRowIndex(){
        return rowIndex;
    }
    public void setRowIndex(int rIndex){
        rowIndex = rIndex;
    }
    public int getColIndex(){
        return colIndex;
    }
    public void setColIndex(int cIndex){
        colIndex = cIndex;
    }
}
