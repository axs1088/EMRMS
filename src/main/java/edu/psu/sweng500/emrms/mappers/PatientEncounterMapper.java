package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.HBed;
import edu.psu.sweng500.emrms.model.HEncounter;
import edu.psu.sweng500.emrms.model.HHealthcareOrganization;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

public interface PatientEncounterMapper {
    @Insert("INSERT INTO h_encounter(UserId," +
            "EncStartDateTime,ENCEndDateTime,ENCStatus,EncLocationName," +
            "EncounterLocation_ObjectID,EncounterID,EncType,BedName," +
            "Patient_ObjectID, AttendingPhysician_ObjectID) VALUES" +
            "(#{encounter.userID}," +
            "#{encounter.encStartDateTime},#{encounter.encEndDateTime}, #{encounter.encStatus}, #{encounter.encLocationName}," +
            "#{encounter.encounterLocation_ObjectID}, #{encounter.encounterID}, #{encounter.encounterType},#{encounter.bedName}," +
            "#{encounter.patient_ObjectID}, #{encounter.attendingPhysician_ObjectID})")
    @Options(useGeneratedKeys=true, keyProperty = "encounter.hEncounterID", keyColumn="HEncounterID" )
    public void insertEncounterDetails(@Param("encounter") HEncounter hEncounter);

    String UPDATE_HENCOUNTER = "update h_Encounter set EncStartDateTime = #{encounter.encStartDateTime}, ENCEndDateTime = #{encounter.encEndDateTime}, " +
            "ENCStatus = #{encounter.encStatus} ,EncLocationName = #{encounter.encLocationName}, EncounterLocation_ObjectID = #{encounter.encounterLocation_ObjectID}, " +
            "EncounterID = #{encounter.encounterID}, EncType = #{encounter.encounterType} , BedName = #{encounter.bedName} where HEncounterID=#{encounter.hEncounterID}";
    @Update(UPDATE_HENCOUNTER)@Options(keyProperty = "encounter.hEncounterID", keyColumn="HEncounterID" )
    public void reviseEncounterDetails(@Param("encounter") HEncounter encounter);

    @Select(value = "{ CALL emrms_getencounterlocations(#{searchString, mode=IN, jdbcType=VARCHAR})}")
    @Options(statementType = StatementType.CALLABLE)
    public List<HHealthcareOrganization> getPatientLocations(String searchString);

    @Select(value = "{ CALL emrms_getlocationbeds(#{searchString, mode=IN, jdbcType=VARCHAR}, #{locationId, mode=IN, jdbcType=INTEGER})}")
    @Options(statementType = StatementType.CALLABLE)
    public List<HBed> getPatientLocationBeds(@Param("searchString")String searchString, @Param("locationId")int locationId );
}