package edu.psu.sweng500.emrms.model;

public class KnownAllergies {
    private boolean noKnownAllergies;
    private boolean noKnownDrugAllergies;
    private boolean noKnownFoodAllergies;

    public boolean getNoKnownAllergies() {
        return noKnownAllergies;
    }

    public void setNoKnownAllergies(boolean noKnownAllergies) {
        this.noKnownAllergies = noKnownAllergies;
    }

    public boolean getNoKnownDrugAllergies() {
        return noKnownDrugAllergies;
    }

    public void setNoKnownDrugAllergies(boolean noKnownDrugAllergies) {
        this.noKnownDrugAllergies = noKnownDrugAllergies;
    }

    public boolean getNoKnownFoodAllergies() {
        return noKnownFoodAllergies;
    }

    public void setNoKnownFoodAllergies(boolean noKnownFoodAllergies) {
        this.noKnownFoodAllergies = noKnownFoodAllergies;
    }
}
