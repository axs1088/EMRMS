package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.mappers.ChartingMapper;
import edu.psu.sweng500.emrms.mappers.SaveEncounterMapper;
import edu.psu.sweng500.emrms.model.HDiagnosis;
import edu.psu.sweng500.emrms.model.HEncounter;
import edu.psu.sweng500.emrms.model.HPatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("manageDiagnosisService")

public class ManageDiagnosisServiceImpl implements ManageDiagnosisService {


    @Autowired
    private ChartingMapper chartingMapper;

    public void setChartingMapperMapper(ChartingMapper chartingMapper) {
        this.chartingMapper = chartingMapper;
    }

    @Override
    public int AddDiagnosis(HDiagnosis diagnosis){
        chartingMapper.addDiagnosis(diagnosis);
        return 0;
    }

    @Override
    public int ReviseDiagnosis(HDiagnosis diagnosis) throws Exception {
        chartingMapper.reviseDiagnosis(diagnosis);
        return 0;
    }

    @Override
    public int DeleteDiagnosis(HDiagnosis diagnosis) throws Exception {
        chartingMapper.deleteDiagnosis(diagnosis);
        return 0;
    }
}
