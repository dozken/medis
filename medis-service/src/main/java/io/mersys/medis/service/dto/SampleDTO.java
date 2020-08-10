package io.mersys.medis.service.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.mersys.medis.documents.type.SampleType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class SampleDTO implements Serializable {

	private String id;

	@NotBlank
	private String name;

	private String shortName;

	private LocalDate birthDate;

	@NotNull
	private SampleType type;
}
