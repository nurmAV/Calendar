package model;



import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Viljami on 11.6.2017.
 */
public class Month {

    Date date  = new Date();
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    SimpleDateFormat dayNoFormat = new SimpleDateFormat("dd");
    SimpleDateFormat weekdayFormat = new SimpleDateFormat("EE");
    SimpleDateFormat yearFormat = new SimpleDateFormat("YYYY");

    int month = Integer.parseInt(monthFormat.format(date));
    int dayNo = Integer.parseInt(dayNoFormat.format(date));
    int year = Integer.parseInt(yearFormat.format(date));
    String weekday = weekdayFormat.format(date);


    String[] monthNames = {"", "Tammikuu", "Helmikuu", "Maaliskuu", "Huhtikuu", "Toukokuu", "Kesäkuu",
                            "Heinäkuu", "Elokuu", "Syyskuu", "Lokakuu", "Marraskuu", "Joulukuu"};
    String monthName = monthNames[month];
    int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    String[] weekdays = {"ma", "ti", "ke", "to", "pe", "la", "su"};
    int[] calendar = new int[42];
    int monthDifference;

    String requestedMonth;
    int offset;


    public Month(int monthNo, int year){

        requestedMonth = monthNames[monthNo];
        monthDifference = Math.abs(monthNo - month);
        offset = getFirstDayOffset();
        System.out.println("Current month first weekday: " + getWeekdayWithOffset(offset) );
        int atMonth = month ;
        if(monthNo < month && year == this.year){
           for(int monthIndex = 1; monthIndex <= monthDifference; monthIndex++){
               atMonth = month - monthIndex;
                for(int dayIndex = 1; dayIndex <= daysInMonth[atMonth]; dayIndex++){
                    offset = previousOffset(offset);
                    System.out.println("Month: " + monthNames[atMonth] + " Day: " + dayIndex + " Offset: " + offset);
                }
           }
        }
        else if(monthNo > month && this.year == year){
            for(int dayIndex = 2; dayIndex <= daysInMonth[atMonth]; dayIndex++){
                offset = nextOffset(offset);
            }
            for(int monthIndex = 1; monthIndex <= monthDifference -1; monthIndex++){
                atMonth = month + monthIndex;
                for(int dayIndex = 1; dayIndex <= daysInMonth[atMonth]; dayIndex++){
                    offset = nextOffset(offset);
                    System.out.println("Month: " + monthNames[atMonth] + " Day: " + dayIndex + " Offset: " + offset);
                }
            }
        }

        calendar = formCalendar(atMonth, year);
        monthName = monthNames[month - monthDifference];
        System.out.println(monthName);





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
     * Finds the offset of the next da based on the given days offset.
     * @param currentOffset The current days offset.
     * @return The offset of the next day.
     */
    private int nextOffset(int currentOffset){

        int next = currentOffset;
            if(currentOffset != 6) next = currentOffset + 1;
            else next = 0;
        return next;
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
    public int getPreviousMonthDays(int month){

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
    public int[] formCalendar( int monthNo, int year){

        // First fill the first indices with day numbers from the previous month.

        int daysInPreviousMonth = getPreviousMonthDays(monthNo);
        System.out.println(monthNo);
        System.out.println(daysInPreviousMonth);
        System.out.println(offset);
        int daysInCurrentMonth = daysInMonth[monthNo];
        int daysInNextMonth = getNextMonthDays();


        for(int i = 0; i < this.offset; i++){
            calendar[i] = daysInPreviousMonth - (offset -1) + i;
        }
        // Fill this month's numbers.

        for(int i = 1; i <= daysInCurrentMonth; i++){
            calendar[offset - 1 + i] = i;
        }
        // Fill the next months day numbers.
        for(int i = 1; i <= 42 - daysInCurrentMonth - offset; i++){
            calendar[offset - 1 + daysInCurrentMonth +i] = i;
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

   public String getMonthName(int monthNo){
       return monthNames[monthNo];
   }

    public int[] getCalendar(){
        return calendar;
    }


}

