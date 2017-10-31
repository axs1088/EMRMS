CREATE PROCEDURE emrms.emrms_findpatientbydemographics(IN lName  VARCHAR(30),
                                                 IN fName  VARCHAR(30),
                                                 IN gender INT)
BEGIN

    IF lName <> '' AND fName <> '' AND gender <> '' AND gender <> 3
    THEN
      SELECT
        hpat.HPatientID,
        hn.LastName,
        hn.FirstName,
        hp.Birthdate,
        hp.Gender,
        hpat.MPINo,
        henc.EncStartdateTime,
        henc.encStatus,
        henc.HEncounterID
      FROM h_name hn INNER JOIN h_person hp ON hn.HpersonID = hp.HPersonID
        INNER JOIN h_patient hpat ON hpat.HPatientID = hp.HPersonID
        LEFT OUTER JOIN h_encounter henc ON henc.Patient_ObjectID = hpat.HPatientID
      WHERE  hn.LastName LIKE CONCAT(lName, '%') AND hn.FirstName LIKE CONCAT(fName, '%') AND
            hp.Gender = gender;
    END IF;

    /*Return All patients*/
    IF lName <> '' AND fName <> '' AND gender = 3
    THEN
      SELECT
        hpat.HPatientID,
        hn.HpersonID,
        hn.LastName,
        hn.FirstName,
        hp.Birthdate,
        hp.Gender,
        hpat.MPINo,
        henc.EncStartdateTime,
        henc.encStatus,
        henc.HEncounterID
      FROM h_name hn INNER JOIN h_person hp ON hn.HpersonID = hp.HPersonID
        INNER JOIN h_patient hpat ON hpat.HPatientID = hp.HPersonID
        LEFT OUTER JOIN h_encounter henc ON henc.Patient_ObjectID = hpat.HPatientID
      WHERE hn.LastName LIKE CONCAT(lName, '%') AND hn.FirstName LIKE CONCAT(fName, '%');
    END IF;

    /*Return All patients*/
    IF lName <> '' AND fName = '' AND gender = 3
    THEN
      SELECT
        hpat.HPatientID,
        hn.LastName,
        hn.FirstName,
        hp.Birthdate,
        hp.Gender,
        hpat.MPINo,
        henc.EncStartdateTime,
        henc.encStatus,
        henc.HEncounterID
      FROM h_name hn INNER JOIN h_person hp ON hn.HpersonID = hp.HPersonID
        INNER JOIN h_patient hpat ON hpat.HPatientID = hp.HPersonID
        LEFT OUTER JOIN h_encounter henc ON henc.Patient_ObjectID = hpat.HPatientID
      WHERE hn.LastName LIKE CONCAT(lName, '%');
    END IF;

  END