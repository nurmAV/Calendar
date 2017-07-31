package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Month;

import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage window){

    MainView mainView = new MainView();
    Scene scene = new Scene(mainView);
    window.setTitle("Calendar");
    window.setScene(scene);
    window.show();



    }


    public static void main(String[] args){
        launch(args);
    }



}
