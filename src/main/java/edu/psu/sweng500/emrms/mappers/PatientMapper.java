package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.HPatient;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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
}