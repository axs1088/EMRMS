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
            "(#{userID}," +
            "#{encStartDateTime},#{encEndDateTime}, #{encStatus}, #{encLocationName}," +
            "#{encounterLocation_ObjectID}, #{encounterID}, #{encounterType},#{bedName}," +
            "#{patient_ObjectID})")
    @Options(keyProperty = "hEncounterID", keyColumn="HEncounterID" )
    public void insertEncounterDetails(HEncounter hEncounter);

    String UPDATE_HENCOUNTER = "update h_Encounter set EncStartDateTime = #{encounter.encStartDateTime}, ENCEndDateTime = #{encounter.encEndDateTime}, " +
            "ENCStatus = #{encounter.encStatus} ,EncLocationName = #{encounter.encLocationName}, EncounterLocation_ObjectID = #{encounter.encounterLocation_ObjectID}, " +
            "EncounterID = #{encounter.encounterID}, EncType = #{encounter.encounterType} , BedName = #{encounter.bedName} where HEncounterID=#{encounter.hEncounterID}";
    @Update(UPDATE_HENCOUNTER)@Options(keyProperty = "hEncounterID", keyColumn="HEncounterID" )
    public void reviseEncounterDetails(@Param("encounter") HEncounter encounter);
}