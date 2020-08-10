package io.mersys.medis.documents.doctor;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.TypeAlias;

import io.mersys.medis.documents.patient.Diseases;
import io.mersys.medis.documents.type.DepartmentType;
import io.mersys.medis.documents.type.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@TypeAlias("Role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
	@NotNull
	private DepartmentType depType;
	
	@NotNull
	private RoleType roleType;
	
	@NotBlank
	private String login;
	
	
	private String note;
}
