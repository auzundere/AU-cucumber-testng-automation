package com.app.tests;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelRead {
	public static void main(String[] args) throws Exception {
		String filePath = "/Users/admin/Documents/OneDrive/Employees.xlsx";
		// Open File and convert to a stream of data
		FileInputStream inStream = new FileInputStream(filePath);
		// take the stream of data and use it as Workbook
		Workbook workbook = WorkbookFactory.create(inStream);
		// get the first worksheet from the workbook
		Sheet workSheet = workbook.getSheetAt(0);
		// go to first row
		//Row row = workSheet.getRow(0);
		// go to first cell
		//Cell cell = row.getCell(0);
		//System.out.println(cell.toString());

		// Find out how many rows in this sheet
		int rowsCount = workSheet.getPhysicalNumberOfRows();
		// int rowsCount = workSheet.getLastRowNum();
		System.out.println("Number of rows:" + rowsCount);

		for (int rowNum = 1; rowNum < rowsCount; rowNum++) {
			if(workSheet.getRow(rowNum).getCell(0).toString().equals("Nancy")) {
				System.out.println(workSheet.getRow(rowNum).getCell(2));
			}
	
		}

		inStream.close();
		workbook.close();
	}
}
