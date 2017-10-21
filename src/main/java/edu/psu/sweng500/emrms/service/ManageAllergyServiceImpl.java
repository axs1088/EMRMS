package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.application.ApplicationAuditHelper;
import edu.psu.sweng500.emrms.mappers.ChartingMapper;
import edu.psu.sweng500.emrms.model.HAllergy;
import edu.psu.sweng500.emrms.model.HAuditRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service("manageAllergyService")
public class ManageAllergyServiceImpl implements ManageAllergyService {

    @Autowired
    private ChartingMapper chartingMapper;

    @Autowired
    private AuditEventService auditEventService;

    public void setChartingMapperMapper(ChartingMapper chartingMapper) {
        this.chartingMapper = chartingMapper;
    }

    @Override
    public int AddAllergy(HAllergy allergy){
        chartingMapper.addAllergy(allergy);
        HAuditRecord auditRecord = new HAuditRecord();
        auditRecord.setEventName("Add Allergy");
        auditRecord.setPolicyId(10);
        auditRecord.setPatient_ObjectID(allergy.getPatientID());
        auditEventService.auditEvent(auditRecord);
        return 0;
    }

    @Override
    public int DeleteAllergy(HAllergy allergy) throws Exception {
        chartingMapper.deleteAllergy(allergy);
        HAuditRecord auditRecord = new HAuditRecord();
        auditRecord.setEventName("Delete Allergy");
        auditRecord.setPolicyId(13);
        auditRecord.setPatient_ObjectID(allergy.getPatientID());
        auditEventService.auditEvent(auditRecord);
        return 0;
    }
}
