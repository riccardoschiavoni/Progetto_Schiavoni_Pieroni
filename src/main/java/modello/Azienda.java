package modello;
import java.lang.Override;
import java.util.List;


public class Azienda {
        private String plants;// (stabilimenti)
        private String operator;
        private String nrg_ball; //(tipi di materiale)
        private String siec;
        private String unit; //(unità di misura)
        private String geo;
        private List<Double> time;

        //costruttuore della classe "AziendaEnergetica
        public Azienda (String plants , String operator , String nrg_ball , String siec , String unit, String geo, List<Double> time){
            this.plants = plants;
            this.operator = operator;
            this.nrg_ball = nrg_ball;
            this.siec = siec;
            this.unit = unit;
            this.geo = geo;
            this.time=time;
        }

    // getters della classe "Azienda"
    public String get_plants (){
        return plants;
    }
    public String get_operator (){
        return operator;
    }
    public String get_nrg_ball (){
        return nrg_ball;
    }
    public String get_siec (){
        return siec;
    }
    public String get_unit (){
        return unit;
    }
    public String get_geo (){
        return geo;
    }
    public List<Double> get_time (){
        return time;
    }

    @Override
    public String toString(){
        return "plants: "+ get_plants() + "\noperator: " + get_operator() + "\nnrg_ball:" + get_nrg_ball() +"\nsiec:" + get_siec() + "\nget_unit: " + get_unit()  + "\nget_geo: " + get_geo();
    }


}
