
DROP DATABASE IF EXISTS emrms;
CREATE DATABASE emrms
	CHARACTER SET utf8
	COLLATE utf8_general_ci;

USE emrms;
CREATE TABLE h_audit_record (
  AuditRecordID BIGINT(20) NOT NULL AUTO_INCREMENT,
  UserId VARCHAR(20) DEFAULT NULL,
  CreationDateTime DATETIME DEFAULT CURRENT_TIMESTAMP,
  PolicyId INT(11) DEFAULT NULL,
  Patient_ObjectID BIGINT(20) DEFAULT NULL,
  Encounter_ObjectID BIGINT(20) DEFAULT NULL,
  PatientName VARCHAR(120) DEFAULT NULL,
  EncounterID VARCHAR(20) DEFAULT NULL,
  EventName VARCHAR(255) NOT NULL,
  PRIMARY KEY (AuditRecordID)
)
ENGINE = INNODB
AUTO_INCREMENT = 28
AVG_ROW_LENGTH = 606
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

CREATE TABLE h_bed (
  BedId BIGINT(20) NOT NULL AUTO_INCREMENT,
  UserId VARCHAR(20) DEFAULT NULL,
  CreationDateTime DATETIME DEFAULT CURRENT_TIMESTAMP,
  BedName VARCHAR(30) DEFAULT NULL,
  BedStatus TINYINT(4) DEFAULT NULL,
  Active TINYINT(1) DEFAULT NULL,
  LocationID BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (BedId),
  INDEX FK_h_bed_h_location (LocationID)
)
ENGINE = INNODB
AUTO_INCREMENT = 10
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

CREATE TABLE h_password (
  HPasswordId BIGINT(20) NOT NULL AUTO_INCREMENT,
  OldPassword VARCHAR(255) DEFAULT NULL,
  CreationDateTime DATETIME DEFAULT CURRENT_TIMESTAMP,
  UserId VARCHAR(20) DEFAULT NULL,
  Password VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (HPasswordId)
)
ENGINE = INNODB
AUTO_INCREMENT = 2
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

CREATE TABLE h_person (
  HPersonID BIGINT(20) NOT NULL AUTO_INCREMENT,
  UserId VARCHAR(20) DEFAULT NULL,
  CreationDateTime DATETIME DEFAULT CURRENT_TIMESTAMP,
  Gender SMALLINT(6) DEFAULT NULL,
  BirthDate DATETIME DEFAULT NULL,
  Race VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (HPersonID)
)
ENGINE = INNODB
AUTO_INCREMENT = 9
AVG_ROW_LENGTH = 8192
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

CREATE TABLE h_policy (
  PolicyName VARCHAR(255) DEFAULT NULL,
  PolicyID INT(11) DEFAULT NULL
)
ENGINE = INNODB
AVG_ROW_LENGTH = 780
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

CREATE TABLE h_units_of_measure (
  measureId INT(11) NOT NULL,
  measureDescription VARCHAR(255) DEFAULT NULL,
  UNIQUE INDEX UK_h_units_of_measure_measureId (measureId)
)
ENGINE = INNODB
AVG_ROW_LENGTH = 2340
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

CREATE TABLE healthcare_organization (
  HealthcareOrganizationID BIGINT(20) NOT NULL AUTO_INCREMENT,
  UserId VARCHAR(20) DEFAULT NULL,
  CreationDateTime DATETIME DEFAULT CURRENT_TIMESTAMP,
  Name VARCHAR(30) DEFAULT NULL,
  Abbreviation VARCHAR(10) DEFAULT NULL,
  Active TINYINT(1) DEFAULT NULL,
  EnterprizeObjectId BIGINT(20) DEFAULT NULL,
  IdIssuer BIGINT(20) DEFAULT NULL,
  IsIdIssuer TINYINT(1) DEFAULT NULL,
  PRIMARY KEY (HealthcareOrganizationID),
  INDEX UK_healthcare_organization_IdIssuer (IdIssuer)
)
ENGINE = INNODB
AUTO_INCREMENT = 7
AVG_ROW_LENGTH = 2730
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

CREATE TABLE h_address (
  HAddressID BIGINT(20) NOT NULL AUTO_INCREMENT,
  UserId VARCHAR(20) DEFAULT NULL,
  CreationDateTime DATETIME DEFAULT CURRENT_TIMESTAMP,
  StrAddress VARCHAR(255) DEFAULT NULL,
  City VARCHAR(64) DEFAULT NULL,
  State VARCHAR(64) DEFAULT NULL,
  Zip VARCHAR(64) DEFAULT NULL,
  Country VARCHAR(64) DEFAULT NULL,
  HomePhoneNo VARCHAR(30) DEFAULT NULL,
  FaxNo VARCHAR(30) DEFAULT NULL,
  CellPhoneNo VARCHAR(30) DEFAULT NULL,
  AddressType TINYINT(4) DEFAULT NULL,
  EmailAddress VARCHAR(50) DEFAULT NULL,
  Active TINYINT(1) DEFAULT NULL,
  MailingAddressInd TINYINT(1) DEFAULT NULL,
  HPersonID BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (HAddressID),
  CONSTRAINT FK_h_address_h_person FOREIGN KEY (HPersonID)
    REFERENCES h_person(HPersonID) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE = INNODB
AUTO_INCREMENT = 2
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

CREATE TABLE h_assessment (
  HAssessmentID BIGINT(20) NOT NULL AUTO_INCREMENT,
  UserId VARCHAR(20) DEFAULT NULL,
  CreationDateTime DATETIME DEFAULT CURRENT_TIMESTAMP,
  AssessmentID INT(11) DEFAULT NULL,
  Status VARCHAR(20) DEFAULT NULL,
  Encounter_ObjectID BIGINT(20) DEFAULT NULL,
  temperature FLOAT DEFAULT NULL,
  height FLOAT DEFAULT NULL,
  weight FLOAT DEFAULT NULL,
  pulse INT(11) DEFAULT NULL,
  systolicBP INT(11) DEFAULT NULL,
  dystolicBP INT(11) DEFAULT NULL,
  heightmeasureId INT(11) DEFAULT NULL,
  weightmeasureId INT(11) DEFAULT NULL,
  temperaturemeasureId INT(11) DEFAULT NULL,
  patient_ObjectID BIGINT(20) NOT NULL,
  collectedDateTime DATETIME DEFAULT NULL,
  PRIMARY KEY (HAssessmentID),
  CONSTRAINT FK_h_assessment_htmeasureId FOREIGN KEY (heightmeasureId)
    REFERENCES h_units_of_measure(measureId) ON DELETE NO ACTION ON UPDATE RESTRICT,
  CONSTRAINT FK_h_assessment_tempmeasureId FOREIGN KEY (temperaturemeasureId)
    REFERENCES h_units_of_measure(measureId) ON DELETE NO ACTION ON UPDATE RESTRICT,
  CONSTRAINT FK_h_assessment_wtmeasureId FOREIGN KEY (weightmeasureId)
    REFERENCES h_units_of_measure(measureId) ON DELETE NO ACTION ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

CREATE TABLE h_name (
  HNameID BIGINT(20) NOT NULL AUTO_INCREMENT,
  UserId VARCHAR(20) DEFAULT NULL,
  CreationDateTime DATETIME DEFAULT CURRENT_TIMESTAMP,
  FirstName VARCHAR(30) DEFAULT NULL,
  MiddleName VARCHAR(30) DEFAULT NULL,
  LastName VARCHAR(30) DEFAULT NULL,
  Active TINYINT(1) DEFAULT NULL,
  HPersonID BIGINT(20) NOT NULL,
  Title VARCHAR(30) DEFAULT NULL,
  GenQualifier VARCHAR(10) DEFAULT NULL,
  PRIMARY KEY (HNameID),
  CONSTRAINT FK_h_name_hperson FOREIGN KEY (HPersonID)
    REFERENCES h_person(HPersonID) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE = INNODB
AUTO_INCREMENT = 9
AVG_ROW_LENGTH = 8192
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

CREATE TABLE h_patient (
  HPatientID BIGINT(20) NOT NULL AUTO_INCREMENT,
  UserId VARCHAR(20) DEFAULT NULL,
  CreationDateTime DATETIME DEFAULT CURRENT_TIMESTAMP,
  MPINo VARCHAR(20) DEFAULT NULL,
  OrganDonor VARCHAR(64) DEFAULT NULL,
  DeceasedInd TINYINT(1) DEFAULT NULL,
  IsPatientUnidentified TINYINT(1) DEFAULT NULL,
  PrimaryLang VARCHAR(64) DEFAULT NULL,
  MedHistoryConcent TINYINT(1) DEFAULT NULL,
  HPersonID BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (HPatientID),
  CONSTRAINT FK_h_patient_h_person FOREIGN KEY (HPersonID)
    REFERENCES h_person(HPersonID) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE = INNODB
AUTO_INCREMENT = 5
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

CREATE TABLE h_staff (
  HStaffID BIGINT(20) NOT NULL AUTO_INCREMENT,
  UserID VARCHAR(20) DEFAULT NULL,
  CreationDateTime DATETIME DEFAULT CURRENT_TIMESTAMP,
  Type INT(11) DEFAULT NULL,
  Active TINYINT(1) DEFAULT NULL,
  HPersonID BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (HStaffID),
  CONSTRAINT FK_h_staff_HPersonID FOREIGN KEY (HPersonID)
    REFERENCES h_person(HPersonID) ON DELETE NO ACTION ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 7
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

CREATE TABLE h_user (
  HUserID BIGINT(20) NOT NULL AUTO_INCREMENT,
  CreationDateTime DATETIME DEFAULT CURRENT_TIMESTAMP,
  LoginID VARCHAR(32) DEFAULT NULL,
  UserType SMALLINT(6) DEFAULT NULL,
  HPersonID BIGINT(20) DEFAULT NULL,
  HPasswordID BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (HUserID),
  CONSTRAINT FK_h_user_h_password FOREIGN KEY (HPasswordID)
    REFERENCES h_password(HPasswordId) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT FK_h_user_h_person_FK_HPersonI FOREIGN KEY (HPersonID)
    REFERENCES h_person(HPersonID) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE = INNODB
AUTO_INCREMENT = 8
AVG_ROW_LENGTH = 2730
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

CREATE TABLE h_allergy (
  HAllergyID BIGINT(20) NOT NULL AUTO_INCREMENT,
  UserID VARCHAR(20) DEFAULT NULL,
  CreationDateTime DATETIME DEFAULT CURRENT_TIMESTAMP,
  AllergyName VARCHAR(120) DEFAULT NULL,
  AllergyCode VARCHAR(32) DEFAULT NULL,
  AllergyType INT(11) DEFAULT NULL,
  PatientID BIGINT(20) DEFAULT NULL,
  Severity VARCHAR(64) DEFAULT NULL,
  PRIMARY KEY (HAllergyID),
  CONSTRAINT FK_h_allergy_h_patient FOREIGN KEY (PatientID)
    REFERENCES h_patient(HPatientID) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE = INNODB
AUTO_INCREMENT = 3
AVG_ROW_LENGTH = 8192
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

CREATE TABLE h_encounter (
  HEncounterID BIGINT(20) NOT NULL AUTO_INCREMENT,
  UserId VARCHAR(20) DEFAULT NULL,
  CreationDateTime DATETIME DEFAULT CURRENT_TIMESTAMP,
  EncStartDateTime DATETIME DEFAULT NULL,
  ENCEndDateTime DATETIME DEFAULT NULL,
  ENCStatus INT(11) DEFAULT NULL,
  EncLocationName VARCHAR(30) DEFAULT NULL,
  EncounterLocation_ObjectID BIGINT(20) DEFAULT NULL,
  EncounterID VARCHAR(20) DEFAULT NULL,
  EncType VARCHAR(20) DEFAULT NULL,
  BedName VARCHAR(64) DEFAULT NULL,
  Patient_ObjectID BIGINT(20) NOT NULL,
  AttendingPhysician_ObjectID BIGINT(20) DEFAULT NULL,
  Bed_ObjectID BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (HEncounterID),
  INDEX UK_h_encounter_EncounterLocation_ObjectID (EncounterLocation_ObjectID),
  CONSTRAINT FK_h_encounter_attending_physi FOREIGN KEY (AttendingPhysician_ObjectID)
    REFERENCES h_staff(HStaffID) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT FK_h_encounter_h_bed FOREIGN KEY (Bed_ObjectID)
    REFERENCES h_bed(BedId) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT FK_h_encounter_h_patient_Patie FOREIGN KEY (Patient_ObjectID)
    REFERENCES h_patient(HPatientID) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE = INNODB
AUTO_INCREMENT = 7
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

CREATE TABLE h_next_of_kin (
  HNextOfKinID BIGINT(20) NOT NULL AUTO_INCREMENT,
  UserId VARCHAR(20) DEFAULT NULL,
  CreationDateTime DATETIME DEFAULT CURRENT_TIMESTAMP,
  Relation VARCHAR(64) DEFAULT NULL,
  HPerson_ObjectID BIGINT(20) DEFAULT NULL,
  HPatient_ObjectID BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (HNextOfKinID),
  CONSTRAINT FK_h_next_of_kin_h_patient FOREIGN KEY (HPatient_ObjectID)
    REFERENCES h_patient(HPatientID) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT FK_h_next_of_kin_h_person FOREIGN KEY (HPerson_ObjectID)
    REFERENCES h_person(HPersonID) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

CREATE TABLE h_patient_ids (
  HPatientId BIGINT(20) NOT NULL AUTO_INCREMENT,
  UserId VARCHAR(20) DEFAULT NULL,
  CreationDateTime DATETIME DEFAULT CURRENT_TIMESTAMP,
  IDValue VARCHAR(20) DEFAULT NULL,
  IdType VARCHAR(64) DEFAULT NULL,
  IdIssuerName VARCHAR(30) DEFAULT NULL,
  IdIssuerID BIGINT(20) DEFAULT NULL,
  PatientID BIGINT(20) NOT NULL,
  PRIMARY KEY (HPatientId),
  CONSTRAINT FK_h_patient_ids_hpatient FOREIGN KEY (PatientID)
    REFERENCES h_patient(HPatientID) ON DELETE NO ACTION ON UPDATE RESTRICT,
  CONSTRAINT FK_h_patient_ids_issuer_id FOREIGN KEY (IdIssuerID)
    REFERENCES healthcare_organization(HealthcareOrganizationID) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
AUTO_INCREMENT = 3
AVG_ROW_LENGTH = 8192
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

CREATE TABLE h_staff_to_unit_association (
  HStaffToUnitAssID BIGINT(20) NOT NULL AUTO_INCREMENT,
  UserId VARCHAR(30) DEFAULT NULL,
  CreationDateTime DATETIME DEFAULT CURRENT_TIMESTAMP,
  StaffID BIGINT(20) DEFAULT NULL,
  HealthcareOrganizationID BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (HStaffToUnitAssID),
  CONSTRAINT FK_h_staff_to_unit_association_h_staff FOREIGN KEY (StaffID)
    REFERENCES h_staff(HStaffID) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT FK_h_staff_to_unit_association_horg FOREIGN KEY (HealthcareOrganizationID)
    REFERENCES healthcare_organization(HealthcareOrganizationID) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

CREATE TABLE h_diagnosis (
  HDiagnosisID BIGINT(20) NOT NULL AUTO_INCREMENT,
  UserId VARCHAR(20) DEFAULT NULL,
  CreationDateTime DATETIME DEFAULT CURRENT_TIMESTAMP,
  Code VARCHAR(20) DEFAULT NULL,
  Description VARCHAR(255) DEFAULT NULL,
  EncounterID BIGINT(20) DEFAULT NULL,
  PatientID BIGINT(20) DEFAULT NULL,
  Priority INT(11) DEFAULT 2,
  PRIMARY KEY (HDiagnosisID),
  CONSTRAINT FK_h_diagnosis_h_encounter FOREIGN KEY (EncounterID)
    REFERENCES h_encounter(HEncounterID) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_h_diagnosis_h_patient FOREIGN KEY (PatientID)
    REFERENCES h_patient(HPatientID) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE = INNODB
AUTO_INCREMENT = 3
AVG_ROW_LENGTH = 8192
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

CREATE TABLE h_medication (
  HMedicationID BIGINT(20) NOT NULL AUTO_INCREMENT,
  UserID VARCHAR(20) DEFAULT NULL,
  CreationDateTime DATETIME DEFAULT CURRENT_TIMESTAMP,
  MedictionName VARCHAR(255) DEFAULT NULL,
  MedDosage VARCHAR(255) DEFAULT NULL,
  MedStartDate DATETIME DEFAULT NULL,
  MedEndDate DATETIME DEFAULT NULL,
  Status VARCHAR(32) DEFAULT NULL,
  EncounterID BIGINT(20) DEFAULT NULL,
  PatientID BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (HMedicationID),
  CONSTRAINT FK_h_medication_h_encounter FOREIGN KEY (EncounterID)
    REFERENCES h_encounter(HEncounterID) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT FK_h_medication_h_patient FOREIGN KEY (PatientID)
    REFERENCES h_patient(HPatientID) ON DELETE CASCADE ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

CREATE TABLE h_problem_list (
  ObjectId BIGINT(20) NOT NULL AUTO_INCREMENT,
  UserId VARCHAR(255) DEFAULT NULL,
  CreationDateTime DATETIME DEFAULT CURRENT_TIMESTAMP,
  ProblemDesc VARCHAR(255) DEFAULT NULL,
  SNOMEDCode VARCHAR(20) DEFAULT NULL,
  EncounterID BIGINT(20) NOT NULL,
  PatientID BIGINT(20) NOT NULL,
  Status VARCHAR(32) DEFAULT NULL,
  Priority INT(11) DEFAULT NULL,
  PRIMARY KEY (ObjectId),
  CONSTRAINT FK_h_problem_list_h_encounter FOREIGN KEY (EncounterID)
    REFERENCES h_encounter(HEncounterID) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT FK_h_problem_list_h_patient FOREIGN KEY (PatientID)
    REFERENCES h_patient(HPatientID) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

DELIMITER $$

CREATE DEFINER = 'root'@'localhost'
PROCEDURE emrms_deleteauditrecordsbypolicy(IN PolId Int)
BEGIN
    DELETE FROM h_audit_record
   Where PolicyId = PolId;
END
$$

CREATE DEFINER = 'root'@'localhost'
PROCEDURE emrms_findpatientbydemographics(IN lName  VARCHAR(30),
                                                 IN fName  VARCHAR(30),
                                                 IN gender INT)
BEGIN

    IF lName <> '' AND fName <> '' AND gender <> '' AND gender <> 3
    THEN
      SELECT
        hpat.HPatientID,
        hn.LastName,
        hn.FirstName,
        hp.Birthdate,
        hp.Gender,
        hpat.MPINo,
        henc.EncStartdateTime,
        henc.encStatus,
        henc.HEncounterID
      FROM h_name hn INNER JOIN h_person hp ON hn.HpersonID = hp.HPersonID
        INNER JOIN h_patient hpat ON hpat.HPersonID = hp.HPersonID
        LEFT OUTER JOIN h_encounter henc ON henc.Patient_ObjectID = hpat.HPatientID AND (henc.HEncounterID in(select MAX(HEncounterID) from h_encounter where ENCStatus = 1 GROUP by Patient_ObjectID))
      WHERE  hn.LastName LIKE CONCAT(lName, '%') AND hn.FirstName LIKE CONCAT(fName, '%') AND
            hp.Gender = gender;
    END IF;

    IF lName <> '' AND fName <> '' AND gender = 3
    THEN
      SELECT
        hpat.HPatientID,
        hn.HpersonID,
        hn.LastName,
        hn.FirstName,
        hp.Birthdate,
        hp.Gender,
        hpat.MPINo,
        henc.EncStartdateTime,
        henc.encStatus,
        henc.HEncounterID
      FROM h_name hn INNER JOIN h_person hp ON hn.HpersonID = hp.HPersonID
        INNER JOIN h_patient hpat ON hpat.HPersonID = hp.HPersonID
        LEFT OUTER JOIN h_encounter henc ON henc.Patient_ObjectID = hpat.HPatientID AND (henc.HEncounterID in(select MAX(HEncounterID) from h_encounter where ENCStatus = 1 GROUP by Patient_ObjectID))
      WHERE hn.LastName LIKE CONCAT(lName, '%') AND hn.FirstName LIKE CONCAT(fName, '%');
    END IF;

    IF lName <> '' AND fName = '' AND gender = 3
    THEN
      SELECT
        hpat.HPatientID,
        hn.LastName,
        hn.FirstName,
        hp.Birthdate,
        hp.Gender,
        hpat.MPINo,
        henc.EncStartdateTime,
        henc.encStatus,
        henc.HEncounterID
      FROM h_name hn INNER JOIN h_person hp ON hn.HpersonID = hp.HPersonID
        INNER JOIN h_patient hpat ON hpat.HPersonID = hp.HPersonID
        LEFT OUTER JOIN h_encounter henc ON henc.Patient_ObjectID = hpat.HPatientID AND (henc.HEncounterID in(select MAX(HEncounterID) from h_encounter where ENCStatus = 1 GROUP by Patient_ObjectID))
      WHERE hn.LastName LIKE CONCAT(lName, '%');
    END IF;

  END
$$

CREATE DEFINER = 'root'@'localhost'
PROCEDURE emrms_findstaff(IN SearchString Varchar(30))
BEGIN
    Select hs.HStaffID As StaffId, CONCAT(hn.LastName, ', ', hn.FirstName, ' ', hn.MiddleName, ' ', hn.title) As StaffName from h_staff hs
    INNER JOIN h_name hn on hn.HPersonID = hs.HStaffID
    WHERE hn.LastName LIKE CONCAT(SearchString, '%') OR hn.FirstName like CONCAT(SearchString, '%')
    ORDER BY hn.LastName;
  END
$$

CREATE DEFINER = 'root'@'localhost'
PROCEDURE emrms_getassessments(IN patientObjectId Int, IN encounterObjectID Int)
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

  END
$$

CREATE DEFINER = 'root'@'localhost'
PROCEDURE emrms_getauditpolicies()
BEGIN
    SELECT PolicyName, PolicyID FROM h_policy order by Policyname ASC;
  END
$$

CREATE DEFINER = 'root'@'localhost'
PROCEDURE emrms_getauditRecords(IN SDateTime Varchar(30), IN EDTime Varchar(30), IN PolId Int)
BEGIN

  IF (SDateTime <> '' AND EDTime <> '') THEN
    SELECT AuditRecordID, UserId,  CreationDateTime, PolicyId, EventName, PatientName, Patient_ObjectID, Encounter_ObjectID, EncounterID
     FROM h_audit_record
   Where CreationDateTime >= STR_TO_DATE(SDateTime, '%Y-%m-%d')
      AND CreationDateTime <= STR_TO_DATE(EDTime + interval 1 day, '%Y-%m-%d')
      AND CreationDateTime is not NULL AND PolicyId = PolId order by CreationDateTime DESC;
 ELSEIF (SDateTime <> '' AND EDTime = '') THEN
    SELECT AuditRecordID, UserId,  CreationDateTime, PolicyId, EventName, PatientName, Patient_ObjectID, Encounter_ObjectID, EncounterID
     FROM h_audit_record
   Where CreationDateTime >= STR_TO_DATE(SDateTime, '%Y-%m-%d')
      AND CreationDateTime is not NULL AND PolicyId = PolId order by CreationDateTime DESC;
  END IF;

  END
$$

CREATE DEFINER = 'root'@'localhost'
PROCEDURE emrms_getauditrecordsbypolicy(IN PolId Int)
BEGIN
    SELECT AuditRecordID, UserId,  CreationDateTime, PolicyId, EventName, PatientName, Patient_ObjectID, Encounter_ObjectID, EncounterID
     FROM h_audit_record
   Where PolicyId = PolId order by CreationDateTime DESC;

  END
$$

CREATE DEFINER = 'root'@'localhost'
PROCEDURE emrms_getencounterlocations(IN SearchString Varchar(30))
BEGIN
     Select ho.HealthcareOrganizationID, ho.Name, ho.Abbreviation from healthcare_organization ho
    WHERE ho.Name LIKE CONCAT(SearchString, '%') ORDER BY ho.Name;
  END
$$

CREATE DEFINER = 'root'@'localhost'
PROCEDURE emrms_getlocationbeds(IN SearchString Varchar(30), IN LocationId Int)
BEGIN
    Select hb.BedId, hb.BedName, hb.BedStatus, hb.locationId from h_bed hb
    INNER JOIN healthcare_organization ho on ho.HealthcareOrganizationID= hb.LocationID
    WHERE hb.BedName LIKE CONCAT(SearchString, '%') and hb.LocationID = LocationId
    ORDER  BY hb.BedName;
  END
$$

CREATE DEFINER = 'root'@'localhost'
PROCEDURE emrms_getloggedinentitydetails()
BEGIN
      SELECT ho.Name, ho.HealthcareOrganizationID, ho.Abbreviation from healthcare_organization ho where ho.HealthcareOrganizationID = 2;
  END
$$

CREATE DEFINER = 'root'@'localhost'
PROCEDURE emrms_getloggedinuserdetails(IN UserObjectID INT)
BEGIN

    DECLARE personID INT;

    SELECT HPersonID
    INTO personID
    FROM h_user
    WHERE HUserID = UserObjectID;

    SELECT CONCAT(hn.LastName, ', ',hn.FirstName, ' ', hn.MiddleName, ' ', hn.Title) AS UserName FROM
      h_name hn WHERE hn.HPersonID = personID;

  END
$$

CREATE DEFINER = 'root'@'localhost'
PROCEDURE emrms_getnursecensuslist(IN LocationObjectID INT)
BEGIN

    SELECT
      hn.LastName,
      hn.FirstName,
      hp.Birthdate,
      hp.Gender,
      hpat.MPINo,
      hpat.HPatientID,
      henc.EncStartdateTime,
      henc.encStatus,
      henc.HEncounterID
    FROM h_name hn INNER JOIN h_person hp ON hn.HpersonID = hp.HPersonID
      INNER JOIN h_patient hpat ON hpat.HPersonID = hp.HPersonID
      LEFT OUTER JOIN h_encounter henc ON henc.Patient_ObjectID = hpat.HPatientID
    WHERE henc.encStatus = 1 AND henc.EncounterLocation_ObjectID = LocationObjectID
    AND henc.HEncounterID in(select MAX(HEncounterID) from h_encounter where ENCStatus = 1 GROUP by Patient_ObjectID);

  END
$$

CREATE DEFINER = 'root'@'localhost'
PROCEDURE emrms_getpatientdemographics(IN PatientObjectID int)
BEGIN
      SELECT
        hn.LastName,
        hn.FirstName,
        hp.Birthdate,
        hp.Gender,
        hpat.MPINo,
        hpid.IDValue AS MRN,
        ha.HAddressID,
        ha.StrAddress,
        ha.City,
        ha.State,
        ha.Zip,
        ha.HomePhoneNo,
        ha.CellPhoneNo,
        ha.FaxNo,
        ha.AddressType,
        ha.EmailAddress,
        ha.MailingAddressInd
      FROM h_name hn INNER JOIN h_person hp ON hn.HpersonID = hp.HPersonID
        LEFT OUTER JOIN h_address ha on ha.HPersonID = hp.HPersonID
        INNER JOIN h_patient hpat ON hpat.HPatientID = hp.HPersonID
        LEFT OUTER JOIN h_patient_ids hpid on hpid.PatientID = hpat.HPatientID and hpid.IdType = 'MRN'
      WHERE hpat.HPatientID = PatientObjectID;

  END
$$

CREATE DEFINER = 'root'@'localhost'
PROCEDURE emrms_getpatientvisitdetails(IN PatientObjectID int, IN EncounterObjectID int)
BEGIN

      SELECT
        hn.LastName,
        hn.FirstName,
        hp.Birthdate,
        hp.Gender,
        hpat.MPINo,
        hpid.IDValue AS MRN,
        ha.HAddressID,
        ha.StrAddress,
        ha.City,
        ha.State,
        ha.Zip,
        ha.HomePhoneNo,
        ha.CellPhoneNo,
        ha.FaxNo,
        ha.AddressType,
        ha.EmailAddress,
        ha.MailingAddressInd,
        henc.HEncounterID,
        henc.EncounterID,
        henc.EncStartDateTime,
        henc.ENCEndDateTime,
        henc.ENCStatus,
        henc.EncounterLocation_ObjectID,
        henc.EncLocationName,
        henc.EncType,
        henc.BedName,
        henc.AttendingPhysician_ObjectID,
        henc.Bed_ObjectID
      FROM h_name hn INNER JOIN h_person hp ON hn.HpersonID = hp.HPersonID
        LEFT OUTER JOIN h_address ha on ha.HPersonID = hp.HPersonID
        INNER JOIN h_patient hpat ON hpat.HPatientID = hp.HPersonID
        LEFT OUTER JOIN h_patient_ids hpid on hpid.PatientID = hpat.HPatientID and hpid.IdType = 'MR'
        LEFT OUTER JOIN h_encounter henc on henc.Patient_ObjectID= hpat.HPatientID
      WHERE hpat.HPatientID = PatientObjectID and henc.HEncounterID = EncounterObjectID;

  END
$$

CREATE DEFINER = 'root'@'localhost'
PROCEDURE emrms_getphysiciancensuslist(IN UserObjectID INT)
BEGIN
    DECLARE staffid INT;
    DECLARE personID INT;
    SELECT HPersonID
    INTO personID
    FROM h_user
    WHERE HUserID = UserObjectID;
    SELECT hs.HStaffID
    INTO staffid
    FROM h_staff hs
    WHERE hs.HPersonID = personID AND type = 1;

    SELECT
      hn.LastName,
      hn.FirstName,
      hp.Birthdate,
      hp.Gender,
      hpat.MPINo,
      hpat.HPatientID,
      henc.EncStartdateTime,
      henc.encStatus,
      henc.HEncounterID
    FROM h_name hn INNER JOIN h_person hp ON hn.HpersonID = hp.HPersonID
      INNER JOIN h_patient hpat ON hpat.HPersonID = hp.HPersonID
      LEFT OUTER JOIN h_encounter henc ON henc.Patient_ObjectID = hpat.HPatientID
    WHERE henc.encStatus = 1 AND henc.AttendingPhysician_ObjectID = staffid
    AND (henc.HEncounterID in(select MAX(HEncounterID) from h_encounter where ENCStatus = 1 GROUP by Patient_ObjectID));

  END
$$

CREATE DEFINER = 'root'@'localhost'
PROCEDURE emrms_getproblemslist(IN patObjId Int, IN encObjectId Int)
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
END
$$

CREATE DEFINER = 'root'@'localhost'
PROCEDURE emrms_getusertype(IN UserObjectID INT)
BEGIN
    DECLARE personID INT;
    DECLARE staffid INT;
    DECLARE UserType INT DEFAULT 0;

    SELECT HPersonID
    INTO personID
    FROM h_user
    WHERE HUserID = UserObjectID;

    /*Physician*/
    SELECT hs.HStaffID
    INTO staffid
    FROM h_staff hs
    WHERE hs.HStaffID = personID AND type = 1;
    IF staffid > 0
    THEN
      SET UserType = 1;
    END IF;

    /*Nurse*/
    SELECT hs.HStaffID
    INTO staffid
    FROM h_staff hs
    WHERE hs.HStaffID = personID AND type = 2;
    IF (staffid > 0)
    THEN
      SET UserType = 2;
    END IF;

    /*Patient*/
    SELECT hp.HPatientID
    INTO staffid
    FROM h_Patient hp
    WHERE hp.HPatientID = personID;
    IF (staffid > 0)
    THEN
      SET UserType = 3;
    END IF;

    SELECT UserType;

  END
$$

DELIMITER ;

INSERT INTO h_audit_record VALUES
(1, 'admin', '2017-11-16 10:04:32', 1, 0, 0, NULL, NULL, 'Login'),
(2, 'admin', '2017-11-16 10:04:32', 3, 0, 0, NULL, NULL, 'View Patient Census'),
(3, 'admin', '2017-11-16 10:06:18', 2, 0, 0, NULL, NULL, 'Logout'),
(4, 'admin', '2017-11-16 10:06:21', 1, 0, 0, NULL, NULL, 'Login'),
(5, 'admin', '2017-11-16 10:06:21', 3, 0, 0, NULL, NULL, 'View Patient Census'),
(6, 'admin', '2017-11-16 10:06:54', 1, 0, 0, NULL, NULL, 'Login'),
(7, 'admin', '2017-11-16 10:06:54', 3, 0, 0, NULL, NULL, 'View Patient Census'),
(8, 'admin', '2017-11-16 10:07:10', 1, 0, 0, NULL, NULL, 'Login'),
(9, 'admin', '2017-11-16 10:07:10', 3, 0, 0, NULL, NULL, 'View Patient Census'),
(10, 'admin', '2017-11-16 10:07:17', 9, 3, 0, NULL, NULL, 'View Patient Details'),
(11, 'admin', '2017-11-16 10:07:39', 4, 0, 0, NULL, NULL, 'Patient Locator'),
(12, 'admin', '2017-11-16 10:07:40', 9, 3, 0, NULL, NULL, 'View Patient Details'),
(13, 'admin', '2017-11-16 10:07:42', 10, 3, 0, NULL, NULL, 'View Encounter Details'),
(14, NULL, '2017-11-16 10:08:17', 7, 3, 3, 'Doe, John Jeff', NULL, 'Add Encounter'),
(15, NULL, '2017-11-16 10:08:42', 7, 3, 4, 'Doe, John Jeff', NULL, 'Add Encounter'),
(16, 'admin', '2017-11-16 10:09:15', 2, 0, 0, NULL, NULL, 'Logout'),
(17, 'admin', '2017-11-16 10:16:06', 1, 0, 0, NULL, NULL, 'Login'),
(18, 'admin', '2017-11-16 10:16:06', 3, 0, 0, NULL, NULL, 'View Patient Census'),
(19, 'admin', '2017-11-16 10:16:12', 9, 4, 0, NULL, NULL, 'View Patient Details'),
(20, 'admin', '2017-11-16 10:16:15', 10, 4, 0, NULL, NULL, 'View Encounter Details'),
(21, NULL, '2017-11-16 10:16:42', 7, 4, 5, 'Davis, Mary Jeff', NULL, 'Add Encounter'),
(22, NULL, '2017-11-16 10:16:53', 7, 4, 6, 'Davis, Mary Jeff', NULL, 'Add Encounter'),
(23, 'admin', '2017-11-16 10:17:11', 1, 0, 0, NULL, NULL, 'Login'),
(24, 'admin', '2017-11-16 10:17:11', 3, 0, 0, NULL, NULL, 'View Patient Census'),
(25, 'admin', '2017-11-16 10:17:33', 1, 0, 0, NULL, NULL, 'Login'),
(26, 'admin', '2017-11-16 10:17:33', 3, 0, 0, NULL, NULL, 'View Patient Census'),
(27, 'admin', '2017-11-16 10:17:38', 2, 0, 0, NULL, NULL, 'Logout');

INSERT INTO h_bed VALUES
(1, 'lg22', '2017-11-16 10:06:03', 'Bed101', 1, 1, 3),
(2, 'lg22', '2017-11-16 10:06:03', 'Bed102', 1, 1, 3),
(3, 'lg22', '2017-11-16 10:06:03', 'Bed103', 1, 1, 3),
(4, 'lg22', '2017-11-16 10:06:03', 'Bed104', 1, 1, 4),
(5, 'lg22', '2017-11-16 10:06:03', 'Bed105', 1, 1, 4),
(6, 'lg22', '2017-11-16 10:06:03', 'Bed106', 1, 1, 5),
(7, 'lg22', '2017-11-16 10:06:03', 'Bed107', 1, 1, 5),
(8, 'lg22', '2017-11-16 10:06:03', 'Bed106', 1, 1, 6),
(9, 'lg22', '2017-11-16 10:06:03', 'Bed107', 1, 1, 6);

INSERT INTO h_password VALUES
(1, NULL, '2017-11-16 10:06:03', 'lg22', 'emrms');

INSERT INTO h_person VALUES
(1, 'jd101', '2017-11-16 10:06:02', 1, '1983-08-23 08:15:10', 'American'),
(2, 'lg22', '2017-11-16 10:06:02', 2, '1982-03-03 04:15:10', 'Asian'),
(3, 'lg22', '2017-11-16 10:06:02', 1, '1981-03-03 04:15:10', 'African American'),
(4, 'lg22', '2017-11-16 10:06:02', 2, '1981-03-03 04:15:10', 'African American'),
(5, 'jd101', '2017-11-16 10:06:02', 1, '1979-08-23 08:15:10', 'American'),
(6, 'lg22', '2017-11-16 10:06:02', 2, '1978-03-03 04:15:10', 'Asian'),
(7, 'jd101', '2017-11-16 10:06:02', 1, '1977-08-23 08:15:10', 'American'),
(8, 'lg22', '2017-11-16 10:06:02', 2, '1976-03-03 04:15:10', 'Asian');

INSERT INTO h_policy VALUES
('Login', 1),
('LogOff', 2),
('View Patient Census', 3),
('Patient Locator', 4),
('Register Patient', 5),
('Revise Patient', 6),
('Add Encounter', 7),
('Revise Encounter', 8),
('View Patient Details', 9),
('View Encounter Details', 10),
('View Patient Charts', 11),
('Add Allergy', 12),
('Revise Allergy', 13),
('Delete Allergy', 14),
('Add Assessment', 15),
('Revise Assessment', 16),
('Add Problem', 17),
('Revise Problem', 18),
('Add Diagnosis', 19),
('Revise Diagnosis', 20),
('Delete Diagnosis', 21);

INSERT INTO h_units_of_measure VALUES
(1, 'Inches'),
(2, 'Centimeters'),
(3, 'lbs'),
(4, 'kg'),
(5, 'F'),
(6, 'C'),
(7, 'Kelvin');

INSERT INTO healthcare_organization VALUES
(1, 'jd101', '2017-11-16 10:06:01', 'Main Line Health', 'MLH', 1, 1, 1, 0),
(2, 'jd101', '2017-11-16 10:06:01', 'Paoli Hospital', 'PH', 1, 1, 1, 1),
(3, 'jd101', '2017-11-16 10:06:01', 'PH-Cardiology', 'PHCard', 1, 2, 1, 0),
(4, 'jd101', '2017-11-16 10:06:01', 'PH-General', 'PHGen', 1, 2, 1, 0),
(5, 'jd101', '2017-11-16 10:06:01', 'PH-Endocrinology', 'PHEndo', 1, 2, 1, 0),
(6, 'jd101', '2017-11-16 10:06:01', 'PH-InternalMedicine', 'PHIntMed', 1, 2, 1, 0);

INSERT INTO h_address VALUES
(1, 'lg22', '2017-11-16 10:06:02', '123 Main St', 'Exton', 'PA', '19341', 'USA', '444-444-444', '123-56-789', '484-484-3124', 1, 'aaa@ss.com', NULL, 1, 3);

INSERT INTO h_name VALUES
(1, 'lg22', '2017-11-16 10:06:02', 'Louis', 'M', 'Joe', 1, 2, 'MD', NULL),
(2, 'jd101', '2017-11-16 10:06:02', 'Jane', 'T', 'Jeff', 1, 1, 'RN', NULL),
(3, 'jd101', '2017-11-16 10:06:02', 'John', 'Jeff', 'Doe', 1, 3, '', NULL),
(4, 'jd101', '2017-11-16 10:06:02', 'Mary', 'Jeff', 'Davis', 1, 4, '', NULL),
(5, 'jd101', '2017-11-16 10:06:02', 'John', 'M', 'Johnson', 1, 5, 'MD', NULL),
(6, 'jd101', '2017-11-16 10:06:02', 'David', 'T', 'Joyce', 1, 6, 'MD', NULL),
(7, 'jd101', '2017-11-16 10:06:02', 'Allerd', 'M', 'Rachel', 1, 7, 'MD', NULL),
(8, 'jd101', '2017-11-16 10:06:02', 'Rex', 'T', 'Alexander', 1, 8, 'MD', NULL);

INSERT INTO h_patient VALUES
(3, 'jd101', '2017-11-16 10:06:02', 'M101', 'Richie', 0, 0, 'English', 0, 3),
(4, 'jd101', '2017-11-16 10:06:02', 'M102', 'Richie', 0, 0, 'English', 0, 4);

INSERT INTO h_staff VALUES
(1, 'lg22', '2017-11-16 10:06:02', 1, 1, 1),
(2, 'lg22', '2017-11-16 10:06:02', 2, 1, 2),
(3, 'lg22', '2017-11-16 10:06:02', 1, 1, 5),
(4, 'lg22', '2017-11-16 10:06:03', 1, 1, 6),
(5, 'lg22', '2017-11-16 10:06:03', 1, 1, 7),
(6, 'lg22', '2017-11-16 10:06:03', 1, 1, 8);

INSERT INTO h_user VALUES
(1, '2017-11-16 10:06:03', 'admin', 1, 1, 1),
(3, '2017-11-16 10:06:03', 'nurse01', 2, 2, 1),
(4, '2017-11-16 10:06:03', 'phys01', 3, 5, 1),
(5, '2017-11-16 10:06:03', 'phys02', 3, 6, 1),
(6, '2017-11-16 10:06:03', 'phys03', 3, 7, 1),
(7, '2017-11-16 10:06:03', 'phys04', 3, 8, 1);

INSERT INTO h_allergy VALUES
(1, 'lg22', '2017-11-16 10:06:03', 'PeaNut', 'PN', 1, 3, 'High'),
(2, 'lg22', '2017-11-16 10:06:03', 'Latex', 'LAT', 2, 3, 'Medium');

INSERT INTO h_encounter VALUES
(1, 'lg22', '2017-11-16 10:06:03', '2006-02-14 21:55:00', NULL, 1, 'Exton', 1, '1', 'Out Patient', 'Bed101', 3, 1, 1),
(2, 'lg22', '2017-11-16 10:06:03', '2008-04-04 11:55:00', NULL, 1, 'Exton', 1, '2', 'Out Patient', '', 4, 1, NULL),
(3, NULL, '2017-11-16 10:08:17', '2017-11-16 00:00:00', NULL, 1, 'Main Line Health', 0, 'E101', 'Outpatient', NULL, 3, 1, NULL),
(4, NULL, '2017-11-16 10:08:42', '2017-11-16 00:00:00', NULL, 1, 'Main Line Health', 0, 'E101', 'Outpatient', NULL, 3, 1, NULL),
(5, NULL, '2017-11-16 10:16:42', '2017-11-16 00:00:00', NULL, 1, 'Main Line Health', 0, 'E151', 'Outpatient', NULL, 4, 3, NULL),
(6, NULL, '2017-11-16 10:16:53', '2017-11-16 00:00:00', NULL, 1, 'Main Line Health', 0, 'E151', 'Outpatient', NULL, 4, 3, NULL);

INSERT INTO h_patient_ids VALUES
(1, 'jd101', '2017-11-16 10:06:02', 'MRN001', 'MRN', 'Paoli Hospital', 2, 3),
(2, 'jd101', '2017-11-16 10:06:02', 'MRN002', 'MRN', 'Paoli Hospital', 2, 4);

INSERT INTO h_diagnosis VALUES
(1, 'lg22', '2017-11-16 10:06:03', '123.456', 'Diabetes', 1, 3, 1),
(2, 'lg22', '2017-11-16 10:06:04', 'ABC.DEF', 'Diabetes', 1, 3, 2);

