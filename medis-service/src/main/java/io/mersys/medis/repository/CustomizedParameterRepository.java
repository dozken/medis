package io.mersys.medis.repository;

import java.util.List;

import io.mersys.medis.documents.Parameter;
import io.mersys.medis.service.dto.ParameterSearchDTO;

public interface CustomizedParameterRepository {

	List<Parameter> findBySearchDto(ParameterSearchDTO dto);
}
