package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.model.HAssessment;

public interface ManageAssessmentService {
    public int AddAssessment(HAssessment assessment);
    public int ReviseAssessment(HAssessment assessment);
}
