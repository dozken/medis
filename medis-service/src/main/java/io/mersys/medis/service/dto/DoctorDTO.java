package io.mersys.medis.service.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.mersys.medis.documents.doctor.Role;
import io.mersys.medis.documents.patient.Address;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class DoctorDTO implements Serializable{

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
	
	private Address address;
	
	private Role role;
}