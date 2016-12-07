package org.twinnation.twinutilities;


//
// IN PROGRESS
//
//
//

public final class DateTimeUtils {
	
	public static final long MS_PER_YEAR   = 31557600000L;
	public static final long MS_PER_DAY    = 86400000L; 
	public static final long MS_PER_HOUR   = 3600000L;  
	public static final long MS_PER_MIN    = 60000L;   
	public static final long MS_PER_SEC    = 1000L;
	
	
	public static final double DAY_PER_YEAR = 365.2425;
	public static final double MONTH_PER_YEAR = 12;
	
	public static final int[] DAY_PER_MONTH = {0,
			31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	public static final String[] MONTH_NAMES = {"NOT_A_MONTH", 
			"January", "February", "March", "April", "May", "June", "July",
			"August", "September", "October", "November", "December"};
	
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
	}
	
	public static int getMonth() {
		return (int)Math.ceil(getMonthSinceUnix()%MONTH_PER_YEAR);
	}
	
	public static int getDayOfWeek() {
		return 0;
	}
	
	public static int getDayOfMonth() {
		return 0;
	}
	
	public static int getDayOfYear() {
		return getDaySinceUnix();
	}
	
	public static int getDaysInMonthAtYear(int year, int month) {
		if (month == FEBRUARY) {
			return isLeapYear(year)?DAY_PER_MONTH[FEBRUARY]+1:DAY_PER_MONTH[FEBRUARY];
		}
		return DAY_PER_MONTH[month]; // TODO: custom error for invalid month number
	}
	
	public static boolean isLeapYear(int year) {
		if (year%4==0 || (year%100==0 && year%400==0)) {
			return true;
		}
		return false;
	}

	
	public static void main(String[] args) {
		System.out.println(getYear());
		System.out.println(getMonth());
		
		
	}
}
