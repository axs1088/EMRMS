package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.HEncounter;
import org.apache.ibatis.annotations.Insert;

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
    //@Options(useGeneratedKeys=true, keyProperty="id", flushCache=true, keyColumn="id")
    public void insertEncounterDetails(HEncounter hEncounter);
}