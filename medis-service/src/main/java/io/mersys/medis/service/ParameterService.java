package io.mersys.medis.service;

import java.util.List;
import java.util.Optional;

import io.mersys.medis.service.dto.ParameterDTO;
import io.mersys.medis.service.dto.ParameterSearchDTO;

public interface ParameterService {

	List<ParameterDTO> getAll();

	Optional<ParameterDTO> get(String id);

	ParameterDTO create(ParameterDTO dto);

	void delete(String id);

	ParameterDTO update(ParameterDTO dto);
	
	List<ParameterDTO> search(ParameterSearchDTO dto);
	
}
