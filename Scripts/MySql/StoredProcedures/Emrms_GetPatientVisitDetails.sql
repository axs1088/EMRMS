
CREATE PROCEDURE emrms_getpatientvisitdetails(IN PatientObjectID int, IN EncounterObjectID int)

  BEGIN

      SELECT
        hn.LastName,
        hn.FirstName,
        hp.Birthdate,
        hp.Gender,
        hpat.MPINo,
        hpid.IDValue AS MRN,
        ha.HAddressID,
        ha.StrAddress,
        ha.City,
        ha.State,
        ha.Zip,
        ha.HomePhoneNo,
        ha.CellPhoneNo,
        ha.FaxNo,
        ha.AddressType,
        ha.EmailAddress,
        ha.MailingAddressInd,
        henc.HEncounterID,
        henc.EncounterID,
        henc.EncStartDateTime,
        henc.ENCEndDateTime,
        henc.ENCStatus,
        henc.EncounterLocation_ObjectID,
        henc.EncLocationName,
        henc.EncType,
        henc.BedName,
        henc.AttendingPhysician_ObjectID,
        henc.Bed_ObjectID
      FROM h_name hn INNER JOIN h_person hp ON hn.HpersonID = hp.HPersonID
        LEFT OUTER JOIN h_address ha on ha.HPersonID = hp.HPersonID
        INNER JOIN h_patient hpat ON hpat.HPatientID = hp.HPersonID
        LEFT OUTER JOIN h_patient_ids hpid on hpid.PatientID = hpat.HPatientID and hpid.IdType = 'MR'
        LEFT OUTER JOIN h_encounter henc on henc.Patient_ObjectID= hpat.HPatientID
      WHERE hpat.HPatientID = PatientObjectID and henc.HEncounterID = EncounterObjectID;

  END;

