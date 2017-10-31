USE EMRMS;

CREATE PROCEDURE emrms_getusertype(IN UserObjectID INT)

  BEGIN
    DECLARE personID INT;
    DECLARE staffid INT;
    DECLARE UserType INT DEFAULT 0;

    SELECT HPersonID
    INTO personID
    FROM h_user
    WHERE HUserID = UserObjectID;

    SELECT hs.HStaffID
    INTO staffid
    FROM h_staff hs
    WHERE hs.HStaffID = personID AND type = 1;
    IF staffid > 0
    THEN
      SET UserType = 1;
    END IF;

    SELECT hs.HStaffID
    INTO staffid
    FROM h_staff hs
    WHERE hs.HStaffID = personID AND type = 2;
    IF (staffid > 0)
    THEN
      SET UserType = 2;
    END IF;

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


