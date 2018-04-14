package com.app.step_definitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import com.app.pages.SuiteCRMCreateTaskPage;
import com.app.pages.SuiteCRMDashboardPage;
import com.app.pages.SuiteCRMTaskSummaryPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreatingTasksStepDefs {
	SuiteCRMDashboardPage dashboardPage = new SuiteCRMDashboardPage();
	SuiteCRMCreateTaskPage createTaskPage = new SuiteCRMCreateTaskPage();
	SuiteCRMTaskSummaryPage taskSummaryPage = new SuiteCRMTaskSummaryPage();
	Map<String, String> createTaskMap = new HashMap<>();
	Map<String, String> overviewTaskMap = new HashMap<>();
	
	@Given("^I click on create task$")
	public void i_click_on_create_task() {
		dashboardPage.clickCreateTask();
	   
	}

	@When("^Set subject as \"([^\"]*)\"$")
	public void set_subject_as(String taskSubject) {
	  
	  createTaskPage.subject.sendKeys(taskSubject);
	  createTaskMap.put("Subject", taskSubject); 
	}

	@And("^Set status as \"([^\"]*)\"$")
	public void set_status_as(String taskStatus) {
	   Select statuses = new Select(createTaskPage.status);
	   statuses.selectByVisibleText(taskStatus);
	   createTaskMap.put("Status", taskStatus);
	}

	@And("^Start date is today's date$")
	public void start_date_is_today_s_date() {
	    String dueDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")).toString();
	    createTaskPage.date_start_date.sendKeys(dueDate.toString()+Keys.TAB);
	    createTaskMap.put("Start Date", dueDate);
	}

	@And("^Due date is (\\d+) days after today's date$")
	public void due_date_is_days_after_today_s_date(int days) {
		String todaysDate = LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")).toString();
	    createTaskPage.date_due_date.sendKeys(todaysDate+Keys.TAB);
	    createTaskMap.put("Due Date", todaysDate);
	}

	@And("^Set \"([^\"]*)\" priority$")
	public void set_priority(String taskPriority) {
		Select priority = new Select(createTaskPage.priority);
		priority.selectByVisibleText(taskPriority);
		createTaskMap.put("Priority", taskPriority);
		   
	    
	}

	@And("^Set description as \"([^\"]*)\"$")
	public void set_description_as(String taskDescription) {
		createTaskPage.description.sendKeys(taskDescription);
		createTaskMap.put("Description", taskDescription);
	}

	@And("^Save the task$")
	public void save_the_task() {
		createTaskPage.SAVE.click();
	}

	@Then("^Taks details page should be displayed$")
	public void taks_details_page_should_be_displayed() {
		assertTrue(taskSummaryPage.taskOverview.isDisplayed());
		overviewTaskMap.put("Subject", taskSummaryPage.subject.getText());
		overviewTaskMap.put("Status", taskSummaryPage.status.getAttribute("value"));
		overviewTaskMap.put("Start Date", taskSummaryPage.date_start.getText().replace(" 12:00am", ""));
		overviewTaskMap.put("Due Date", taskSummaryPage.date_due.getText().replace(" 12:00am", ""));
		overviewTaskMap.put("Priority", taskSummaryPage.priority.getAttribute("value"));
		overviewTaskMap.put("Description", taskSummaryPage.description.getText());
	}

	@And("^Data should match with created task data$")
	public void data_should_match_with_created_task_data() {
	   assertEquals(overviewTaskMap,createTaskMap);
	   
	}
	
}
