package model;

import java.util.ArrayList;

/**
 * Created by Viljami on 13.6.2017.
 */
public class Day {

    int dayNumber;
    int monthNumber;
    int year;
    ArrayList<Task> tasks  = new ArrayList<>();


    public Day(int day, int month, int year){

        dayNumber = day;
        monthNumber = month;
        this.year = year;


    }

    public String toString(){
        String day = Integer.toString(dayNumber);
        String month = Integer.toString(monthNumber);
        String year = Integer.toString(this.year);
        return day + "." + month + "." + year;
    }
}
