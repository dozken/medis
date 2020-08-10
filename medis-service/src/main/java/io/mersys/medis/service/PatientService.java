package io.mersys.medis.service;

import java.util.List;
import java.util.Optional;

import io.mersys.medis.service.dto.PatientDTO;
import io.mersys.medis.service.dto.PatientSearchDTO;

public interface PatientService {

	List<PatientDTO> getAll();

	Optional<PatientDTO> get(String id);

	PatientDTO create(PatientDTO dto);

	void delete(String id);

	PatientDTO update(PatientDTO dto);
	
	List<PatientDTO> search(PatientSearchDTO dto);
	
}