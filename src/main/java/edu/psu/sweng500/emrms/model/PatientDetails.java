package edu.psu.sweng500.emrms.model;

import edu.psu.sweng500.emrms.util.RatifiedDate;

public class PatientDetails {
	private String firstName;
	private String middleName;
	private String gender;
	private RatifiedDate dateOfBirth;
	private String streetAddress;
	private String city;
	private String state;
	private String zip;
	private Phone cellPhone;
	
	private String email;
		
	public String getfirstName() {
		return firstName;
	}	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	private String lastName;
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public RatifiedDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(RatifiedDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public Phone getCellphone() {
		return cellPhone;
	}
	public void setCellphone(Phone cellphone) {
		this.cellPhone = cellphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}

}
