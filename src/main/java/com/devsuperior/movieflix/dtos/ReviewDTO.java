package com.devsuperior.movieflix.dtos;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;

public class ReviewDTO {
	private Long movieId;
	
	@NotBlank(message = "Campo requerido")
	private String text;

	public ReviewDTO() {
	}
	
	public ReviewDTO(Long movieId, String text) {
		this.movieId = movieId;
		this.text = text;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public int hashCode() {
		return Objects.hash(movieId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReviewDTO other = (ReviewDTO) obj;
		return Objects.equals(movieId, other.movieId);
	}
}
