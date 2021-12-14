package com.soa.sport.controller.api;

import com.soa.sport.model.dto.PlayerDTO;
import com.soa.sport.model.entity.Player;
import com.soa.sport.model.service.PlayerAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(path = "/sport/api/players")
public class PlayerAPIController {
    private final PlayerAPIService playerAPIService;

    @Autowired
    public PlayerAPIController(WebClient playerAPI) {
        this.playerAPIService = new PlayerAPIService(playerAPI);
    }

    @GetMapping
    public String getOverviewPlayers(Model model) {
        List<Player> players = Arrays.asList(this.playerAPIService.requestAllPlayers());
        model.addAttribute("players", players);
        return "api-basketballplayers";
    }

    @GetMapping( value = "/{id}")
    public String getShowPlayer(@PathVariable int id, Model model){
        Player player = this.playerAPIService.readPlayer(id);
        model.addAttribute("players", player);
        return "api-basketballplayers";
    }

    @GetMapping( value = "/filterById")
    public String showPlayer(@RequestParam int id, Model model){
        model.addAttribute("players",this.playerAPIService.readPlayer(id));
        return "api-basketballplayers";
    }

    @GetMapping(value = "/new")
    public String getNewSoccerPlayer(Model model){
        model.addAttribute("player",new PlayerDTO());
        return "api-basketballplayer-new";
    }

    @PostMapping(value = "/new", produces = "application/json", consumes = "application/json")
    public PlayerDTO postNewPlayer(@RequestBody Player player) {
        PlayerDTO playerDTO = createPlayerDTO(player);
        return this.playerAPIService.create(playerDTO);
    }

    @PostMapping(value = "/new")
    public String postNewPlayer(
            @RequestParam(name = "first_name") String first_name,
            @RequestParam(name = "last_name") String last_name,
            @RequestParam(name = "team") String team,
            @RequestParam(name = "age") int age,
            @RequestParam(name = "height") int height,
            @RequestParam(name = "weight") int weight
    ){
        PlayerDTO playerDTO = new PlayerDTO(first_name, last_name, team, age, height, weight);
        this.playerAPIService.create(playerDTO);
        return "redirect:/sport/api/players";
    }

    @PutMapping(value = "/update/{id}")
    public PlayerDTO updatePlayer(@PathVariable int id, @RequestBody Player player) {
        PlayerDTO playerDTO = createPlayerDTO(player);
        return this.playerAPIService.update(id, playerDTO);
    }

    @GetMapping("update/{id}")
    public String getUpdatePlayer(@PathVariable int id, Model model){
        Player player = this.playerAPIService.readPlayer(id);
        model.addAttribute("id",id);
        model.addAttribute("player", player);
        return "api-basketballplayer-update";
    }

    @PostMapping("/update/{id}")
    public String updatePlayer(
            @PathVariable int id,
            @RequestParam(name = "first_name") String first_name,
            @RequestParam(name = "last_name") String last_name,
            @RequestParam(name = "team") String team,
            @RequestParam(name = "age") int age,
            @RequestParam(name = "height") int height,
            @RequestParam(name = "weight") int weight
    ){
        PlayerDTO playerDTO = new PlayerDTO(first_name, last_name, team, age, height, weight);
        this.playerAPIService.update(id, playerDTO);
        return "redirect:/sport/api/players/" + id;
    }

    @DeleteMapping("/delete/{id}")
    public void deletePlayers(@PathVariable int id){
        this.playerAPIService.delete(id);
    }

    @GetMapping("/delete/{id}")
    public String getDeletePlayer(@PathVariable int id){
        this.playerAPIService.delete(id);
        return "redirect:/sport/api/players";
    }

    public PlayerDTO createPlayerDTO(Player player){
        String first_name = player.getFirst_name();
        String last_name = player.getLast_name();
        String team = player.getTeam();
        int age = player.getAge();
        int height = player.getHeight();
        int weight = player.getWeight();
        return new PlayerDTO(first_name, last_name, team, age, height, weight);
    }
}
