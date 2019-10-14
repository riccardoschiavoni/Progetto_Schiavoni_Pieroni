package service;

import modello.Azienda;

import java.util.*;

public class FiltriCampi extends Filtri {
    public String value;

    public static Map<String,Object> FiltroCampo(String field,String operator,String value,List<Azienda> list){
        Map<String,Object> map=new HashMap<>();
        if (Filtri.ContrOP(operator) != true) {
            map.put("l'operatore considerato non è valido ", operator);
            return map;
        }
        switch(field){
            case "plants":
                switch (operator){
                    case "$in":{
                        Map<String,Object> ret=new HashMap<>();
                        for (Azienda o : list) {
                            if(o.get_plants().equals(value)) ret.put(o.toString(),o.get_time());
                        }
                        return map;}
                    case "$nin":{
                         Map<String,Object> ret=new HashMap<>();
                        for (Azienda o : list) {
                            if((o.get_plants().equals(value))!=true) ret.put(o.toString(),o.get_time());
                        }
                        return map;}

                }
            case "operator":
                switch (operator){
                    case "$in":{
                        Map<String,Object> ret=new HashMap<>();
                        for (Azienda o : list) {
                            if(o.get_operator().equals(value)) ret.put(o.toString(),o.get_time());
                        }
                        return map;}
                    case "$nin":{
                        Map<String,Object> ret=new HashMap<>();
                        for (Azienda o : list) {
                            if((o.get_operator().equals(value))!=true) ret.put(o.toString(),o.get_time());
                        }
                        return map;}

                }
            case "nrg_ball":
                switch (operator){
                    case "$in":{
                        Map<String,Object> ret=new HashMap<>();
                        for (Azienda o : list) {
                            if(o.get_nrg_ball().equals(value)) ret.put(o.toString(),o.get_time());
                        }
                        return map;}
                    case "$nin":{
                        Map<String,Object> ret=new HashMap<>();
                        for (Azienda o : list) {
                            if((o.get_nrg_ball().equals(value))!=true) ret.put(o.toString(),o.get_time());
                        }
                        return map;}

                }
            case "siec":
                switch (operator){
                    case "$in":{
                        Map<String,Object> ret=new HashMap<>();
                        for (Azienda o : list) {
                            if(o.get_siec().equals(value)) ret.put(o.toString(),o.get_time());
                        }
                        return map;}
                    case "$nin":{
                        Map<String,Object> ret=new HashMap<>();
                        for (Azienda o : list) {
                            if((o.get_siec().equals(value))!=true) ret.put(o.toString(),o.get_time());
                        }
                        return map;}

                }
            case "unit":
                switch (operator){
                    case "$in":{
                        Map<String,Object> ret=new HashMap<>();
                        for (Azienda o : list) {
                            if(o.get_unit().equals(value)) ret.put(o.toString(),o.get_time());
                        }
                        return map;}
                    case "$nin":{
                        Map<String,Object> ret=new HashMap<>();
                        for (Azienda o : list) {
                            if((o.get_unit().equals(value))!=true) ret.put(o.toString(),o.get_time());
                        }
                        return map;}

                }
            case "geo":
                switch (operator){
                    case "$in":{
                        Map<String,Object> ret=new HashMap<>();
                        for (Azienda o : list) {
                            if(o.get_geo().equals(value)) ret.put(o.toString(),o.get_time());
                        }
                        return map;}
                    case "$nin":{
                        Map<String,Object> ret=new HashMap<>();
                        for (Azienda o : list) {
                            if((o.get_geo().equals(value))!=true) ret.put(o.toString(),o.get_time());
                        }
                        return map;}
                    default:return map;
                }
            default:return map;
        }


    }
}
