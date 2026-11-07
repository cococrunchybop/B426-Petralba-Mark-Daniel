package com.joysistvi.day22.activity.encap;


public class Main {

    public static void main(String[] args) {

        Ballpen ballpen = new Ballpen("Pilot","Black",1.0,20.0);
        Highlighter highlighter = new Highlighter("HBW","Pink","Neon",50);
        GelPen gelPen = new GelPen("Panda","Blue",1.0,20.0);

        System.out.println("\n=== Ballpen ===");
        System.out.println(ballpen);
        ballpen.write();

        System.out.println("\n=== Highlighter ===");
        System.out.println(highlighter);
        highlighter.write();

        System.out.println("\n=== GelPen ===");
        System.out.println(gelPen);
        gelPen.write();


    }
}
