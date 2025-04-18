package com.gambasoftware.poc.webflux.service;

import reactor.core.publisher.Flux;

import java.util.List;

public class FluxAndMonoService {

    public Flux<String> getNames(){
        return Flux.fromIterable(List.of("migi", "nami", "tini"));
    }
}
