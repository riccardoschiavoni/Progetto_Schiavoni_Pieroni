# Progetto_Schiavoni_Pieroni
   
Il progetto in esame si occupa della gestione di un dataset in formato tsv attraverso il salvataggio di questo 
in una classe modellante ben definita di cui verrà esaurientemente discusso in seguito. Il programma interagirà poi con l'utente mediante un server web locale avviato da un framework appositamente pensato per java di nome Spring. In tal modo l'utente potrà effettuare richieste GET e POST: con get come verrà mostrato in seguito l'utente dovrà inserire appositi parametri messi a disposizione proprio dal programma sotto opportune tracce. Allo stesso modo lavorerà POST solo che invece di aspettare parametri dovrà 
essere inserito un body. A prescindere dalle richieste verranno restituiti dati sotto forma di jason cioè descritti da due campi:chiave e valore.
Il tsv preso in esame descrive la produzione di energia da diferse fonti energetiche in diverse regioni europee nel corso di 17 anni:a partire dal 1990 fino al 2017.


##STRUTTURA

Il nucleo del progetto è diviso in 4 packages:
	-c.project.progOgg:dentro troviamo la classe ProgOggApplication che ha al suo interno il main()
	-controller:
		-controllo:è la classe che gestisce i filtri e le statistiche:gestisce cioè le interazione con l'utente
	-modello:
		-Azienda:è il modello che descrive la situazione precedentemente esposta attraverso 7 attributi:6 stringhe che desccrivonoil                      plants, l'operator, il bilancio energetico(ciò che viene prodotto),il sinc(cioè da cosa deriva la produzione),
               la unit(unità di       misura) e geo(la località a livello europeo). Come ultima istanza che descrive il modello adottato                prevede una lista di double che presenta la produzione dagli anni 1990 al 2017.
	-service:
		-Download:contiene la parte relativa al download del file TSV e al parsing del file 
		-Filtri assieme a FiltriCampi e FiltriNum:gestiscono i filtri del dataset
		-Statistiche:riportano le statistiche dei vari campi


##FUNZIONAMENTO

Il programma ad avvio esegue il download del file .tsv controllando prima che esso non sia già esistenza nella cartella di destinazione.
dopodichè esegue il parsing del file:va a creare cioè una lista di oggetti della classe Azienda che ha come valore degli attributi
i singoli campi del file; a quel punto aspetta le interazioni con l'utente. Egli può attraverso l'utilizzo del framework Spring sul 
server web locale sulla porta 8080 tramite richieste GET e POST:
GET:
	-/cercaValori:ritorna le statistiche di una data azienda su tutti gli anni
	-/statisticheAnnuali:ritorna le statistiche su un dato anno di tutte le aziende
	-/operatori:restituisce gli operatori logici e condizionali che si possono usare 
	-/data:stampa il dataset in formato json 
	-/anni:stampa gli anni presi in considerazione
	-/campi:stampa i campi che possono essere inseriti
	-/metadata:stampa i metadata 
POST:
	-/FiltraValori:in base all'operatore inserito e al valore numerico inserito restituise la lista filtrata:
		con valore intero verranno effettuati filtri sugli anni, mentre con valore double verranno effettuali filtri sui valori
	-/FiltraCampi:in base all'operatore inserito e al valore del campo inserito restituisce la lista filtrata
N.B. Entrambe le richieste POST ricevono in input un body in formato JSON ove le chiavi sono field operator e value
