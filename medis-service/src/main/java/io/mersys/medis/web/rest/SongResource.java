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

import io.mersys.medis.service.dto.SongDTO;
import io.mersys.medis.service.dto.SongSearchDTO;

@RequestMapping("/api")
public interface SongResource {

	@GetMapping(value = "/song")
	public ResponseEntity<List<SongDTO>> getAll();

	@PostMapping(value = "/song")
	public ResponseEntity<SongDTO> create(@Valid @RequestBody SongDTO dto);

	@PutMapping(value = "/song")
	public ResponseEntity<SongDTO> update(@Valid @RequestBody SongDTO dto);

	@DeleteMapping(value = "/song/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String id);

	@GetMapping(value = "/song/{id}")
	public ResponseEntity<SongDTO> get(@PathVariable("id") String id);

	@PostMapping(value = "/song/search")
	public ResponseEntity<List<SongDTO>> getBySearch(@Valid @RequestBody SongSearchDTO dto);
}
