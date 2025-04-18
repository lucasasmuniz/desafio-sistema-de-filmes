package com.devsuperior.movieflix.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dtos.GenreDTO;
import com.devsuperior.movieflix.services.GenreService;

@RestController
@RequestMapping("/genres")
public class GenreController {

	@Autowired
	private GenreService service;
	
	@PreAuthorize("hasAnyRole('ROLE_VISITOR','ROLE_MEMBER')")
	@GetMapping
	public ResponseEntity<List<GenreDTO>> findAll(){
		return ResponseEntity.ok(service.findAll());
	}
}
