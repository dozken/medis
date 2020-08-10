package io.mersys.medis.service;

import java.util.List;
import java.util.Optional;

import io.mersys.medis.service.dto.SongDTO;
import io.mersys.medis.service.dto.SongSearchDTO;

public interface SongService {

	List<SongDTO> getAll();

	Optional<SongDTO> get(String id);

	SongDTO create(SongDTO dto);

	SongDTO update(SongDTO dto);

	void delete(String id);

	List<SongDTO> search(SongSearchDTO dto);
}
