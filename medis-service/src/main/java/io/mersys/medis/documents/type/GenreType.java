package io.mersys.medis.documents.type;

public enum GenreType {
	GENRE1("GENRE1"), GENRE2("GENRE2"), GENRE3("GENRE3");

	private String value;

	public String getValue() {
		return this.value;
	}

	private GenreType(String value) {
		this.value = value;
	}
}
