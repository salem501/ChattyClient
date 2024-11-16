package com.chatty;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.control.Button;

public class App extends Application {

    static int WINDOW_WIDTH = 600;
    static int WINDOW_HEIGHT = 600;

    static int MESSAGE_INPUT_WIDTH = 400;

    static int MESSAGE_INPUT_HEIGHT = 200;

    static int SEND_MESSAGE_BUTTON_WIDTH = WINDOW_WIDTH - MESSAGE_INPUT_WIDTH - 30;

    static int MESSAGES_CONTAINER_WIDTH = WINDOW_WIDTH - 20;

    static int MESSAGES_CONTAINER_HEIGHT = WINDOW_HEIGHT - 250;

    static Server server = new Server();

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create a button with text
        Button button = new Button("Send");
        TextArea messageInput = new TextArea();
        VBox messagesContainer = new VBox();
        ScrollPane scrollPane = new ScrollPane(messagesContainer);
        scrollPane.setMaxSize(MESSAGES_CONTAINER_WIDTH, MESSAGES_CONTAINER_HEIGHT);
        scrollPane.setMinSize(MESSAGES_CONTAINER_WIDTH, MESSAGES_CONTAINER_HEIGHT);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setTranslateX(10);
        scrollPane.setTranslateY(15);

        messagesContainer.setStyle("-fx-border-color: cyan; -fx-border-width: 3 ; -fx-background-color: white; -fx-padding: 20");
        messagesContainer.setMaxWidth(MESSAGES_CONTAINER_WIDTH);
        messagesContainer.setMinWidth(MESSAGES_CONTAINER_WIDTH);

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
        root.getChildren().add(scrollPane);
        messagesContainer.getChildren().add(wrapMessageInBubble("Salem salem salem salem", true));
        messagesContainer.getChildren().add(wrapMessageInBubble("Salem salem salem salem", false));
        messagesContainer.getChildren().add(wrapMessageInBubble("Salem salem salem salem", true));
        messagesContainer.getChildren().add(wrapMessageInBubble("Salem salem salem salem", true));
        messagesContainer.getChildren().add(wrapMessageInBubble("Salem salem salem salem", false));
        messagesContainer.getChildren().add(wrapMessageInBubble("Salem salem salem salem", false));
        messagesContainer.getChildren().add(wrapMessageInBubble("Salem salem salem salem", false));
        messagesContainer.getChildren().add(wrapMessageInBubble("Salem salem salem salem", true));
        messagesContainer.getChildren().add(wrapMessageInBubble("Salem salem salem salem", true));
        messagesContainer.getChildren().add(wrapMessageInBubble("Salem salem salem salem", true));
        messagesContainer.getChildren().add(wrapMessageInBubble("Salem salem salem salem", true));
        messagesContainer.getChildren().add(wrapMessageInBubble("Salem salem salem salem", true));
        messagesContainer.getChildren().add(wrapMessageInBubble("Salem salem salem salem", true));
        messagesContainer.getChildren().add(wrapMessageInBubble("Salem salem salem salem", false));
        messagesContainer.getChildren().add(wrapMessageInBubble("Salem salem salem salem", false));
        messagesContainer.getChildren().add(wrapMessageInBubble("Salem salem salem salem", false));
        messagesContainer.getChildren().add(wrapMessageInBubble("Salem salem salem salem", false));
        messagesContainer.getChildren().add(wrapMessageInBubble("Salem salem salem salem", false));
        messagesContainer.getChildren().add(wrapMessageInBubble("Salem salem salem salem", false));
        messagesContainer.getChildren().add(wrapMessageInBubble("Salem salem salem salem", false));
        messagesContainer.getChildren().add(wrapMessageInBubble("Salem salem salem salem", false));
        messagesContainer.getChildren().add(wrapMessageInBubble("Salem salem salem salem", false));
        messagesContainer.getChildren().add(wrapMessageInBubble("Salem salem salem salem", false));
        messagesContainer.getChildren().add(wrapMessageInBubble("Salem salem salem salem", false));
        messagesContainer.getChildren().add(wrapMessageInBubble("Salem salem salem salem", false));

        // Create a scene with the layout, set its size
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setResizable(false);
        // Set up the stage with the scene and a title
        primaryStage.setTitle("JavaFX Button App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static HBox wrapMessageInBubble(String message, boolean isIncoming) {
        Text messageBubble = new Text();
        messageBubble.setText(message);
        messageBubble.setWrappingWidth(150);
        TextFlow textFlow = new TextFlow(messageBubble);
        HBox hbox = new HBox();
        hbox.getChildren().add(textFlow);
        if (isIncoming) {
            hbox.setAlignment(Pos.CENTER_LEFT);
            textFlow.setStyle("-fx-background-color: #eeeeee; -fx-background-radius: 5px; -fx-padding: 5 10 5 10");
            messageBubble.setFill(Color.valueOf("#0f0f0f"));
        } else {
            hbox.setAlignment(Pos.CENTER_RIGHT);
            textFlow.setStyle("-fx-background-color: #7d42ec; -fx-background-radius: 5px; -fx-padding: 5 10 5 10");
            messageBubble.setFill(Color.WHITE);
        }
        HBox.setMargin(textFlow, new Insets(0, 0, 5, 0));
        return hbox;
    }
}
