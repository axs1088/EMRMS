package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.model.HPerson;

public interface UserService {
	
  public HPerson getUserDetails() throws Exception;
  
  public void insertUserDetails(HPerson person);
}
