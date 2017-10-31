

CREATE PROCEDURE emrms_getloggedinuserdetails(IN UserObjectID INT)

  BEGIN

    DECLARE personID INT;

    SELECT HPersonID
    INTO personID
    FROM h_user
    WHERE HUserID = UserObjectID;

    SELECT CONCAT(hn.LastName, ', ',hn.FirstName, ' ', hn.MiddleName, ' ', hn.Title) AS UserName FROM
      h_name hn WHERE hn.HPersonID = personID;

  END;


