package io.mersys.medis.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.mersys.medis.documents.Song;

public interface SongRepository extends MongoRepository<Song, String>, SongCustomizedRepository {

	Song findByName();

	List<Song> findByNameOrArtistAllIgnoreCaseLike(String name, String artist);
}
