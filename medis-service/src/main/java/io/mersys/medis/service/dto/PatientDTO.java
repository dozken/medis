package io.mersys.medis.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.mersys.medis.documents.patient.Address;
import io.mersys.medis.documents.patient.Allergy;
import io.mersys.medis.documents.patient.Diseases;
import io.mersys.medis.documents.type.GenderType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class PatientDTO implements Serializable{

	private String id;

	@NotBlank
	private String name;
	
	@NotBlank
	private String surname;

	@NotBlank
	private String patronymic; //отчество
	
	@NotBlank
	private String pin; //personal identification number - ИИН
	

	private LocalDate dateOfBirth;
	
	private GenderType gender;
	
	private List <Diseases> diseases;
	
	private List <Allergy> allergies;
	
	private List <Address> address;
}