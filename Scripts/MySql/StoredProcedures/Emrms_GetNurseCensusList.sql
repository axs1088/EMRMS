Use EMRMS;

DELIMITER $$
  CREATE PROCEDURE Emrms_GetNurseCensusList (IN LocationObjectID INT)

  BEGIN

  Select hn.LastName, hn.FirstName, hp.Birthdate, hp.Gender, hpat.MPINo, henc.EncStartdateTime, henc.encStatus
  from h_name hn INNER JOIN h_person hp on hn.HpersonID= hp.HPersonID
  INNER JOIN H_Patient hpat on hpat.HPatientID = hp.HPersonID
  Left OUTER JOIN h_encounter henc on henc.Patient_ObjectID = hpat.HPatientID
  Where henc.encStatus = 1 and henc.EncounterLocation_ObjectID = LocationObjectID;

  END
  $$