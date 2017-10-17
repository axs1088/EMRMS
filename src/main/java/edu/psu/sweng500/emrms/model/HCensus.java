/**
 *
 */
package edu.psu.sweng500.emrms.model;

import java.sql.Date;

/**
 * @author vkumar
 */
public class HCensus {
	private int hPatientID;
    private String firstName;
    private String lastName;
    private Date birthdate;
    private int gender;
    private String MPINo;
    private Date encStartdateTime;
    private String encStatus;

    public int gethPatientID() {
    	return hPatientID;
    }
    public void sethPatientID(int hPatientID) {
    	this.hPatientID = hPatientID;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getMPINo() {
        return MPINo;
    }

    public void setMPINo(String MPINo) {
        this.MPINo = MPINo;
    }

    public Date getEncStartdateTime() {
        return encStartdateTime;
    }

    public void setEncStartdateTime(Date encStartdateTime) {
        this.encStartdateTime = encStartdateTime;
    }

    public String getEncStatus() {
        return encStatus;
    }

    public void setEncStatus(String encStatus) {
        this.encStatus = encStatus;
    }

}
