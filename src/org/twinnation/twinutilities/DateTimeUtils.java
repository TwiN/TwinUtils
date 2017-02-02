package org.twinnation.twinutilities;

import java.util.Calendar;

//
// IN PROGRESS
//

/**
 * Date/Time-related utility class
 */
public final class DateTimeUtils {
	
	/** Local timezone offset in ms */
	public static final long TIMEZONE_MODIFIER = Calendar.getInstance().getTimeZone().getRawOffset();
	
	
	
	/** Number of millisecond in a day */
	public static final long MS_PER_DAY = 86400000L; 
	/** Number of hours in a day */
	public static final int HOUR_PER_DAY = 24;
	/** Exact number of day per year */
	public static final double DAY_PER_YEAR = 365.2425;
	/** Number of days in a normal calendar year */
	public static final int CAL_DAY_PER_YEAR = 365;
	/** Number of days in a leap calendar year */
	public static final int CAL_LEAP_DAY_PER_YEAR = 366;
	/** Number of months in a year */
	public static final double MONTH_PER_YEAR = 12;
	
	/** Array representing the number of days in each month of the year */
	public static final int[] DAY_PER_MONTH = {0, 
			31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	/** Name of each months of the year */
	public static final String[] MONTH_NAMES = {"",
			"January", "February", "March", "April", "May", "June", "July",
			"August", "September", "October", "November", "December"};
	/** Name of each day of the week */
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

	
	/** Prevents instantiation of this utility class */
	private DateTimeUtils() {}
	
	
	/**
	 * Gets the amount of milliseconds since 1970-01-01 00:00:00
	 * with local timezone taken in consideration
	 * @return Milliseconds since 1970-01-01 00:00:00 
	 */
	public static long getUnixTimeWithTimezoneOffset() {
		return System.currentTimeMillis()+TIMEZONE_MODIFIER;
	}
	
	
	/**
	 * Gets the amount of days since 1970-01-01 00:00:00 
	 * @return Days since 1970-01-01 00:00:00 
	 */
	private static double getDaySinceUnix() {
		return (getUnixTimeWithTimezoneOffset()/MS_PER_DAY);
	}
	
	
	/**
	 * Gets the amount of years since 1970-01-01 00:00:00 
	 * @return Years since 1970-01-01 00:00:00 
	 */
	private static double getYearSinceUnix() {
		return (getDaySinceUnix()/DAY_PER_YEAR);
	}
	
	
	/**
	 * Gets the amount of months since 1970-01-01 00:00:00 
	 * @return Months since 1970-01-01 00:00:00
	 */
	private static double getMonthSinceUnix() {
		return (getYearSinceUnix()*MONTH_PER_YEAR);
	}
	
	
	/**
	 * Gets the current year
	 * @return Current year
	 */
	public static int getYear() {
		return (int)getYearSinceUnix()+START_YEAR;
		//return Calendar.getInstance().get(Calendar.YEAR);
	}
	
	
	/**
	 * Gets the current month
	 * @return current Month
	 */
	public static int getMonth() {
		return (int)Math.ceil(getMonthSinceUnix()%MONTH_PER_YEAR);
		//return Calendar.getInstance().get(Calendar.MONTH)+1;
	}
	
	
	/**
	 * Gets the current day of the year
	 * @return Current day of the year
	 */
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
		int currentMonth = JANUARY, currentDay = 0, currentYear = getYear(), 
				currentDayOfYear = getDayOfYear();
		for (int i = 0; i < currentDayOfYear; i++) {
			if (currentDay > 27 // only perform check at end of each month
					&& currentDay == getDaysInMonthAtYear(currentYear, currentMonth)) {
				currentDay = 0;
				currentMonth++;
			}
			currentDay++;
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

	
	/**
	 * Gets Timestamp in format YYYY-MM-DD HH:MM:SS
	 * @return YYYY-MM-DD HH:MM:SS
	 */
	public static String getTimestamp() {
		return getDate()+" "+getTime();
	}
	
	
	/**
	 * Gets date in format YYYY-MM-DD
	 * @return YYYY-MM-DD
	 */
	public static String getDate() {
		int y = getYear();
		String m = ConversionUtils.zeroPad(2, ""+getMonth());
		String d = ConversionUtils.zeroPad(2, ""+getDayOfMonth());
		return y+"-"+m+"-"+d;
	}
	
	
	/**
	 * Gets the local time in format HH:MM:SS
	 * @return HH:MM:SS
	 */
	public static String getTime() {
		String h = ConversionUtils.zeroPad(2, ""+getHours());
		String m = ConversionUtils.zeroPad(2, ""+getMinutes());
		String s = ConversionUtils.zeroPad(2,""+getSeconds());
		return h+":"+m+":"+s;
	}
	
	/**
	public static void main(String[] args) {
		System.out.println(getTimestamp());
		System.out.println(getDayName(getDayOfWeek()));
	}*/
}
