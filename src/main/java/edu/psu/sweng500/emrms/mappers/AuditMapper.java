package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.HAuditRecord;
import edu.psu.sweng500.emrms.model.Policy;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

public interface AuditMapper {
    @Insert("INSERT INTO h_audit_record(AuditRecordID,UserId," +
            "PolicyId,Patient_ObjectID,Encounter_ObjectID,PatientName, EncounterID, EventName) VALUES " +
            "(#{auditRecordID},#{userId}, " +
            "#{policyId}, #{patient_ObjectID}, #{encounter_ObjectID},#{patientName},#{encounterID}, #{eventName})")
    public void insertAuditRecord(HAuditRecord auditRecord);

    @Select(value = "{CALL emrms_getauditpolicies()}")
    @Options(statementType = StatementType.CALLABLE)
    public List<Policy> getAuditPolicies();

    @Select(value = "{CALL emrms_getauditRecords(#{sDateTime, mode=IN, jdbcType=VARCHAR}, #{eDateTime, mode=IN, jdbcType=VARCHAR},#{polId, mode=IN, jdbcType=INTEGER})}")
    @Options(statementType = StatementType.CALLABLE)
    public List<HAuditRecord> getAuditPoliciesByDatesAndPolicyId(@Param("sDateTime")String sDateTime, @Param("eDateTime")String eDateTime, @Param("polId")int polId);

    @Select(value = "{CALL emrms_getauditrecordsbypolicy(#{polId, mode=IN, jdbcType=INTEGER})}")
    @Options(statementType = StatementType.CALLABLE)
    public List<HAuditRecord> getAuditPoliciesByPolicyId(int polId);

    @Delete(value = "{CALL emrms_deleteauditrecordsbypolicy(#{polId, mode=IN, jdbcType=INTEGER})}")
    @Options(statementType = StatementType.CALLABLE)
    public void deleteAuditRecordsByPolicyId(int polId);

}
