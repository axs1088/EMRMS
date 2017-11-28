CREATE PROCEDURE Emrms_FindPatientByDemographics(IN lName  VARCHAR(30),
                                                 IN fName  VARCHAR(30),
                                                 IN gender INT)
BEGIN

    IF lName <> '' AND fName <> '' AND gender <> '' AND gender <> 3
    THEN
      SELECT
        hpat.HPatientID as patientObjectid,
        hn.LastName,
        hn.FirstName,
        hp.Birthdate,
        hp.Gender,
        hPtId.IDValue as MPINo,
        henc.EncStartdateTime,
        henc.encStatus,
        henc.HEncounterID,
        ho.Name AS encounterLocationName
      FROM h_name hn INNER JOIN h_person hp ON hn.HpersonID = hp.HPersonID
        INNER JOIN H_Patient hpat ON hpat.HPersonID = hp.HPersonID
        INNER JOIN h_patient_ids hPtId ON hPtId.PatientID = hpat.HPatientID and hPtId.IdType = "MRN"
        LEFT OUTER JOIN h_encounter henc ON henc.Patient_ObjectID = hpat.HPatientID AND (henc.HEncounterID in(select MAX(HEncounterID) from h_encounter where ENCStatus = 1 GROUP by Patient_ObjectID))
        LEFT OUTER JOIN healthcare_organization ho on ho.HealthcareOrganizationID = henc.EncounterLocation_ObjectID
      WHERE  hn.LastName LIKE CONCAT(lName, '%') AND hn.FirstName LIKE CONCAT(fName, '%') AND
            hp.Gender = gender;
    END IF;

    /*Return All patients*/
    IF lName <> '' AND fName <> '' AND gender = 3
    THEN
      SELECT
        hpat.HPatientID as patientObjectid,
        hn.HpersonID,
        hn.LastName,
        hn.FirstName,
        hp.Birthdate,
        hp.Gender,
        hPtId.IDValue as MPINo,
        henc.EncStartdateTime,
        henc.encStatus,
        henc.HEncounterID,
        ho.Name AS encounterLocationName
      FROM h_name hn INNER JOIN h_person hp ON hn.HpersonID = hp.HPersonID
        INNER JOIN H_Patient hpat ON hpat.HPersonID = hp.HPersonID
        INNER JOIN h_patient_ids hPtId ON hPtId.PatientID = hpat.HPatientID and hPtId.IdType = "MRN"
        LEFT OUTER JOIN h_encounter henc ON henc.Patient_ObjectID = hpat.HPatientID AND (henc.HEncounterID in(select MAX(HEncounterID) from h_encounter where ENCStatus = 1 GROUP by Patient_ObjectID))
        LEFT OUTER JOIN healthcare_organization ho on ho.HealthcareOrganizationID = henc.EncounterLocation_ObjectID
      WHERE hn.LastName LIKE CONCAT(lName, '%') AND hn.FirstName LIKE CONCAT(fName, '%');
    END IF;

    /*Return All patients*/
    IF lName <> '' AND fName = '' AND gender = 3
    THEN
      SELECT
        hpat.HPatientID as patientObjectid,
        hn.LastName,
        hn.FirstName,
        hp.Birthdate,
        hp.Gender,
        hPtId.IDValue as MPINo,
        henc.EncStartdateTime,
        henc.encStatus,
        henc.HEncounterID,
        ho.Name AS encounterLocationName
      FROM h_name hn INNER JOIN h_person hp ON hn.HpersonID = hp.HPersonID
        INNER JOIN H_Patient hpat ON hpat.HPersonID = hp.HPersonID
        INNER JOIN h_patient_ids hPtId ON hPtId.PatientID = hpat.HPatientID and hPtId.IdType = "MRN"
        LEFT OUTER JOIN h_encounter henc ON henc.Patient_ObjectID = hpat.HPatientID AND (henc.HEncounterID in(select MAX(HEncounterID) from h_encounter where ENCStatus = 1 GROUP by Patient_ObjectID))
        LEFT OUTER JOIN healthcare_organization ho on ho.HealthcareOrganizationID = henc.EncounterLocation_ObjectID
      WHERE hn.LastName LIKE CONCAT(lName, '%');
    END IF;

  END