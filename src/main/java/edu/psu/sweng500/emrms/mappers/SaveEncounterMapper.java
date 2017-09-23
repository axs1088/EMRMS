package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.HEncounter;
import org.apache.ibatis.annotations.Insert;

import java.util.Date;

public interface SaveEncounterMapper {
    @Insert("INSERT INTO h_encounter(HEncounterID,UserId,CreationDateTime," +
            "EncStartDateTime,ENCEndDateTime,ENCStatus,EncLocationName," +
            "EncounterLocation_ObjectID,EncounterID,EncType,BedName," +
            "Patient_ObjectID,AttendingPhysician_ObjectID, Bed_ObjectID) VALUES" +
            "(#{hEncounterID}, #{userID}, #{creationDateTime}, " +
            "#{encStartDateTime},#{encEndDateTime}, #{encStatus}, #{encLocationName}," +
            "#{encounterLocation_ObjectID}, #{encounterID}, #{encounterType},#{bedName}," +
            "#{patient_ObjectID}, #{attendingPhysician_ObjectID}, #{bed_ObjectID})")
    //@Options(useGeneratedKeys=true, keyProperty="id", flushCache=true, keyColumn="id")
    public void insertEncounterDetails(HEncounter hEncounter);
}