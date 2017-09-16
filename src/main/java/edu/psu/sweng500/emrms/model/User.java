package edu.psu.sweng500.emrms.model;

import java.sql.Date;

/**
 * @author vkumar
 */
public class User {

    private long userId;
    private Date creationDateTime;
    private String loginId;
    private long userType;
    private long hPersonId;
    private long hPasswordId;
    private String username;
    private String password;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public long getUserType() {
        return userType;
    }

    public void setUserType(long userType) {
        this.userType = userType;
    }

    public long gethPersonId() {
        return hPersonId;
    }

    public void sethPersonId(long hPersonId) {
        this.hPersonId = hPersonId;
    }

    public long gethPasswordId() {
        return hPasswordId;
    }

    public void sethPasswordId(long hPasswordId) {
        this.hPasswordId = hPasswordId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
