package com.soa.sport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sport")
public class SportController {

    @GetMapping
    public String getSport(){ return "index"; }

    @GetMapping("/api")
    public String getApis(){ return "redirect:/sport"; }

    @GetMapping("/api/template")
    public String getTemplate(){ return "TEMPLATE"; }

}
