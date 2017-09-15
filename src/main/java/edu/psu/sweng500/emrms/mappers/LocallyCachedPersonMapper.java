package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.HPerson;

import java.util.ArrayList;
import java.util.List;

public class LocallyCachedPersonMapper implements PersonMapper {

    ArrayList<HPerson> personList;

    public LocallyCachedPersonMapper() {
        personList = new ArrayList<>();
    }

    @Override
    public List<HPerson> getPersonDetails() {
        return personList;
    }

    @Override
    public void insertPersonDetails(HPerson person) {
        personList.add(person);
    }
}
