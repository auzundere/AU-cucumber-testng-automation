package com.app.tests;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelWrite {
	public static void main(String[] args) throws Exception {
		String filePath = "./src/test/resources/testdata/Employees.xlsx";
		
		//System.out.println(filePath);
		//Create Fileinputstream
		FileInputStream inStream = new FileInputStream(filePath);
		Workbook wb = WorkbookFactory.create(inStream);
		Sheet ws = wb.getSheet("Sheet1");
		Cell job = ws.getRow(6).getCell(2);
		job.setCellValue("Automation Architect");
		//save changes
		FileOutputStream outStream = new FileOutputStream(filePath);
		wb.write(outStream);
		outStream.close();
		wb.close();
		inStream.close();
	} 
}
