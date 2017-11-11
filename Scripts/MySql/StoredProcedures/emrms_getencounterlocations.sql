CREATE PROCEDURE emrms_getencounterlocations(IN SearchString Varchar(30))

  BEGIN
     Select ho.HealthcareOrganizationID, ho.Name, ho.Abbreviation from healthcare_organization ho
    WHERE ho.Name LIKE CONCAT(SearchString, '%') ORDER BY ho.Name;
  END;