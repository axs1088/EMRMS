package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.HCensus;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

public interface PhysicianCensusMapper {

    //user object id
    @Select(value = "{ CALL  Emrms_GetPhyciainCensusList(#{userObjectID, mode=IN, jdbcType=INTEGER})}")
    @Options(statementType = StatementType.CALLABLE)
    public List<HCensus> getPhysicianCensus(Integer userObjectID);
}
