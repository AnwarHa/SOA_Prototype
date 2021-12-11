package com.soa.sport.controller.api;

import com.soa.sport.model.dto.Running_raceDTO;
import com.soa.sport.model.entity.Running_race;
import com.soa.sport.model.service.Running_racesAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.sql.Time;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/sport/api/running_races")
public class Running_racesAPIController {

    private final Running_racesAPIService running_racesAPIService;

    @Autowired
    public Running_racesAPIController(WebClient running_racesAPI){
        this.running_racesAPIService = new Running_racesAPIService(running_racesAPI);
    }

    @GetMapping
    public List<Running_race> getOverviewRunning_races(Model model) {
        List<Running_race> running_races = Arrays.asList(this.running_racesAPIService.requestAllRunning_races());
        model.addAttribute("running_races", running_races);
        return running_races;
    }

    @GetMapping( value = "/{id}")
    public Running_race showRunning_racer(@PathVariable int id, Model model){
        Running_race running_race = this.running_racesAPIService.readrunning_race(id);
        model.addAttribute("running_race", running_race);
        return running_race;
    }

    @PostMapping(value = "/new")
    public Running_raceDTO postNewRunning_race(@RequestBody Running_race running_race) {
        Running_raceDTO running_raceDTO = createRunning_raceDTO(running_race) ;
        return this.running_racesAPIService.create(running_raceDTO);
    }

    @PutMapping(value = "/update/{id}")
    public Running_raceDTO updateRunning_race(@PathVariable int id, @RequestBody Running_race running_race) {
        Running_raceDTO running_raceDTO = createRunning_raceDTO(running_race);
        return this.running_racesAPIService.update(id, running_raceDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRunning_race(@PathVariable int id){
        this.running_racesAPIService.delete(id);
    }

    public Running_raceDTO createRunning_raceDTO(Running_race running_race){
        String name = running_race.getName();
        String organizer = running_race.getOrganizer();
        String location = running_race.getLocation();
        int distance = running_race.getDistance();
        int registration_price = running_race.getRegistration_price();
        String date = running_race.getDate();
        Time starting_hour = running_race.getStarting_hour();
        return new Running_raceDTO(name, organizer, location, distance, registration_price, date, starting_hour);
    }
}
