package com.app.step_definitions;

import com.app.pages.SuiteCRMDashboardPage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DashbordActionsStepDefs {
	SuiteCRMDashboardPage dashbordPage = new SuiteCRMDashboardPage();
	
	@When("^I post \"([^\"]*)\"$")
	public void i_post(String arg1) {
		dashbordPage.postNote(arg1);
	}

	@Then("^Post should be displayed$")
	public void post_should_be_displayed() {
	  
	}
}
