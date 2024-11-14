package com.chatty;

import javafx.application.Application;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class App extends Application {

    static int PORT = 1234;
    static int WINDOW_WIDTH = 600;
    static int WINDOW_HEIGHT = 600;

    static int MESSAGE_INPUT_WIDTH = 400;

    static int MESSAGE_INPUT_HEIGHT = 200;

    static int SEND_MESSAGE_BUTTON_WIDTH = WINDOW_WIDTH - MESSAGE_INPUT_WIDTH - 30;

    static String hostname = "localhost";
    static Server server = new Server();

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create a button with text
        Button button = new Button("Send");
        TextArea messageInput = new TextArea();
        messageInput.setMaxSize(MESSAGE_INPUT_WIDTH, MESSAGE_INPUT_HEIGHT);
        messageInput.setMinSize(MESSAGE_INPUT_WIDTH, MESSAGE_INPUT_HEIGHT);
        messageInput.setTranslateY(WINDOW_HEIGHT - MESSAGE_INPUT_HEIGHT - 10);
        messageInput.setTranslateX(10);

        button.setMaxSize(SEND_MESSAGE_BUTTON_WIDTH, 30);
        button.setMinSize(SEND_MESSAGE_BUTTON_WIDTH, 30);
        button.setTranslateX(MESSAGE_INPUT_WIDTH + 20);
        button.setTranslateY(WINDOW_HEIGHT - MESSAGE_INPUT_HEIGHT - 10);

        button.setOnAction(event -> {
            try {
                String message = messageInput.getText();
                server.sendMessage(message);
                messageInput.clear();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Create a layout to hold the button
        AnchorPane root = new AnchorPane();
        root.getChildren().add(messageInput);
        root.getChildren().add(button);

        // Create a scene with the layout, set its size
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setResizable(false);
        // Set up the stage with the scene and a title
        primaryStage.setTitle("JavaFX Button App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
