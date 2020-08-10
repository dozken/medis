package io.mersys.medis.documents;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.mersys.medis.documents.type.SampleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Document("sample")
@TypeAlias("Sample")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Sample implements Serializable {

	@Id
	@JsonProperty("id")
	private String id;

	@NotBlank
	private String name;

	private String shortName;

	private LocalDate birthDate;

	@NotNull
	private SampleType type;
}
