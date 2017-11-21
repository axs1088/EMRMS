CREATE PROCEDURE emrms_findstaffList()

  BEGIN
    Select hs.HStaffID As StaffId, CONCAT(hn.LastName, ', ', hn.FirstName, ' ', hn.MiddleName, ' ', hn.title) As StaffName from h_staff hs
    INNER JOIN h_name hn on hn.HPersonID = hs.HStaffID;
  END;