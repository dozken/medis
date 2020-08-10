package io.mersys.medis.documents.patient;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.mersys.medis.documents.type.GenderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Document("patient")
@TypeAlias("Patient")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Patient implements Serializable {

	@Id
	@JsonProperty("id")
	private String id;

	@NotBlank
	private String name;

	@NotBlank
	private String surname;

	@NotBlank
	private String patronymic; // отчество

	@NotBlank
	private String pin; // personal identification number - ИИН

	
	private LocalDate dateOfBirth;

	@NotBlank
	private GenderType gender;

	private List<Diseases> diseases;

	private List<Allergy> allergies;

	private List<Address> address;
}
