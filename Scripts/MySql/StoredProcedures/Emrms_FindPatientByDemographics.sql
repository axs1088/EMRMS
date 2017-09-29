USE EMRMS;

CREATE PROCEDURE Emrms_FindPatientByDemographics(IN lName  VARCHAR(30),
                                                 IN fName  VARCHAR(30),
                                                 IN gender INT)

  BEGIN

    IF lName <> '' AND fName <> '' AND gender <> '' AND gender <> 3
    THEN
      SELECT
        hn.LastName,
        hn.FirstName,
        hp.Birthdate,
        hp.Gender,
        hpat.MPINo,
        henc.EncStartdateTime,
        henc.encStatus
      FROM h_name hn INNER JOIN h_person hp ON hn.HpersonID = hp.HPersonID
        INNER JOIN H_Patient hpat ON hpat.HPatientID = hp.HPersonID
        LEFT OUTER JOIN h_encounter henc ON henc.Patient_ObjectID = hpat.HPatientID
      WHERE henc.encStatus = 1 AND hn.LastName LIKE CONCAT(lName, '%') AND hn.FirstName LIKE CONCAT(fName, '%') AND
            hp.Gender = gender;
    END IF;

    /*Return All patients*/
    IF lName <> '' AND fName <> '' AND gender = 3
    THEN
      SELECT
        hn.LastName,
        hn.FirstName,
        hp.Birthdate,
        hp.Gender,
        hpat.MPINo,
        henc.EncStartdateTime,
        henc.encStatus
      FROM h_name hn INNER JOIN h_person hp ON hn.HpersonID = hp.HPersonID
        INNER JOIN H_Patient hpat ON hpat.HPatientID = hp.HPersonID
        LEFT OUTER JOIN h_encounter henc ON henc.Patient_ObjectID = hpat.HPatientID
      WHERE henc.encStatus = 1 AND hn.LastName LIKE CONCAT(lName, '%') AND hn.FirstName LIKE CONCAT(fName, '%');
    END IF;

    /*Return All patients*/
    IF lName <> '' AND fName = '' AND gender = 3
    THEN
      SELECT
        hn.LastName,
        hn.FirstName,
        hp.Birthdate,
        hp.Gender,
        hpat.MPINo,
        henc.EncStartdateTime,
        henc.encStatus
      FROM h_name hn INNER JOIN h_person hp ON hn.HpersonID = hp.HPersonID
        INNER JOIN H_Patient hpat ON hpat.HPatientID = hp.HPersonID
        LEFT OUTER JOIN h_encounter henc ON henc.Patient_ObjectID = hpat.HPatientID
      WHERE henc.encStatus = 1 AND hn.LastName LIKE CONCAT(lName, '%');
    END IF;

  END;