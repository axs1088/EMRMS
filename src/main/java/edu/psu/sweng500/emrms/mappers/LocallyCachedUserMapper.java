package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.User;

import java.util.ArrayList;

public class LocallyCachedUserMapper implements UserMapper {
    ArrayList<User> userList;

    public LocallyCachedUserMapper() {
        userList = new ArrayList<>();
    }

    public void addUser(User user) {
        userList.add(user);
    }

    @Override
    public User validateUser(String loginID, String password) {
        for (User thisUser : userList) {
            if (thisUser.getLoginId().equals(loginID)) {
                return thisUser;
            }
        }

        return null;
    }
}
