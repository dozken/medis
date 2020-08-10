package io.mersys.medis.documents.type;

public enum ParameterType {
	DISEASES("DISEASES"),
	ALLERGY_CATEGORY("ALLERGY_CATEGORY"),
	ALLERGEN("ALLERGEN"),
	REGION("REGION"),
	CITY("CITY");
	   
	private String value;
	   
	public String getValue() {
	       return this.value;
	}
	   
	ParameterType(String value) {
	       this.value = value;
	}
}