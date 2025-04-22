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
    public void testFlatMap(){
        Flux.fromIterable(List.of("migi", "nami", "tini"))
                .flatMap(s -> Flux.fromArray(s.split("")))
                .log()
                .subscribe(LOGGER::info);
    }

    @Test
    public void testMap(){
        FluxAndMonoService fluxAndMonoService = new FluxAndMonoService();
        var namesFlux = fluxAndMonoService.getNames();

        StepVerifier.create(namesFlux.map(String::toUpperCase))
                .expectNext("MIGI", "NAMI", "TINI")
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    public void testFilter(){
        StepVerifier.create(Flux.fromIterable(List.of("mimao", "naminha", "tini"))
                .filter(s -> s.length() < 5))
                .expectNext("tini")
                .verifyComplete();
    }

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

    @Test
    public void testDelay(){
        FluxAndMonoService fluxAndMonoService = new FluxAndMonoService();

        StepVerifier.create(fluxAndMonoService.getNamesDelayed())
                .expectNext("migi", "nami", "tini")
                .verifyComplete();
    }

    @Test
    void getNamesFlatMap() {
        FluxAndMonoService fluxAndMonoService = new FluxAndMonoService();
        StepVerifier.create(fluxAndMonoService.getNamesFlatMap())
                .expectNextCount(12)
                .verifyComplete();

    }

    @Test
    void getNamesConcat() {
        FluxAndMonoService fluxAndMonoService = new FluxAndMonoService();
        StepVerifier.create(fluxAndMonoService.getNamesConcat())
                .expectNextCount(12)
                .verifyComplete();

    }
}
