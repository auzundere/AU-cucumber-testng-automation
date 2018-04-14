package com.app.beans;

public class ContactBean {
	private String firstName;
	private String lastName;
	private String officePhone;
	private String cellPhone;
	private String department;
	private String email;
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public String getDepartment() {
		return department;
	}
	public String getEmail() {
		return email;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
