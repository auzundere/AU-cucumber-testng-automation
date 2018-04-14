package com.app.step_definitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Keys;

import com.app.pages.SuiteCRMDashboardPage;
import com.app.pages.SuiteCRMLoginPage;
import com.app.pages.SuiteCRMSearchResultsPage;
import com.app.utilities.ConfigurationReader;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UITTestsStepDefs {

	SuiteCRMLoginPage loginPage = new SuiteCRMLoginPage();
	SuiteCRMDashboardPage dashboardPage = new SuiteCRMDashboardPage();
	SuiteCRMSearchResultsPage searchResultsPage = new SuiteCRMSearchResultsPage();
	
	
	@Given("^I logged into suiteCRM$")
	public void i_logged_into_suiteCRM() {
		loginPage.login(ConfigurationReader.getProperty("username"), 
				ConfigurationReader.getProperty("password"));
	}

	@Then("^logging out suiteCRM$")
	public void logging_out_suiteCRM() {
		dashboardPage.logout();
	    
	}
	
	@Then("^CRM Name should be SuiteCRM$")
	public void crm_Name_should_be_SuiteCRM() {
		assertTrue(loginPage.verifyTitle("SuiteCRM"));
		System.out.println("SuiteCRM's title is verified!");
	}

	@Then("^Modules should be displayed$")
	public void modules_should_be_displayed() {
		assertTrue(dashboardPage.sales.isDisplayed());
		assertTrue(dashboardPage.marketing.isDisplayed());
		assertTrue(dashboardPage.support.isDisplayed());
		assertTrue(dashboardPage.activities.isDisplayed());
		assertTrue(dashboardPage.collaboration.isDisplayed());
		assertTrue(dashboardPage.all.isDisplayed());
		System.out.println("Modules has been verified");
	}
	
	@When("^I search for \"([^\"]*)\"$")
	public void i_search_for(String searchTerm) {
		//BrowserUtils.waitForVisibility(dashboardPage.searchbutton, 3);
		try{
			assertTrue(dashboardPage.searchInput.isDisplayed(), searchTerm + " was not displayed"); 
		}catch(AssertionError e) {
			dashboardPage.searchbutton.click();
		}
		dashboardPage.searchInput.sendKeys(searchTerm+ Keys.ENTER);
		
	}

	@Then("^link for user \"([^\"]*)\" should be displayed$")
	public void link_for_user_should_be_displayed(String searchTerm) {
	   assertTrue(searchResultsPage.resultLink(searchTerm).isDisplayed(), searchTerm + " was not displayed");
	    
	}
	
	@Then("^there should be (\\d+) result for \"([^\"]*)\"$")
	public void there_should_be_result_for(int count, String searchTerm) {
		int actual = searchResultsPage.resultsLink(searchTerm).size();
		assertEquals(actual, count, "not same results");
	}

}
