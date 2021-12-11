package com.soa.sport.controller.api;

import com.soa.sport.model.dto.CyclistDTO;
import com.soa.sport.model.dto.SoccerPlayerDTO;
import com.soa.sport.model.entity.Cyclist;
import com.soa.sport.model.entity.SoccerPlayer;
import com.soa.sport.model.service.CyclistAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(path = "/sport/api/cyclists")
public class CyclistAPIController {

    private final CyclistAPIService cyclistAPIService;

    @Autowired
    public CyclistAPIController(WebClient cyclistAPI) {
        this.cyclistAPIService = new CyclistAPIService(cyclistAPI);
    }

    @GetMapping
    public String getOverviewProCyclists(Model model) {
        List<Cyclist> cyclists = Arrays.asList(this.cyclistAPIService.requestAllProCyclists());
        model.addAttribute("cyclists", cyclists);
        return "api-cyclists";
    }

    @GetMapping( value = "/{id}")
    public String getShowCyclist(@PathVariable int id, Model model){
        Cyclist cyclist = this.cyclistAPIService.readcyclist(id);
        model.addAttribute("cyclists", cyclist);
        return "api-cyclists";
    }

    @GetMapping( value = "/filterById")
    public String showCyclist(@RequestParam int id, Model model){
        Cyclist cyclist = this.cyclistAPIService.readcyclist(id);
        model.addAttribute("cyclists", cyclist);
        return "api-cyclists";
    }

    @PostMapping(value = "/new", produces = "application/json", consumes = "application/json")
    public CyclistDTO postNewCyclist(@RequestBody Cyclist cyclist) {
        CyclistDTO cyclistDTO = createCyclistDTO(cyclist);
        return this.cyclistAPIService.create(cyclistDTO);
    }

    @PostMapping(value = "/new")
    public String postNewCyclist(
            @RequestParam(name = "first_name") String first_name,
            @RequestParam(name = "last_name") String last_name,
            @RequestParam(name = "team") String team,
            @RequestParam(name = "nationality") String nationality,
            @RequestParam(name = "age") int age,
            @RequestParam(name = "height") int height,
            @RequestParam(name = "weight") int weight
    ){
        CyclistDTO cyclistDTO = new CyclistDTO(first_name, last_name, team, nationality, age, height, weight);
        CyclistDTO receivedCyclist = this.cyclistAPIService.create(cyclistDTO);
        System.out.println("CREATED: " + receivedCyclist);
        return "redirect:/sport/api/cyclists";
    }

    @PutMapping(value = "/update/{id}")
    public CyclistDTO updateCyclist(@PathVariable int id, @RequestBody Cyclist cyclist) {
        CyclistDTO cyclistDTO = createCyclistDTO(cyclist);
        return this.cyclistAPIService.update(id, cyclistDTO);
    }

    @GetMapping("update/{id}")
    public String getUpdateCyclist(@PathVariable int id, Model model){
        Cyclist cyclist = this.cyclistAPIService.readcyclist(id);
        model.addAttribute("id",id);
        model.addAttribute("cyclist", cyclist);
        return "update-cyclist";
    }

    @PostMapping("/update/{id}")
    public String updateCyclist(
            @PathVariable int id,
            @RequestParam(name = "first_name") String first_name,
            @RequestParam(name = "last_name") String last_name,
            @RequestParam(name = "team") String team,
            @RequestParam(name = "nationality") String nationality,
            @RequestParam(name = "age") int age,
            @RequestParam(name = "height") int height,
            @RequestParam(name = "weight") int weight
    ){
        CyclistDTO cyclistDTO = new CyclistDTO(first_name, last_name, team, nationality, age, height, weight);
        CyclistDTO receivedCyclist = this.cyclistAPIService.update(id, cyclistDTO);
        System.out.println("UPDATED: " + receivedCyclist);
        return "redirect:/sport/api/cyclists/" + id;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCyclist(@PathVariable int id){
        this.cyclistAPIService.delete(id);
    }

    @GetMapping("/delete/{id}")
    public String getDeleteCyclist(@PathVariable int id){
        this.cyclistAPIService.delete(id);
        return "redirect:/sport/api/cyclists";
    }

    @GetMapping("/add-cyclist")
    public String addCyclist(Model model){
        model.addAttribute("cyclist", new CyclistDTO());
        return "add-cyclist";
    }

    public CyclistDTO createCyclistDTO(Cyclist cyclist){
        String first_name = cyclist.getFirst_name();
        String last_name = cyclist.getLast_name();
        String team = cyclist.getTeam();
        String nationality = cyclist.getNationality();
        int age = cyclist.getAge();
        int height = cyclist.getHeight();
        int weight = cyclist.getWeight();
        return new CyclistDTO(first_name, last_name, team, nationality, age, height, weight);
    }

}
