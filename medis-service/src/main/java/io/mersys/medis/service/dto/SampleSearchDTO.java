package io.mersys.medis.service.dto;

import java.io.Serializable;

import io.mersys.medis.documents.type.SampleType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class SampleSearchDTO implements Serializable {
	private String name;
	private String shortName;
	private SampleType type;
}
