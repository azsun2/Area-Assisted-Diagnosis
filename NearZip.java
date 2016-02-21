package test2;


import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NearZip {
	private String radiusKm;
	private String maxZips;
	
	NearZip(int rK, int mZ){
		radiusKm = Integer.toString(rK);
		maxZips = Integer.toString(mZ);
	}
	
	NearZip(int rK){
		radiusKm = Integer.toString(rK);
		maxZips = "90";  // upper bound
	}
	
	public static void main(String[] args) throws Exception {// hardcoding postal code to check
		NearZip ucr = new NearZip(5);
		
		for (String s : ucr.zipNeighbors("10009")){
			System.out.println(s);
		}
	}

	public ArrayList<String> zipNeighbors(String zip){
		ArrayList<String> zips = new ArrayList<String>();
		URL oracle;
		try {
			String url = "http://api.geonames.org/findNearbyPostalCodes?postalcode=";
			url = url + URLEncoder.encode(zip,"UTF8") +"&radius="+radiusKm+"&maxRows="+maxZips+"&username=JoLBree";
			oracle = new URL(url);
			URLConnection yc = oracle.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) 
				if(zipcodeTag(inputLine) != ""){
					zips.add(zipcodeTag(inputLine));
				}
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return zips;
	}


	public static String zipcodeTag(String x){
		String pattern = "^<postalcode>([\\d]{5})</postalcode>";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(x);
		if (m.find( )) {
			String s = m.group(1);
			return s;
		} else {
			return "";
		}  
	}


}