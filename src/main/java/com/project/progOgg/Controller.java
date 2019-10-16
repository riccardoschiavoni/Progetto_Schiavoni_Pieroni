package com.project.progOgg;
import static service.FiltriNum.*;
import static service.FiltriCampi.*;
import static service.Statistiche.*;

import modello.Azienda;
import service.Download;
import service.FiltriCampi;
import service.FiltriNum;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
@RestController


public class Controller{

    @GetMapping("/operatori")
    public Map<String,String> op(){
        Map<String,String> map=new HashMap<>();
        map.put("operatorinum","filtri numerici");
        map.put("%in per num","resituisce il valore passato come parametro e dove si trova");
        map.put("%not","restituisce tutti i dati tranne il valore passato come parametro");
        map.put("%gte","restituisce tutti i campi con valore maggiore-uguale di quello passato");
        map.put("%gt","restituisce tutti i campi con valore maggiore di quello passato");
        map.put("%it","restituisce tutti i campi con valore minore di quello passato");
        map.put("%ite","restituisce tutti i campi con valore minore-uguale di quello passato");
        map.put("operatori","filtri per i campi");
        map.put("%in","resituisce il valore passato come parametro e dove si trova");
        map.put("%nin","restituisce tutti i dati tranne il valore passato come parametro");
        return map;
    }

    @GetMapping("/data")
    public List<Azienda> ret(){
        return Download.getAziende();
    }

    @GetMapping("/anni")
    public Map<String,Integer> anni(){
        Map<String,Integer> map=new HashMap<>();
        int i=2017;
        for(;i>1989;i--)
            map.put("anno",i);
        return map;
    }


    @GetMapping("/cercaValori")//ritorna le statistiche di una data azienda su tutti gli anni
    public Map<List<Double>,Object> getStatistiche(@RequestParam(value="campo",defaultValue = "")String Campo){
        if(!Campo.equals("")){
            return returnstat(Download.getAziende(), Campo);
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Insert a value...");
    }

    @GetMapping("/statisticheAnnuali")
    public Map<Object,Object> colonna(@RequestParam(value="anno",defaultValue = "")Integer anno){
        Map<Object, Object> m=new HashMap<>();
        m.put("anno considerato",anno);
        if(anno<2018&&anno>1989){
            m=Yearstatic(Download.getAziende(),anno);
            return m;
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Insert a year between 1990 and 2017...");
    }

    @PostMapping("/FiltraValori")
    public Map<String,Object> retVal(@RequestBody FiltriNum body){
        if((body.value instanceof Integer)&&(body.field.equals("anno"))){
            return FiltroAnno(body.operator,(Integer)body.value,Download.getAziende());
        }
        else{
            if((body.value instanceof Double)&&(body.field.equals("anno"))){
                return FiltroAnno(body.operator,(Double)body.value,Download.getAziende());
            }
            if(body.field.equals("anno")) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Insert the field anno and the year integer value or a double value...");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Insert anno...");
        }
    }


    @PostMapping("/FiltraCampi")
    public Map<String,Object> retCamp(@RequestBody FiltriCampi body){
        if((body.field.equals("plants")||body.field.equals("operator")||body.field.equals("nrg_ball")||body.field.equals("siec")||body.field.equals("unit")||body.field.equals("geo"))!=true)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Insert an appropiate field...");
        return FiltroCampo(body.field,body.operator,(String)body.value,Download.getAziende());
    }




}
