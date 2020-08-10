package io.mersys.medis.repository.impl;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import io.mersys.medis.documents.Song;
import io.mersys.medis.repository.SongCustomizedRepository;
import io.mersys.medis.service.dto.SongSearchDTO;

public class SongCustomizedRepositoryImpl implements SongCustomizedRepository {

	@Autowired
	private MongoTemplate template;

	@Override
	public List<Song> findBySearchDTO(SongSearchDTO dto) {
		final Query q = new Query();
		if (dto.getName() != null && !dto.getName().trim().isEmpty()) {
			q.addCriteria(Criteria.where("name").regex(Pattern.compile(Pattern.quote(dto.getName().trim()),
					Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
		}
		if (dto.getArtist() != null && !dto.getArtist().trim().isEmpty()) {
			q.addCriteria(Criteria.where("artist").regex(Pattern.compile(Pattern.quote(dto.getArtist().trim()),
					Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
		}
		if (dto.getGenre() != null) {
			q.addCriteria(Criteria.where("genre").is(dto.getGenre()));
		}
		q.with(new Sort(Sort.Direction.DESC, "_id"));
		return template.find(q, Song.class);
	}

}
