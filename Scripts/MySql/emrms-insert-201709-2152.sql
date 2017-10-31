DELETE FROM h_name;
DELETE FROM h_address;
DELETE FROM h_password;
DELETE FROM h_user;
DELETE FROM h_diagnosis;
DELETE FROM h_encounter;
DELETE FROM h_patient_ids;
DELETE FROM h_patient;
DELETE FROM h_person;
DELETE FROM h_bed;
DELETE FROM h_staff;
DELETE FROM healthcare_organization;

INSERT INTO healthcare_organization(HealthcareOrganizationID,UserId,Name,Abbreviation,Active,EnterprizeObjectId,IdIssuer,IsIdIssuer)
  VALUES (1,'jd101','Main Line Health','MLH',1, 1, 1,0);

  INSERT INTO healthcare_organization(HealthcareOrganizationID,UserId,Name,Abbreviation,Active,EnterprizeObjectId,IdIssuer,IsIdIssuer)
  VALUES (2,'jd101','Paoli Hospital','PH',1, 1, 1,1);

INSERT INTO h_person(HPersonID,UserId,Gender,BirthDate,Race)
  VALUES (1,'jd101',1,'1983-08-23 08:15:10','American');

INSERT INTO h_person(HPersonID,UserId,Gender,BirthDate,Race)
  VALUES (2,'lg22',2,'1982-03-03 04:15:10','Asian');

 INSERT INTO h_person(HPersonID,UserId,Gender,BirthDate,Race)
  VALUES (3,'lg22',1,'1981-03-03 04:15:10','African American');

  INSERT INTO h_person(HPersonID,UserId,Gender,BirthDate,Race)
  VALUES (4,'lg22',2,'1981-03-03 04:15:10','African American');

INSERT INTO h_name(HNameID,UserId,FirstName,MiddleName,LastName,Title,Active,HPersonID)
  VALUES(1,'lg22','Louis','M','Joe','MD',1,2);

INSERT INTO h_name(HNameID,UserId,FirstName,MiddleName,LastName,Title,Active,HPersonID)
  VALUES(2,'jd101','Jane','T','Jeff','RN',1,1);

 INSERT INTO h_name(HNameID,UserId,FirstName,MiddleName,LastName,Title,Active,HPersonID)
  VALUES(3,'jd101','John','Jeff','Doe','',1,3);

   INSERT INTO h_name(HNameID,UserId,FirstName,MiddleName,LastName,Title,Active,HPersonID)
  VALUES(4,'jd101','Mary','Jeff','Davis','',1,4);

INSERT INTO h_address(HAddressID,UserId,StrAddress,City,State,Zip,Country,HomePhoneNo,FaxNo,CellPhoneNo,AddressType,EmailAddress,MailingAddressInd,HPersonID)
  VALUES(1,'lg22','123 Main St','Exton','PA','19341','USA','444-444-444','123-56-789','484-484-3124',1,'aaa@ss.com',1,3);

  INSERT INTO h_patient(HPatientID,UserId,MPINo,OrganDonor,DeceasedInd,IsPatientUnidentified,PrimaryLang,MedHistoryConcent,HPersonID)
  VALUES(3,'jd101','M101','Richie',0,0,'English',0,3);

INSERT INTO h_patient(HPatientID,UserId,MPINo,OrganDonor,DeceasedInd,IsPatientUnidentified,PrimaryLang,MedHistoryConcent,HPersonID)
  VALUES(4,'jd101','M102','Richie',0,0,'English',0,4);
  
 INSERT INTO h_patient_ids(`HPatientId`, `UserId`,`IDValue`,`IdType`,`IdIssuerID`,`IdIssuerName`,`PatientID`)
  VALUES(1,'jd101','MRN001','MRN',2,'Paoli Hospital',3);

  INSERT INTO h_patient_ids(`HPatientId`, `UserId`,`IDValue`,`IdType`,`IdIssuerID`,`IdIssuerName`,`PatientID`)
  VALUES(2,'jd101','MRN002','MRN',2,'Paoli Hospital',4);

  INSERT INTO h_staff(HStaffID,UserID,Type,Active)
  VALUES(1,'lg22',1,1);

  
INSERT INTO h_staff(HStaffID,UserID,Type,Active)
  VALUES(2,'lg22',2,1);


INSERT INTO h_password(HPasswordId,UserId,OldPassword,Password)
  VALUES(1,'lg22',null,'emrms');

  INSERT INTO h_user(HUserID,LoginID,UserType,HPersonID,HPasswordID)
  VALUES(1,'admin',1,1,1);

 INSERT INTO h_user(HUserID,LoginID,UserType,HPersonID,HPasswordID)
  VALUES(3,'nurse01',2,2,1);

  INSERT INTO h_user(HUserID,LoginID,UserType,HPersonID,HPasswordID)
  VALUES(4,'pat01',3,3,1);

  INSERT INTO h_user(HUserID,LoginID,UserType,HPersonID,HPasswordID)
  VALUES(5,'pat02',3,4,1);


INSERT INTO h_bed(BedId,UserId,BedName,BedStatus,Active,LocationID)
  VALUES(1,'lg22','Bed101',1,1,1);


 INSERT INTO h_allergy(HAllergyID,UserID,AllergyName,AllergyCode,AllergyType,Severity,PatientID)
  VALUES(1,'lg22','PeaNut','PN',1,'High',3);

INSERT INTO h_allergy(HAllergyID,UserID,AllergyName,AllergyCode,AllergyType,Severity,PatientID)
  VALUES(2,'lg22','Latex','LAT',2,'Medium',3);


INSERT INTO h_encounter(HEncounterID,UserId,EncStartDateTime,ENCEndDateTime,ENCStatus,EncLocationName
  ,EncounterLocation_ObjectID,EncounterID,EncType,BedName,Patient_ObjectID,AttendingPhysician_ObjectID,Bed_ObjectID)
  VALUES(1,'lg22','2006-02-14 21:55:00',NULL,1,'Exton',1,1,'Out Patient','Bed101',3,1,1);

INSERT INTO h_encounter(HEncounterID,UserId,EncStartDateTime,ENCEndDateTime,ENCStatus,EncLocationName
  ,EncounterLocation_ObjectID,EncounterID,EncType,BedName,Patient_ObjectID,AttendingPhysician_ObjectID,Bed_ObjectID)
  VALUES(2,'lg22','2008-04-04 11:55:00',NULL,1,'Exton',1,2,'Out Patient','',4,1,null);


 INSERT INTO h_diagnosis(HDiagnosisID,UserID,Code,Description,EncounterID,PatientID,Priority)
  VALUES(1,'lg22','123.456','Diabetes',1,3,1);

INSERT INTO h_diagnosis(HDiagnosisID,UserID,Code,Description,EncounterID,PatientID,Priority)
  VALUES(2,'lg22','ABC.DEF','Diabetes',1,3,2);
