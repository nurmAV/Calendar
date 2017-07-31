package model;

import java.text.DateFormatSymbols;
import java.util.Calendar;


/**
 *
 */
public class Month {

    int[] calendarArray = new int[42];
    int month;
    int year;
    String monthName;
    String[] daysOfWeek;

    public Month(){
    this((Calendar.getInstance().get(Calendar.MONTH) + 1) % 12, Calendar.getInstance().get(Calendar.YEAR));
    }

    public Month(int month, int year) {

        // House keeping
        this.month = month;
        this.year = year;



        // Setting up a calendar
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DATE, 1);

        // Finding the name and number of days of the given month
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] monthNames = dfs.getMonths();

        if(month != 0) monthName = monthNames[month - 1];
        else monthName = monthNames[11];
        int givenMonthDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);


        // Finding the weekday of the first day of the given month.
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int beginningIndex = dayOfWeek - 2;
        if(beginningIndex == -1) beginningIndex = 6;
        String[] weekdays= dfs.getWeekdays();


        // Finding the number of days in the previous month
        cal.add(Calendar.MONTH, -1);
        int previousMonthDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);


        // Populating the array with appropriate  day numbers
        for (int previousMonthIndex = 0; previousMonthIndex < beginningIndex; previousMonthIndex++) {

            calendarArray[previousMonthIndex] = previousMonthDays - (beginningIndex - 1) +previousMonthIndex;

        }

        for (int dayIndex = 1; dayIndex <= givenMonthDays; dayIndex++) {

            calendarArray[beginningIndex + dayIndex - 1] = dayIndex;
        }
        for (int nextMonthIndex = 1; nextMonthIndex <= 42 - beginningIndex - givenMonthDays; nextMonthIndex++) {
            calendarArray[beginningIndex - 1 + givenMonthDays + nextMonthIndex] = nextMonthIndex;
        }

    }

    public Month getPreviousMonth() {

        if (month == 1) return new Month(12, year - 1);
        else return new Month(month - 1, year);

    }

    public Month getNextMonth() {
        if (month == 12) return new Month(1, year + 1);
        else return new Month(month + 1, year);
    }

    public int[] getCalendarArray() {
        return calendarArray;
    }

    public String getMonthName() {
        return monthName;
    }

    public int getYear(){
        return year;
    }

    public int getMonth(){
        return month;
    }
}



