package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.HEncounter;
import edu.psu.sweng500.emrms.model.HPatient;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

public interface SaveEncounterMapper {
    @Insert("INSERT INTO h_encounter(UserId," +
            "EncStartDateTime,ENCEndDateTime,ENCStatus,EncLocationName," +
            "EncounterLocation_ObjectID,EncounterID,EncType,BedName," +
            "Patient_ObjectID) VALUES" +
            "(#{encounter.userID}," +
            "#{encounter.encStartDateTime},#{encounter.encEndDateTime}, #{encounter.encStatus}, #{encounter.encLocationName}," +
            "#{encounter.encounterLocation_ObjectID}, #{encounter.encounterID}, #{encounter.encounterType},#{encounter.bedName}," +
            "#{encounter.patient_ObjectID})")
    @Options(useGeneratedKeys=true, keyProperty = "encounter.hEncounterID", keyColumn="HEncounterID" )
    public void insertEncounterDetails(@Param("encounter") HEncounter hEncounter);

    String UPDATE_HENCOUNTER = "update h_encounter set EncStartDateTime = #{encounter.encStartDateTime}, ENCEndDateTime = #{encounter.encEndDateTime}, " +
            "ENCStatus = #{encounter.encStatus} ,EncLocationName = #{encounter.encLocationName}, EncounterLocation_ObjectID = #{encounter.encounterLocation_ObjectID}, " +
            "EncounterID = #{encounter.encounterID}, EncType = #{encounter.encounterType} , BedName = #{encounter.bedName} where HEncounterID=#{encounter.hEncounterID}";
    @Update(UPDATE_HENCOUNTER)@Options(keyProperty = "encounter.hEncounterID", keyColumn="HEncounterID" )
    public void reviseEncounterDetails(@Param("encounter") HEncounter encounter);
}