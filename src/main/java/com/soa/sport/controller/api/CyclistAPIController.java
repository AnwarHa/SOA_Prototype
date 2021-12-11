package com.soa.sport.controller.api;

import com.soa.sport.model.dto.CyclistDTO;
import com.soa.sport.model.entity.Cyclist;
import com.soa.sport.model.service.CyclistAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/sport/api/cyclists")
public class CyclistAPIController {

    private final CyclistAPIService cyclistAPIService;

    @Autowired
    public CyclistAPIController(WebClient cyclistAPI) {
        this.cyclistAPIService = new CyclistAPIService(cyclistAPI);
    }

    @GetMapping
    public List<Cyclist> getOverviewProCyclists(Model model) {
        List<Cyclist> cyclists = Arrays.asList(this.cyclistAPIService.requestAllProCyclists());
        model.addAttribute("cyclists", cyclists);
        return cyclists;
    }

    @GetMapping( value = "/{id}")
    public Cyclist showPlayer(@PathVariable int id, Model model){
        Cyclist cyclist = this.cyclistAPIService.readcyclist(id);
        model.addAttribute("cyclist", cyclist);
        return cyclist;
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
