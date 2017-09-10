package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.HPatient;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PatientMapper {
    @Select("SELECT OBJECTID as ObjectID, "
            + "USERID as userId, "
            + "CREATIONDATETIME as creationDateTime, "
            + "MPINUMBER as mPINumber, "
            + "ORGANDONOR as organDonor, "
            + "DECEASEDINDICATOR as deceasedIndicator, "
            + "ISPATIENTUNDENTIFIED as isPatientUndentified, "
            + "PRIMARYLANG as primarylang, "
            + "MEDHISTORYCONSENT as medHistoryConsent, "
            + "FROM h_patient")
    List<HPatient> readAll();
}