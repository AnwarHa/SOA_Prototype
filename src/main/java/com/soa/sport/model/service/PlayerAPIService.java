package com.soa.sport.model.service;

import com.soa.sport.model.dto.PlayerDTO;
import com.soa.sport.model.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class PlayerAPIService {
    @Autowired
    private final WebClient API;

    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(10);

    @Autowired
    public PlayerAPIService(@Qualifier("API") WebClient API){ this.API = API; }

    public Player[] requestAllPlayers(){
        return API
                .get()
                .uri("/sport/api/players/")
                .retrieve()
                .bodyToMono(Player[].class)
                .block(REQUEST_TIMEOUT);
    }

    public Player readPlayer(int id){ return requestPlayer(id); }
    private Player requestPlayer(int id){
        return API
                .get()
                .uri("/sport/api/players/" + id + "/")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Player.class)
                .block(REQUEST_TIMEOUT);
    }

    public PlayerDTO create(PlayerDTO playerDTO) { return requestCreatePlayer(playerDTO);}
    private PlayerDTO requestCreatePlayer(PlayerDTO playerDTO){
        return API
                .post()
                .uri("/sport/api/players/new")
                .body(Mono.just(playerDTO), PlayerDTO.class)
                .retrieve()
                .bodyToMono(PlayerDTO.class)
                .block(REQUEST_TIMEOUT);
    }

    public PlayerDTO update(int id, PlayerDTO playerDTO) { return requestCreatePlayer(id, playerDTO); }
    private PlayerDTO requestCreatePlayer(int id, PlayerDTO playerDTO){
        return API
                .put()
                .uri("/sport/api/players/update/" + id + "/")
                .body(Mono.just(playerDTO), PlayerDTO.class)
                .retrieve()
                .bodyToMono(PlayerDTO.class)
                .block(REQUEST_TIMEOUT);
    }

    public void delete(int id) { this.requestDeletePlayer(id); }
    private void requestDeletePlayer(int id){
        API
                .delete()
                .uri("/sport/api/players/delete/" + id + "/")
                .retrieve()
                .bodyToMono(Void.class)
                .block(REQUEST_TIMEOUT);
    }
}
