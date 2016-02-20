package test2;

//import test.SQLException;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) {
		//parseNamesAndRun("Pain knee", "07928");
		String[] names = {"Pain knee", "Pain back", "Pain arm"};
		int[] counts = countMultiNamesOneZip(names, "07928");
		getFinalArrays(counts, names);
		Integer[] i = {1, 3281};
		//getCount(i);
		//getIds("pain", "07928");
		//System.out.println(getCount("pain", "07asdf28"));
	}
	
	public static void getFinalArrays (int[] counts, String[] names)
	{
		int[] countsClone = counts.clone();
		String[] namesClone = names.clone();
		
		for(int j = 0; j < counts.length; j++)
		{
			int largest = j;
			for(int i = j; i < counts.length; i++)
			{
				if( countsClone[i] > countsClone[largest])
				{
					largest = i;
				}
			}
			int temp = countsClone[j];
			String tempString = namesClone[j];
			countsClone[j] = countsClone[largest];
			countsClone[largest] = temp;
			namesClone[j] = tempString;
		}
		counts = countsClone;
		names = namesClone;
		/*for(int i = 0; i < names.length; i++)
		{
			System.out.print(names[i] + " ");
			System.out.println(counts[i]);
		}*/
	}

	public static int[] countMultiNamesOneZip(String[] names, String zipcode)
	{
		int[] countArray = new int[names.length];
		int i = 0;
		for(String name : names)
		{
			countArray[i] = parseNamesAndRun(name, zipcode);
			i++;
		}
		return countArray;
		
	}
	
	public static int parseNamesAndRun (String fullName, String zipcode)
	{
		String[] splitString = fullName.split(" ");		
		HashMap<Integer, Integer> ids = new HashMap<Integer, Integer>();
		int length = splitString.length;
		for (String name : splitString)
		{
			ids = getIds(name, zipcode, ids);
		}
		int count = 0;
		for (Integer id : ids.keySet()){
			if (ids.get(id) == length){
				count++;
			}}
		return count;
	}
	
	public static HashMap<Integer, Integer> getIds (String disease, String zipcode, HashMap<Integer, Integer> ids){
		
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = DriverManager.getConnection( // apologies for hardcoding the password
					"jdbc:mysql://linktothepast.cm0t4637eidl.us-east-1.rds.amazonaws.com:3306/portal","triforce","turtlerock");
			stmt = con.prepareStatement(""
					+ " select s1.requestlog_id as id from ( "
					+ "  select requestlog_id  "
					+ " from requestlog_terms "
					+ "  where word=?  "
					+ " limit 1000  )"
					+ " s1 "
					+ "join requestlog on s1.requestlog_id=requestlog.id "
					+ "where requestlog.zip = ?; ");
			stmt.setString(1, disease);
			stmt.setString(2, zipcode);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Integer tempId = rs.getInt("id");
				if (ids.containsKey(tempId)){
					Integer tempValue = (Integer) ids.get(tempId);
					tempValue++;
					ids.put(tempId, tempValue);
				}
				else{
					ids.put(tempId, 1);
				}
			}
			return ids;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if (stmt != null) { stmt.close(); }
			}
			catch (Exception e) {
				System.out.println("Error: Query prep failed");
			}
			try {
				if (con != null) { con.close(); }
			}
			catch (Exception e) {
				System.out.println("Error: Connection failed");
			}
		}
		System.out.println(ids);
		return ids;
	}	
}


// Code for testing connection
//String url = "jdbc:mysql://linktothepast.cm0t4637eidl.us-east-1.rds.amazonaws.com:3306/portal";
//String username = "triforce";
//String password = "turtlerock";
//System.out.println("Loading driver...");
//System.out.println("Connecting database...");
//try (Connection connection = DriverManager.getConnection(url, username, password)) {
//    System.out.println("Database connected!");
//} catch (SQLException e) {
//    throw new IllegalStateException("Cannot connect the database!", e);
//}

/*
 * Limitations:
 * Disease currently limited to one-word diseases only
 * No input filters, simply returns 0 if bullshit (which i guess makes sense)
 * 
 * Steps for java-mysql hookup:
 * Following the comment with 244 +1s here: http://stackoverflow.com/questions/2839321/connect-java-to-a-mysql-database
 * If using eclipse, this tells you how to add it to the class path: http://www.wikihow.com/Add-JARs-to-Project-Build-Paths-in-Eclipse-(Java)
 * Commented out code below to test connection
 * 
 */
//public static int getCount(String disease, String zipcode){
//	Connection con = null;
//	PreparedStatement stmt = null;
//	try {
//		con = DriverManager.getConnection( // apologies for hardcoding the password
//				"jdbc:mysql://linktothepast.cm0t4637eidl.us-east-1.rds.amazonaws.com:3306/portal","triforce","turtlerock");
//		stmt = con.prepareStatement("select count(*) as total from ( "
//				+ " select s1.requestlog_id  from ( "
//				+ "  select requestlog_id  "
//				+ " from requestlog_terms "
//				+ "  where word=?  "
//				+ " limit 1000  )"
//				+ " s1 ) ids "
//				+ "join requestlog on ids.requestlog_id=requestlog.id "
//				+ "where requestlog.zip = ?; ");
//		stmt.setString(1, disease);
//		stmt.setString(2, zipcode);
//		ResultSet rs = stmt.executeQuery();
//		if(rs.next()){
//			return (rs.getInt("total"));
//		}
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	finally {
//		try {
//			if (stmt != null) { stmt.close(); }
//		}
//		catch (Exception e) {
//			System.out.println("Error: Query prep failed");
//		}
//		try {
//			if (con != null) { con.close(); }
//		}
//		catch (Exception e) {
//			System.out.println("Error: Connection failed");
//		}
//	}
//	return 0;
//}

//public static int getCount(Integer[] commonIds){ // change param type 
//Object[] objIds = (Object[]) commonIds; // figure out casting later
//Connection con = null;
//PreparedStatement stmt = null;
//
//try {
//	con = DriverManager.getConnection( // apologies for hardcoding the password
//			"jdbc:mysql://linktothepast.cm0t4637eidl.us-east-1.rds.amazonaws.com:3306/portal","triforce","turtlerock");
//	stmt = con.prepareStatement("select count(*) as total from requestlog where id in ?; ");
//	Array array = con.createArrayOf("INT", objIds );
//	stmt.setArray(1, array);
//	ResultSet rs = stmt.executeQuery();
//	if(rs.next()){
//		return rs.getInt("total");
//	}
//} catch (SQLException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}
//finally {
//	try {
//		if (stmt != null) { stmt.close(); }
//	}
//	catch (Exception e) {
//		System.out.println("Error: Query prep failed");
//	}
//	try {
//		if (con != null) { con.close(); }
//	}
//	catch (Exception e) {
//		System.out.println("Error: Connection failed");
//	}
//}
//return 0;
//
//}
