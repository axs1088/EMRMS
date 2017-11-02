package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.mappers.ChartingMapper;
import edu.psu.sweng500.emrms.model.HAssessment;
import edu.psu.sweng500.emrms.model.HAuditRecord;
import edu.psu.sweng500.emrms.util.PersonPatientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("manageAssessmentService")

public class ManageAssessmentServiceImpl implements ManageAssessmentService {
    @Autowired
    private ChartingMapper chartingMapper;

    @Autowired
    private AuditEventService auditEventService;

    @Autowired
    private PersonPatientUtils patientUtils;

    public void setChartingMapper(ChartingMapper chartingMapper) {
        this.chartingMapper = chartingMapper;
    }

    @Override
    public int AddAssessment(HAssessment assessment) {
        chartingMapper.addAssessment(assessment);
        HAuditRecord auditRecord = new HAuditRecord();
        auditRecord.setEventName("Add Assessment");
        auditRecord.setPolicyId(15);
        auditRecord.setEncounter_ObjectID(assessment.getEncounterObjectId());
        auditRecord.setPatient_ObjectID(assessment.getPatientObjectId());
        auditRecord.setPatientName(patientUtils.getPatientName(assessment.getPatientObjectId()));
        auditEventService.auditEvent(auditRecord);
        return 0;
    }

    @Override
    public int ReviseAssessment(HAssessment assessment) {
        chartingMapper.addAssessment(assessment);
        HAuditRecord auditRecord = new HAuditRecord();
        auditRecord.setEventName("Revise Assessment");
        auditRecord.setPolicyId(16);
        auditRecord.setEncounter_ObjectID(assessment.getEncounterObjectId());
        auditRecord.setPatient_ObjectID(assessment.getPatientObjectId());
        auditRecord.setPatientName(patientUtils.getPatientName(assessment.getPatientObjectId()));
        auditEventService.auditEvent(auditRecord);
        return 0;
    }
}
