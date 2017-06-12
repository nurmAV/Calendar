package sample;

import javafx.scene.layout.GridPane;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by axelv on 3.6.2017.
 */
public class MonthView{

    int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    int[] calendarArray = new int[42];
    String[] months = {"", "Tammikuu", "Helmikuu", "Maaliskuu", "Huhtikuu", "Toukokuu", "Kesäkuu", "Heinäkuu", "Elokuu", "Syyskuu", "Lokakuu", "Marraskuu", "Joulukuu"};

    String weekday = "";
    int month = 0;
    int dayNo = 0;

    public MonthView() {

        Date date = new Date();

        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
         month = Integer.parseInt(monthFormat.format(date));

        SimpleDateFormat dayNoFormat = new SimpleDateFormat("dd");
         dayNo = Integer.parseInt(dayNoFormat.format(date));

        SimpleDateFormat weekdayFormat = new SimpleDateFormat("EE");
        weekday = weekdayFormat.format(date);



    }

    public String getMonth(){
        return months[month];
    }
    public String getPreviousMonth(){
        String result;
        if(month == 1) result = months[12];
        else result = months[month -1];
        return result;
    }

    public String getNextMonth(){
        String result;
        if(month == 12) result = months[1];
        else result = months[month + 1];
        return result;
    }
    public int getDayNo(){
        return dayNo;
    }

    /**
     * Returns the index of the given weekday
     * @param weekday
     * @return
     */
    private int findOffset(String weekday){
        int offset = 0;

        switch(weekday){
            case "ma": offset = 0;
                break;
            case "ti": offset = 1;
                break;
            case "ke": offset = 2;
                break;
            case "to": offset = 3;
                break;
            case "pe": offset = 4;
                break;
            case "la": offset = 5;
                break;
            case "su": offset = 6;
                break;
        }
        return offset;

    }

    /**
     * Returns the weekday matching the given index
     * @param offset
     * @return
     */
    public String findWeekdaybyOffset(int offset){
        String weekday = "";

        switch(offset){
            case 0: weekday = "ma";
                break;
            case 1: weekday = "ti";
                break;
            case 2: weekday = "ke";
                break;
            case 3: weekday = "to";
                break;
            case 4: weekday = "pe";
                break;
            case 5: weekday = "la";
                break;
            case 6: weekday = "su";
                break;
        }
        return weekday;

    }

    private int previousDayOffset(int offset){
        int previous = 0;
        if(offset != 0){
            previous = offset - 1;

        }
        else{
            previous = 6;
        }
        return previous;
    }

    public int findMonthFirstDayOffset(){
        int offset = findOffset(weekday);
        int daysToReverse = dayNo - 1;
        for(int i = 0; i< daysToReverse; i++){
            offset = previousDayOffset(offset);
        }
        return offset;
    }

    private int previousMonthDays(){
        int numDays = 0;
        if(month != 0) numDays = daysInMonth[month -1];
        else numDays = daysInMonth[12];
        return numDays;
    }

    public int[] getCalendarArray(){
        int lastMonthDaysToShow = findMonthFirstDayOffset();
        int atIndex = 0;

        for(int i= 0; i < lastMonthDaysToShow; i++){
            calendarArray[i] = previousMonthDays() - lastMonthDaysToShow + i;
            atIndex = i;


        }
        for(int i = 1; i <= daysInMonth[month] ; i++ ){
            calendarArray[atIndex + i] = i;


        }
        for(int i = 1; i <= 42 - daysInMonth[month] - lastMonthDaysToShow; i++){
            calendarArray[atIndex + daysInMonth[month] +i] = i;
        }
        return calendarArray;
    }






}
