package io.mersys.medis.web.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.mersys.medis.service.dto.SampleDTO;
import io.mersys.medis.service.dto.SampleSearchDTO;

@RequestMapping("/api")
public interface SampleResource {

	@GetMapping(value = "/sample")
	public ResponseEntity<List<SampleDTO>> getAll();

	@PostMapping(value = "/sample")
	public ResponseEntity<SampleDTO> create(@Valid @RequestBody SampleDTO dto);

	@PutMapping(value = "/sample")
	public ResponseEntity<SampleDTO> update(@Valid @RequestBody SampleDTO dto);

	@DeleteMapping(value = "/sample/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String id);

	@GetMapping(value = "/sample/{id}")
	public ResponseEntity<SampleDTO> get(@PathVariable("id") String id);

	@PostMapping(value = "/sample/search")
	public ResponseEntity<List<SampleDTO>> getBySearch(@Valid @RequestBody SampleSearchDTO dto);
}
