package com.devsuperior.movieflix.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dtos.ReviewDTO;
import com.devsuperior.movieflix.dtos.ReviewDetailsDTO;
import com.devsuperior.movieflix.dtos.UserDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository repository;

	@Autowired
	private AuthService authService;
	
	@Autowired
	private MovieService movieService;
	
	@Transactional(readOnly = true)
	public List<ReviewDetailsDTO> findFilmReviews(Long movieId){
		List<Review> results = repository.findByMovieId(movieId);
		if(results.isEmpty()) {
			throw new ResourceNotFoundException("Entity not found");
		}
				
		List<ReviewDetailsDTO> dtos = new ArrayList<>();
		for(Review review : results) {
			dtos.add(new ReviewDetailsDTO(review.getId(), review.getText(), review.getMovie().getId(), new UserDTO(review.getUser())));
		}
		
		return dtos;
	}

	@Transactional()
	public ReviewDetailsDTO insert(ReviewDTO dto) {
		Review entity = new Review();
		entity.setText(dto.getText());
		entity.setUser(authService.authenticated());
		Movie movie = movieService.getExistingReferenceById(dto.getMovieId());
		
		entity.setMovie(movie);
		entity = repository.save(entity);
		return new ReviewDetailsDTO(entity.getId(), entity.getText(), entity.getMovie().getId(), new UserDTO(entity.getUser()));
	}
}
