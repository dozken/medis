package io.mersys.medis.service.dto;

import java.io.Serializable;

import io.mersys.medis.documents.type.GenderType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class PatientSearchDTO implements Serializable {
	private String fullInfo;
	private GenderType gender;
	private int ageFrom;
	private int ageTo;
}
