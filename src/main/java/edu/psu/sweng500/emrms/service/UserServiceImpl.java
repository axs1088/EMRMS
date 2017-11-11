package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.mappers.PersonMapper;
import edu.psu.sweng500.emrms.mappers.UserMapper;
import edu.psu.sweng500.emrms.model.HHealthcareOrganization;
import edu.psu.sweng500.emrms.model.HPerson;
import edu.psu.sweng500.emrms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private UserMapper userMapper;

    public void setPersonMapper(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<HPerson> getUserDetails() throws Exception {
        return personMapper.getPersonDetails();
    }

    public User validateUser(String loginId, String password) {
        return userMapper.validateUser(loginId,password);
    }

    public void insertUserDetails(HPerson person) {
        personMapper.insertPersonDetails(person);
    }

    @Override
    public List<HHealthcareOrganization> getLoggedinEntityDetails() {
        return userMapper.getLoggedinEntityDetails();
    }

    @Override
    public User getLoggedinUserDetails(int userObjectId) {
        return userMapper.getLoggedinUserDetails(userObjectId);
    }
}
