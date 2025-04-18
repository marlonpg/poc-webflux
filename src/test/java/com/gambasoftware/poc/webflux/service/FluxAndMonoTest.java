package com.gambasoftware.poc.webflux.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

public class FluxAndMonoTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(FluxAndMonoTest.class);

    @Test
    public void testGetNamesFlux(){
        FluxAndMonoService fluxAndMonoService = new FluxAndMonoService();
        var namesFlux = fluxAndMonoService.getNames();

        StepVerifier.create(namesFlux)
                .expectNext("migi", "nami", "tini")
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    public void testMapForGetNamesFlux(){
        FluxAndMonoService fluxAndMonoService = new FluxAndMonoService();
        var namesFlux = fluxAndMonoService.getNames();

        StepVerifier.create(namesFlux.map(String::toUpperCase))
                .expectNext("MIGI", "NAMI", "TINI")
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    public void fluxTest(){
        Flux.fromIterable(List.of("migi", "nami", "tini"))
                .log()
                .subscribe(LOGGER::info);
    }

    @Test
    public void monoTest(){
        Mono.just("jatobi")
                .subscribe(LOGGER::info);
    }
}
