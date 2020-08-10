package io.mersys.medis.documents;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.mersys.medis.documents.type.GenreType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Document("song")
@TypeAlias("Song")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Song implements Serializable {

	@Id
	@JsonProperty("id")
	private String id;

	@NotBlank
	private String name;

	private String artist;

	private LocalDate changedDate;

	private LocalDate releaseDate;

	@NotNull
	private GenreType genre;
}
