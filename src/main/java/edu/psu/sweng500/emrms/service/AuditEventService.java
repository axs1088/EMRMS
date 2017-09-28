package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.model.HAuditRecord;

public interface AuditEventService {
    public int auditEvent(HAuditRecord auditRecord);
}
