package io.mersys.medis.web.rest.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.mersys.medis.service.DoctorService;
import io.mersys.medis.service.dto.DoctorDTO;
import io.mersys.medis.service.dto.DoctorSearchDTO;
import io.mersys.medis.service.dto.PatientDTO;
import io.mersys.medis.service.dto.PatientSearchDTO;
import io.mersys.medis.web.rest.DoctorResource;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class DoctorResourceImpl implements DoctorResource{
	private DoctorService service;
	
	@Override
	public ResponseEntity<List<DoctorDTO>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
	}

	@Override
	public ResponseEntity<List<DoctorDTO>> getBySearch(@Valid @RequestBody DoctorSearchDTO dto) {
		return ResponseEntity.status(HttpStatus.OK).body(service.search(dto));
	}
	
	@Override
	public ResponseEntity<DoctorDTO> get(String id) {
		return service.get(id).map(ResponseEntity.ok()::body)
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@Override
	public ResponseEntity<DoctorDTO> create(@Valid DoctorDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
	}

	@Override
	public ResponseEntity<DoctorDTO> update(@Valid DoctorDTO dto) {
		return ResponseEntity.status(HttpStatus.OK).body(service.update(dto));
	}

	@Override
	public ResponseEntity<Void> delete(String id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}


	
}


