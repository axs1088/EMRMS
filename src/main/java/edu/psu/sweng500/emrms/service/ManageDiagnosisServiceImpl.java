package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.mappers.ChartingMapper;
import edu.psu.sweng500.emrms.mappers.PatientDemographicsMapper;
import edu.psu.sweng500.emrms.mappers.SaveEncounterMapper;
import edu.psu.sweng500.emrms.model.*;
import edu.psu.sweng500.emrms.util.PersonPatientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("manageDiagnosisService")

public class ManageDiagnosisServiceImpl implements ManageDiagnosisService {

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
    public int AddDiagnosis(HDiagnosis diagnosis){
        chartingMapper.addDiagnosis(diagnosis);
        HAuditRecord auditRecord = new HAuditRecord();
        auditRecord.setEventName("Add Diagnosis");
        auditRecord.setPolicyId(19);
        auditRecord.setPatient_ObjectID(diagnosis.getPatientID());
        auditRecord.setPatientName(patientUtils.getPatientName(diagnosis.getPatientID()));
        auditEventService.auditEvent(auditRecord);
        return 0;
    }

    @Override
    public int ReviseDiagnosis(HDiagnosis diagnosis) throws Exception {
        chartingMapper.reviseDiagnosis(diagnosis);
        HAuditRecord auditRecord = new HAuditRecord();
        auditRecord.setEventName("Revise Diagnosis");
        auditRecord.setPolicyId(20);
        auditRecord.setPatient_ObjectID(diagnosis.getPatientID());
        auditRecord.setPatientName(patientUtils.getPatientName(diagnosis.getPatientID()));
        auditEventService.auditEvent(auditRecord);
        return 0;
    }

    @Override
    public int DeleteDiagnosis(HDiagnosis diagnosis) throws Exception {
        chartingMapper.deleteDiagnosis(diagnosis);
        HAuditRecord auditRecord = new HAuditRecord();
        auditRecord.setEventName("Delete Diagnosis");
        auditRecord.setPolicyId(21);
        auditRecord.setPatient_ObjectID(diagnosis.getPatientID());
        auditRecord.setPatientName(patientUtils.getPatientName(diagnosis.getPatientID()));
        auditEventService.auditEvent(auditRecord);
        return 0;
    }
}
