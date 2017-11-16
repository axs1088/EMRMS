package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.HCensus;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

public interface CensusMapper {

    @Select(value = "{ CALL emrms_getphysiciancensuslist(#{userObjectID, mode=IN, jdbcType=INTEGER})}")
    @Options(statementType = StatementType.CALLABLE)
    public List<HCensus> getPhysicianCensus(Integer userObjectID);

    @Select(value = "{ CALL emrms_getnursecensuslist(#{locationObjectID, mode=IN, jdbcType=INTEGER})}")
    @Options(statementType = StatementType.CALLABLE)
    public List<HCensus> getNurseCensus(Integer locationObjectID);

    //user object id
    @Select(value = "{ CALL " +
            "emrms_findpatientbydemographics(" +
            "#{lName, mode=IN, jdbcType=VARCHAR}," +
            "#{fName, mode=IN, jdbcType=VARCHAR}," +
            "#{gender, mode=IN, jdbcType=INTEGER})}")
    @Options(statementType = StatementType.CALLABLE)
    public List<HCensus> getPatientListByDemographics(
            @Param("lName") String lName,
            @Param("fName") String fName,
            @Param("gender") Integer gender);

}
