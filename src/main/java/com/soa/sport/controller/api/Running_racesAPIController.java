package com.soa.sport.controller.api;

import com.soa.sport.model.dto.CyclistDTO;
import com.soa.sport.model.dto.Running_raceDTO;
import com.soa.sport.model.dto.SoccerPlayerDTO;
import com.soa.sport.model.entity.Cyclist;
import com.soa.sport.model.entity.Running_race;
import com.soa.sport.model.service.Running_racesAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.sql.Time;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(path = "/sport/api/running_races")
public class Running_racesAPIController {

    private final Running_racesAPIService running_racesAPIService;

    @Autowired
    public Running_racesAPIController(WebClient running_racesAPI){
        this.running_racesAPIService = new Running_racesAPIService(running_racesAPI);
    }

    @GetMapping
    public String getOverviewRunning_races(Model model) {
        List<Running_race> running_races = Arrays.asList(this.running_racesAPIService.requestAllRunning_races());
        model.addAttribute("running_races", running_races);
        return "api-running_races";
    }
    @GetMapping( value = "/{id}")
    public String getShowRunning_racer(@PathVariable int id, Model model){
        Running_race running_race = this.running_racesAPIService.readrunning_race(id);
        model.addAttribute("running_races", running_race);
        return "api-running_races";
    }

    @GetMapping( value = "/filterById")
    public String showRunning_racer(@RequestParam int id, Model model){
        model.addAttribute("running_races", this.running_racesAPIService.readrunning_race(id));
        return "api-running_races";
    }

    @GetMapping(value = "/new")
    public String getNewRunning_racer(Model model){
        model.addAttribute("running_race",new Running_raceDTO());
        return "api-running_races-new";
    }

    @PostMapping(value = "/new", produces = "application/json", consumes = "application/json")
    public Running_raceDTO postNewRunning_race(@RequestBody Running_race running_race) {
        Running_raceDTO running_raceDTO = createRunning_raceDTO(running_race);
        return this.running_racesAPIService.create(running_raceDTO);
    }

    @PostMapping(value = "/new")
    public String postNewRunning_race(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "organizer") String organizer,
            @RequestParam(name = "location") String location,
            @RequestParam(name = "distance") int distance,
            @RequestParam(name = "registration_price") int registration_price,
            @RequestParam(name = "date") String date,
            @RequestParam(name = "starting_hour") String starting_hour
    ){
        Running_raceDTO running_raceDTO = new Running_raceDTO(name,organizer,location,distance,registration_price,date,starting_hour);
        this.running_racesAPIService.create(running_raceDTO);
        return "redirect:/sport/api/running_races";
    }


    @PutMapping(value = "/update/{id}")
    public Running_raceDTO updateRunning_race(@PathVariable int id, @RequestBody Running_race running_race) {
        Running_raceDTO running_raceDTO = createRunning_raceDTO(running_race);
        return this.running_racesAPIService.update(id, running_raceDTO);
    }

    @GetMapping("update/{id}")
    public String getUpdateRunning_race(@PathVariable int id, Model model){
        Running_race running_race = this.running_racesAPIService.readrunning_race(id);
        model.addAttribute("id",id);
        model.addAttribute("running_race", running_race);
        return "api-running_races-update";
    }

    @PostMapping("/update/{id}")
    public String updateRunning_race(
            @PathVariable int id,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "organizer") String organizer,
            @RequestParam(name = "location") String location,
            @RequestParam(name = "distance") int distance,
            @RequestParam(name = "registration_price") int registration_price,
            @RequestParam(name = "date") String date,
            @RequestParam(name = "starting_hour") String starting_hour
    ){
        Running_raceDTO running_raceDTO = new Running_raceDTO(name,organizer,location,distance,registration_price,date,starting_hour);
        this.running_racesAPIService.update(id,running_raceDTO);
        return "redirect:/sport/api/running_races/" + id;
    }


    @DeleteMapping("/delete/{id}")
    public void deleteRunning_race(@PathVariable int id){
        this.running_racesAPIService.delete(id);
    }

    @GetMapping("/delete/{id}")
    public String getDeleteCyclist(@PathVariable int id){
        this.running_racesAPIService.delete(id);
        return "redirect:/sport/api/running_races";
    }

    public Running_raceDTO createRunning_raceDTO(Running_race running_race){
        String name = running_race.getName();
        String organizer = running_race.getOrganizer();
        String location = running_race.getLocation();
        int distance = running_race.getDistance();
        int registration_price = running_race.getRegistration_price();
        String date = running_race.getDate();
        String starting_hour = running_race.getStarting_hour();
        return new Running_raceDTO(name, organizer, location, distance, registration_price, date, starting_hour);
    }
}
