package com.app.tests;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.app.pages.GasMileageCalculatorPage;
import com.app.utilities.ConfigurationReader;
import com.app.utilities.Driver;

public class BasicDataDrivenTest {
	WebDriver driver;
	Workbook wb;
	Sheet ws;
	FileInputStream inStream;
	FileOutputStream outStream;
	GasMileageCalculatorPage page;
	SoftAssert  soft = new SoftAssert();
	public static final int CURRENTOD_COLNUM = 1;
	public static final int PREVIOUSOD_COLNUM = 2;
	public static final int GAS_COLNUM = 3;
	public static final int EXPECTED_COLNUM = 4;
	public static final int ACTUAL_COLNUM = 5;
	public static final int STATUS_COLNUM = 6;
	public static final int TIME_COLNUM = 7;

	@BeforeClass
	public void setUp() throws Exception {
		inStream = new FileInputStream(ConfigurationReader.getProperty("gasmileage.testdata.path"));
		wb = WorkbookFactory.create(inStream);
		ws = wb.getSheetAt(0);
		driver = Driver.getDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.calculator.net/gas-mileage-calculator.html");

	}

	@AfterClass
	public void tearUp() throws Exception {
		inStream.close();
		wb.close();
		outStream.close();
		driver.close();
	}

	@Test
	public void dataDrivenMileageCalculatorTest() throws Exception {
		for (int i = 1; i < ws.getPhysicalNumberOfRows(); i++) {
			Row currentRow = ws.getRow(i);
			if (!currentRow.getCell(0).toString().equalsIgnoreCase("y")) {
				if (currentRow.getCell(STATUS_COLNUM) == null) {
					currentRow.createCell(STATUS_COLNUM);
				}
				currentRow.getCell(STATUS_COLNUM).setCellValue("Skip Requested");
				continue;
			}
			double currentOdometer = Double.parseDouble(currentRow.getCell(CURRENTOD_COLNUM).toString());
			double previousOdometer = Double.parseDouble(currentRow.getCell(PREVIOUSOD_COLNUM).toString());
			double gas = Double.parseDouble(currentRow.getCell(GAS_COLNUM).toString());
			double actualResult = (currentOdometer - previousOdometer) / gas;
			DecimalFormat format = new DecimalFormat("#.00");
			String actual = format.format(actualResult);
			if(actual.startsWith(".")) {
				actual="0"+actual;
			}
			page = new GasMileageCalculatorPage();
			page.currentOdometerReading.clear();
			page.currentOdometerReading.sendKeys("" + currentOdometer);
			page.previousOdometerReading.clear();
			page.previousOdometerReading.sendKeys("" + previousOdometer);
			page.gas.clear();
			page.gas.sendKeys("" + gas);
			page.calculateButton.click();
			String result = page.result.getText().split(" ")[0].replace(",", "");
			if(result.startsWith(".")) {
				result="0"+result;
			}

			if (currentRow.getCell(EXPECTED_COLNUM) == null) {
				currentRow.createCell(EXPECTED_COLNUM);
			}
			currentRow.getCell(EXPECTED_COLNUM).setCellValue(result);

			if (currentRow.getCell(ACTUAL_COLNUM) == null) {
				currentRow.createCell(ACTUAL_COLNUM);
			}
			currentRow.getCell(ACTUAL_COLNUM).setCellValue(actual);

			String passFail;
			if (actual.equals(result)) {
				passFail = "Pass";
			} else {
				passFail = "Fail";
			}

			if (currentRow.getCell(STATUS_COLNUM) == null) {
				currentRow.createCell(STATUS_COLNUM);
			}
			currentRow.getCell(STATUS_COLNUM).setCellValue(passFail);

			if (currentRow.getCell(TIME_COLNUM) == null) {
				currentRow.createCell(TIME_COLNUM);
			}
			currentRow.getCell(TIME_COLNUM).setCellValue(LocalDateTime.now().toString());

			outStream = new FileOutputStream(ConfigurationReader.getProperty("gasmileage.testdata.path"));
			wb.write(outStream);
//			System.out.println("Expected: " + result);
//			System.out.println("Actual  : " + actual);
			
			soft.assertEquals(actual, result,"actual result, "+actual + " was not equal to expected, " + result);
		}
		soft.assertAll();
	}

}
