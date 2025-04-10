package com.devsuperior.movieflix.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devsuperior.movieflix.dtos.MovieLargeDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.projections.MovieProjection;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

	@Query(nativeQuery = true,value = """
			SELECT * 
			FROM(
			SELECT DISTINCT tb_movie.id, tb_movie.title 
			FROM tb_movie INNER JOIN tb_genre 
			ON tb_genre.id = tb_movie.genre_id 
			WHERE (:genreId IS NULL OR tb_genre.id = :genreId)
			) AS tb_result
			""",
			countQuery = """
			SELECT COUNT(*)
			FROM (
			SELECT DISTINCT tb_movie.id, tb_movie.title 
			FROM tb_movie INNER JOIN tb_genre 
			ON tb_genre.id = tb_movie.genre_id 
			WHERE (:genreId IS NULL OR tb_genre.id = :genreId)
			) AS tb_result
			""")
	Page<MovieProjection> searchMovies(@Param("genreId") Long genreId, Pageable pageable );
	
	List<Movie> findByIdIn(List<Long> ids);

    @Query("SELECT obj FROM Movie obj JOIN FETCH obj.genre WHERE obj.id = :id")
	Movie searchById(@Param("id") Long id);
}
