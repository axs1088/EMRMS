package edu.psu.sweng500.emrms.model;

import java.sql.Date;

/**
 * @author vkumar
 * 
 */
public class HPerson {
	
	private	long personId;
	private	String userId;
	private	Date creationDateTime;
	private	long gender;
	private Date birthDate;
	private	String race;
	
	public long getPersonId() {
		return personId;
	}
	public void setPersonId(long personId) {
		this.personId = personId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getCreationDateTime() {
		return creationDateTime;
	}
	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
	public long getGender() {
		return gender;
	}
	public void setGender(long gender) {
		this.gender = gender;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}

    
	
}
