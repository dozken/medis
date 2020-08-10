package io.mersys.medis.documents.doctor;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.mersys.medis.documents.patient.Address;
import io.mersys.medis.documents.patient.Patient;
import io.mersys.medis.documents.type.GenderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Document("doctor")
@TypeAlias("Doctor")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Doctor implements Serializable{

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
	
	@NotNull
	private Address address;

	@NotNull
	private Role role;
}
