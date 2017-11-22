CREATE PROCEDURE emrms_getdiagnosislist(IN patObjId Int)

  BEGIN

 SELECT
  hd.HDiagnosisID,
  hd.UserId,
  hd.CreationDateTime,
  hd.Code,
  hd.Description AS description,
  hd.EncounterID AS encObjectId,
  hd.PatientID,
  hd.Priority,
  henc.encounterId,
  henc.EncStartDateTime,
  henc.EncType
FROM
  h_diagnosis hd INNER JOIN h_encounter henc ON hd.EncounterID = henc.HEncounterID
WHERE
  hd.PatientID = patObjId;
END;