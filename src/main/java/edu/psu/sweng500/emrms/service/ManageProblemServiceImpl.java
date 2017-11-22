package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.mappers.ChartingMapper;
import edu.psu.sweng500.emrms.model.HAuditRecord;
import edu.psu.sweng500.emrms.model.HProblem;
import edu.psu.sweng500.emrms.util.PersonPatientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("manageProblemService")
public class ManageProblemServiceImpl implements  ManageProblemService{

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
    public int AddProblem(HProblem problem) {
        chartingMapper.addProblem(problem);
        HAuditRecord auditRecord = new HAuditRecord();
        auditRecord.setEventName("Add Problem");
        auditRecord.setPolicyId(17);
        auditRecord.setPatient_ObjectID(problem.getPatientID());
        auditRecord.setPatientName(patientUtils.getPatientName(problem.getPatientID()));
        auditRecord.setEncounter_ObjectID(problem.getEncObjectId());
        auditEventService.auditEvent(auditRecord);
        return 0;
    }

    @Override
    public int ReviseProblem(HProblem problem) {
        chartingMapper.reviseProblem(problem);
        HAuditRecord auditRecord = new HAuditRecord();
        auditRecord.setEventName("Revise Problem");
        auditRecord.setPolicyId(18);
        auditRecord.setPatient_ObjectID(problem.getPatientID());
        auditRecord.setPatientName(patientUtils.getPatientName(problem.getPatientID()));
        auditRecord.setEncounter_ObjectID(problem.getEncObjectId());
        auditEventService.auditEvent(auditRecord);
        return 0;
    }

    @Override
    public int DeleteProblem(HProblem problem) {
        chartingMapper.deleteProblem(problem);
        HAuditRecord auditRecord = new HAuditRecord();
        auditRecord.setEventName("Delete Problem");
        auditRecord.setPolicyId(22);
        auditRecord.setPatient_ObjectID(problem.getPatientID());
        auditRecord.setPatientName(patientUtils.getPatientName(problem.getPatientID()));
        auditRecord.setEncounter_ObjectID(problem.getEncObjectId());
        auditEventService.auditEvent(auditRecord);
        return 0;
    }

    @Override
    public List<HProblem> GetProblemsList(int patientObjId, int encObjId) {
        return chartingMapper.getPatientProblemList(patientObjId,encObjId);
    }
}
