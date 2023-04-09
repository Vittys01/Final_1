package com.dh.movieservice.api.service.queue;

import com.dh.movieservice.api.service.MovieService;
import com.dh.movieservice.domain.model.Movie;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;

public class MovieListener {

    private final MovieService service;

    public MovieListener(MovieService service) {
        this.service = service;
    }
    @RabbitListener(queues = { "${queue.movie.name}" })
    public void receive(@Payload Movie movie) {
        service.save(movie);
    }
}
