package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.HAuditRecord;
import edu.psu.sweng500.emrms.model.Policy;

import java.util.ArrayList;
import java.util.List;

public class LocallyCachedAuditMapper implements AuditMapper {
    List<HAuditRecord> auditRecordList;

    public LocallyCachedAuditMapper() {
        auditRecordList = new ArrayList<>();
    }

    @Override
    public void insertAuditRecord(HAuditRecord auditRecord) {
        auditRecordList.add(auditRecord);
    }

    @Override
    public List<Policy> getAuditPolicies() {
        return null;
    }

    @Override
    public List<HAuditRecord> getAuditPoliciesByDatesAndPolicyId(String sDateTime, String eDateTime, int polId) {
        return null;
    }

    @Override
    public List<HAuditRecord> getAuditPoliciesByPolicyId(int polId) {
        return null;
    }

    @Override
    public void deleteAuditRecordsByPolicyId(int polId) {

    }
}
