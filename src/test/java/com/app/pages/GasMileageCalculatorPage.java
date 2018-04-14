package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.utilities.Driver;

public class GasMileageCalculatorPage {
private WebDriver driver;
	
	public GasMileageCalculatorPage() {
		this.driver=Driver.getDriver();
		PageFactory.initElements(driver, this); 
	}
	
	@FindBy(id="uscodreading")
	public WebElement currentOdometerReading;

	@FindBy(id="uspodreading")
	public WebElement previousOdometerReading;
	
	@FindBy(id="usgasputin")
	public WebElement gas;
	
	@FindBy(id="usgasprice")
	public WebElement price;
	
	@FindBy(xpath="//table[@id='standard']//input[@value='Calculate']")
	public WebElement calculateButton;
	
	@FindBy(xpath="//b[contains(text(),'miles per gallon')]")
	public WebElement result;
	
	
}
