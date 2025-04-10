package com.devsuperior.movieflix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dtos.MovieDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.projections.MovieProjection;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.utils.Utils;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;
	
	@Transactional(readOnly = true)
	public Page<MovieDTO> findAllPaged(Long genreId,Pageable pageable) {
		
		if (pageable.getSort().isUnsorted()) {
			pageable = PageRequest.of(
					pageable.getPageNumber(),
					pageable.getPageSize(),
					Sort.by("title").ascending()
			);
		}
		
		Page<MovieProjection> page = repository.searchMovies(genreId, pageable);
		List<Long> movieIds = page.map(x -> x.getId()).toList();
		
		List<Movie> entities = repository.findByIdIn(movieIds);
		
		entities = Utils.orderPage(entities, page.getContent());
		List<MovieDTO> dtos = entities.stream().map(x -> new MovieDTO(x)).toList();
		
		Page<MovieDTO> resultOrdered = new PageImpl<>(dtos, page.getPageable(), page.getTotalElements()); 
		
		return resultOrdered;
	}

}
