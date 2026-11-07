package com.joysistvi.day22.activity.activity;

public class GelPen extends Ballpen{

    boolean waterproof=true;

    public GelPen(String brand, String color, double pointSize, double weightInGrams){
        super(brand, color, pointSize, weightInGrams);
    }

    public boolean isWaterproof() {
        return waterproof;
    }

    public void setWaterproof(boolean waterproof) {
        this.waterproof = waterproof;
    }
    @Override
    public void write(){
        System.out.println("Writing smoothly with a GelPen.");
    }
    @Override
    public String toString() {
        return super.toString() + '\n'+
                "Waterproof: " + (waterproof ? "Yes" : "No");
    }
}
