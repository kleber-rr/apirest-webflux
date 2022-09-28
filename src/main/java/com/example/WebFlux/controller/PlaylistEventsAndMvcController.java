package com.example.WebFlux.controller;

import com.example.WebFlux.document.Playlist;
import com.example.WebFlux.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class PlaylistEventsAndMvcController {

    @Autowired
    private PlaylistService service;

    @GetMapping(value="/async", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tuple2<Long, Playlist>> getPlaylistByWebflux(){

        System.out.println("---Start get Playlists by WEBFLUX--- " + LocalDateTime.now());
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));
        Flux<Playlist> playlistFlux = service.findAll();

        return Flux.zip(interval, playlistFlux);

    }

    @GetMapping(value="/sync")
    public List<String> getPlaylistByMvc() throws InterruptedException {

        System.out.println("---Start get Playlists by MVC--- " + LocalDateTime.now());


        List<String> playlistList = new ArrayList<>();
        playlistList.add("Java 8");
        playlistList.add("Spring Security");
        playlistList.add("Github");
        playlistList.add("Deploy de uma aplicação java no IBM Cloud");
        playlistList.add("Bean no Spring Framework");
        TimeUnit.SECONDS.sleep(10);

        return playlistList;

    }
}
