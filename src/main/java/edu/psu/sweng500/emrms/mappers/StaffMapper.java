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
}
