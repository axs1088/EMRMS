
CREATE PROCEDURE emrms_deleteauditrecordsbypolicy(IN PolId Int)
  BEGIN
    DELETE FROM h_audit_record
   Where PolicyId = PolId;
END;