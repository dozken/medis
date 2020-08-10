
package io.mersys.medis.documents.patient;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.TypeAlias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@TypeAlias("Diseases")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Diseases {

	@NotBlank
	private String diseasesName;

	private LocalDate startDate;

	private LocalDate endDate;
	
	private String description;

}
