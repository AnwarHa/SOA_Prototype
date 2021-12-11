package com.soa.sport.controller.api;


import com.soa.sport.model.entity.F1Race;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Controller
@RequestMapping(path = "/sport/api/extern")
public class ExterneAPIController {
    @GetMapping(value = "/soccerleagues", produces = MediaType.APPLICATION_JSON_VALUE)
    private String getSoccerleagues() {
        String url="https://api-football-standings.azharimm.site/leagues";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping(value =  "/soccerleagues/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private String getSoccerleaguesById(@PathVariable String id) throws JSONException {
        String url="https://api-football-standings.azharimm.site/leagues/" + id;
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = new JSONObject(json);
        System.out.println(jsonObject.getJSONObject("data").getString("name"));

        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping(value = "/basketballNBA", produces = MediaType.APPLICATION_JSON_VALUE)
    private String getBasketballNBA() {
        String url="https://www.balldontlie.io/api/v1/players";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping(value = "/basketballNBA/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private String getBasketballNBAById(@PathVariable int id) {
        String url="https://www.balldontlie.io/api/v1/players/" + id;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping(value = "/f1Races", produces = MediaType.APPLICATION_JSON_VALUE)
    private String getf1Races(Model model) {
        String url="http://127.0.0.1:8080/sport/api/extern/f1Races";
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONObject("MRData").getJSONObject("RaceTable").getJSONArray("Races");
        ArrayList<F1Race> races = new ArrayList<>();
        for(int i=0; i < jsonArray.length(); i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            int season = Integer.parseInt(obj.getString("season"));
            String raceName = obj.getString("raceName");
            String date = obj.getString("date");
            F1Race race = new F1Race(season, raceName, date);
            races.add(race);
        }
        model.addAttribute("races", races);
        return "api-f1races";
    }

    @GetMapping(value = "/f1Races/{year}", produces = MediaType.APPLICATION_JSON_VALUE)
    private String getf1RacesByYear(@RequestParam int year, Model model) {
        String url="http://127.0.0.1:8080/sport/api/extern/f1Races/" + year;
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONObject("MRData").getJSONObject("RaceTable").getJSONArray("Races");
        ArrayList<F1Race> races = new ArrayList<>();
        for(int i=0; i < jsonArray.length(); i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            int season = Integer.parseInt(obj.getString("season"));
            String raceName = obj.getString("raceName");
            String date = obj.getString("date");
            F1Race race = new F1Race(season, raceName, date);
            races.add(race);
        }
        model.addAttribute("races", races);
        return "api-f1races";
    }

    @GetMapping(value = "/citybikes", produces = MediaType.APPLICATION_JSON_VALUE)
    private String getCitybikes() {
        String url="https://api.citybik.es/v2/networks/";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping(value = "/citybikes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private String getCitybikesById(@PathVariable String id) {
        String url="https://api.citybik.es/v2/networks/" + id;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

}
