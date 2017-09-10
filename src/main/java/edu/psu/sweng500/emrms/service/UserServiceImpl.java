package edu.psu.sweng500.emrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.psu.sweng500.emrms.mappers.ApplicationMapper;
import edu.psu.sweng500.emrms.model.HPerson;

@Service("userService")
public class UserServiceImpl implements  UserService {
	
	@Autowired
	private ApplicationMapper applicationMapper;

	public List<HPerson> getUserDetails() throws Exception {
		List<HPerson> personList = (List<HPerson>) applicationMapper.getPersonDetails();
		return personList;
	}

	public boolean validateUser(String loginId)  {
		return applicationMapper.validateUser();
	}
	
	public void insertUserDetails(HPerson person) {
		applicationMapper.insertPersonDetails(person);
	}
  
  
}
