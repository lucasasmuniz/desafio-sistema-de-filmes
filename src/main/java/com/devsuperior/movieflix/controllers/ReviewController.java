package com.devsuperior.movieflix.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.movieflix.dtos.ReviewDTO;
import com.devsuperior.movieflix.dtos.ReviewDetailsDTO;
import com.devsuperior.movieflix.services.ReviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
	
	@Autowired
	private ReviewService service;
	
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	@PostMapping
	public ResponseEntity<ReviewDetailsDTO> insert(@Valid @RequestBody ReviewDTO dto) {
		ReviewDetailsDTO entity = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(entity);
	}
}
