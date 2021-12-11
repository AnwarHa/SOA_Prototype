package com.soa.sport.controller.api;

import com.soa.sport.model.dto.SoccerPlayerDTO;
import com.soa.sport.model.entity.Cyclist;
import com.soa.sport.model.entity.SoccerPlayer;
import com.soa.sport.model.service.SoccerAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(path = "/sport/api/soccer")
public class SoccerAPIController {

    private final SoccerAPIService soccerAPIService;

    @Autowired
    public SoccerAPIController(WebClient soccerAPI){
        this.soccerAPIService = new SoccerAPIService(soccerAPI);
    }

    @GetMapping()
    public String getOverviewSoccerPlayers(Model model){
        List<SoccerPlayer> players = Arrays.asList(this.soccerAPIService.requestAllSoccerPlayers());
        model.addAttribute("players", players);
        return "api-players";
    }

    @GetMapping( value = "/{id}")
    public String getShowPlayer(@PathVariable int id, Model model){
        SoccerPlayer player = this.soccerAPIService.readPlayer(id);
        model.addAttribute("players", player);
        return "api-players";
    }


    @GetMapping( value = "/filterById")
    public String showPlayer(@RequestParam int id, Model model){
        model.addAttribute("players",this.soccerAPIService.readPlayer(id));
        return "api-players";
    }

    @GetMapping(value = "/new")
    public String getNewSoccerPlayer(Model model){
        model.addAttribute("player",new SoccerPlayerDTO());
        return "api-player-new";
    }

    // via html
    @PostMapping(value = "/new")
    public String postNewSoccerPlayer(
            @RequestParam(name = "first_name") String first_name,
            @RequestParam(name = "last_name") String last_name,
            @RequestParam(name = "team") String team,
            @RequestParam(name = "position") String position,
            @RequestParam(name = "dob") String dob,
            @RequestParam(name = "goals") int goals,
            @RequestParam(name = "assists") int assists
    ){

        SoccerPlayerDTO soccerPlayerDTO = new SoccerPlayerDTO(first_name, last_name, team, position, dob, goals, assists);
        SoccerPlayerDTO receivedSoccerPlayer = this.soccerAPIService.create(soccerPlayerDTO);
        System.out.println("CREATED: " + receivedSoccerPlayer);
        return "redirect:/sport/api/soccer";
    }

    // via json
    @PostMapping(value = "/new",produces = "application/json", consumes = "application/json")
    public SoccerPlayerDTO postNewSoccerPlayer(@RequestBody SoccerPlayer soccerPlayer){
        SoccerPlayerDTO soccerPlayerDTO = createSoccerDTO(soccerPlayer);
        SoccerPlayerDTO receivedSoccerPlayer = this.soccerAPIService.create(soccerPlayerDTO);
        System.out.println("CREATED: " + receivedSoccerPlayer);
        return receivedSoccerPlayer;
    }

    @DeleteMapping("/{id}/delete")
    public void deleteSoccerPlayer(@PathVariable int id){
        this.soccerAPIService.delete(id);
    }

    @GetMapping("/{id}/delete")
    public String getDeleteSoccerPlayer(@PathVariable int id){
        this.soccerAPIService.delete(id);
        return "redirect:/sport/api/soccer";
    }


    @GetMapping("/{id}/update")
    public String getUpdateMovie(@PathVariable int id, Model model){
        SoccerPlayer player = this.soccerAPIService.readPlayer(id);
        model.addAttribute("id",id);
        model.addAttribute("player", player);
        return "api-player-update";
    }

    // via json
    @PutMapping(value = "/{id}/update", produces = "application/json", consumes = "application/json")
    public SoccerPlayerDTO postUpdateSoccerPlayer(@PathVariable int id, @RequestBody SoccerPlayer soccerPlayer){

        SoccerPlayerDTO soccerPlayerDTO = createSoccerDTO(soccerPlayer);
        SoccerPlayerDTO receivedSoccerPlayer = this.soccerAPIService.update(id, soccerPlayerDTO);
        System.out.println("UPDATED: " + receivedSoccerPlayer);
        return receivedSoccerPlayer;
    }

    // via html
    @PostMapping("/{id}/update")
    public String postUpdateSoccerPlayer(
            @PathVariable int id,
            @RequestParam(name = "first_name") String first_name,
            @RequestParam(name = "last_name") String last_name,
            @RequestParam(name = "team") String team,
            @RequestParam(name = "position") String position,
            @RequestParam(name = "dob") String dob,
            @RequestParam(name = "goals") int goals,
            @RequestParam(name = "assists") int assists
    ){
        SoccerPlayerDTO soccerPlayerDTO = new SoccerPlayerDTO(first_name, last_name, team, position, dob, goals, assists);
        SoccerPlayerDTO receivedSoccerPlayer = this.soccerAPIService.update(id, soccerPlayerDTO);
        System.out.println("UPDATED: " + receivedSoccerPlayer);
        return "redirect:/sport/api/soccer/" + id;
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
