
CREATE PROCEDURE emrms_getnursecensuslist(IN LocationObjectID INT)

  BEGIN

    SELECT
      hn.LastName,
      hn.FirstName,
      hp.Birthdate,
      hp.Gender,
      hpat.MPINo,
      hpat.HPatientID,
      henc.EncStartdateTime,
      henc.encStatus,
      henc.HEncounterID
    FROM h_name hn INNER JOIN h_person hp ON hn.HpersonID = hp.HPersonID
      INNER JOIN h_patient hpat ON hpat.HPatientID = hp.HPersonID
      LEFT OUTER JOIN h_encounter henc ON henc.Patient_ObjectID = hpat.HPatientID
    WHERE henc.encStatus = 1 AND henc.EncounterLocation_ObjectID = LocationObjectID;

  END;