CREATE PROCEDURE emrms_getproblemslist(IN patObjId Int, IN encObjectId Int)

  BEGIN

 SELECT
  hpl.ObjectId,
  hpl.UserId,
  hpl.CreationDateTime,
  hpl.ProblemDesc AS description,
  hpl.SNOMEDCode AS code,
  hpl.EncounterID AS encObjectId,
  hpl.PatientID,
  hpl.Status,
  hpl.Priority,
  henc.encounterId ,
  henc.EncStartDateTime,
  henc.EncType
FROM
  h_problem_list hpl INNER JOIN h_encounter henc ON hpl.EncounterID = henc.HEncounterID
WHERE
  PatientID = patObjId;
END;