package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("SELECT LoginId as LoginId, UserType as UserType, "
            + " HPersonID as HPersonID FROM h_User "
            + " where loginid = #{loginID}")
    public User validateUser(String loginID);
}
