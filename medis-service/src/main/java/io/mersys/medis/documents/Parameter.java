package io.mersys.medis.documents;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import io.mersys.medis.documents.type.ParameterType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Document(collection = "parameter")
@TypeAlias("Parameter")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor

public class Parameter implements Serializable {

	@Id
	private String id;

	@NotNull
	private ParameterType type;

	@NotBlank
	private String value;

	private List<Parameter> childParams;

}
