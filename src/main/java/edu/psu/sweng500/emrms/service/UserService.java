package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.model.HPerson;
import edu.psu.sweng500.emrms.model.User;

import java.util.List;

public interface UserService {

    public List<HPerson> getUserDetails() throws Exception;

    public User validateUser(String loginID, String password);

    public void insertUserDetails(HPerson person);
}
