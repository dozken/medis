package io.mersys.medis.service.mapper;

import org.mapstruct.Mapper;

import io.mersys.medis.documents.Song;
import io.mersys.medis.service.dto.SongDTO;

@Mapper(componentModel = "spring", uses = {})
public interface SongMapper extends DocumentMapper<SongDTO, Song> {
}
