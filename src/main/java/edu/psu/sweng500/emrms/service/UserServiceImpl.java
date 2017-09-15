package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.mappers.PersonMapper;
import edu.psu.sweng500.emrms.mappers.PhysicianCensusMapper;
import edu.psu.sweng500.emrms.mappers.UserMapper;
import edu.psu.sweng500.emrms.model.HCensus;
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
    private PhysicianCensusMapper censusMapper;

    @Autowired
    private UserMapper userMapper;

    public void setPersonMapper(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<HPerson> getUserDetails() throws Exception {
        List<HPerson> personList = (List<HPerson>) personMapper.getPersonDetails();
        return personList;
    }

    public User validateUser(String loginId) {
        return userMapper.validateUser(loginId);
    }

    public void insertUserDetails(HPerson person) {
        personMapper.insertPersonDetails(person);
    }

    public List<HCensus> getPhysicianCensus(Integer userObjectID) {
        return censusMapper.getPhysicianCensus(userObjectID);
    }

}
