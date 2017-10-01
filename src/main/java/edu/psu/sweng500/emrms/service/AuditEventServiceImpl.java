package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.mappers.AuditMapper;
import edu.psu.sweng500.emrms.model.HAuditRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("auditEventService")
public class AuditEventServiceImpl implements AuditEventService {
    @Autowired
    private AuditMapper auditMapper;

    @Override
    public int auditEvent(HAuditRecord auditRecord) {
        auditMapper.insertAuditRecord(auditRecord);
        return 0;
    }
}
