package com.joysistvi.day22.activity.activity;

public class Ballpen extends Pen {

    double pointSize;

    // parameterize constructor (special method)
    public Ballpen(String brand, String color, double pointSize, double weightInGrams) {
        super(brand, color, weightInGrams);
        this.pointSize = pointSize;
    }


    // Shortcut to generate Getter and Setter: press alt + insert

    public double getPointSize() {
        return pointSize;
    }

    public void setPointSize(double pointSize) {
        this.pointSize = pointSize;
    }
    @Override
    public void write(){
        System.out.println("Writing smoothly with a ballpen.");
    }
    // String representation of object
    @Override
    public String toString() {
        return "Type: " + getClass().getSimpleName() + "\n" +
                super.toString() + "\n" +
                "Point Size: " + pointSize;
    }
}
