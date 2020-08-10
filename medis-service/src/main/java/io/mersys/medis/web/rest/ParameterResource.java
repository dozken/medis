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

import io.mersys.medis.service.dto.ParameterDTO;
import io.mersys.medis.service.dto.ParameterSearchDTO;


@RequestMapping("/api")
public interface ParameterResource {
	@GetMapping(value = "/parameter")
	public ResponseEntity<List<ParameterDTO>> getAll();
	
	@PostMapping(value="/parameter")
	public ResponseEntity<ParameterDTO> create(@Valid @RequestBody ParameterDTO dto);
	
	@PutMapping(value="/parameter")
	public ResponseEntity<ParameterDTO> update(@Valid @RequestBody ParameterDTO dto);
	
	@DeleteMapping(value ="/parameter/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String id);
	
	@GetMapping(value="/parameter/{id}")
	public ResponseEntity<ParameterDTO> get(@PathVariable("id") String id);
	
	@PostMapping(value = "/parameter/search")
	public ResponseEntity<List<ParameterDTO>> getBySearch(@Valid @RequestBody ParameterSearchDTO dto);
}

