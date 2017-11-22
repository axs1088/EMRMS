CREATE PROCEDURE emrms_getencounterlocations()

  BEGIN
     Select HealthcareOrganizationID as objectId, Name as name, Abbreviation as abbreviation from healthcare_organization ORDER BY Name;
  END;