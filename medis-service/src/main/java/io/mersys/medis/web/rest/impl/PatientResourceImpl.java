package io.mersys.medis.web.rest.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.mersys.medis.service.PatientService;
import io.mersys.medis.service.dto.PatientDTO;
import io.mersys.medis.service.dto.PatientSearchDTO;
import io.mersys.medis.web.rest.PatientResource;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class PatientResourceImpl implements PatientResource{

	private PatientService service;

	@Override
	public ResponseEntity<List<PatientDTO>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
	}
	
	@Override
	public ResponseEntity<List<PatientDTO>> getBySearch(@Valid @RequestBody PatientSearchDTO dto) {
		return ResponseEntity.status(HttpStatus.OK).body(service.search(dto));
	}

	@Override
	public ResponseEntity<PatientDTO> create(@Valid @RequestBody PatientDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
	}

	@Override
	public ResponseEntity<PatientDTO> update(@Valid @RequestBody PatientDTO dto) {
		return ResponseEntity.status(HttpStatus.OK).body(service.update(dto));
	}

	@Override
	public ResponseEntity<Void> delete(@PathVariable("id") String id) {
		return service.get(id).map(this::delete).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@Override
	public ResponseEntity<PatientDTO> get(@PathVariable("id") String id) {
		return service.get(id).map(ResponseEntity.ok()::body)
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	private ResponseEntity<Void> delete(PatientDTO dto) {
		service.delete(dto.getId());
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}