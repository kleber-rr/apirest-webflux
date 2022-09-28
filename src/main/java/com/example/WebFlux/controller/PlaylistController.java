package com.example.WebFlux.controller;

import com.example.WebFlux.document.Playlist;
import com.example.WebFlux.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/playlist_ac")
public class PlaylistController {

    @Autowired
    private PlaylistService service;

    @GetMapping
    public Flux<Playlist> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Playlist> findById(@PathVariable("id") String id){
        return service.findById(id);
    }

    @PostMapping
    public Mono<Playlist> save(@RequestBody Playlist playlist){
        return service.save(playlist);
    }
}
