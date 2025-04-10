package com.devsuperior.movieflix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dtos.MovieDTO;
import com.devsuperior.movieflix.dtos.MovieLargeDTO;
import com.devsuperior.movieflix.services.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
	
	@Autowired
	private MovieService service;
	
	@PreAuthorize("hasAnyRole('ROLE_VISITOR','ROLE_MEMBER')")
	@GetMapping
	public ResponseEntity<Page<MovieDTO>> findAllPaged(@RequestParam(value = "genreId" ,required = false ) Long genreId,Pageable pageable){
		return ResponseEntity.ok(service.findAllPaged(genreId,pageable));
	}
	
	@PreAuthorize("hasAnyRole('ROLE_VISITOR','ROLE_MEMBER')")
	@GetMapping("/{id}")
	public ResponseEntity<MovieLargeDTO> findById(@PathVariable Long id){
		return ResponseEntity.ok(service.findById(id));
	}
}
