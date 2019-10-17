package com.project.progOgg;
import static service.FiltriNum.*;
import static service.FiltriCampi.*;
import static service.Statistiche.*;
import service.Metadati;

import modello.Azienda;
import service.Download;
import service.FiltriCampi;
import service.FiltriNum;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import service.Metadati;

@RestController


public class Controller{

    @GetMapping("/operatori") //stampa gli operatori disponibili
    public Map<String,String> op(){
        Map<String,String> map=new HashMap<>();
        map.put("%in per num","per i num:resituisce il valore passato come parametro e dove si trova");
        map.put("%not","per i num:restituisce tutti i dati tranne il valore passato come parametro");
        map.put("%gte","per i num:restituisce tutti i campi con valore maggiore-uguale di quello passato");
        map.put("%gt","per i num:restituisce tutti i campi con valore maggiore di quello passato");
        map.put("%it","per i num:restituisce tutti i campi con valore minore di quello passato");
        map.put("%ite","per i num:restituisce tutti i campi con valore minore-uguale di quello passato");
        map.put("%in"," per i campi:resituisce il valore passato come parametro e dove si trova");
        map.put("%nin","per i campi:restituisce tutti i dati tranne il valore passato come parametro");
        return map;
    }

    @GetMapping("/data") //stampa il dataset in formato json
    public List<Azienda> ret(){
        return Download.getAziende();
    }

    @GetMapping("/anni") //stampa gli anni disponibili
    public Map<String,Integer> anni(){
        Map<String,Integer> map=new HashMap<>();
        int i=2017;
        for(;i>1989;i--)
            map.put("anno"+i,i);
        return map;
    }

    @GetMapping("/metadata")
    public List<Map> meta(){
        return Metadati.getMetadata();
    }

    @GetMapping("/cercaValori")//ritorna le statistiche di una data azienda su tutti gli anni
    public Map<List<Double>,Object> getStatistiche(@RequestParam(value="campo",defaultValue = "")String Campo){
        if(!Campo.equals("")){
            return returnstat(Download.getAziende(), Campo); //richiama il metodo delle statistiche
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Insert a value...");//ritorna un valore di errore qualora non venga inserito il campo opportuno
    }

    @GetMapping("/statisticheAnnuali")//ritorna le statistiche su un dato anno
    public Map<Object,Object> colonna(@RequestParam(value="anno",defaultValue = "")Integer anno){
        Map<Object, Object> m=new HashMap<>();
        if(anno<2018&&anno>1989){
            return Yearstatic(Download.getAziende(),anno);//richiama il metodo di statistiche
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Insert a year between 1990 and 2017...");
    }

    @PostMapping("/FiltraValori")//stampa le statistiche su anni in base al valore inserito
    public Map<String,Object> retVal(@RequestBody FiltriNum body){
        if((body.value instanceof Integer)&&(body.field.equals("anno"))){ //se il valore è intero richiama il metodo di filtrinum relativo agli anni
            return FiltroAnno(body.operator,(Integer)body.value,Download.getAziende());
        }
        else{
            if((body.value instanceof Double)&&(body.field.equals("anno"))){//se il valore è double richiama il metodo di filtrinum relativo al campo
                return FiltroAnno(body.operator,(Double)body.value,Download.getAziende());
            }
            if(body.field.equals("anno")) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Insert the field anno and the year integer value or a double value...");//stampa un messaggio di errore a seconda dell'errore commesso
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Insert anno...");
        }
    }


    @PostMapping("/FiltraCampi")//filtro sui campi
    public Map<String,Object> retCamp(@RequestBody FiltriCampi body){
        if((body.field.equals("plants")||body.field.equals("operator")||body.field.equals("nrg_ball")||body.field.equals("siec")||body.field.equals("unit")||body.field.equals("geo"))!=true)//controllo che il campo inserito esista tra le possibilità
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Insert an appropiate field...");//in caso contrario viene stampato un messaggio di errore
        return FiltroCampo(body.field,body.operator,(String)body.value,Download.getAziende());//richiamo il metodo che filtra in filtricampi
    }




}
