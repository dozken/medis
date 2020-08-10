package io.mersys.medis.service.dto;

import java.io.Serializable;

import io.mersys.medis.documents.type.DepartmentType;
import io.mersys.medis.documents.type.GenderType;
import io.mersys.medis.documents.type.RoleType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class DoctorSearchDTO implements Serializable {
	private String fullInfo;
	private RoleType roleType;
	private DepartmentType depType;
}
