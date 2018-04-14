package com.app.tests;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

public class JDBCConnection {

	String oracledbUrl = "jdbc:oracle:thin:@ec2-52-15-120-108.us-east-2.compute.amazonaws.com:1521:xe";
	String oracledbUsername = "hr";
	String oracledbPassword = "hr";

	//@Test
	public void oracleJDBC() throws SQLException {
		Connection connection = DriverManager.getConnection(oracledbUrl, oracledbUsername, oracledbPassword);
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet resultSet = statement.executeQuery("SELECT * FROM countries");

		
		//Find out how many row in resultset
		resultSet.last();
		int rowsCount = resultSet.getRow();
		System.out.println("Number of rows: "+rowsCount);
		resultSet.beforeFirst();
		while (resultSet.next()) {
			System.out.println(resultSet.getObject(1) + "-" + resultSet.getObject("country_name") + "-"
					+ resultSet.getObject("region_id"));
		}
		resultSet.close();
		statement.close();
		connection.close();
	}
	
	
	@Test
	public void jdbcMetadata() throws SQLException {
		Connection connection = DriverManager.getConnection(oracledbUrl, oracledbUsername, oracledbPassword);
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		
		String sql = "SELECT employee_id, last_name, job_id, salary FROM employees";
		
		ResultSet resultSet = statement.executeQuery(sql);
		
		DatabaseMetaData dbMetadata = connection.getMetaData();
		System.out.println("User: " + dbMetadata.getUserName());
		System.out.println("Database Type: " + dbMetadata.getDatabaseProductName());
		
		
		//resultset Metadata
		
		ResultSetMetaData rsMetadata = resultSet.getMetaData();
		System.out.println("Columns count:"+rsMetadata.getColumnCount());
		System.out.println("Column name:"+rsMetadata.getColumnName(1));
		
		//print all column names using a loop
		
		for(int i=1; i<=rsMetadata.getColumnCount();i++) {
			System.out.println("Column name:"+rsMetadata.getColumnName(i));
		}
		//Throw resulstSet into a List of Maps
		
				//Create a list of Maps
				
				List<Map<String,Object>> list = new ArrayList<>();
				ResultSetMetaData rsMdata = resultSet.getMetaData();
				int colCount = rsMdata.getColumnCount();
				
				while(resultSet.next()) {
					Map<String,Object> rowMap = new HashMap<>();
					for(int col=1;col<=colCount;col++) {
						rowMap.put(rsMdata.getColumnName(col), resultSet.getObject(col));
					}
					
					
					list.add(rowMap);
				}
				
				//print all employee_id from a list of maps.
				
				
				for(Map<String, Object> emp:list) {
					System.out.println(emp.get("EMPLOYEE_ID"));
				}
				
		resultSet.close();
		statement.close();
		connection.close();
	}
}
