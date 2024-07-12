package CS_2410_Stewart_Braeden_FINAL;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Key;
import java.util.Scanner;

public class ChatBox extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane window = new BorderPane();
        Scene myScene = new Scene(window);

        // add the chat and editor boxes to the window
        VBox chat = new VBox();
        HBox editor = new HBox();
        window.setCenter(chat);
        window.setBottom(editor);

        // add the editing field and send button to the editor
        TextField editingMessage = new TextField();
        Button sendButton = new Button("Send");
        editor.getChildren().add(editingMessage);
        editor.getChildren().add(sendButton);

        // add event for when send button is pressed
        sendButton.setOnAction(e -> {
            Text sentMessage = new Text("Player: " + editingMessage.getText());
            editingMessage.clear();
            chat.getChildren().add(sentMessage);
            boolean isShort = false;
            while (!isShort) {
                if (chat.getChildren().size() > 10) {
                    chat.getChildren().remove(0);
                }
                else {
                    isShort = true;
                }
            }

        });
        myScene.setOnKeyPressed(e -> { // alternate way to send message: press enter
            if (e.getCode().equals(KeyCode.ENTER)) {
                sendButton.setDefaultButton(true);
            }
        });

        // chat portion adjustments
        chat.setSpacing(10);
        window.setPrefSize(300, 300);

        // set stage and scene
        Stage myStage = new Stage();
        myStage.setScene(myScene);
        myStage.show();
    }
}
