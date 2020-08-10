package io.mersys.medis.web.rest.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.mersys.medis.service.SongService;
import io.mersys.medis.service.dto.SongDTO;
import io.mersys.medis.service.dto.SongSearchDTO;
import io.mersys.medis.web.rest.SongResource;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class SongResourceImpl implements SongResource {

	private SongService service;

	@Override
	public ResponseEntity<List<SongDTO>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
	}

	@Override
	public ResponseEntity<List<SongDTO>> getBySearch(@Valid @RequestBody SongSearchDTO dto) {
		return ResponseEntity.status(HttpStatus.OK).body(service.search(dto));
	}

	@Override
	public ResponseEntity<SongDTO> get(@PathVariable("id") String id) {
		return service.get(id).map(ResponseEntity.ok()::body)
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@Override
	public ResponseEntity<SongDTO> create(@Valid @RequestBody SongDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
	}

	@Override
	public ResponseEntity<SongDTO> update(@Valid @RequestBody SongDTO dto) {
		return ResponseEntity.status(HttpStatus.OK).body(service.update(dto));
	}

	@Override
	public ResponseEntity<Void> delete(@PathVariable("id") String id) {
		return service.get(id).map(this::delete).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	private ResponseEntity<Void> delete(SongDTO dto) {
		service.delete(dto.getId());
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}