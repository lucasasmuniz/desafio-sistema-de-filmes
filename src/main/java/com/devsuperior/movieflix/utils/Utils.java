package com.devsuperior.movieflix.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.projections.MovieProjection;

public class Utils {

	public static List<Movie> orderPage(List<Movie> unordered, List<MovieProjection> ordered) {
		List<Movie> result = new ArrayList<>();
		
		Map<Long, Movie> map = new HashMap<>();
		
		for(Movie movie : unordered) {
			map.put(movie.getId(), movie);
		}
		
		for(MovieProjection projection : ordered) {
			result.add(map.get(projection.getId()));
		}
		
		return result;
	}
	
}
