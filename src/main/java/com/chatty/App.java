package com.chatty;

import javafx.application.Application;

import javafx.stage.Stage;


public class App extends Application {



    static Server server;

    @Override
    public void start(Stage primaryStage) throws Exception {
        UserInterfaceService userInterfaceService = new UserInterfaceService(primaryStage);
        Server server = new Server(userInterfaceService);
        server.sendMessage("HI");
    }


}
