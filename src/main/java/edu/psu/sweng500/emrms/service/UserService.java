package edu.psu.sweng500.emrms.service;

import java.util.List;

import edu.psu.sweng500.emrms.model.HPerson;

public interface UserService {
	
  public List<HPerson> getUserDetails() throws Exception;
  
  public void insertUserDetails(HPerson person);
}
