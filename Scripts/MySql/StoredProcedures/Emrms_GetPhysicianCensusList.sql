  Use EMRMS;

DELIMITER $$
  CCREATE PROCEDURE Emrms_GetPhyciainCensusList (IN UserObjectID INT)

  BEGIN

  /*DECLARE @staffid = Select huser.HPersonID from h_User huser left outer join h_staff hs on hs.StaffID = huser.HPersonID*/


  DECLARE staffid INT;
  DECLARE personID INT;
  Select HPersonID into personID from h_user where HUserID = UserObjectID;
  SELECT hs.HStaffID into staffid from h_staff hs where hs.HStaffID = personID and type = 1;

  Select hn.LastName, hn.FirstName, hp.Birthdate, hp.Gender, hpat.MPINo, henc.EncStartdateTime, henc.encStatus
  from h_name hn INNER JOIN h_person hp on hn.HpersonID= hp.HPersonID
  INNER JOIN H_Patient hpat on hpat.HPatientID = hp.HPersonID
  Left OUTER JOIN h_encounter henc on henc.Patient_ObjectID = hpat.HPatientID
  Where henc.encStatus = 1 and henc.AttendingPhysician_ObjectID = staffid;

  END
  $$


