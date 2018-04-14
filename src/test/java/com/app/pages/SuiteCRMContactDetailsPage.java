package com.app.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.utilities.Driver;

public class SuiteCRMContactDetailsPage {
private WebDriver driver;
	
	public SuiteCRMContactDetailsPage() {
		this.driver=Driver.getDriver();
		PageFactory.initElements(driver, this); 
	}
	
	@FindBy(id="grouptab_0")
	public WebElement salesMenu;
	
	public WebElement moduleTab_9_Contacts;
	
	public WebElement moduleTab_Contacts;
	
	@FindBy(xpath="//div[@class='actionmenulink']/../../a[@data-action-name='Create_Contact_Vcard']")
	public WebElement createContactFromVCard;

	@FindBy(xpath="//table[@class='list view table-responsive']//tbody//tr[substring(@class, string-length(@class) - string-length('RowS1') +1) = 'RowS1']/td[@class=' inlineEdit']")
	public List<WebElement> contacts;
	
	public WebElement first_name;
	
	public WebElement last_name;
	
	public WebElement vcard_file;
	
	public WebElement import_vcard_button;
	
	@FindBy(id="merge_duplicate_button")
	public WebElement findDuplicateLink;
	
	public WebElement next_step_button;
	
	@FindBy(xpath="//a[@title='Filter']")
	public WebElement filterLink;
	
	public WebElement first_name_advanced;
	
	public WebElement last_name_advanced;
	
	public WebElement search_form_submit_advanced;
	
	public WebElement search_form_clear_advanced;
	
	@FindBy(xpath="//div[.='View Contacts']")
	public WebElement viewContactsLink;
	
	@FindBy(linkText="ACTIONS")
	public WebElement actionsMenu;
	
	public WebElement delete_button;
	
	@FindBy(xpath="//div[.='View Contacts']")
	public WebElement viewContactLink;
	
	@FindBy(xpath="//a[@class='email-link']")
	public WebElement email;
	
	public void clickElement(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}
	
	public void confirmDeleteContact() {
		driver.switchTo().alert().accept();
	}
	
}
