package com.dh.catalogservice.api.controller;

import com.dh.catalogservice.api.client.IMovieClient;
import com.dh.catalogservice.api.queue.MovieSender;
import com.dh.catalogservice.api.queue.SerieSender;
import com.dh.catalogservice.domain.model.Movie;
import com.dh.catalogservice.domain.model.Serie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private  IMovieClient iMovieClient;
    private final MovieSender movieSender;

    private final SerieSender serieSender;

    public CatalogController(IMovieClient iMovieClient, MovieSender movieSender, SerieSender serieSender) {
        this.iMovieClient = iMovieClient;
        this.movieSender = movieSender;
        this.serieSender = serieSender;
    }

    @GetMapping("/{genre}")
    ResponseEntity<List<Movie>> getMoviesGenre(@PathVariable String genre) {
        return ResponseEntity.ok().body(iMovieClient.getMovieByGenre(genre));
    }

    @PostMapping("/NewMovie")
    public void saveMovie(@RequestBody Movie movie) {
        movieSender.send(movie);
    }

    @PostMapping("/NewSerie")
    public void saveSerie(@RequestBody Serie serie) {
        serieSender.send(serie);
    }

}
