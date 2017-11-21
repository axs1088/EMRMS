package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.HStaff;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

public interface StaffMapper {
    @Select(value = "{ CALL emrms_findstaff(#{searchString, mode=IN, jdbcType=VARCHAR})}")
    @Options(statementType = StatementType.CALLABLE)
    public List<HStaff> getStaffList(String searchString);
    
    @Select("Select hs.HStaffID As StaffId, CONCAT(hn.LastName, ', ', hn.FirstName, ' ', hn.MiddleName, ' ', hn.title) As StaffName from h_staff hs "
    + "INNER JOIN h_name hn on hn.HPersonID = hs.HStaffID where hs.HStaffID = #{hStaffId}")
    public HStaff getPhysicianDetails(int hStaffId);
}
