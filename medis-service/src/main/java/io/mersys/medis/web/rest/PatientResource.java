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

import io.mersys.medis.service.dto.PatientDTO;
import io.mersys.medis.service.dto.PatientSearchDTO;

@RequestMapping("/api")
public interface PatientResource {
	
	@GetMapping(value = "/patient")
	public ResponseEntity<List<PatientDTO>> getAll();
	
	@PostMapping(value="/patient")
	public ResponseEntity<PatientDTO> create(@Valid @RequestBody PatientDTO dto);
	
	@PutMapping(value="/patient")
	public ResponseEntity<PatientDTO> update(@Valid @RequestBody PatientDTO dto);
	
	@DeleteMapping(value ="/patient/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String id);
	
	@GetMapping(value="/patient/{id}")
	public ResponseEntity<PatientDTO> get(@PathVariable("id") String id);
	
	@PostMapping(value = "/patient/search")
	public ResponseEntity<List<PatientDTO>> getBySearch(@Valid @RequestBody PatientSearchDTO dto);

}