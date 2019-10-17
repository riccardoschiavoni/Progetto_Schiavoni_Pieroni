package service;
import modello.Azienda;
import java.util.*;

public class FiltriNum extends Filtri{
    public Number value;
    public static Map<String,Object> FiltroAnno(String Operator,Integer value,List<Azienda> list) {
        Map<String, Object> map = new HashMap<>();
        int n=0;
        if (Filtri.ContrOP(Operator) != true||value<1990||value>2017) {
            map.put("l'operatore o l'anno considerato non è valido ", Operator+value);
            return map;
        }
        else switch (Operator) {//gestisce i vari casi di inserimento degli operatori
            case "$in":{
                Vector<Double> vet = new Vector();
                for (Azienda o : list) {
                    int i = 0;//contatore che serve per cambiare l'anno
                    for (Double o1 : o.get_time()) {
                        if (2017-i == value.intValue()) vet.add(o1);
                        i++;
                    }
                    map.put(o.toString(), vet.get(n));
                    n++;
                    }
                }
                return map;
            case "$not":
                for (Azienda o : list) {
                    int i = 0;
                    Vector<Double> vet = new Vector();
                    for (Double o1 : o.get_time()) {
                        if (2017 - i != value.intValue()) vet.add(o1);
                        i++;
                    }
                    map.put(o.toString(), vet);
                }
                return map;
            case "$gt":
                for (Azienda o : list) {
                    int i = 0;
                    Vector<Double> vet = new Vector();
                    for (Double o1 : o.get_time()) {
                        if (2017 - i > value.intValue()) vet.add(o1);
                        i++;
                    }
                    map.put(o.toString(), vet);
                }
                return map;
            case "$gte":
                for (Azienda o : list) {
                    int i = 0;
                    Vector<Double> vet = new Vector();
                    for (Double o1 : o.get_time()) {
                        if (2017 - i >= value.intValue()) vet.add(o1);
                        i++;
                    }
                    map.put(o.toString(), vet);
                }
                return map;
            case "$it":
                for (Azienda o : list) {
                    int i = 0;
                    Vector<Double> vet = new Vector();
                    for (Double o1 : o.get_time()) {
                        if (2017 - i < value.intValue()) vet.add(o1);
                        i++;
                    }
                    map.put(o.toString(), vet);
                }
                return map;
            case "$ite":
                for (Azienda o : list) {
                    int i = 0;
                    Vector<Double> vet = new Vector();
                    for (Double o1 : o.get_time()) {
                        if (2017 - i <= value.intValue()) vet.add(o1);
                        i++;
                    }
                    map.put(o.toString(), vet);
                }
                return map;
            default:return map;
        }
    }

    public static Map<String,Object> FiltroAnno(String Operator,Double value,List<Azienda> list) {//gestisce il filtro sui valori double dell'energia prodotta
        Map<String, Object> map = new HashMap<>();
        if (Filtri.ContrOP(Operator) != true) {
            map.put("l'operatore considerato non è valido ", Operator);
            return map;
        }
        else {switch (Operator) {
            case "$in":{
                Vector<Integer> anni = new Vector();
                boolean flag=false;
                boolean flad=false;
                for (Azienda o : list) {
                    int i=0;
                    for (Double o1 : o.get_time()) {
                        if (o1.doubleValue()==value.doubleValue()){
                            anni.add(2017-i);
                            flad=true;
                            flag=true;
                        }
                        if(flag) map.put(o.toString(), anni);
                        flag=false;
                        i++;
                    }
                }
                if(flad)return map;//flad mi dice se la mappa è piena cioè se esiste o meno il valore in input
                else {
                    map.put("non esiste il valore digitato " ,value);
                    return map;}}


            case "$gt":{
                Vector<Double> valori = new Vector();
                boolean flag=false;
                boolean flad=false;
                for (Azienda o : list) {
                    int i=0;
                    for (Double o1 : o.get_time()) {
                        if (o1.doubleValue()>value.doubleValue()){
                            valori.add(o1.doubleValue());
                            flad=true;
                            flag=true;
                        }
                        if(flag) map.put(o.toString()+"nell'anno"+(2017-i), valori);
                        flag=false;
                        i++;
                    }
                }
                if(flad)return map;
                else {
                    map.put("non esiste valore superiore al valore digitato digitato " ,value);
                    return map;}}

            case "$gte":{
                Vector<Double> valori = new Vector();
                boolean flag=false;
                boolean flad=false;
                for (Azienda o : list) {
                    int i=0;
                    for (Double o1 : o.get_time()) {
                        if (o1.doubleValue()>=value.doubleValue()){
                            valori.add(o1.doubleValue());
                            flad=true;
                            flag=true;
                        }
                        if(flag) map.put(o.toString()+"nell'anno"+(2017-i), valori);
                        flag=false;
                        i++;
                    }
                }
                if(flad)return map;
                else {
                    map.put("non esiste valore superiore o uguale al valore digitato digitato " ,value);
                    return map;}}
            case "$it":{
                Vector<Double> valori = new Vector();
                boolean flag=false;
                boolean flad=false;
                for (Azienda o : list) {
                    int i=0;
                    for (Double o1 : o.get_time()) {
                        if (o1.doubleValue()<value.doubleValue()){
                            valori.add(o1.doubleValue());
                            flad=true;
                            flag=true;
                        }
                        if(flag) map.put(o.toString()+"nell'anno"+(2017-i), valori);
                        flag=false;
                        i++;
                    }
                }
                if(flad)return map;
                else {
                    map.put("non esiste valore minore al valore digitato digitato " ,value);
                    return map;}}
            case "$ite":{
                Vector<Double> valori = new Vector();
                boolean flag=false;
                boolean flad=false;
                for (Azienda o : list) {
                    int i=0;
                    for (Double o1 : o.get_time()) {
                        if (o1.doubleValue()<=value.doubleValue()){
                            valori.add(o1.doubleValue());
                            flad=true;
                            flag=true;
                        }
                        if(flag) map.put(o.toString()+"nell'anno"+(2017-i), valori);
                        flag=false;
                        i++;
                    }
                }
                if(flad)return map;
                else {
                    map.put("non esiste valore minore o uguale al valore digitato digitato " ,value);
                    return map;}}
            default:return map;
        }}

    }
}
