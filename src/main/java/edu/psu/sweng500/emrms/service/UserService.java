package edu.psu.sweng500.emrms.service;

import java.util.List;

import edu.psu.sweng500.emrms.model.HPerson;
import edu.psu.sweng500.emrms.model.User;

public interface UserService {
	
  public List<HPerson> getUserDetails() throws Exception;
  public User validateUser(String loginID);
  public void insertUserDetails(HPerson person);
}
