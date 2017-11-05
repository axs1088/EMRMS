package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.model.HAuditRecord;
import edu.psu.sweng500.emrms.model.Policy;

import java.util.List;

public interface AuditEventService {
    public int auditEvent(HAuditRecord auditRecord);
    public List<Policy> getAuditPolicies();
    public List<HAuditRecord> getAuditRecords(String sDateTime, String eDateTime, int polId);
    public List<HAuditRecord> getAuditRecordsByPolicyId(int polId);
    public void deleteAuditRecordsByPolicyId(int polId);
}
