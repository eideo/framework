package bea.com.util;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;



public class DateParse {
	public static String date2String(Date date){
		date.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
		//return ""+date.getTime();
	}
	
	
	
	
	public static String date3String(Date date){
		date.getTime();
		
		return ""+date.getTime();
	}
	
	//yyyy-MM-dd hh:mm:ss
	public static Date string2Date(String string){
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMDDHHMMSSmmm");
//		try {
//			return sdf.parse(string);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
		Date ret = new Date();
		ret.setTime(Long.parseLong(string));
		return ret;
	}
	//sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"))
	public static String stringFormat(String string){
		Date d = string2Date(string);
		String ret="";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		ret = sdf.format(d);
		return ret;
	}
	public static String stringFormat(Date date){
		String ret="";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		ret = sdf.format(date);
		return ret;
	}
}
