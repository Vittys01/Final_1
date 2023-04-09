package com.dh.catalogservice.api.queue;


import com.dh.catalogservice.api.client.IMovieClient;
import com.dh.catalogservice.domain.model.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieSender {

    private final IMovieClient client;

    private final RabbitTemplate rabbitTemplate;

    private final Queue movieQueue;

    public void send(Movie movie) {
        this.rabbitTemplate.convertAndSend(this.movieQueue.getName(), movie);
    }
}
