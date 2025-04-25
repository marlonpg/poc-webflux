package com.gambasoftware.poc.webflux.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

public class FluxAndMonoService {

    public Flux<String> getNames() {
        return Flux.fromIterable(List.of("migi", "nami", "tini"));
    }

    public Flux<String> getNamesDelayed() {
        return Flux.fromIterable(List.of("migi", "nami", "tini"))
                .log()
                .delayElements(Duration.ofMillis(1000));
    }

    //Flatmap is concurrent
    public Flux<String> getNamesFlatMap() {
        return Flux.fromIterable(List.of("migi", "nami", "tini"))
                .flatMap(s -> Flux.fromArray(s.split(""))
                        .delayElements(Duration.ofMillis(100)))
                .log();
    }

    //Concat preserve the order but it is sequential so it will take more time
    public Flux<String> getNamesConcat() {
        return Flux.fromIterable(List.of("migi", "nami", "tini"))
                .concatMap(s -> Flux.fromArray(s.split(""))
                        .delayElements(Duration.ofMillis(100)))
                .log();
    }

    public Flux<String> getNamesFlatMapMany() {
        return Mono.just("migi")
                .flatMapMany(n -> Flux.fromArray(n.split("")))
                .log();
    }
}
