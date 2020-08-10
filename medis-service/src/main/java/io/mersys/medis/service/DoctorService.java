package io.mersys.medis.service;

import java.util.List;
import java.util.Optional;

import io.mersys.medis.service.dto.DoctorDTO;
import io.mersys.medis.service.dto.DoctorSearchDTO;


public interface DoctorService {
	List<DoctorDTO> getAll();

	Optional<DoctorDTO> get(String id);

	DoctorDTO create(DoctorDTO dto);

	void delete(String id);

	DoctorDTO update(DoctorDTO dto);
	
	List<DoctorDTO> search(DoctorSearchDTO dto);
	
}