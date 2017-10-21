package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.model.HAllergy;

public interface ManageAllergyService {

    public int AddAllergy(HAllergy allergy);

    public int DeleteAllergy(HAllergy allergy) throws Exception;
}
