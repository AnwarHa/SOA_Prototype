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
    private final WebClient playerAPI;

    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(10);

    @Autowired
    public PlayerAPIService(@Qualifier("playerAPI") WebClient playerAPI){ this.playerAPI = playerAPI; }

    public Player[] requestAllPlayers(){
        return playerAPI
                .get()
                .uri("/players/")
                .retrieve()
                .bodyToMono(Player[].class)
                .block(REQUEST_TIMEOUT);
    }

    public Player readPlayer(int id){ return requestPlayer(id); }
    private Player requestPlayer(int id){
        return playerAPI
                .get()
                .uri("/players/" + id + "/")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Player.class)
                .block(REQUEST_TIMEOUT);
    }

    public PlayerDTO create(PlayerDTO playerDTO) { return requestCreatePlayer(playerDTO);}
    private PlayerDTO requestCreatePlayer(PlayerDTO playerDTO){
        return playerAPI
                .post()
                .uri("/players/")
                .body(Mono.just(playerDTO), PlayerDTO.class)
                .retrieve()
                .bodyToMono(PlayerDTO.class)
                .block(REQUEST_TIMEOUT);
    }

    public PlayerDTO update(int id, PlayerDTO playerDTO) { return requestCreatePlayer(id, playerDTO); }
    private PlayerDTO requestCreatePlayer(int id, PlayerDTO playerDTO){
        return playerAPI
                .put()
                .uri("/players/" + id + "/")
                .body(Mono.just(playerDTO), PlayerDTO.class)
                .retrieve()
                .bodyToMono(PlayerDTO.class)
                .block(REQUEST_TIMEOUT);
    }

    public void delete(int id) { this.requestDeletePlayer(id); }
    private void requestDeletePlayer(int id){
        playerAPI
                .delete()
                .uri("/players/" + id + "/")
                .retrieve()
                .bodyToMono(Void.class)
                .block(REQUEST_TIMEOUT);
    }
}
