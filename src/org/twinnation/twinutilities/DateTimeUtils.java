package org.twinnation.twinutilities;

import java.util.Calendar;

//
// IN PROGRESS
//

public final class DateTimeUtils {
	
	public static final long MS_PER_YEAR   = 31557600000L;
	public static final long MS_PER_DAY    = 86400000L; 
	public static final long MS_PER_HOUR   = 3600000L;  
	public static final long MS_PER_MIN    = 60000L;   
	public static final long MS_PER_SEC    = 1000L;
	
	
	public static final double DAY_PER_YEAR = 365.2425;
	public static final int CAL_DAY_PER_YEAR = 365;
	public static final int CAL_LEAP_DAY_PER_YEAR = 366;
	public static final double MONTH_PER_YEAR = 12;
	
	public static final int[] DAY_PER_MONTH = {0, 
			31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	public static final String[] MONTH_NAMES = {"",
			"January", "February", "March", "April", "May", "June", "July",
			"August", "September", "October", "November", "December"};
	
	public static final String[] DAY_NAMES = {"",
			"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	
	public static final int JANUARY   = 1;
	public static final int FEBRUARY  = 2;
	public static final int MARCH     = 3;
	public static final int APRIL     = 4;
	public static final int MAY       = 5;
	public static final int JUNE      = 6;
	public static final int JULY      = 7;
	public static final int AUGUST    = 8;
	public static final int SEPTEMBER = 9;
	public static final int OCTOBER   = 10;
	public static final int NOVEMBER  = 11;
	public static final int DECEMBER  = 12;
	
	public static final int START_YEAR = 1970;

	
	private DateTimeUtils() {}
	
	
	public static long getUnixTime() {
		return System.currentTimeMillis();
	}
	
	private static double getDaySinceUnix() {
		return (getUnixTime()/MS_PER_DAY);
	}
	
	private static double getYearSinceUnix() {
		return (getDaySinceUnix()/DAY_PER_YEAR);
	}
	
	private static double getMonthSinceUnix() {
		return (getYearSinceUnix()*MONTH_PER_YEAR);
	}
	

	public static int getYear() {
		return (int)getYearSinceUnix()+START_YEAR;
		//return Calendar.getInstance().get(Calendar.YEAR);
	}
	
	public static int getMonth() {
		return (int)Math.ceil(getMonthSinceUnix()%MONTH_PER_YEAR);
		//return Calendar.getInstance().get(Calendar.MONTH)+1;
	}
	
	public static int getDayOfYear() {
		return (int)Math.ceil(getYearSinceUnix()%1*(isLeapYear(getYear()) ?
				CAL_LEAP_DAY_PER_YEAR : CAL_DAY_PER_YEAR));
		//return Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
	}
	
	public static int getDayOfWeek() {
		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK); // TODO: dayofweek
	}
	
	public static String getDayName(int day) {
		return DAY_NAMES[day]; // TODO: prevent invalid number
	}
	
	public static int getDayOfMonth() {
		int currentMonth = JANUARY, currentDay = 0;
		for (int i = 0; i < getDayOfYear(); i++) {
			currentDay++;
			if (currentDay == getDaysInMonthAtYear(getYear(), currentMonth)) {
				currentDay = 0;
				currentMonth++;
			}
		}
		return currentDay;
	}
	
	
	public static int getDaysInMonthAtYear(int year, int month) {
		if (month == FEBRUARY) {
			return isLeapYear(year)?DAY_PER_MONTH[FEBRUARY]+1:DAY_PER_MONTH[FEBRUARY];
		}
		return DAY_PER_MONTH[month]; // TODO: custom error for invalid month number
	}
	
	
	/**
	 * Checks if the year passed as parameter is a leap year
	 * @param year Year to check
	 * @return Whether the year is a leap year or not
	 */
	public static boolean isLeapYear(int year) {
		return (year%4==0 || (year%100==0 && year%400==0));
	}
	
	
	/**
	 * Gets the number of hours on the clock of the current time 
	 * @return Number of hours
	 */
	public static long getHours() {
		return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * Gets the number of minutes on the clock of the current time 
	 * @return Number of minutes
	 */
	public static int getMinutes() {
		return Calendar.getInstance().get(Calendar.MINUTE);
	}
	
	/**
	 * Gets the number of seconds on the clock of the current time 
	 * @return Number of seconds
	 */
	public static int getSeconds() {
		return Calendar.getInstance().get(Calendar.SECOND);
	}

	
	public static String getTimestamp() {
		return getDate()+" "+getTime();
	}
	
	public static String getDate() {
		int y = getYear();
		String m = ConversionUtils.zeroPad(2, ""+getMonth());
		String d = ConversionUtils.zeroPad(2, ""+getDayOfMonth());
		return y+"-"+m+"-"+d;
	}
	
	public static String getTime() {
		String h = ConversionUtils.zeroPad(2, ""+getHours());
		String m = ConversionUtils.zeroPad(2, ""+getMinutes());
		String s = ConversionUtils.zeroPad(2,""+getSeconds());
		return h+":"+m+":"+s;
	}
	
	/*
	public static void main(String[] args) {
		System.out.println(getTimestamp());
		System.out.println(getDayName(getDayOfWeek()));
	}*/
}
