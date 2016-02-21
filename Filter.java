package test2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filter
{    
    public boolean zipcode(String x){
    	String pattern = "(^[\\d]{5}$)";
      Pattern r = Pattern.compile(pattern);
      Matcher m = r.matcher(x);
      if (m.find( )) {
         return true;
      } else {
         return false;
      }  
    }
    
    public boolean disease(String x){
    	String pattern = "(^[A-Za-z.,' -]{1,100}$)";
      Pattern r = Pattern.compile(pattern);
      Matcher m = r.matcher(x);
      if (m.find( )) {
         return true;
      } else {
         return false;
      }  
    }
    	
 }

