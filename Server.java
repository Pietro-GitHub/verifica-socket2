package com.example;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public static ArrayList<String> lista = new ArrayList<>();
    
    public Socket attendi(){
        try{
            System.out.println("1 SERVER partito in esecuzione ...");
            //creo un server sulla porta 6789
            server = new ServerSocket(6789);
            //rimane in attesa di un client
            client = server.accept();
            //chiudo il server per inibire altri client
            server.close();
            //associo due oggetti al socket del client per effettuare la srittura e la lettura
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server !");
            System.exit(1);
        }
        return client;
    }

    public void comunica(){
        try{
            stringaRicevuta = inDalClient.readLine();
            System.out.println(stringaRicevuta);
            //outVersoClient.writeBytes("3 Inserisci la nota da memorizzare o digita LISTA per visualizzare le note salvate");
            do{
                //rimango in attesa della riga trasmessa dal cient
                stringaRicevuta = inDalClient.readLine();
                System.out.println("6 ricevuta la stringa dal cient : " + stringaRicevuta);

                outVersoClient.writeBytes("Nota salvata\n");

                lista.add(stringaRicevuta.toUpperCase());

                if(stringaRicevuta == "LISTA"){
                    for(int i = 0; i < lista.size(); i++){
                        outVersoClient.writeBytes(lista.get(i));
                    }
                }

                
            }while(stringaRicevuta != "EXIT");

            //termina elaborazione sul server : chiudo la connessione del server
            System.out.println("9 SERVER: fine elaborazione ... buona notte!");
            client.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col server!");
            System.exit(1);
        }
    }
}
