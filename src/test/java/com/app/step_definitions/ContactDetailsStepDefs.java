package com.app.step_definitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.app.pages.SuiteCRMContactDetailsPage;
import com.app.utilities.BrowserUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class ContactDetailsStepDefs {
	SuiteCRMContactDetailsPage contactDetailsPage = new SuiteCRMContactDetailsPage();

	@Given("^I open contact \"([^\"]*)\"$")
	public void i_open_contact(String arg1) {
		contactDetailsPage.clickElement(contactDetailsPage.salesMenu);
		contactDetailsPage.moduleTab_9_Contacts.click();
		contactDetailsPage.contacts.get(contactDetailsPage.contacts.size() - 1).click();
	}

	@Then("^contact name should be \"([^\"]*)\"$")
	public void contact_name_should_be(String contactName) {
		String full_name = contactDetailsPage.first_name.getText() + " " + contactDetailsPage.last_name.getText();
		assertEquals(full_name, contactName, "Contact name was not " + contactName);
	}

	@Then("^contact email should be \"([^\"]*)\"$")
	public void contact_email_should_be(String contactEmail) {
		assertTrue(contactDetailsPage.email.getText().equals(contactEmail),
				"Contact email address was not " + contactEmail);
	}

	
	@Given("^duplicated contact \"([^\"]*)\" exists$")
	public void duplicated_contact_exists(String contactName) {
			String vCard = contactName.replace(" ", "-").toLowerCase() + ".vcf";//john-doe.vcf
			String[] names = contactName.split(" ");
			
			contactDetailsPage.clickElement(contactDetailsPage.salesMenu);
			contactDetailsPage.moduleTab_9_Contacts.click();
			contactDetailsPage.createContactFromVCard.click();
			contactDetailsPage.vcard_file
					.sendKeys(System.getProperty("user.dir") + "/src/test/resources/com/app/files/" + vCard);
			                  
			contactDetailsPage.import_vcard_button.click();
//			contactDetailsPage.actionsMenu.click();
//			contactDetailsPage.findDuplicateLink.click();
//			contactDetailsPage.next_step_button.click();
			contactDetailsPage.viewContactsLink.click();
			
			contactDetailsPage.filterLink.click();
			BrowserUtils.waitFor(1);
			contactDetailsPage.first_name_advanced.clear();
			contactDetailsPage.first_name_advanced.sendKeys(names[0]);
			contactDetailsPage.last_name_advanced.clear();
			contactDetailsPage.last_name_advanced.sendKeys(names[1]);
			BrowserUtils.waitFor(1);
			contactDetailsPage.search_form_submit_advanced.click();
			assertTrue(contactDetailsPage.contacts.size() > 1, "There no duplicate for " + contactName);
			
	}

	@Then("^remove duplicates for this contact$")
	public void remove_duplicates_for_this_contact() {
		int numberOfDuplicateContacts = contactDetailsPage.contacts.size();
		for (int i = 0; i < numberOfDuplicateContacts; i++) {
			if (contactDetailsPage.contacts.size() > 1) {
				contactDetailsPage.contacts.get(0).click();
				contactDetailsPage.actionsMenu.click();
				contactDetailsPage.delete_button.click();
				contactDetailsPage.confirmDeleteContact();
				System.out.println(contactDetailsPage.contacts.size());
			}
		}
		
		
	}

	@Then("^there should be only (\\d+) John Doe in the contacts page$")
	public void there_should_be_only_John_Doe_in_the_contacts_page(int numberOfContact) {
		assertEquals(contactDetailsPage.contacts.size(), numberOfContact, "Still there is duplicate contact");
		contactDetailsPage.filterLink.click();
		BrowserUtils.waitFor(1);
		contactDetailsPage.search_form_clear_advanced.click();
		contactDetailsPage.search_form_submit_advanced.click();

	}
}
