package com.devsuperior.movieflix.dtos;

public class ReviewDetailsDTO {
	private Long id;
	private String text;
	private Long movieId;
	private Long userId;
	private String userName;
	private String userEmail;
	
	public ReviewDetailsDTO() {
	}
	
	public ReviewDetailsDTO(Long id, String text, Long movieId, UserDTO user) {
		this.id = id;
		this.text = text;
		this.movieId = movieId;
		this.userId = user.getId();
		this.userName = user.getName();
		this.userEmail = user.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
}
