package service;

import modello.Azienda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Metadati {
    private static List<Map> lista = new ArrayList();
    public Metadati(String name_file) throws IOException {
        final String TAB_DELIMITER = "\t";
        int i = 0;
        Field[] fields = Azienda.class.getDeclaredFields();
        BufferedReader br = new BufferedReader(new FileReader(name_file));
        String line = br.readLine();
        line= line.replace("," , TAB_DELIMITER);
        line= line.replace("\\" , TAB_DELIMITER);
        String [] orderedline = line.trim().split("\t"); //.trim rimuove gli spazi bianchi da entrambe le estremità di una stringa
        for (Field f : fields) { //scorro tutti i campi
            Map<String, String> map = new HashMap<>();
            map.put("alias",f.getName());
            map.put("SourcedFile", orderedline[i]);
            map.put("Type", f.getType().getSimpleName());
            lista.add(map);
            i++;
        }
    }
    public static List<Map> getMetadata() {
        return lista;
    }
}
