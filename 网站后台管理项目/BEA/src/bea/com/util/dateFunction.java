package bea.com.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dateFunction {
   public static long getDateCha(String Sdate,String Edate2){
	   long result;
	   try {
		DateFormat format=DateFormat.getDateInstance();
	        long s=(format.parse(Edate2).getTime()-format.parse(Sdate).getTime())/3600/24/1000;
	        if (s<0) {
	        	result=0;
			}else{
				result=s;
			}
		return result;
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return 0;
	}
   }
   
   
   public static Date string2Date(String string){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(string);		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
	}
}
