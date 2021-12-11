package com.soa.sport.controller.api;

import com.soa.sport.model.dto.PlayerDTO;
import com.soa.sport.model.entity.Player;
import com.soa.sport.model.service.PlayerAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/sport/api/players")
public class PlayerAPIController {
    private final PlayerAPIService playerAPIService;

    @Autowired
    public PlayerAPIController(WebClient playerAPI) {
        this.playerAPIService = new PlayerAPIService(playerAPI);
    }

    @GetMapping
    public List<Player> getOverviewPlayers(Model model) {
        List<Player> players = Arrays.asList(this.playerAPIService.requestAllPlayers());
        model.addAttribute("players", players);
        return players;
    }

    @GetMapping( value = "/{id}")
    public Player showPlayer(@PathVariable int id, Model model){
        Player player = this.playerAPIService.readPlayer(id);
        model.addAttribute("player", player);
        return player;
    }

    @PostMapping(value = "/new")
    public PlayerDTO postNewPlayer(@RequestBody Player player) {
        PlayerDTO playerDTO = createPlayerDTO(player);
        return this.playerAPIService.create(playerDTO);
    }

    @PutMapping(value = "/update/{id}")
    public PlayerDTO updatePlayer(@PathVariable int id, @RequestBody Player player) {
        PlayerDTO playerDTO = createPlayerDTO(player);
        return this.playerAPIService.update(id, playerDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePlayers(@PathVariable int id){
        this.playerAPIService.delete(id);
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
