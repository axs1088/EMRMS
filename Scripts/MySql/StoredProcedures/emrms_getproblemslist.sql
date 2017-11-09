CREATE PROCEDURE emrms_getproblemslist(IN patObjId Int, IN encObjectId Int)

  BEGIN

 SELECT
  ObjectId,
  UserId,
  CreationDateTime,
  ProblemDesc AS description,
  SNOMEDCode AS code,
  EncounterID,
  PatientID,
  Status,
  Priority
FROM
  h_problem_list
WHERE
  PatientID = patObjId AND EncounterID = encObjectId ORDER By Priority DESC;
END;