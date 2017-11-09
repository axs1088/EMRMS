package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.model.HProblem;

import java.util.List;

public interface ManageProblemService {

    public int AddProblem(HProblem problem);
    public int ReviseProblem(HProblem problem);
    public int DeleteProblem(HProblem problem) ;
    public List<HProblem> GetProblemsList(int patientObjId, int encObjId);
}
