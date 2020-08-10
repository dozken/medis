package io.mersys.medis.service.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.mersys.medis.documents.type.GenreType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class SongDTO implements Serializable {

	private String id;

	@NotBlank
	private String name;

	private String artist;
	
	private LocalDate releaseDate;

	@NotNull
	private GenreType genre;
}
