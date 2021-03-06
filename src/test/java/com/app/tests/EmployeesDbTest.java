package com.app.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.app.utilities.DBType;
import com.app.utilities.DBUtility;

public class EmployeesDbTest {
	@BeforeClass
	public void setUp() throws SQLException {
		DBUtility.establishConnection(DBType.ORACLE);
	}
	
	@AfterClass
	public void tearDown() {
		DBUtility.closeConnections();
	}
	
	@Test()
	public void countTest() throws SQLException {
		//Connect to oracle db
		//run following slq query
		//select * from eployees where job_id = ''IT_PROG
		//more than 0 records should be returned
		
		int rowsCount = DBUtility.getRowCount("Select * from employees where job_id='IT_PROG'");
		assertTrue(rowsCount>0);
		
	}
	
	@Test
	public void nameTestByID() throws SQLException {
		//Connect to oracle DB
		//employees fullname with Employee ID 105 should be David Austin
		List<Map<String,Object>> empData = DBUtility.runSQLQuery(
				"Select first_name, last_name FROM employees WHERE employee_id=106");
		assertEquals(empData.get(0).get("FIRST_NAME"),"Valli");
		assertEquals(empData.get(0).get("LAST_NAME"),"Pataballa");
	}
	
}
