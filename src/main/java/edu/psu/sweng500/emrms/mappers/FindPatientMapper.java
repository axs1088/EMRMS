package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.HCensus;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

public interface FindPatientMapper {
    //user object id
    @Select(value = "{ CALL " +
            "Emrms_FindPatientByDemographics(" +
            "#{lName, mode=IN, jdbcType=VARCHAR}," +
            "#{fName, mode=IN, jdbcType=VARCHAR}," +
            "#{gender, mode=IN, jdbcType=INTEGER})}")
    @Options(statementType = StatementType.CALLABLE)
    //public List<HCensus> getPatientListByDemogrpahics(String lName, String fName, Integer gender);
    public List<HCensus> getPatientListByDemogrpahics(
            @Param("lName") String lName,
            @Param("fName") String fName,
            @Param("gender") Integer gender);
}
