package com.dh.movieservice.api.controller;

import com.dh.movieservice.api.service.MovieService;
import com.dh.movieservice.api.service.queue.MovieListener;
import com.dh.movieservice.domain.model.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService service;

    private final MovieListener movieListener;

    public MovieController(MovieService service, MovieListener movieListener) {
        this.service = service;
        this.movieListener = movieListener;
    }

    @GetMapping("/{genre}")
    ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre) {
        return ResponseEntity.ok().body(service.findByGenre(genre));
    }

    @PostMapping("/salvar")
    public void saveMovie(@RequestBody Movie movie) {
        movieListener.receive(movie);
    }
}
