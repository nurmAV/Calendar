package view;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import model.Day;

/**
 * Created by Viljami on 13.6.2017.
 * Lists all the tasks for the given day.
 */
public class TaskListView extends VBox{

    public TaskListView(Day day){
        Label date = new Label(day.toString());
        date.setAlignment(Pos.TOP_CENTER);

        this.getChildren().add(date);

    }
}
