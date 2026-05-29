package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.XMLGregorianCalendar;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

public class DateUtil {
	private static GregorianCalendar gregorianCalendar = new GregorianCalendar();
	public static final String TIMEZONE_ID = "Europe/Copenhagen";
	private static final SimpleDateFormat DATE_FORMATTER_WITH_HYPHENS = new SimpleDateFormat("yyyy-MM-dd");

	public static XMLGregorianCalendar getXMLGregorianCalendarCurrentDate(Date date) {
		gregorianCalendar.setTime(date);
		XMLGregorianCalendar xmlGregorianCalendar = new XMLGregorianCalendarImpl(gregorianCalendar);
		return xmlGregorianCalendar;
	}

	public static String getXMLGregorianCalendarNextDate(Date date,int addDate) {
		gregorianCalendar.setTime(date);
		gregorianCalendar.add(Calendar.DATE, addDate);
		int year = gregorianCalendar.get(Calendar.YEAR);
		int month = gregorianCalendar.get(Calendar.MONTH)+1;
		int day = gregorianCalendar.get(Calendar.DATE);
		return year + "-" + month + "-" + day;
	}
	
	public static String getXMLGregorianCalendarTime(Date date) {
		gregorianCalendar.setTime(date);
		StringBuilder build = new StringBuilder(" ");
		build.append (gregorianCalendar.get(Calendar.HOUR_OF_DAY));
		build.append (":");
		build.append (gregorianCalendar.get(Calendar.MINUTE));
		build.append (":");
		build.append ( gregorianCalendar.get(Calendar.SECOND));
		build.append (":");
		build.append ( gregorianCalendar.get(Calendar.MILLISECOND));
		return build.toString();
	}

	public static Date parseLocalDateWithHyphens(String s) throws ParseException {
		return DATE_FORMATTER_WITH_HYPHENS.parse(s);
	}


}
