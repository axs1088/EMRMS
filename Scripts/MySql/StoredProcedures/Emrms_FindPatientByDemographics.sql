Use EMRMS;

DELIMITER $$
  CREATE PROCEDURE Emrms_FindPatientByDemographics (IN lName VARCHAR(30),
                                                    IN fName VARCHAR(30),
                                                    IN gender INT)

  BEGIN

  IF lName <> '' AND fName <> '' AND gender <> ''
  THEN
  Select hn.LastName, hn.FirstName, hp.Birthdate, hp.Gender, hpat.MPINo, henc.EncStartdateTime, henc.encStatus
  from h_name hn INNER JOIN h_person hp on hn.HpersonID= hp.HPersonID
  INNER JOIN H_Patient hpat on hpat.HPatientID = hp.HPersonID
  Left OUTER JOIN h_encounter henc on henc.Patient_ObjectID = hpat.HPatientID
  Where henc.encStatus = 1 and hn.LastName like CONCAT(lName,'%') and hn.FirstName like CONCAT(fName,'%') and hp.Gender = gender;
  END IF;

  END
  $$