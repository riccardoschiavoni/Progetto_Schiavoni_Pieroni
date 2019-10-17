package service;
import java.util.*;
import java.util.ArrayList;

import modello.Azienda;

public abstract class Statistiche {
    public static double avg(List<Double> listaVal) {//metodo per il calcolo della media
        return sum(listaVal) / listaVal.size();
    }

    public static double min(List<Double> listaVal) {
        double min;
        min = (double) listaVal.get(0);
        for (double n : listaVal) {
            if (n < min) min = n;
        }
        return min;
    }//metodo per il calcolo del minimo

    public static double max(List<Double> listaVal) {
        double max;
        max = (double) listaVal.get(0);
        for (double n : listaVal) {
            if (n > max) max = n;
        }
        return max;
    }//metodo per il calcolo del massimo

    public static double dev_std(List<Double> listaVal) {
        double avg = avg(listaVal);
        double var = 0;
        for (double n : listaVal) {
            var += (Math.pow(n - avg, 2) / 18);
        }
        return Math.sqrt(var);
    }//metodo per il calcolo della deviazione standard

    public static double sum(List<Double> listaVal) {
        double somma = 0;
        for (double n : listaVal) {
            somma += n;
        }
        return somma;
    }//metodo per il calcolo della somma

    public static double count(List<Double> listaVal) {
        return listaVal.size();
    }//metodo per il calcolo della lunghezza della lista


    public static Map<List<Double>,Object> returnstat(List<Azienda> lista,String Campo){ //ritorna le statistiche relative ad un campo
        Map<List<Double>,Object> map=new HashMap<>();
        int i=0;                                                                        //inizializzo un contatore servirà per differenziare le keys delle mappe
        for(Azienda o1: lista){
            if(o1.get_plants ().equals(Campo)||o1.get_operator().equals(Campo)||o1.get_nrg_ball().equals(Campo)||o1.get_siec ().equals(Campo)||o1.get_unit().equals(Campo)||o1.get_geo().equals(Campo)) {
                List<Double> vet = new ArrayList<>();
                vet.add(sum(o1.get_time()));
                vet.add(dev_std(o1.get_time()));
                vet.add(max(o1.get_time()));
                vet.add(min(o1.get_time()));
                vet.add(avg(o1.get_time()));
                vet.add(count(o1.get_time()));
                vet.add((double) i);
                map.put(o1.get_time(), "valori per i diversi anni "+i);
                map.put(vet, "somma deviazione standard, massimo, minimo, media, lunghezza lista ,numero di occorrenze del valore "+Campo+ "numero"+i);
                i++;
            }
        }

        return map;
    }


    public static Map<Object,Object> Yearstatic(List<Azienda> lista,Integer anno){
        Map<Object,Object> ret=new HashMap<>();
        List<Double> app=new ArrayList<>();

        for(Azienda o: lista){
            int i=0;
            for(Double o1:o.get_time()){
                int ref=anno.intValue();
                if((2017-i)==ref) app.add(o1);
                i++;
            }
        }
        ret.put("somma",sum(app));
        ret.put("deviazione standard",dev_std(app));
        ret.put("massimo",max(app));
        ret.put("minimo",min(app));
        ret.put("lunghezza lista",count(app));
        ret.put("media",avg(app));
        return ret;
    }
}










