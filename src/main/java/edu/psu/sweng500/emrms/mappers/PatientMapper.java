package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.HEncounter;
import edu.psu.sweng500.emrms.model.HPatient;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
    
    @Insert("INSERT INTO h_encounter(HEncounterID,UserId," +
            "EncStartDateTime,ENCEndDateTime,ENCStatus,EncLocationName," +
            "EncounterLocation_ObjectID,EncounterID,EncType,BedName," +
            "Patient_ObjectID,AttendingPhysician_ObjectID, Bed_ObjectID) VALUES" +
            "(#{hEncounterID}, #{userID}," +
            "#{encStartDateTime},#{encEndDateTime}, #{encStatus}, #{encLocationName}," +
            "#{encounterLocation_ObjectID}, #{encounterID}, #{encounterType},#{bedName}," +
            "#{patient_ObjectID}, #{attendingPhysician_ObjectID}, #{bed_ObjectID})")
    //@Options(useGeneratedKeys=true, keyProperty="id", flushCache=true, keyColumn="id")
    public void insertEncounterDetails(HEncounter hEncounter);
    
    @Insert("INSERT INTO h_person(UserId, Gender, BirthDate) " +
    		"VALUES (#{userId}, #{gender},#{birthDate})")
    @Options(useGeneratedKeys=true, keyProperty="personId", keyColumn="HPersonID")
    public void insertPerson(HPatient patient);

    @Insert("INSERT INTO h_name(UserId, FirstName, MiddleName, LastName, HPersonID) " +
    		"VALUES (#{patient.userId}, #{patient.name.first}, #{patient.name.middle}, #{patient.name.last}, #{patient.personId})")
	public void insertPatientName(@Param("patient") HPatient patient);
    
    @Insert("INSERT INTO h_patient(UserId, MPINo, OrganDonor, HPersonID) " +
    		"VALUES (#{patient.userId}, #{patient.mPINumber}, #{patient.organDonor}, #{patient.personId})")
	@Options(useGeneratedKeys=true, keyProperty="patient.objectID", keyColumn="HPatientID")
    public void insertPatient(@Param("patient") HPatient patient);

    @Insert("INSERT INTO h_patient_ids(UserId, IDValue, IdType, IdIssuerName, IdIssuerID, PatientID) " +
            "VALUES (#{patient.userId}, #{patient.patientIds.idValue}, #{patient.patientIds.idType}, #{patient.patientIds.idIssuerName}," +
            " #{patient.patientIds.idIssuerId}, #{patient.objectID})")
    public void insertPatientIdentifiers(@Param("patient") HPatient patient);
    
    @Insert("INSERT INTO h_address(UserId, StrAddress, City, State, Zip, Country, MailingAddressInd, " +
    		"HomePhoneNo, CellPhoneNo, EmailAddress, HPersonID) " +
    		"VALUES (#{patient.userId}, #{patient.address.line1}, #{patient.address.city}, #{patient.address.state}, #{patient.address.zip}, " +
    		"#{patient.address.country}, #{patient.address.mailingAddrSameAsHomeAddr},  #{patient.homePhone.number},#{patient.cellPhone.number}, #{patient.email}, #{patient.personId})")
	public void insertPatientAddress(@Param("patient") HPatient patient);

    String UPDATE_HPERSON = "update h_person set Gender=#{patient.gender},BirthDate=#{patient.birthDate}, Race = #{patient.race} where HPersonID=#{patient.personId}";
    @Update(UPDATE_HPERSON)@Options(keyProperty = "personId" )
    public void revisePerson(@Param("patient") HPatient patient) throws Exception;

    String UPDATE_HNAME = "update h_name set LastName=#{patient.name.last},FirstName=#{patient.name.first}, " +
            "MiddleName = #{patient.name.middle}, Title = #{patient.name.title}, GenQualifier = #{patient.name.genQualifier} where HPersonID=#{patient.personId}";
    @Update(UPDATE_HNAME)@Options(keyProperty = "personId" )
    public void revisePersonName(@Param("patient") HPatient patient) throws Exception;

    String UPDATE_HAddress = "update h_address set StrAddress=#{patient.address.line1},City=#{patient.address.city},State =#{patient.address.state}, Zip = #{patient.address.zip}, " +
            "Country = #{patient.address.country}, MailingAddressInd = #{patient.address.mailingAddrSameAsHomeAddr}, HomePhoneNo = #{patient.homePhone.number}, " +
            "CellPhoneNo = #{patient.cellPhone.number}, EmailAddress = #{patient.email} where HPersonID=#{patient.personId}";
    @Update(UPDATE_HAddress)@Options(keyProperty = "personId" )
    public void revisePersonAddress(@Param("patient") HPatient patient) throws Exception;
}