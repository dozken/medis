package io.mersys.medis.service.dto;

import java.io.Serializable;

import io.mersys.medis.documents.type.GenreType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class SongSearchDTO implements Serializable {
	private String name;
	private String artist;
	private GenreType genre;
}
