package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("SELECT LoginId as LoginId, UserType as UserType, "
            + " HPersonID as HPersonID, HUserId as UserId " +
            "FROM h_user INNER JOIN h_password on h_user.HPasswordID = h_password.HPasswordID"
            + " where h_user.loginid = #{loginID} and h_password.password = #{password}")
    public User validateUser(@Param("loginID")String loginID, @Param("password")String password);
}
