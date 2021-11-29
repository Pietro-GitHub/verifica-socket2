package com.example;

public class MainServer {
    public static void main(String args[]){
        Server servente = new Server();
        servente.attendi();
        servente.comunica();
    }
}
