package service;

public class Filtri {
        public String field;
        public String operator;
        public static boolean ContrOP(String operator){
            if((operator.equals("$not"))||(operator.equals("$in"))||(operator.equals("$nin"))||(operator.equals("$gt"))||(operator.equals("$gte"))||(operator.equals("$it"))||(operator.equals("$ite")))return true;
            else return false;
        }

}
