package com.soa.sport.model.service;

import com.soa.sport.model.dto.Running_raceDTO;
import com.soa.sport.model.entity.Running_race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class Running_racesAPIService {
    @Autowired
    private final WebClient API;

    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(10);

    @Autowired
    public Running_racesAPIService(@Qualifier("API") WebClient API){ this.API = API; }

    public Running_race[] requestAllRunning_races(){
        return API
                .get()
                .uri("/sport/api/running_races/")
                .retrieve()
                .bodyToMono(Running_race[].class)
                .block(REQUEST_TIMEOUT);
    }

    public Running_race readrunning_race(int id){ return requestRunning_race(id); }
    private Running_race requestRunning_race(int id){
        return API
                .get()
                .uri("/sport/api/running_races/" + id + "/")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Running_race.class)
                .block(REQUEST_TIMEOUT);
    }

    public Running_raceDTO create(Running_raceDTO running_raceDTO) { return requestCreateRunning_race(running_raceDTO);}
    private Running_raceDTO requestCreateRunning_race(Running_raceDTO running_raceDTO){
        return API
                .post()
                .uri("/sport/api/running_races/new")
                .body(Mono.just(running_raceDTO), Running_raceDTO.class)
                .retrieve()
                .bodyToMono(Running_raceDTO.class)
                .block(REQUEST_TIMEOUT);
    }

    public Running_raceDTO update(int id, Running_raceDTO running_raceDTO) { return requestCreateRunning_race(id, running_raceDTO); }
    private Running_raceDTO requestCreateRunning_race(int id, Running_raceDTO running_raceDTO){
        return API
                .put()
                .uri("/sport/api/running_races/update/" + id + "/")
                .body(Mono.just(running_raceDTO), Running_raceDTO.class)
                .retrieve()
                .bodyToMono(Running_raceDTO.class)
                .block(REQUEST_TIMEOUT);
    }

    public void delete(int id) { this.requestDeleteRunning_race(id); }
    private void requestDeleteRunning_race(int id){
        API
                .delete()
                .uri("/sport/api/running_races/delete/" + id + "/")
                .retrieve()
                .bodyToMono(Void.class)
                .block(REQUEST_TIMEOUT);
    }
}
