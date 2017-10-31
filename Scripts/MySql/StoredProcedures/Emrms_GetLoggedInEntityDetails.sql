
CREATE PROCEDURE emrms_getloggedinentitydetails()

  BEGIN
      SELECT ho.Name, ho.HealthcareOrganizationID, ho.Abbreviation from healthcare_organization ho where ho.HealthcareOrganizationID = 2;
  END;