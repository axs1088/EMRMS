CREATE PROCEDURE emrms_getassessments(IN patientObjectId Int, IN encounterObjectID Int)

  BEGIN

 SELECT
  ha.HAssessmentID AS objectId,
  ha.AssessmentID,
  ha.Status ,
  ha.Encounter_ObjectID AS encounterObjectId,
  ha.temperature ,
  ha.height ,
  ha.weight ,
  ha.pulse ,
  ha.systolicBP ,
  ha.dystolicBP ,
  ha.heightmeasureId ,
  ha.weightmeasureId ,
  ha.temperaturemeasureId ,
  ha.patient_ObjectID  AS patientObjectId,
  ha.collectedDateTime,
  henc.encounterId,
  henc.EncStartDateTime,
  henc.EncType
FROM
  h_assessment ha INNER JOIN h_Encounter henc ON ha.Encounter_ObjectID = henc.HEncounterID
WHERE
  ha.patient_ObjectID = patientObjectId;
  END;