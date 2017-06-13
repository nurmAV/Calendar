package model;



import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Viljami on 11.6.2017.
 */
public class Month {

    Date date;
    int month;
    int dayNo;
    int year;
    String weekday;
    String monthName;
    String[] monthNames = {"", "Tammikuu", "Helmikuu", "Maaliskuu", "Huhtikuu", "Toukokuu", "Kesäkuu",
                            "Heinäkuu", "Elokuu", "Syyskuu", "Lokakuu", "Marraskuu", "Joulukuu"};
    int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    String[] weekdays = {"ma", "ti", "ke", "to", "pe", "la", "su"};
    int[] calendar = new int[42];



    public Month(Date date){

        this.date = date;
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat dayNoFormat = new SimpleDateFormat("dd");
        SimpleDateFormat weekdayFormat = new SimpleDateFormat("EE");
        SimpleDateFormat yearFormat = new SimpleDateFormat("YYYY");

        month = Integer.parseInt(monthFormat.format(date));
        dayNo = Integer.parseInt(dayNoFormat.format(date));
        year = Integer.parseInt(yearFormat.format(date));
        weekday = weekdayFormat.format(date);
        monthName = monthNames[month];

    }

    /**
     * Finds the offset of the given weekday, i.e. the index of the given weekday in the calendar array.
     * @param weekday The weekday to get the offset of.
     * @return The offset
     */
    private int getOffset(String weekday){
        return java.util.Arrays.asList(weekdays).indexOf(weekday);
    }

    /**
     * Finds the offset of the previous day.
     * @return the offset of the previous day.
     */
    private int previousOffset(int currentOffset){
        int previous = currentOffset;
        if(currentOffset == 0) previous = 6;
        else previous--;
        return previous;

    }

    /**
     * Finds the offset of the first day of the current month.
     * @return The offset of the current month's first day.
     */
    public int getFirstDayOffset(){
        int offset = getOffset(weekday);
        for(int i = 1; i < dayNo; i++){
            offset = previousOffset(offset);

        }
        return offset;
    }

    /**
     * Finds the number of days in the previous month.
     * @return The number of days in the previous month.
     */
    public int getPreviousMonthDays(){

        int numberOfDays;
        if(month != 1) numberOfDays = daysInMonth[month -1];
        else numberOfDays = daysInMonth[12];
        return numberOfDays;
    }

    /**
     * Finds the number of days in the next month.
     * @return The number of days in the next month.
     */
    private int getNextMonthDays(){

        int numberOfDays;
        if(month != 12) numberOfDays = daysInMonth[month + 1];
        else numberOfDays = daysInMonth[1];
        return numberOfDays;
    }

    /**
     * Creates an array with the appropriate numbers of days on the correct indices
     * @return The calendar for the current month as a one-dimensional array.
     */
    public int[] getCalendar(){

        // First fill the first indices with day numbers from the previous month.
        int offset = getFirstDayOffset();
        int daysInPreviousMonth = getPreviousMonthDays();
        int daysInCurrentMonth = daysInMonth[month];
        int daysInNextMonth = getNextMonthDays();


        for(int i = 0; i < offset; i++){
            calendar[i] = daysInPreviousMonth - offset + i;
        }
        // Fill this month's numbers.

        for(int i = 1; i <= daysInCurrentMonth; i++){
            calendar[offset + i] = i;
        }
        // Fill the next months day numbers.
        for(int i = 1; i < 42 - daysInCurrentMonth - offset; i++){
            calendar[offset + daysInCurrentMonth +i] = i;
        }
        return calendar;
    }

    /**
     *
     * @param offset The offset of the weekday wanted.
     * @return  The name of the weekday corresponding to the offset.
     */
   public String getWeekdayWithOffset(int offset){
        return weekdays[offset];

   }

   public String getMonthName(){
       return monthNames[month];
   }

}