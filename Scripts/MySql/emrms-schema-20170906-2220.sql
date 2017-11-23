DROP DATABASE IF EXISTS emrms;
CREATE DATABASE IF NOT EXISTS emrms
	CHARACTER SET utf8
	COLLATE utf8_general_ci;


USE emrms;

CREATE TABLE h_units_of_measure (
  measureId INT(11) NOT NULL,
  measureDescription VARCHAR(255) DEFAULT NULL,
  UNIQUE INDEX UK_h_units_of_measure_measureId (measureId)
)
ENGINE = INNODB
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

CREATE TABLE h_audit_record (
  AuditRecordID BIGINT(20) NOT NULL AUTO_INCREMENT,
  UserId VARCHAR(100) DEFAULT NULL,
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
AUTO_INCREMENT = 2
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
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;


CREATE TABLE  h_person (
  HPersonID BIGINT(20) NOT NULL AUTO_INCREMENT,
  UserId VARCHAR(20) DEFAULT NULL,
  CreationDateTime DATETIME DEFAULT CURRENT_TIMESTAMP,
  Gender SMALLINT(6) DEFAULT NULL,
  BirthDate DATETIME DEFAULT NULL,
  Race VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (HPersonID)
)
ENGINE = INNODB
AUTO_INCREMENT = 3
AVG_ROW_LENGTH = 8192
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;


CREATE TABLE  h_policy (
  PolicyName VARCHAR(255) DEFAULT NULL,
  PolicyID INT(11) DEFAULT NULL
)
ENGINE = INNODB
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
    REFERENCES emrms.h_person(HPersonID) ON DELETE NO ACTION ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 7
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;


CREATE TABLE  healthcare_organization (
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
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

CREATE TABLE  h_address (
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
AVG_ROW_LENGTH = 8192
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;


CREATE TABLE  h_patient (
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
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

CREATE TABLE  h_staff_to_unit_association (
  HStaffToUnitAssID BIGINT(20)  NOT NULL AUTO_INCREMENT,
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
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

CREATE TABLE  h_user (
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
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;


CREATE TABLE  h_allergy (
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
AUTO_INCREMENT = 1
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
  EncounterReason VARCHAR(255) DEFAULT NULL,
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
AUTO_INCREMENT = 2
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;


CREATE TABLE  h_next_of_kin (
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
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;


CREATE TABLE  h_patient_ids (
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
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;


CREATE TABLE  h_diagnosis (
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
AUTO_INCREMENT = 1
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
