package view;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Month;

import java.util.Date;

/**
 * Created by Viljami on 12.6.2017.
 */
public class MonthView extends VBox {

    Month month;
    GridPane grid;

    public MonthView() {


        month = new Month(new Date());
        Label monthText = new Label(month.getMonthName());
        monthText.setTextFill(Color.LIGHTGREY);
        this.getChildren().add(monthText);
        this.setStyle("-fx-background-color: #333333");
        grid = new GridPane();

        // Writing the names of the weekdays
        for(int i = 0; i < 7; i++){
            Label weekday = new Label(month.getWeekdayWithOffset(i));
            weekday.setTextFill(Color.BLACK);

            grid.setConstraints(weekday, i,0);
            grid.getChildren().add(weekday);
        }
       this.getChildren().addAll(grid);
    }
}
