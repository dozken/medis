package io.mersys.medis.service.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.mersys.medis.documents.Parameter;
import io.mersys.medis.documents.type.ParameterType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParameterDTO implements Serializable {

	private String id;
	
	@NotNull
	private ParameterType type;
	
	@NotBlank
	private String value;
	
	private List<ParameterDTO> childParams;
}
