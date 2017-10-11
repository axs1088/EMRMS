USE EMRMS;

CREATE PROCEDURE Emrms_GetPatientDemographics(IN PatientObjectID int)

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
        ha.MailingAddressInd
      FROM h_name hn INNER JOIN h_person hp ON hn.HpersonID = hp.HPersonID
        LEFT OUTER JOIN h_address ha on ha.HPersonID = hp.HPersonID
        INNER JOIN H_Patient hpat ON hpat.HPatientID = hp.HPersonID
        LEFT OUTER JOIN h_patient_ids hpid on hpid.PatientID = hpat.HPatientID and hpid.IdType = 'MRN'
      WHERE hpat.HPatientID = PatientObjectID;

  END;

