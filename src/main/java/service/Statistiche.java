package service;
import java.util.*;
import java.util.ArrayList;

import modello.Azienda;

public abstract class Statistiche {
    public static double avg(List<Double> listaVal) {
        return sum(listaVal) / listaVal.size();
    }

    public static Double min(List<Double> listaVal) {
        double min;
        min = (double) listaVal.get(0);
        for (double n : listaVal) {
            if (n < min) min = n;
        }
        return min;
    }

    public static double max(List<Double> listaVal) {
        double max;
        max = (double) listaVal.get(0);
        for (double n : listaVal) {
            if (n > max) max = n;
        }
        return max;
    }

    public static double dev_std(List<Double> listaVal) {
        double avg = avg(listaVal);
        double var = 0;
        for (double n : listaVal) {
            var += (Math.pow(n - avg, 2) / 18);
        }
        return Math.sqrt(var);
    }

    public static Double sum(List<Double> listaVal) {
        double somma = 0;
        for (double n : listaVal) {
            somma += n;
        }
        return somma;
    }

    public static double count(List<Double> listaVal) {
        return listaVal.size();
    }


    public static Map<List<Double>,Object> returnstat(List<Azienda> lista,String Campo){
        Map<List<Double>,Object> map=new HashMap<>();
        int i=0;
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
                map.put(vet, "somma deviazione standard, massimo, minimo, media, lunghezza lista "+i+",numero di occorrenze del valore "+Campo);
                i++;
            }
        }

        return map;
    }


    public static Map<Object,Object> Yearstatic(List<Azienda> lista,Integer anno){
        int i=0;
        Map<Object,Object> ret=new HashMap<>();
        List<Double> app=new ArrayList<>();
        for(Azienda o: lista){
            for(Double o1:o.get_time()){
                if(2017-i==anno) app.add(o1);
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










