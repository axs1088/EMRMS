CREATE PROCEDURE emrms_getauditrecordsbypolicy(IN PolId Int)
  BEGIN
    SELECT AuditRecordID, UserId,  CreationDateTime, PolicyId, EventName, PatientName, Patient_ObjectID, Encounter_ObjectID, EncounterID
     FROM h_audit_record
   Where PolicyId = PolId order by CreationDateTime DESC;

  END;
