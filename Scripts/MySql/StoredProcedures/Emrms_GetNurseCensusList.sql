
USE EMRMS;

CREATE PROCEDURE Emrms_GetNurseCensusList(IN LocationObjectID INT)

  BEGIN

    SELECT
      hn.LastName,
      hn.FirstName,
      hp.Birthdate,
      hp.Gender,
      hpat.HPatientID as patientObjectid,
      hPtId.IDValue as MPINo,
      henc.EncStartdateTime,
      henc.encStatus,
      henc.HEncounterID,
      ho.Name AS encounterLocationName
    FROM h_name hn INNER JOIN h_person hp ON hn.HpersonID = hp.HPersonID
      INNER JOIN H_Patient hpat ON hpat.HPersonID = hp.HPersonID
      INNER JOIN h_patient_ids hPtId ON hPtId.PatientID = hpat.HPatientID and hPtId.IdType = "MRN"
      LEFT OUTER JOIN h_encounter henc ON henc.Patient_ObjectID = hpat.HPatientID
      LEFT OUTER JOIN healthcare_organization ho on ho.HealthcareOrganizationID = henc.EncounterLocation_ObjectID
    WHERE henc.encStatus = 1 AND henc.EncounterLocation_ObjectID = LocationObjectID
    AND henc.HEncounterID in(select MAX(HEncounterID) from h_encounter where ENCStatus = 1 GROUP by Patient_ObjectID);

  END;

