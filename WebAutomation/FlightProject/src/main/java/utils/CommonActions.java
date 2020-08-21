package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class CommonActions {
	
	public static String formatDate(String indate) throws ParseException
	{  
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
		LocalDate dateTime = LocalDate.parse(indate.trim(), formatter); 		
				
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateTime.toString());
        return    new SimpleDateFormat("dd-MM-yyyy").format(date);   
	    
	  }

}
