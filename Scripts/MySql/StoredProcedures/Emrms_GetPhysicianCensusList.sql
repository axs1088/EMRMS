USE EMRMS;

CREATE PROCEDURE Emrms_GetPhysicianCensusList(IN UserObjectID INT)

  BEGIN

    /*DECLARE @staffid = Select huser.HPersonID from h_User huser left outer join h_staff hs on hs.StaffID = huser.HPersonID*/


    DECLARE staffid INT;
    DECLARE personID INT;
    SELECT HPersonID
    INTO personID
    FROM h_user
    WHERE HUserID = UserObjectID;
    SELECT hs.HStaffID
    INTO staffid
    FROM h_staff hs
    WHERE hs.HPersonID = personID AND type = 1;

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
      INNER JOIN H_Patient hpat ON hpat.HPersonID = hp.HPersonID
      LEFT OUTER JOIN h_encounter henc ON henc.Patient_ObjectID = hpat.HPatientID
    WHERE henc.encStatus = 1 AND henc.AttendingPhysician_ObjectID = staffid
    AND (henc.HEncounterID in(select MAX(HEncounterID) from h_encounter where ENCStatus = 1 GROUP by Patient_ObjectID));

  END;