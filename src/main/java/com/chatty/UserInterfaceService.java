package com.chatty;

import javafx.stage.Stage;

public class UserInterfaceService {
    private final UserInterface userInterface;

    public UserInterfaceService(Stage primaryStage) {
        this.userInterface = new UserInterface(primaryStage);
    }

    public void addMessageInMessageContainer(String message, boolean isIncoming) {
        userInterface.addMessageInMessageContainer(message, isIncoming);
    }
}
