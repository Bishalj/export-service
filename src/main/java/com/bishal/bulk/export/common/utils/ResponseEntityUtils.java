package com.bishal.bulk.export.common.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ResponseEntityUtils {

    private ResponseEntityUtils() {
    }

    public static <T> ResponseEntity<Mono<T>> getResponseFromMono(final Mono<T> data, final HttpStatus httpStatus) {
        return new ResponseEntity<>(data, httpStatus);
    }

    public static <T> ResponseEntity<Flux<T>> getResponseFromFlux(final Flux<T> data, final HttpStatus httpStatus) {
        return new ResponseEntity<>(data, httpStatus);
    }

    public static <T> ResponseEntity<T> getResponse(final T data, final HttpStatus httpStatus) {
        return new ResponseEntity<>(data, httpStatus);
    }
}
