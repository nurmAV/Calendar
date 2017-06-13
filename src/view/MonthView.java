package view;

import javafx.geometry.Pos;
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

        // Creating a new Month instance.
            month = new Month(new Date());
            int[] calendar = month.getCalendar();

        // Creating a label for the month.
            Label monthText = new Label(month.getMonthName());
            monthText.setTextFill(Color.LIGHTGREY);
            this.setAlignment(Pos.CENTER);
            this.getChildren().add(monthText);
            this.setStyle("-fx-background-color: #333333");

        // Creating a GridPane to hold the actual calendar elements.
            grid = new GridPane();
            grid.setVgap(10);
            grid.setHgap(10);

        // Writing the names of the weekdays
            for(int i = 0; i < 7; i++){
                Label weekday = new Label(month.getWeekdayWithOffset(i));
                weekday.setTextFill(Color.LIGHTGREY);

                grid.setConstraints(weekday, i,0);
                grid.getChildren().add(weekday);
        }

        // Writing the numbers of days row by row
        int offset = month.getFirstDayOffset();
        int numPreviousMonthDays = month.getPreviousMonthDays();
            for(int row = 0; row < 6; row++ ){
                for(int column = 0; column < 7; column++){
                     Label dayNumber = new Label(Integer.toString(calendar[7 * row + column]));
                     dayNumber.setTextFill(Color.LIGHTGREY);

                     grid.setConstraints(dayNumber, column, row + 1);
                     grid.getChildren().add(dayNumber);
                }

            }
       this.getChildren().addAll(grid);
    }
}
