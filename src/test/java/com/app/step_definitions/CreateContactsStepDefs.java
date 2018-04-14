package com.app.step_definitions;

import static org.testng.Assert.assertEquals;

import com.app.pages.SuiteCRMContactInformationPage;
import com.app.pages.SuiteCRMCreateContactPage;
import com.app.pages.SuiteCRMDashboardPage;
import com.app.utilities.BrowserUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreateContactsStepDefs {
	SuiteCRMDashboardPage dashboard = new SuiteCRMDashboardPage();
	SuiteCRMCreateContactPage createContact = new SuiteCRMCreateContactPage();
	SuiteCRMContactInformationPage contactInformation = new SuiteCRMContactInformationPage();
	
	@Given("^I open the create contact page$")
	public void i_open_the_create_contact_page() {
		BrowserUtils.hover(dashboard.createLink);
		dashboard.createContact.click();

	}

	@Given("^I enter the first name \"([^\"]*)\" and the last name \"([^\"]*)\"$")
	public void i_enter_the_first_name_and_the_last_name(String firstName, String lastName) {
	   createContact.firstName.sendKeys(firstName);
	   createContact.lastName.sendKeys(lastName);
	}

	@Given("^I enter the phone number \"([^\"]*)\"$")
	public void i_enter_the_phone_number(String officePhone) {
		createContact.officePhoneNumber.sendKeys(officePhone);
	}

	@Given("^I enter the department \"([^\"]*)\"$")
	public void i_enter_the_department(String department) {
		createContact.department.sendKeys(department);
	}

	@When("^click on the save button$")
	public void click_on_the_save_button() {
		createContact.save();
	}

	@Then("^I should see contact information for \"([^\"]*)\"$")
	public void i_should_see_contact_information_for(String fullName) {
	    assertEquals(contactInformation.firstName.getText(),fullName.split(" ")[0]);
	    assertEquals(contactInformation.lastName.getText(),fullName.split(" ")[1]);
	}

}
