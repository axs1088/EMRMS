USE EMRMS;

CREATE PROCEDURE Emrms_GetUserType(IN UserObjectID INT)

  BEGIN

    /*DECLARE @staffid = Select huser.HPersonID from h_User huser left outer join h_staff hs on hs.StaffID = huser.HPersonID*/


    DECLARE personID INT;
    DECLARE staffid INT;
    DECLARE UserType INT DEFAULT 0;

    SELECT HPersonID
    INTO personID
    FROM h_user
    WHERE HUserID = UserObjectID;

    /*Physician*/
    SELECT hs.HStaffID
    INTO staffid
    FROM h_staff hs
    WHERE hs.HStaffID = personID AND type = 1;
    IF staffid > 0
    THEN
      SET UserType = 1;
    END IF;

    /*Nurse*/
    SELECT hs.HStaffID
    INTO staffid
    FROM h_staff hs
    WHERE hs.HStaffID = personID AND type = 2;
    IF (staffid > 0)
    THEN
      SET UserType = 2;
    END IF;

    /*Patient*/
    SELECT hp.HPatientID
    INTO staffid
    FROM h_Patient hp
    WHERE hp.HPatientID = personID;
    IF (staffid > 0)
    THEN
      SET UserType = 3;
    END IF;

    SELECT UserType;

  END;


