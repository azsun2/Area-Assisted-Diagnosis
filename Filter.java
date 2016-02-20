import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filter
{
    public static void main( String args[] ){
    	Filter f = new Filter();
    	System.out.println(f.zipcode("55709"));
    	System.out.println(f.disease("cat sickn-6789ess"));
    	
    	// String to be scanned to find the pattern.
      
   }
    
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
    	String pattern = "(^[A-z -]+$)";
      Pattern r = Pattern.compile(pattern);
      Matcher m = r.matcher(x);
      if (m.find( )) {
         return true;
      } else {
         return false;
      }  
    }
    	
 }

