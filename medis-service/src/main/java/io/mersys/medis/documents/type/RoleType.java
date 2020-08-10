package io.mersys.medis.documents.type;

public enum RoleType {
	NURSE("NURSE"),
	MEDICAL_PERSONNEL("MEDICAL_PERSONNEL"),
	DIRECTOR("DIRECTOR"),
	DEPUTY_DIRECTOR_CD("DEPUTY_DIRECTOR_CD"),
	MAIN_DOCTOR("MAIN_DOCTOR"),
	CHIEF_NURSE("CHIEF_NURSE");
	   
	private String value;
	   
	public String getValue() {
	       return this.value;
	}
	   
	RoleType(String value) {
	       this.value = value;
	}
}
