package General;

import Screen.TopBar;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class Time {

    public Time() {
        Calendar maintenant = Calendar.getInstance();

    }

    public String getTime(){
        String time = "";

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();

        time+=dtf.format(now);
        return time ;
    }

    public String getDate() {
        String date = "";
        LocalDate currentDate = LocalDate.now();
        DayOfWeek day = currentDate.getDayOfWeek();

        switch (day){
            case MONDAY : date += "lundi" ;
                break ;
            case TUESDAY : date += "mardi" ;
                break ;
            case WEDNESDAY: date += "mercredi" ;
                break ;
            case THURSDAY: date += "jeudi" ;
                break ;
            case FRIDAY: date += "vendredi" ;
                break ;
            case SATURDAY: date += "samedi" ;
                break ;
            case SUNDAY: date += "dimanche" ;
                break ;
        }

        return date ;
    }

    public String getMonth(){
        String time = "";

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d MMM");
        LocalDateTime now = LocalDateTime.now();

        time+=dtf.format(now);

        return time;
    }

    public String unixToDate(int unixSeconds){
        Date date = new java.util.Date(unixSeconds*1000L);                                          // convert seconds to miliseconds
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm z");       // the format of your date
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));                                   // give a timezone reference for formatting (see comment at the bottom)
        String formattedDate = sdf.format(date);
        return formattedDate ;
    }

    public String unixToHours(int unixSeconds){
        Date date = new java.util.Date(unixSeconds*1000L);                                          // convert seconds to miliseconds
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH");       // the format of your date
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));                                   // give a timezone reference for formatting (see comment at the bottom)
        String formattedDate = sdf.format(date);
        return formattedDate ;
    }


}
