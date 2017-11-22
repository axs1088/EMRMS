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
            "EncounterLocation_ObjectID,EncounterID,EncType," +
            "Patient_ObjectID, AttendingPhysician_ObjectID, Bed_ObjectID, EncounterReason) VALUES" +
            "(#{userID}," +
            "#{encStartDateTime},#{encEndDateTime}, #{encStatus}, #{encLocationName}," +
            "#{encounterLocation_ObjectID}, #{encounterID}, #{encounterType}, " +
            "#{patient_ObjectID}, #{attendingPhysician_ObjectID}, #{bed_ObjectID}, #{encounterReason})")
    @Options(useGeneratedKeys=true, keyProperty = "hEncounterID", keyColumn="HEncounterID" )
    public void insertOPEncounterDetails(HEncounter hEncounter);

    @Insert("INSERT INTO h_encounter(UserId," +
            "EncStartDateTime,ENCEndDateTime,ENCStatus,EncLocationName," +
            "EncounterLocation_ObjectID,EncounterID,EncType,BedName," +
            "Patient_ObjectID, AttendingPhysician_ObjectID, Bed_ObjectID, EncounterReason) VALUES" +
            "(#{userID}," +
            "#{encStartDateTime},#{encEndDateTime}, #{encStatus}, #{encLocationName}," +
            "#{encounterLocation_ObjectID}, #{encounterID}, #{encounterType},#{bedName}," +
            "#{patient_ObjectID}, #{attendingPhysician_ObjectID}, #{bed_ObjectID}, #{encounterReason})")
    @Options(useGeneratedKeys=true, keyProperty = "hEncounterID", keyColumn="HEncounterID" )
    public void insertIPEncounterDetails(HEncounter hEncounter);

    String UPDATE_OP_HENCOUNTER = "update h_encounter set EncStartDateTime = #{encounter.encStartDateTime}, ENCEndDateTime = #{encounter.encEndDateTime}, " +
            "ENCStatus = #{encounter.encStatus} ,EncLocationName = #{encounter.encLocationName}, EncounterLocation_ObjectID = #{encounter.encounterLocation_ObjectID}, " +
            "EncounterID = #{encounter.encounterID}, EncType = #{encounter.encounterType} , " +
            "AttendingPhysician_ObjectID = #{encounter.attendingPhysician_ObjectID}, EncounterReason = #{encounter.encounterReason} " +
            " where HEncounterID=#{encounter.hEncounterID}";
    @Update(UPDATE_OP_HENCOUNTER)@Options(keyProperty = "encounter.hEncounterID", keyColumn="HEncounterID" )
    public void reviseOPEncounterDetails(@Param("encounter") HEncounter encounter);

    String UPDATE_IP_HENCOUNTER = "update h_encounter set EncStartDateTime = #{encounter.encStartDateTime}, ENCEndDateTime = #{encounter.encEndDateTime}, " +
            "ENCStatus = #{encounter.encStatus} ,EncLocationName = #{encounter.encLocationName}, EncounterLocation_ObjectID = #{encounter.encounterLocation_ObjectID}, " +
            "EncounterID = #{encounter.encounterID}, EncType = #{encounter.encounterType} , BedName = #{encounter.bedName}, " +
            "AttendingPhysician_ObjectID = #{encounter.attendingPhysician_ObjectID}, Bed_ObjectID = #{encounter.bed_ObjectID}, EncounterReason = #{encounter.encounterReason} " +
            " where HEncounterID=#{encounter.hEncounterID}";
    @Update(UPDATE_IP_HENCOUNTER)@Options(keyProperty = "encounter.hEncounterID", keyColumn="HEncounterID" )
    public void reviseIPEncounterDetails(@Param("encounter") HEncounter encounter);


    @Select(value = "{ CALL emrms_getencounterlocations()}")
    @Options(statementType = StatementType.CALLABLE)
    public List<HHealthcareOrganization> getPatientLocations(); 
    
    @Select("Select HealthcareOrganizationID as objectId, Name as name, Abbreviation as abbreviation from healthcare_organization where HealthcareOrganizationID = #{encounterLocationObjectID}")
    public HHealthcareOrganization getPatientLocationByObjectId(int encounterLocationObjectID);

    @Select(value = "{ CALL emrms_getlocationbeds(#{searchString, mode=IN, jdbcType=VARCHAR}, #{locationId, mode=IN, jdbcType=INTEGER})}")
    @Options(statementType = StatementType.CALLABLE)
    public List<HBed> getPatientLocationBeds(@Param("searchString")String searchString, @Param("locationId")int locationId );
}