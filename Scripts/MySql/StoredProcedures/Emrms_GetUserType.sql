  Use EMRMS;

DELIMITER $$
  CREATE PROCEDURE Emrms_GetUserType (IN UserObjectID INT, OUT UserType INT)

  BEGIN

  /*DECLARE @staffid = Select huser.HPersonID from h_User huser left outer join h_staff hs on hs.StaffID = huser.HPersonID*/


   DECLARE personID INT;
   DECLARE staffid INT;

   Select HPersonID into personID from h_user where HPersonID = UserObjectID;

   /*Physician*/
  SELECT hs.HStaffID into staffid from h_staff hs where hs.HStaffID = personID and type = 1;
  IF staffid > 0
  THEN
    SET UserType = 1;
  END IF;

  /*Nurse*/
  SELECT hs.HStaffID into staffid from h_staff hs where hs.HStaffID = personID and type = 2;
  IF (staffid > 0)
  THEN
    SET UserType = 2;
  END IF;

  /*Patient*/
  SELECT hp.HPatientID into staffid from h_Patient hp where hp.HPatientID = personID;
  IF (staffid > 0)
  THEN
    SET UserType = 3;
  END IF;

  END
  $$


