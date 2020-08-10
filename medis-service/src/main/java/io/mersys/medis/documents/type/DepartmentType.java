package io.mersys.medis.documents.type;

public enum DepartmentType {
	HEMODIALYSIS("HEMODIALYSIS"), 
	LABORATORY("LABORATORY"), 
	CEO("CEO"),
	DEPUTY_DIRECTOR("DEPUTY_DIRECTOR"),
	MEDICAL_PERSONNEL("MEDICAL_PERSONNEL");
	
	private String value;

	public String getValue() {
		return this.value;
	}

	private DepartmentType(String value) {
		this.value = value;
	}
}
