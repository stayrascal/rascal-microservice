package com.stayrascal.cloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.rx.RxResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import rx.Observable;
import rx.Single;

import java.util.List;
import java.util.concurrent.TimeUnit;


@SpringBootApplication
@RestController
public class InfraConfigClientApplication {

    @Value("${info.profile}")
    private String profile;

    @Value("${info.descript}")
    private String description;

    public static void main(String[] args) {
        SpringApplication.run(InfraConfigClientApplication.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "Hello World: " + profile + " : " + description;
    }

    @GetMapping(value = "/single")
    public Single<String> single() {
//        return Observable.just("single value").toSingle();
        return Single.just("single value");
    }

    @GetMapping(value = "/multiple")
    public Single<List<String>> multiple() {
        return Observable.just("multiple", "values").toList().toSingle();
    }

    @GetMapping(value = "/timeout")
    public Observable<String> timeout() {
        return Observable.timer(1, TimeUnit.MILLISECONDS).map(aLong -> "single value");
    }

    @GetMapping(value = "/sse")
    public SseEmitter sseEmitter() {
        return RxResponse.sse(Observable.just("single value"));
    }

    @GetMapping(value = "/messages")
    public SseEmitter messages() {
        return RxResponse.sse(Observable.just("message 1", "message 2", "message 3"));
    }

    @GetMapping(value = "/singleWithResponse")
    public ResponseEntity<Single<String>> singleWithResponse() {
        return new ResponseEntity<Single<String>>(Single.just("single value"), HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/throw")
    public Single<Object> error() {
        return Single.error(new RuntimeException("Unexpected"));
    }
}
