package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.HHealthcareOrganization;
import edu.psu.sweng500.emrms.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class LocallyCachedUserMapper implements UserMapper {
    ArrayList<User> userList;
    @Autowired
    private UserMapper userMapper;

    public LocallyCachedUserMapper() {
        userList = new ArrayList<>();
    }

    public void addUser(User user) {
        userList.add(user);
    }

    @Override
    public User validateUser(String loginID, String password) {
        boolean isAdmin = false;
        if(loginID.matches("admin")) isAdmin = true;
        for (User thisUser : userList) {
            if (thisUser.getLoginId().equals("admin") && isAdmin == true) {
                thisUser.setUserType(1);
                thisUser.setUserId(1);
                isAdmin = true;
                return thisUser;
            }
            if (thisUser.getLoginId().equals(loginID) && isAdmin == false){
                thisUser.setUserType(2);
                thisUser.setUserId(3);
                return thisUser;
            }
        }
        return null;
    }

    @Override
    public List<HHealthcareOrganization> getLoggedinEntityDetails() {
        return null;
    }

    @Override
    public User getLoggedinUserDetails(int userObjectID) {
        User thisUser = new User();
        if(userObjectID == 1){
            thisUser.setUserType(1);
            thisUser.setUserId(1);
            thisUser.setUsername("Doctor");

        }
        else if(userObjectID == 3){
            thisUser.setUserType(2);
            thisUser.setUserId(3);
            thisUser.setUsername("Nurse");
        }
        return thisUser;
    }
}
