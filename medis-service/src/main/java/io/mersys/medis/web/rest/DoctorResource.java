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

import io.mersys.medis.service.dto.DoctorDTO;
import io.mersys.medis.service.dto.DoctorSearchDTO;
import io.mersys.medis.service.dto.PatientDTO;
import io.mersys.medis.service.dto.PatientSearchDTO;

@RequestMapping("/api")

public interface DoctorResource {
	@GetMapping(value = "/doctor")
	public ResponseEntity<List<DoctorDTO>> getAll();
	
	@PostMapping(value="/doctor")
	public ResponseEntity<DoctorDTO> create(@Valid @RequestBody DoctorDTO dto);
	
	@PutMapping(value="/doctor")
	public ResponseEntity<DoctorDTO> update(@Valid @RequestBody DoctorDTO dto);
	
	@DeleteMapping(value ="/doctor/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String id);
	
	@GetMapping(value="/doctor/{id}")
	public ResponseEntity<DoctorDTO> get(@PathVariable("id") String id);
	
	@PostMapping(value = "/doctor/search")
	public ResponseEntity<List<DoctorDTO>> getBySearch(@Valid @RequestBody DoctorSearchDTO dto);
}
