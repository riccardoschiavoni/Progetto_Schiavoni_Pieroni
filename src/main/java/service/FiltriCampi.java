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
        else{
        switch(field){
            case "plants":
                switch (operator){
                    case "$in":{
                        for (Azienda o : list) {
                            if(o.get_plants().equals(value)) map.put(o.toString(),o.get_time());
                        }
                        return map;}
                    case "$nin":{
                        for (Azienda o : list) {
                            if((o.get_plants().equals(value))!=true) map.put(o.toString(),o.get_time());
                        }
                        return map;}

                }
            case "operator":
                switch (operator){
                    case "$in":{
                        for (Azienda o : list) {
                            if(o.get_operator().equals(value)) map.put(o.toString(),o.get_time());
                        }
                        return map;}
                    case "$nin":{
                        for (Azienda o : list) {
                            if((o.get_operator().equals(value))!=true) map.put(o.toString(),o.get_time());
                        }
                        return map;}

                }
            case "nrg_ball":
                switch (operator){
                    case "$in":{
                        for (Azienda o : list) {
                            if(o.get_nrg_ball().equals(value)) map.put(o.toString(),o.get_time());
                        }
                        return map;}
                    case "$nin":{
                        for (Azienda o : list) {
                            if((o.get_nrg_ball().equals(value))!=true) map.put(o.toString(),o.get_time());
                        }
                        return map;}

                }
            case "siec":
                switch (operator){
                    case "$in":{
                        for (Azienda o : list) {
                            if(o.get_siec().equals(value)) map.put(o.toString(),o.get_time());
                        }
                        return map;}
                    case "$nin":{
                        for (Azienda o : list) {
                            if((o.get_siec().equals(value))!=true) map.put(o.toString(),o.get_time());
                        }
                        return map;}

                }
            case "unit":
                switch (operator){
                    case "$in":{
                        for (Azienda o : list) {
                            if(o.get_unit().equals(value)) map.put(o.toString(),o.get_time());
                        }
                        return map;}
                    case "$nin":{
                        for (Azienda o : list) {
                            if((o.get_unit().equals(value))!=true) map.put(o.toString(),o.get_time());
                        }
                        return map;}

                }
            case "geo":
                switch (operator){
                    case "$in":{
                        for (Azienda o : list) {
                            if(o.get_geo().equals(value)) map.put(o.toString(),o.get_time());
                        }
                        return map;}
                    case "$nin":{
                        for (Azienda o : list) {
                            if((o.get_geo().equals(value))!=true) map.put(o.toString(),o.get_time());
                        }
                        return map;}
                }
            default:return map;
        }


    }
}}
