package com.dh.catalogservice.api.controller;

import com.dh.catalogservice.api.client.IMovieClient;
import com.dh.catalogservice.api.queue.MovieSender;
import com.dh.catalogservice.domain.model.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private  IMovieClient iMovieClient;
    private final MovieSender movieSender;

    public CatalogController(IMovieClient iMovieClient, MovieSender movieSender) {
        this.iMovieClient = iMovieClient;
        this.movieSender = movieSender;
    }

    @GetMapping("/{genre}")
    ResponseEntity<List<Movie>> getGenre(@PathVariable String genre) {
        return ResponseEntity.ok().body(iMovieClient.getMovieByGenre(genre));
    }

    @PostMapping("/salvar")
    public void saveMovie(@RequestBody Movie movie) {
        movieSender.send(movie);
    }
}
