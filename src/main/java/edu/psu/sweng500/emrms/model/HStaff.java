package edu.psu.sweng500.emrms.model;

import javax.validation.Valid;

public class HStaff {
    private int ObjectId;
    private int type;
    private boolean active;

    @Valid
    private ComplexName staffName;

    public int getObjectId() {
        return ObjectId;
    }

    public void setObjectId(int objectId) {
        ObjectId = objectId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ComplexName getStaffNameName() {
        return staffName;
    }

    public void setStaffName(ComplexName staffName) {
        this.staffName = staffName;
    }
}
