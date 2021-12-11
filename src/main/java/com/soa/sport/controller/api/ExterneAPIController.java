package com.soa.sport.controller.api;

import netscape.javascript.JSObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
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
    private String getf1Races() {
        String url="https://ergast.com/api/f1/races.json";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping(value = "/f1Races/{year}", produces = MediaType.APPLICATION_JSON_VALUE)
    private String getf1RacesByYear(@PathVariable int year) {
        String url="https://ergast.com/api/f1/" + year + "/races.json";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
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
