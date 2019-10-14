package service;

public class Filtri {
        public String field;
        public String operator;
        public static boolean ContrOP(String operator){
            if((operator=="$not")||(operator=="$in")||(operator=="$nin")||(operator=="$gt")||(operator=="$gte")||(operator=="$it")||(operator=="$ite"))return true;
            else return false;
        }

}
