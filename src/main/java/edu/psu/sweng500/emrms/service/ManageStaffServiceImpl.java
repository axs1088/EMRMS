package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.mappers.StaffMapper;
import edu.psu.sweng500.emrms.model.HStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("manageStaffService")
public class ManageStaffServiceImpl implements ManageStaffService {

    @Autowired
    private StaffMapper staffMapper;

    @Override
    public List<HStaff> GetStaffList(String searchString) {
        List<HStaff>staffList = staffMapper.getStaffList(searchString);
        return staffList;
    }
    
    @Override
    public List<HStaff> GetPhysicianList() {
        List<HStaff>staffList = staffMapper.getPhysicianList();
        return staffList;
    }
}
