package test2;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


public class Main {
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
	
	public static String genPlaceHolders(int size){
		String s = "(";
		for (int i = 0; i < size -1; i++){
			s = s + "?,";
		}
		s = s + "?)";
		return s;
	}
	
	public static HashMap<Integer, Integer> getIds (String disease, String zipcode, HashMap<Integer, Integer> ids){
		NearZip nz = new NearZip();
		ArrayList<String> al = nz.zipNeighbors(zipcode);
		int numZips = al.size();
		String placeholder = genPlaceHolders(numZips); 
		System.out.println(placeholder);
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
					+ ")"
					+ " s1 "
					+ "join requestlog on s1.requestlog_id=requestlog.id "
					+ "where requestlog.zip in "
					+ placeholder
					+ "; ");
			stmt.setString(1, disease);
			for (int i = 2; i < numZips + 2; i++){
				stmt.setString(i, al.get(i-2));
			}
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