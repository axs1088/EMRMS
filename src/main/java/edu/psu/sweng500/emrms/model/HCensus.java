/**
 *
 */
package edu.psu.sweng500.emrms.model;

import java.sql.Date;

/**
 * @author vkumar
 */
public class HCensus {
    private String firstName;
    private String lastName;
    private Date birthdate;
    private long gender;
    private String mPINo;
    private Date encStartdateTime;
    private String encStatus;

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

    public long getGender() {
        return gender;
    }

    public void setGender(long gender) {
        this.gender = gender;
    }

    public String getmPINo() {
        return mPINo;
    }

    public void setmPINo(String mPINo) {
        this.mPINo = mPINo;
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
