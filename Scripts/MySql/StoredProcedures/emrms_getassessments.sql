CREATE PROCEDURE emrms_getassessments(IN patientObjectId Int, IN encounterObjectID Int)

  BEGIN

 SELECT 
  HAssessmentID AS objectId,
  AssessmentID,
  Status ,
  Encounter_ObjectID AS encounterObjectId,
  temperature ,
  height ,
  weight ,
  pulse ,
  systolicBP ,
  dystolicBP ,
  heightmeasureId ,
  weightmeasureId ,
  temperaturemeasureId ,
  patient_ObjectID  AS patientObjectId,
  collectedDateTime
FROM
  h_assessment
WHERE
  patient_ObjectID = patientObjectId AND Encounter_ObjectID = EncounterObjectID ORDER By collectedDateTime DESC;

  END;