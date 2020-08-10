package io.mersys.medis.documents.type;

public enum GenderType {
	MALE("MALE"), FEMALE("FEMALE");

	private String value;

	public String getValue() {
		return this.value;
	}

	GenderType(String value) {
		this.value = value;
	}
}
