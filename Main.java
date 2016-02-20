package test2;

//import test.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		doSmth("Pain knee", "07928");
		//getIds("pain", "07928");
		//System.out.println(getCount("pain", "07asdf28"));
	}

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
	public static int getCount(String disease, String zipcode){
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DriverManager.getConnection( // apologies for hardcoding the password
					"jdbc:mysql://linktothepast.cm0t4637eidl.us-east-1.rds.amazonaws.com:3306/portal","triforce","turtlerock");
			stmt = con.prepareStatement("select count(*) as total from ( "
					+ " select s1.requestlog_id  from ( "
					+ "  select requestlog_id  "
					+ " from requestlog_terms "
					+ "  where word=?  "
					+ " limit 1000  )"
					+ " s1 ) ids "
					+ "join requestlog on ids.requestlog_id=requestlog.id "
					+ "where requestlog.zip = ?; ");
			stmt.setString(1, disease);
			stmt.setString(2, zipcode);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				return (rs.getInt("total"));
			}
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
		return 0;
	}
	public static void doSmth (String fullName, String zipcode)
	{
		String[] splitString = fullName.split(" ");		
		HashMap<Integer, Integer> ids = new HashMap<Integer, Integer>();
		for (String name : splitString)
		{
			ids = getIds(name, zipcode, ids);
		}
		System.out.println(ids);
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
				if (ids.containsKey(tempId))
				{
					Integer tempValue = (Integer) ids.get(tempId);
					tempValue++;
					ids.put(tempId, tempValue);
					
				}
				else
				{
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
		//return {0,0};
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
