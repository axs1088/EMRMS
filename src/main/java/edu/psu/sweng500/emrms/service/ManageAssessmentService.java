package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.model.HAssessment;

import java.util.List;

public interface ManageAssessmentService {
    public int AddAssessment(HAssessment assessment);
    public int ReviseAssessment(HAssessment assessment);
    public List<HAssessment> GetPatientAssessments(int patientObjectId, int encounterObjectId);
}
