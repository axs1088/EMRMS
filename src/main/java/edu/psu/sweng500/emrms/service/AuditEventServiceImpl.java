package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.mappers.AuditMapper;
import edu.psu.sweng500.emrms.model.HAuditRecord;
import edu.psu.sweng500.emrms.model.Policy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("auditEventService")
public class AuditEventServiceImpl implements AuditEventService {
    @Autowired
    private AuditMapper auditMapper;

    public void setAuditMapper(AuditMapper auditMapper) {
        this.auditMapper = auditMapper;
    }

    @Override
    public int auditEvent(HAuditRecord auditRecord) {
        auditMapper.insertAuditRecord(auditRecord);
        return 0;
    }

    @Override
    public List<Policy> getAuditPolicies() {
        List<Policy> policies = auditMapper.getAuditPolicies();
        return policies;
    }

    @Override
    public List<HAuditRecord> getAuditRecords(String sDateTime, String eDateTime, int polId) {
        List<HAuditRecord>auditRecords = auditMapper.getAuditPoliciesByDatesAndPolicyId(sDateTime,eDateTime,polId);
        return auditRecords;
    }

    @Override
    public List<HAuditRecord> getAuditRecordsByPolicyId(int polId) {
        List<HAuditRecord>auditRecords = auditMapper.getAuditPoliciesByPolicyId(polId);
        return auditRecords;
    }

    @Override
    public void deleteAuditRecordsByPolicyId(int polId) {
        auditMapper.deleteAuditRecordsByPolicyId(polId);
    }
}
