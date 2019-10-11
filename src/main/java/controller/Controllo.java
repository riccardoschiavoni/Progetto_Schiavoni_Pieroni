package controller;

import modello.Azienda;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import service.Download;
import service.Statistiche;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.Statistiche;

import static service.Statistiche.returnstat;

@RestController
public  class Controllo {
    private Download service;
    @Autowired //dependency injection
    public Controllo(Download service) {
        this.service = service;
    }




    @GetMapping("/cercaValori")
    public Map<List<Double>,Object> getStatistiche(@RequestParam(value="campo",defaultValue = "")String Campo){
        if(!Campo.equals("")){
            return returnstat(/*ci entra la lista dell'aziende*/, Campo)

        }
    }

    @GetMapping("/")







}