CREATE PROCEDURE emrms_getauditRecords(IN SDateTime Varchar(30), IN EDTime Varchar(30), IN PolId Int)
  BEGIN

  IF (SDateTime <> '' AND EDTime <> '') THEN
    SELECT AuditRecordID, UserId,  CreationDateTime, PolicyId, EventName, PatientName, Patient_ObjectID, Encounter_ObjectID, EncounterID
     FROM h_audit_record
   Where CreationDateTime >= STR_TO_DATE(SDateTime, '%Y-%m-%d %H:%i:%s')
      AND CreationDateTime <= STR_TO_DATE(EDTime, '%Y-%m-%d %H:%i:%s')
      AND CreationDateTime is not NULL AND PolicyId = PolId order by CreationDateTime DESC;
 ELSEIF (SDateTime <> '' AND EDTime = '') THEN
    SELECT AuditRecordID, UserId,  CreationDateTime, PolicyId, EventName, PatientName, Patient_ObjectID, Encounter_ObjectID, EncounterID
     FROM h_audit_record
   Where CreationDateTime >= STR_TO_DATE(SDateTime, '%Y-%m-%d %H:%i:%s')
      AND CreationDateTime is not NULL AND PolicyId = PolId order by CreationDateTime DESC;
  END IF;

  END;