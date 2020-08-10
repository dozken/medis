package io.mersys.medis.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.mersys.medis.documents.Song;
import io.mersys.medis.repository.SongRepository;
import io.mersys.medis.service.SongService;
import io.mersys.medis.service.dto.SongDTO;
import io.mersys.medis.service.dto.SongSearchDTO;
import io.mersys.medis.service.mapper.SongMapper;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class SongServiceImpl implements SongService {

	private SongMapper mapper;
	private SongRepository repository;

	@Override
	public SongDTO create(SongDTO dto) {
		if (dto == null) {
			throw new IllegalArgumentException("To create Song, 'dto' must not be null");
		}
		if (dto.getId() != null) {
			throw new RuntimeException("To create Song, 'id' must be null");
		}
		
		return save(dto);
	}

	@Override
	public void delete(String id) {
		if (!ObjectId.isValid(id)) {
			throw new IllegalArgumentException("Song 'id' is not valid value: '" + id + "'");
		}

		repository.deleteById(id);
	}

	@Override
	public Optional<SongDTO> get(String id) {
		if (!ObjectId.isValid(id)) {
			throw new IllegalArgumentException("Song 'id' is not valid value: '" + id + "'");
		}

		return repository.findById(id).map(mapper::toDto);
	}

	@Override
	public List<SongDTO> getAll() {
		return mapper.toDto(repository.findAll());
	}

	private SongDTO save(SongDTO dto) {
		Song doc = mapper.toDocument(dto);
     	doc.setChangedDate(LocalDate.now());
		doc = repository.save(doc);
		return mapper.toDto(doc);
	}

	@Override
	public SongDTO update(SongDTO dto) {
		if (dto == null) {
			throw new IllegalArgumentException("To update Song, 'dto' must not be null");
		}
		if (dto.getName() == null) {
			throw new RuntimeException("To update Song, 'name' must not be null");
		}

		return save(dto);
	}

	@Override
	public List<SongDTO> search(SongSearchDTO dto) {
		if (dto == null) {
			throw new IllegalArgumentException("To search Song, 'dto' must not be null");
		}

		List<Song> list = repository.findBySearchDTO(dto);
		return mapper.toDto(list);
	}

}
