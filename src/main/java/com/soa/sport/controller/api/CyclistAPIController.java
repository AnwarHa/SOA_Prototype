package com.soa.sport.controller.api;

import com.soa.sport.model.dto.CyclistDTO;
import com.soa.sport.model.entity.Cyclist;
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

    @GetMapping( value = "/filterById")
    public String showPlayer(@RequestParam int id, Model model){
        Cyclist cyclist = this.cyclistAPIService.readcyclist(id);
        model.addAttribute("cyclists", cyclist);
        return "api-cyclists";
    }

    @PostMapping(value = "/new")
    public CyclistDTO postNewCyclist(@RequestBody Cyclist cyclist) {
        CyclistDTO cyclistDTO = createCyclistDTO(cyclist);
        return this.cyclistAPIService.create(cyclistDTO);
    }

    @PutMapping(value = "/update/{id}")
    public CyclistDTO updateCyclist(@PathVariable int id, @RequestBody Cyclist cyclist) {
        CyclistDTO cyclistDTO = createCyclistDTO(cyclist);
        return this.cyclistAPIService.update(id, cyclistDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCyclist(@PathVariable int id){
        this.cyclistAPIService.delete(id);
    }

    @GetMapping("/add-cyclist")
    public String addCyclist(){
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
