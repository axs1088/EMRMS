package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.HEncounter;
import edu.psu.sweng500.emrms.model.HPatient;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

public interface PatientMapper {
    @Select("SELECT HPatientID as ObjectID, "
            + "UserId as userId, "
            + "CreationDateTime as creationDateTime, "
            + "MPINo as mPINumber, "
            + "OrganDonor as organDonor, "
            + "DeceasedInd as deceasedIndicator, "
            + "IsPatientUnidentified as isPatientUndentified, "
            + "PrimaryLang as primarylang, "
            + "MedHistoryConcent as medHistoryConsent "
            + "FROM h_patient")
    List<HPatient> readAll();

    @Delete("DELETE FROM h_patient")
    void deleteAll();

    @Insert("INSERT INTO h_patient")
    void create();
    
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
    
    @Insert("INSERT INTO h_person(UserId, CreationDateTime, Gender, BirthDate) " +
    		"VALUES (#{userId}, #{creationDateTime}, #{gender},#{birthDate, typeHandler=edu.psu.sweng500.emrms.database.RatifiedDateTypeHandler, jdbcType=DATE})")
    public void insertPerson(HPatient patient);

    @Insert("INSERT INTO h_name(UserId, CreationDateTime, FirstName, MiddleName, LastName, HPersonID) " +
    		"VALUES (#{patient.userId}, #{patient.creationDateTime}, #{patient.name.first}, #{patient.name.middle}, #{patient.name.last}, #{personId})")
	@SelectKey(statement="SELECT max(HPersonID) as personId FROM h_person", keyProperty="personId", before=true, resultType=int.class)
    public void insertPatientName(@Param("patient") HPatient patient);
    
    @Insert("INSERT INTO h_patient(UserId, CreationDateTime, OrganDonor, HPersonID) " +
    		"VALUES (#{userId}, #{creationDateTime}, #{organDonor}, #{personId})")
	@SelectKey(statement="SELECT max(HPersonID) as personId FROM h_person", keyProperty="personId", before=true, resultType=int.class)
    public void insertPatient(HPatient patient);
    
    @Insert("INSERT INTO h_address(UserId, CreationDateTime, StrAddress, City, State, Zip, Country, " +
    		"HomePhoneNo, CellPhoneNo, EmailAddress, HPersonID) " +
    		"VALUES (#{patient.userId}, #{patient.creationDateTime}, #{patient.address.line1}, #{patient.address.city}, #{patient.address.state}, #{patient.address.zip}, #{patient.address.country}, " +
    		"#{patient.cellPhone.number}, #{patient.homePhone.number}, #{patient.email}, #{personId})")
	@SelectKey(statement="SELECT max(HPersonID) as personId FROM h_person", keyProperty="personId", before=true, resultType=int.class)
    public void insertPatientAddress(@Param("patient") HPatient patient);
    
    
}