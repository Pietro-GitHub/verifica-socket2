package com.example;

public class MainClient {
    public static void main(String args[]){
        Client cliente = new Client();
        cliente.connetti();
        cliente.comunica();
    }
}
