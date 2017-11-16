package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.HHealthcareOrganization;
import edu.psu.sweng500.emrms.model.User;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

public interface UserMapper {
    @Select("SELECT LoginId as LoginId, UserType as UserType, "
            + " HPersonID as HPersonID, HUserId as UserId " +
            "FROM h_user INNER JOIN h_Password on h_user.HPasswordID = h_Password.HPasswordID"
            + " where h_user.loginid = #{loginID} and h_password.password = #{password}")
    public User validateUser(@Param("loginID")String loginID, @Param("password")String password);

    @Select(value = "{CALL emrms_getloggedinentitydetails()}")
    @Options(statementType = StatementType.CALLABLE)
    public List<HHealthcareOrganization> getLoggedinEntityDetails();

    @Select(value = "{CALL emrms_getloggedinuserdetails(#{userObjectID, mode=IN, jdbcType=INTEGER})}")
    @Options(statementType = StatementType.CALLABLE)
    public User getLoggedinUserDetails(int userObjectID);
}
