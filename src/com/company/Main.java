package com.company;

public class Main {

    public static void main(String[] args) {

        Mapa<String,Integer> MojaMapa = new Mapa<String,Integer>();

        MojaMapa.put("A",8);
        MojaMapa.put("B",7);
        MojaMapa.put("C",8);
        MojaMapa.put("D",5);
        MojaMapa.put("E",9);
        MojaMapa.put("F",7);

        Mapa<String,Integer> DrugaMapa = new Mapa<String,Integer>();

        DrugaMapa.put("H",10);
        DrugaMapa.put("I",2);

        MojaMapa.putAll(DrugaMapa);

        System.out.println(MojaMapa.get("I"));
        System.out.println(MojaMapa.get("E"));
        

    }
}
