package edu.psu.sweng500.emrms.model;

public class Policy {
	private String startDate;
	private String endDate;
	private String policyName;
	private String policyId;
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return policyName;
	}
}
