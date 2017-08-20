package com.techmaster.minesweeper;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MineSweeper extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception {
        GameBoard gb = new GameBoard(15, 20, 0.15f);
        gb.grid.setPadding(new Insets(10, 10, 10, 10));

        BorderPane bdp = new BorderPane();
        bdp.setCenter(gb.grid);

        Scene scene = new Scene(bdp);

        primaryStage.setTitle("MineSweeper");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
