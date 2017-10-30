package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.model.HStaff;

import java.util.List;

public interface ManageStaffService {

    public List<HStaff>GetStaffList(String searchString);
}
