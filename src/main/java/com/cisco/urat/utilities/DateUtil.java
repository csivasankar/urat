package com.cisco.urat.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String TIME_ZONE = "UTC";

	public static Date getDate(Date datetime) {
		TimeZone tz = Calendar.getInstance().getTimeZone();
		if (!tz.getDisplayName().equals(TIME_ZONE)) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			sdf.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
			String dateStr = sdf.format(datetime);
			sdf.setTimeZone(tz);
			try {
				datetime = sdf.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return datetime;
	}

	public static boolean isValidDate(String datetime) {
		return true;
	}

	public static Calendar getDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getDate(new Date()));
		return cal;
	}

	public static Calendar getDate(Calendar cal) {
		cal.setTime(getDate(cal.getTime()));
		return cal;
	}

	/*
	 * public static void main(String[] a) { System.out.println(getDate(new
	 * Date())); System.out.println(getDate().getTime()); }
	 */
}
