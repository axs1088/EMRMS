CREATE PROCEDURE emrms_getauditpolicies()
  BEGIN
    SELECT PolicyName, PolicyID FROM h_policy order by Policyname ASC;
  END;