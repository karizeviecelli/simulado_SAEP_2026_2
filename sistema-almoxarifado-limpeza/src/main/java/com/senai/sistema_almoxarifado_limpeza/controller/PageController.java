package com.senai.sistema_almoxarifado_limpeza.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String raiz() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String getHome() {
        return "home";
    }
}
