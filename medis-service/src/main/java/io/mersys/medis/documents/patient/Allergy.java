package io.mersys.medis.documents.patient;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.TypeAlias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TypeAlias("Allergy")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Allergy{
	
	@NotBlank
	public String category;

	@NotBlank
	public String allergen;
	
	//@NotBlank
	//public String reaction;
	
	@NotBlank
	public String degree;
	
}	

