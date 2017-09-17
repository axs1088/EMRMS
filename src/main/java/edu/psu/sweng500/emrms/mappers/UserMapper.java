package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("SELECT LoginId as LoginId, UserType as UserType, "
            + " HPersonID as HPersonID, HUserId as UserId " +
            "FROM h_User INNER JOIN h_Password on h_User.HPasswordID = h_Password.HPasswordID"
            + " where h_User.loginid = #{loginID} and h_Password.password = #{password}")
    public User validateUser(@Param("loginID")String loginID, @Param("password")String password);
}
