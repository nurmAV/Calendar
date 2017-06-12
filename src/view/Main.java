package view;


/**
 * Created by Viljami on 12.6.2017.
 * This class represents the main window of the program.
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Calendar");
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);
        MonthView monthView = new MonthView();

        HBox hbox = new HBox(5);
        hbox.getChildren().addAll(monthView);
        Scene scene = new Scene(hbox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

