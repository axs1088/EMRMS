CREATE PROCEDURE emrms_getlocationbeds(IN SearchString Varchar(30), IN LocationId Int)

  BEGIN
    Select hb.BedId, hb.BedName, hb.BedStatus, hb.locationId from h_bed hb
    INNER JOIN healthcare_organization ho on ho.HealthcareOrganizationID= hb.LocationID
    WHERE hb.BedName LIKE CONCAT(SearchString, '%') and hb.LocationID = LocationId
    ORDER  BY hb.BedName;
  END;