CREATE PROCEDURE emrms_getphysiciancensuslist(IN UserObjectID INT)

  BEGIN
    DECLARE staffid INT;
    DECLARE personID INT;
    SELECT HPersonID
    INTO personID
    FROM h_user
    WHERE HUserID = UserObjectID;
    SELECT hs.HStaffID
    INTO staffid
    FROM h_staff hs
    WHERE hs.HStaffID = personID AND type = 1;

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
    WHERE henc.encStatus = 1 AND henc.AttendingPhysician_ObjectID = staffid;
  END;


