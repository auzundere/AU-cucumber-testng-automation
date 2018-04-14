package com.app.step_definitions;

import java.util.Map;

import org.openqa.selenium.WebElement;

import com.app.pages.SuiteCRMContactInformationPage;
import com.app.pages.SuiteCRMCreateContactPage;
import com.app.pages.SuiteCRMDashboardPage;
import com.app.utilities.BrowserUtils;

import cucumber.api.java.en.When;

public class CreateContactMapsStepDefs {
	SuiteCRMDashboardPage dashboard = new SuiteCRMDashboardPage();
	SuiteCRMCreateContactPage createContact = new SuiteCRMCreateContactPage();
	SuiteCRMContactInformationPage contactInformation = new SuiteCRMContactInformationPage();
	
	@When("^I create a new contact:$")
	public void i_create_a_new_contact(Map<String, String> contact) {
		// open the create contact dialog
		BrowserUtils.hover(dashboard.createLink);
		dashboard.createContact.click();
		// enter information
		sendKeysToFields(createContact.firstName,"first_name",contact);
		sendKeysToFields(createContact.lastName,"last_name",contact);
		sendKeysToFields(createContact.officePhoneNumber,"office_phone",contact);
		sendKeysToFields(createContact.cellPhoneNumber,"cell_phone",contact);
		createContact.save();
	}
	public void sendKeysToFields(WebElement element, String field, Map<String, String> mContact) {
		if(mContact.get(field)!=null)
			element.sendKeys(mContact.get(field));
	}
	
}
