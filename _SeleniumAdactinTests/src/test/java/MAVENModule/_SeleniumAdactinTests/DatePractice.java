package MAVENModule._SeleniumAdactinTests;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/*
 * 
 * 
 * boolean 	after(Date when)
Tests if this date is after the specified date.
boolean 	before(Date when)
Tests if this date is before the specified date.
Object 	clone()
Return a copy of this object.
int 	compareTo(Date anotherDate)
Compares two Dates for ordering.
boolean 	equals(Object obj)
Compares two dates for equality.
 * Date Comparison

Following are the three ways to compare two dates −

    You can use getTime( ) to obtain the number of milliseconds that have elapsed since midnight, 
    January 1, 1970, for both objects and then compare these two values.

    You can use the methods before( ), after( ), and equals( ). 
    Because the 12th of the month comes before the 18th, 
    for example, new Date(99, 2, 12).before(new Date (99, 2, 18)) returns true.

    You can use the compareTo( ) method, which is defined by the Comparable interface and implemented by Date.

Date Formatting Using SimpleDateFormat

SimpleDateFormat is a concrete class for formatting and parsing dates in a locale-sensitive manner. SimpleDateFormat allows you to start by choosing any user-defined patterns for date-time formatting.
 */


public class DatePractice {
	
	private static Date getDayAdjustedDate(Date today, int daysAdjusted) {
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, daysAdjusted);
		dt = c.getTime();
		return dt;
	}
	
	private static String debugGetDayAdjustedDate(SimpleDateFormat dateFormat, String date, int daysAdjusted) throws Exception {
		//String untildate="05-21-2017";//can take any date in current format    
		//SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy);
		//		testData[3][0]  = 1 - One
		//		testData[4][0]  = 05/15/2017
		//		testData[5][0]  = 05/13/2017
		Calendar cal = Calendar.getInstance();    
		cal.setTime(dateFormat.parse(date));    
		cal.add( Calendar.DATE, daysAdjusted );    
		String convertedDate=dateFormat.format(cal.getTime());    
		System.out.println("Date increase by " +daysAdjusted + "... " +convertedDate);
		return convertedDate;
	}

	public static void main(String[] args) {
		 //get today's date
	    Date dateToday = Calendar.getInstance().getTime();

	    //create a date "formatter" (the date format we want)
	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");


		// display today's date
		System.out.println("today = " + formatter.format(dateToday));
		
		//get check-in date = 7 days from today
		Date dateCheckIn = getDayAdjustedDate(dateToday, 7);
		System.out.println("dateCheckIn - today + 7 = " + formatter.format(dateCheckIn));
		
		//get check-in date = 7 days from today
		Date dateCheckOut = getDayAdjustedDate(dateToday, 5);
		System.out.println("dateCheckOut - today + 5 = " + formatter.format(dateCheckOut));
		
		//write a function to compare dates
		

	}
}


