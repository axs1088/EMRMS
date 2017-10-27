USE EMRMS;

CREATE PROCEDURE Emrms_FindPhysicians(IN SearchString Varchar(30))

  BEGIN
    Select hs.HStaffID, CONCAT(hn.LastName, ', ', hn.FirstName, ' ', hn.MiddleName, ' ', hn.title) As StaffName from h_staff hs
    INNER JOIN h_name hn on hn.HPersonID = hs.HStaffID
    WHERE hn.LastName LIKE CONCAT(SearchString, '%') OR hn.FirstName like CONCAT(SearchString, '%') ;
  END;