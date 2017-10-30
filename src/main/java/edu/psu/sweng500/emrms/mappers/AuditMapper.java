package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.HAuditRecord;
import org.apache.ibatis.annotations.Insert;

public interface AuditMapper {
    @Insert("INSERT INTO h_audit_record(AuditRecordID,UserId," +
            "PolicyId,Patient_ObjectID,Encounter_ObjectID,PatientName, EncounterID, EventName) VALUES " +
            "(#{auditRecordID},#{userId}, " +
            "#{policyId}, #{patient_ObjectID}, #{encounter_ObjectID},#{patientName},#{encounterID}, #{eventName})")
    public void insertAuditRecord(HAuditRecord auditRecord);

}
