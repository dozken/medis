package io.mersys.medis.repository;

import java.util.List;

import io.mersys.medis.documents.Song;
import io.mersys.medis.service.dto.SongSearchDTO;

public interface SongCustomizedRepository {

	List<Song> findBySearchDTO(SongSearchDTO dto);

}
