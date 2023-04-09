package com.example.serieservice.service.queue;

import com.example.serieservice.model.Serie;
import com.example.serieservice.service.SerieService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;

public class SerieListener {

    private final SerieService service;

    public SerieListener(SerieService service) {
        this.service = service;
    }

    @RabbitListener(queues = {"${queue.serie.name}"})
    public void receive(@Payload Serie serie){ service.create(serie);}
}
