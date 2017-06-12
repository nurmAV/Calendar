package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;





public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Stage window = primaryStage;
        window.setTitle("Calendar");
        HBox hbox = new HBox();
        VBox vbox = new VBox();

        VBox taskList = new VBox(5);
        taskList.setAlignment(Pos.CENTER);
        Label date = new Label("Select a day to see your tasks");
        date.setTextFill(Color.LIGHTGREY);
        date.setAlignment(Pos.CENTER);
        taskList.getChildren().add(date);


        GridPane  grid = new GridPane();
        grid.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setMinWidth(200);


        MonthView month = new MonthView();
        Label monthName = new Label(month.getMonth());
        monthName.setTextFill(Color.WHITE);
        monthName.setFont(new Font(18));
        monthName.setAlignment(Pos.CENTER);
        monthName.setPadding(new Insets(0, 20, 0, 0));

        int[] calendar = month.getCalendarArray();
        Label[] dayNos = new Label[42];

        for(int i = 0; i < 7; i++ ){
            Label weekday = new Label(new MonthView().findWeekdaybyOffset(i));
            weekday.setTextFill(Color.LIGHTGREY);
            grid.setConstraints(weekday, i, 0);
            grid.getChildren().add(weekday);
        }
        for(int row = 0; row <= 5; row++) {
            for (int i = 0; i < 7; i++) {
                Label dayNo = new Label(Integer.toString(calendar[7 * row +i]));
                dayNo.setTextFill(Color.WHITE);
                dayNos[7 *row + i] = dayNo;
                int number = 7 * row + i;
                if(row == 0 &&calendar[7 * row + i] > 7) dayNo.setTextFill(Color.LIGHTGREY);
                if((row == 4 || row == 5) && calendar[7 *row + i] < 12) dayNo.setTextFill(Color.LIGHTGREY);
                if(row != 0 && row != 5 && calendar[ 7 * row + i] == month.getDayNo()) dayNo.setTextFill(Color.web("#BC0001"));

                grid.setConstraints(dayNo, i, row + 1);
                grid.getChildren().add(dayNo);
                dayNo.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {


                        for(Label dayNo: dayNos){
                            dayNo.setStyle("-fx-background-color: #333333;");
                        }

                        dayNo.setStyle("-fx-background-color: #555555;");
                        String monthToPrint = monthName.getText();
                        if(grid.getRowIndex(dayNo) == 1 && Integer.parseInt(dayNo.getText()) > 7){
                            monthToPrint = month.getPreviousMonth();
                        }else if((grid.getRowIndex(dayNo) == 5 || grid.getRowIndex(dayNo) == 6) && Integer.parseInt(dayNo.getText()) < 12){
                            monthToPrint = month.getNextMonth();
                        }

                        date.setText(dayNo.getText() + ". " +monthToPrint.toLowerCase() + "ta" );
                    }
                });

            }
        }


        /*System.out.println(dateString);
        System.out.println(day);
        System.out.println(month);
        System.out.println(weekday);*/






        vbox.getChildren().addAll(monthName, grid);
        vbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(vbox, taskList);
        Scene scene = new Scene(hbox);
        vbox.setStyle("-fx-background-color: #333333;");


        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
