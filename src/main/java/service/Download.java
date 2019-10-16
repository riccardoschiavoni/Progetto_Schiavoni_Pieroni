package service;
import java.io.*;  //importo tutti i metodi della classe io che mi serviranno in seguito
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import modello.Azienda;
import org.json.simple.JSONArray;       //importazione classi per la gestione json
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

//inizio sviluppo classe che permette il download del file tsv e il relativo parsing

public class Download {
    private static List<Azienda> Aziende;

    public Download() throws IOException {
        String name_file = "dataset.tsv"; //do il nome al dataset
        if(Files.exists(Paths.get(name_file))) //il metodo exist mi controlla che il file non sia già presente nella cartella poichè in quel caso non dovrei fare il download
        Aziende=Parsing(name_file);                //effettuo il parsing sul file
        else{
        try {
            String url = "http://data.europa.eu/euodp/data/api/3/action/package_show?id=KEQ1ryI277JtmzoN6ThqQ"; //metodi relativi al download: definisco l'url di ingresso
            URLConnection openConnection = new URL(url).openConnection();//creo l'oggetto della connessione
            openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");//aggiunge proprietà di apertura connessione del link
            InputStream in = openConnection.getInputStream();//accesso ai contenuti dell'url
            try {
                StringBuilder data = new StringBuilder();
                String line = "";
                try {
                    InputStreamReader inR = new InputStreamReader(in);
                    BufferedReader buf = new BufferedReader(inR);
                    while ((line = buf.readLine()) != null)
                        data.append(line);//al json basta una stringa poichè non ha ritorni a capo. uso append perchè line è di tipo stringbuilder

                } finally {   //istruzioni vengono sempre eseguite ed usate per chiudere flussi
                    in.close();
                }


                // lettura del file che ora deve essere importato come oggetto
                JSONObject obj = (JSONObject) JSONValue.parseWithException(String.valueOf(data));    //creazione dell'oggetto jason(collezione di 0 più coppie nome valore). data ha dentro i dati letti
                JSONObject objI = (JSONObject) (obj.get("result"));                  //get prendi i valori associati alla chiave result e resources
                JSONArray objA = (JSONArray) (objI.get("resources"));                //jasonarray è una collezione ordinata di valori


                for (Object o : objA) {
                    if (o instanceof JSONObject) {
                        JSONObject o1 = (JSONObject) o;
                        String format = (String) o1.get("format");                    //ottiene il valore della chiave format
                        String urlD = (String) o1.get("url");                         //ottiene il valore della chiave url
                        if (format.equals("http://publications.europa.eu/resource/authority/file-type/TSV")) {
                            download(urlD, name_file); //richieamo il metodo download
                        }
                    }
                }
                System.out.println("download file tsv eseguito");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Aziende=Parsing(name_file);
        //new Metadati(name_file);
    }}

    public static List<Azienda> getAziende() {
        return Aziende;
    }

    public static void download(String url, String name_file) throws Exception { //effettua il download traformando l'html in file tsv
        HttpURLConnection openConnection = (HttpURLConnection) new URL(url).openConnection();
        openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
        InputStream in = openConnection.getInputStream();
        String data = "";
        String line = "";
        try {
            if (openConnection.getResponseCode() >= 300 && openConnection.getResponseCode() < 400) {
                download(openConnection.getHeaderField("Location"), name_file);
                in.close();
                openConnection.disconnect();
                return;
            }
            Files.copy(in, Paths.get(name_file));//  copia il file letto in apertura(in) in Paths.get(fileName):questo metodo richiama la cartella del progetto e copia il file con il nome filename
        } finally {
            in.close();
        }
    }    //toURL crea un url partendo da un uri
    //url è un puntatore a una pagina e openstream apre la connessione all'url e permette la lettura


    public List<Azienda> Parsing(String name_file) throws IOException { //fa il parsing del file e riceve in input il nome del file
        final String TAB_DELIMITER = "\t" ; //essendo un file tsv abbiamo come delimitazione il carattere tab \t
        List<Azienda> Aziende = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(name_file));
        try  {
            String line;
            br.readLine();//non leggo la prima linea di comando che non contiene informazioni
            while ((line = br.readLine()) != null) {
                line=line.replace(",",TAB_DELIMITER);//sostituzione della virgola con \t
                line=line.replace(":","0");//le informazioni non valide vengono sostituite con 0
                String[] values = line.split(TAB_DELIMITER);
                int i;                                      //inizializzo una variabile contatore per gli elementi double del file
                List<Double> app=new ArrayList<>();
                for(i=6;i<values.length;i++){
                    app.add((Double.parseDouble(values[i]))); //creo la lista di double
                }
                Aziende.add(new Azienda (values[0], values[1], values[2], values[3], values[4], values[5], app));//richiamo il costruttore di Azienda


            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            br.close();
        }

        return Aziende;
    }
}