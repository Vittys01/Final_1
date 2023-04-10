package com.example.serieservice.api.controller;

import com.example.serieservice.api.service.SerieService;
import com.example.serieservice.api.service.queue.SerieListener;
import com.example.serieservice.model.Serie;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/series")
public class SerieController {

    private final SerieService serieService;

    private final SerieListener serieListener;

    public SerieController(SerieService serieService, SerieListener serieListener) {
        this.serieService = serieService;
        this.serieListener = serieListener;
    }

    @GetMapping
    public List<Serie> getAll() {
        return serieService.getAll();
    }

    @GetMapping("/{genre}")
    public List<Serie> getSerieByGenre(@PathVariable String genre) {
        return serieService.getSeriesBygGenre(genre);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody Serie serie) {
        serieListener.receive(serie);
        return serie.getId();
    }
}
