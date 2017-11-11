
CREATE PROCEDURE emrms_findstaff(IN SearchString Varchar(30))

  BEGIN
    Select hs.HStaffID As StaffId, CONCAT(hn.LastName, ', ', hn.FirstName, ' ', hn.MiddleName, ' ', hn.title) As StaffName from h_staff hs
    INNER JOIN h_name hn on hn.HPersonID = hs.HStaffID
    WHERE hn.LastName LIKE CONCAT(SearchString, '%') OR hn.FirstName like CONCAT(SearchString, '%')
    ORDER BY hn.LastName;
  END;