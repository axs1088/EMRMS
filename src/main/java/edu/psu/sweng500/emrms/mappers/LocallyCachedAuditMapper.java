package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.HAuditRecord;

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
}
