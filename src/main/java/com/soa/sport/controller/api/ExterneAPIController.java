package com.soa.sport.controller.api;


import com.soa.sport.model.entity.BasketballNBAPlayer;
import com.soa.sport.model.entity.Citybike;
import com.soa.sport.model.entity.F1Race;
import com.soa.sport.model.entity.League;
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
    private String getSoccerleagues(Model model) throws JSONException {
        String url="http://127.0.0.1:8080/sport/api/extern/soccerleagues/";
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.optJSONArray("data");
        ArrayList<League> leagues = new ArrayList<>();
        for(int i=0; i < jsonArray.length(); i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            String id = obj.getString("id");
            String name = obj.getString("name");
            String light = obj.getJSONObject("logos").getString("light");
            String dark = obj.getJSONObject("logos").getString("dark");
            League league = new League(id, name, light, dark);
            leagues.add(league);
        }
        model.addAttribute("leagues", leagues);

        return "api-league";
    }

    @GetMapping(value =  "/soccerleagues/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private String getSoccerleaguesById(@RequestParam String id, Model model) throws JSONException {
        String url="http://127.0.0.1:8080/sport/api/extern/soccerleagues/" + id;
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = new JSONObject(json);
        String jsonId = jsonObject.getJSONObject("data").getString("id");
        String name = jsonObject.getJSONObject("data").getString("name");
        String light = jsonObject.getJSONObject("data").getJSONObject("logos").getString("light");
        String dark = jsonObject.getJSONObject("data").getJSONObject("logos").getString("dark");
        League league = new League(jsonId, name, light, dark);
        model.addAttribute("leagues", league);

        return "api-league";
    }

    @GetMapping(value = "/basketballNBA", produces = MediaType.APPLICATION_JSON_VALUE)
    private String getBasketballNBA(Model model) {
        String url = "http://127.0.0.1:8080/sport/api/extern/basketballNBA";
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        ArrayList<BasketballNBAPlayer> basketballNBAPlayers = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String first_name = obj.getString("first_name");
            String last_name = obj.getString("last_name");
            String position = obj.getString("position");
            int id = obj.getInt("id");
            BasketballNBAPlayer basketballNBAPlayer = new BasketballNBAPlayer(id, first_name, last_name, position);
            basketballNBAPlayers.add(basketballNBAPlayer);
        }
        model.addAttribute("basketballNBAPlayers", basketballNBAPlayers);
        return "api-basketballNBA";
    }

    @GetMapping(value = "/basketballNBA/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private String getBasketballNBAById(@RequestParam int id, Model model) {
        String url = "http://127.0.0.1:8080/sport/api/extern/basketballNBA/" + id;
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = new JSONObject(json);
        String first_name = jsonObject.getString("first_name");
        String last_name = jsonObject.getString("last_name");
        String position = jsonObject.getString("position");
        int idV2 = jsonObject.getInt("id");
        BasketballNBAPlayer basketballNBAPlayer = new BasketballNBAPlayer(idV2, first_name, last_name, position);
        model.addAttribute("basketballNBAPlayers", basketballNBAPlayer);
        return "api-basketballNBA";
    }


    @GetMapping(value = "/f1Races", produces = MediaType.APPLICATION_JSON_VALUE)
    private String getf1Races(Model model) {
        String url = "http://127.0.0.1:8080/sport/api/extern/f1Races";
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONObject("MRData").getJSONObject("RaceTable").getJSONArray("Races");
        ArrayList<F1Race> races = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
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
        String url = "http://127.0.0.1:8080/sport/api/extern/f1Races/" + year;
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONObject("MRData").getJSONObject("RaceTable").getJSONArray("Races");
        ArrayList<F1Race> races = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
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
    private String getCitybikes(Model model) {
        String url = "http://127.0.0.1:8080/sport/api/extern/citybikes";
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("networks");
        ArrayList<Citybike> citybikes = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String id = obj.getString("id");
            String name = obj.getString("name");
            String city = obj.getJSONObject("location").getString("city");
            String country = obj.getJSONObject("location").getString("country");
            Citybike citybike = new Citybike(id, name, city, country);
            citybikes.add(citybike);
        }
        model.addAttribute("citybikes", citybikes);
        return "api-citybikes";
    }

    @GetMapping(value = "/citybikes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private String getCitybikesById(@RequestParam String id, Model model) {
        String url = "http://127.0.0.1:8080/sport/api/extern/citybikes/" + id;
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = new JSONObject(json);
        JSONObject obj = jsonObject.getJSONObject("network");
        String jsonId = obj.getString("id");
        String name = obj.getString("name");
        String city = obj.getJSONObject("location").getString("city");
        String country = obj.getJSONObject("location").getString("country");
        Citybike citybike = new Citybike(jsonId, name, city, country);
        model.addAttribute("citybikes", citybike);
        return "api-citybikes";
    }
}
