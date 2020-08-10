package io.mersys.medis.service.dto;

import java.io.Serializable;

import io.mersys.medis.documents.type.ParameterType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
public class ParameterSearchDTO implements Serializable{	
	private ParameterType type;
	private String value;
}