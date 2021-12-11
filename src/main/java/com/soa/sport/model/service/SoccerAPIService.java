package com.soa.sport.model.service;

import com.soa.sport.model.dto.SoccerPlayerDTO;
import com.soa.sport.model.entity.SoccerPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class SoccerAPIService {

    @Autowired
    private final WebClient soccerAPI;

    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(10);

    @Autowired
    public SoccerAPIService(@Qualifier("soccerAPI") WebClient soccerAPI){ this.soccerAPI = soccerAPI; }

    public SoccerPlayer[] requestAllSoccerPlayers(){
        return soccerAPI
                .get()
                .uri("/player/")
                .retrieve()
                .bodyToMono(SoccerPlayer[].class)
                .block(REQUEST_TIMEOUT);
    }

    public SoccerPlayer readPlayer(int id){ return requestPlayer(id); }
    private SoccerPlayer requestPlayer(int id){
        return soccerAPI
                .get()
                .uri("/details/" + id + "/")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(SoccerPlayer.class)
                .block(REQUEST_TIMEOUT);
    }

    public SoccerPlayerDTO create(SoccerPlayerDTO soccerPlayerDTO) { return requestCreateSoccerPlayer(soccerPlayerDTO);}
    private SoccerPlayerDTO requestCreateSoccerPlayer(SoccerPlayerDTO soccerPlayerDTO){
        return soccerAPI
                .post()
                .uri("/player/")
                .body(Mono.just(soccerPlayerDTO), SoccerPlayerDTO.class)
                .retrieve()
                .bodyToMono(SoccerPlayerDTO.class)
                .block(REQUEST_TIMEOUT);
    }

    public void delete(int id) { this.requestDeleteSoccerPlayer(id); }
    private void requestDeleteSoccerPlayer(int id){
        soccerAPI
                .delete()
                .uri("/details/" + id + "/")
                .retrieve()
                .bodyToMono(Void.class)
                .block(REQUEST_TIMEOUT);
    }

    public SoccerPlayerDTO update(int id, SoccerPlayerDTO soccerPlayerDTO) { return requestUpdateSoccerPlayer(id, soccerPlayerDTO); }
    private SoccerPlayerDTO requestUpdateSoccerPlayer(int id, SoccerPlayerDTO soccerPlayerDTO){
        return soccerAPI
                .put()
                .uri("/details/" + id + "/")
                .body(Mono.just(soccerPlayerDTO), SoccerPlayerDTO.class)
                .retrieve()
                .bodyToMono(SoccerPlayerDTO.class)
                .block(REQUEST_TIMEOUT);
    }




}
