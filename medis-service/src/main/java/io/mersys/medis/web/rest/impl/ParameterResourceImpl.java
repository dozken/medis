package io.mersys.medis.web.rest.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.mersys.medis.service.ParameterService;
import io.mersys.medis.service.dto.ParameterDTO;
import io.mersys.medis.service.dto.ParameterSearchDTO;
import io.mersys.medis.web.rest.ParameterResource;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ParameterResourceImpl implements ParameterResource {

	private ParameterService service;

	@Override
	public ResponseEntity<List<ParameterDTO>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
	}

	@Override
	public ResponseEntity<List<ParameterDTO>> getBySearch(@Valid @RequestBody ParameterSearchDTO dto) {
		return ResponseEntity.status(HttpStatus.OK).body(service.search(dto));
	}

	@Override
	public ResponseEntity<ParameterDTO> create(@Valid @RequestBody ParameterDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
	}

	@Override
	public ResponseEntity<ParameterDTO> update(@Valid @RequestBody ParameterDTO dto) {
		return ResponseEntity.status(HttpStatus.OK).body(service.update(dto));
	}

	@Override
	public ResponseEntity<ParameterDTO> get(@PathVariable("id") String id) {
		return service.get(id).map(ResponseEntity.ok()::body)
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@Override
	public ResponseEntity<Void> delete(String id) {
		return service.get(id).map(this::delete).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	private ResponseEntity<Void> delete(ParameterDTO dto) {
		service.delete(dto.getId());
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
