package com.soa.sport.controller.api;

import com.soa.sport.model.dto.SoccerPlayerDTO;
import com.soa.sport.model.entity.SoccerPlayer;
import com.soa.sport.model.service.SoccerAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping(path = "/sport/api/soccer")
public class SoccerAPIController {

    private final SoccerAPIService soccerAPIService;

    @Autowired
    public SoccerAPIController(WebClient soccerAPI){
        this.soccerAPIService = new SoccerAPIService(soccerAPI);
    }

    @GetMapping(produces = "application/json")
    public SoccerPlayer[] getOverviewSoccerPlayers(Model model){
        return this.soccerAPIService.requestAllSoccerPlayers();
    }

    @GetMapping( value = "/{id}", produces = "application/json")
    public SoccerPlayer showPlayer(@PathVariable int id, Model model){
        return this.soccerAPIService.readPlayer(id);
    }

    @PostMapping(value = "/new",produces = "application/json", consumes = "application/json")
    public SoccerPlayerDTO postNewSoccerPlayer(@RequestBody SoccerPlayer soccerPlayer){
        SoccerPlayerDTO soccerPlayerDTO = createSoccerDTO(soccerPlayer);
        SoccerPlayerDTO receivedSoccerPlayer = this.soccerAPIService.create(soccerPlayerDTO);
        System.out.println("CREATED: " + receivedSoccerPlayer);
        return receivedSoccerPlayer;
    }

    @DeleteMapping(value = "/{id}/delete", produces = "application/json")
    public String deleteSoccerPlayer(@PathVariable int id){
        this.soccerAPIService.delete(id);
        return "DELETED";
    }

    @PutMapping(value = "/{id}/update", produces = "application/json", consumes = "application/json")
    public SoccerPlayerDTO postUpdateSoccerPlayer(@PathVariable int id, @RequestBody SoccerPlayer soccerPlayer){

        SoccerPlayerDTO soccerPlayerDTO = createSoccerDTO(soccerPlayer);
        SoccerPlayerDTO receivedSoccerPlayer = this.soccerAPIService.update(id, soccerPlayerDTO);
        System.out.println("UPDATED: " + receivedSoccerPlayer);
        return receivedSoccerPlayer;
    }

    public SoccerPlayerDTO createSoccerDTO(SoccerPlayer soccerPlayer){
        String first_name = soccerPlayer.getFirst_name();
        String last_name = soccerPlayer.getLast_name();
        String team = soccerPlayer.getTeam();
        String position = soccerPlayer.getPosition();
        String dob = soccerPlayer.getDob();
        int goals = soccerPlayer.getGoals();
        int assists = soccerPlayer.getAssists();
        SoccerPlayerDTO soccerPlayerDTO = new SoccerPlayerDTO(first_name, last_name, team, position, dob, goals, assists);
        return soccerPlayerDTO;
    }


}
