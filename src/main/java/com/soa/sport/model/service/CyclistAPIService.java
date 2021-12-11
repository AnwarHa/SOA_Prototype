package com.soa.sport.model.service;

import com.soa.sport.model.dto.CyclistDTO;
import com.soa.sport.model.entity.Cyclist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class CyclistAPIService {

    @Autowired
    private final WebClient API;

    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(10);

    @Autowired
    public CyclistAPIService(@Qualifier("API") WebClient API){ this.API = API; }

    public Cyclist[] requestAllProCyclists(){
        return API
                .get()
                .uri("/sport/api/cyclists")
                .retrieve()
                .bodyToMono(Cyclist[].class)
                .block(REQUEST_TIMEOUT);
    }

    public Cyclist readcyclist(int id){ return requestCyclist(id); }
    private Cyclist requestCyclist(int id){
        return API
                .get()
                .uri("/sport/api/cyclists/" + id + "/")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Cyclist.class)
                .block(REQUEST_TIMEOUT);
    }

    public CyclistDTO create(CyclistDTO cyclistDTO) { return requestCreateCyslist(cyclistDTO);}
    private CyclistDTO requestCreateCyslist(CyclistDTO cyclistDTO){
        return API
                .post()
                .uri("/sport/api/cyclists/new")
                .body(Mono.just(cyclistDTO), CyclistDTO.class)
                .retrieve()
                .bodyToMono(CyclistDTO.class)
                .block(REQUEST_TIMEOUT);
    }

    public CyclistDTO update(int id, CyclistDTO cyclistDTO) { return requestCreateCyslist(id, cyclistDTO); }
    private CyclistDTO requestCreateCyslist(int id, CyclistDTO cyclistDTO){
        return API
                .put()
                .uri("/sport/api/cyclists/update/" + id + "/")
                .body(Mono.just(cyclistDTO), CyclistDTO.class)
                .retrieve()
                .bodyToMono(CyclistDTO.class)
                .block(REQUEST_TIMEOUT);
    }

    public void delete(int id) { this.requestDeleteCyslist(id); }
    private void requestDeleteCyslist(int id){
        API
                .delete()
                .uri("/sport/api/cyclists/delete/" + id + "/")
                .retrieve()
                .bodyToMono(Void.class)
                .block(REQUEST_TIMEOUT);
    }
}
