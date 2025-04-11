package com.devsuperior.movieflix.dtos;

import java.util.Objects;

import com.devsuperior.movieflix.entities.User;

public class UserDTO {
	private Long id;
	private String email;
	private String name;
	
	public UserDTO() {
	}
	
	public UserDTO(Long id, String email, String name) {
		this.id = id;
		this.email = email;
		this.name = name;
	}
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.name = user.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return Objects.equals(id, other.id);
	}
}
