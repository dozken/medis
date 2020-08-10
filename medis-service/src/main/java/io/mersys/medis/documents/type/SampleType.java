package io.mersys.medis.documents.type;

public enum SampleType {
	TYPE1("TYPE1"), TYPE2("TYPE2"), TYPE3("TYPE3");

	private String value;

	public String getValue() {
		return this.value;
	}

	private SampleType(String value) {
		this.value = value;
	}
}
