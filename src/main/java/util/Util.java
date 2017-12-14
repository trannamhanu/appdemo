package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mongodb.DBObject;

import constant.CommonConstant;

public class Util {
	public static SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstant.DATE_FORMAT);
	
	public static Date parseDate(String dateString) throws ParseException {
		return dateFormat.parse(dateString);
	}
	
	public static String formatStringDate(Date date) {
		return dateFormat.format(date);
	}
	
	public static String getStringFromDBObject(DBObject obj, String key) {
		return (String) obj.get(key);
	} 
}
