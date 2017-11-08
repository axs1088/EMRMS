package edu.psu.sweng500.emrms.model;

public class HAssessment {
        private int objectId;
        private String creationDateTime;
        private String userId;
        private int assessmentId;
        private String status;
        private int encounterObjectId;
        private float temperature;
        private float height;
        private float weight;
        private int pulse;
        private int systolicBP;
        private int dystolicBP;
        private int heightmeasureId;
        private int weightmeasureId;
        private int temperaturemeasureId;
        private int patientObjectId;
        private String collectedDateTime;

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public Integer getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Integer assessmentId) {
        this.assessmentId = assessmentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getEncounterObjectId() {
        return encounterObjectId;
    }

    public void setEncounterObjectId(Integer encounterObjectId) {
        this.encounterObjectId = encounterObjectId;
    }

    public Integer getPatientObjectId() {
        return patientObjectId;
    }

    public void setPatientObjectId(Integer patientObjectId) {
        this.patientObjectId = patientObjectId;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Integer getPulse() {
        return pulse;
    }

    public void setPulse(Integer pulse) {
        this.pulse = pulse;
    }

    public Integer getSystolicBP() {
        return systolicBP;
    }

    public void setSystolicBP(Integer systolicBP) {
        this.systolicBP = systolicBP;
    }

    public Integer getDystolicBP() {
        return dystolicBP;
    }

    public void setDystolicBP(Integer dystolicBP) {
        this.dystolicBP = dystolicBP;
    }

    public Integer getHeightmeasureId() {
        return heightmeasureId;
    }

    public void setHeightmeasureId(Integer heightmeasureId) {
        this.heightmeasureId = heightmeasureId;
    }

    public Integer getWeightmeasureId() {
        return weightmeasureId;
    }

    public void setWeightmeasureId(Integer weightmeasureId) {
        this.weightmeasureId = weightmeasureId;
    }

    public Integer getTemperaturemeasureId() {
        return temperaturemeasureId;
    }

    public void setTemperaturemeasureId(Integer temperaturemeasureId) {
        this.temperaturemeasureId = temperaturemeasureId;
    }

    public String getCollectedDateTime() {
        return collectedDateTime;
    }

    public void setCollectedDateTime(String collectedDateTime) {
        this.collectedDateTime = collectedDateTime;
    }
}
