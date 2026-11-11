package com.joysistvi.day24.abstraction.activity;

public class Ballpen extends Pen implements Refillable, Clickable {

    double pointSize;

    public Ballpen(String brand, String color, double pointSize, double weightInGrams) {
        super(brand, color, weightInGrams);
        this.pointSize = pointSize;
    }


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
    @Override
    public void refill(){
        System.out.println("Replacing the ballpen ink refill.");
    }
    @Override
    public void click(){
        System.out.println(CLICK_SOUND + " The ballpen tip comes out.");
    }
    // String representation of object
    @Override
    public String toString() {
        return "Type: " + getClass().getSimpleName() + "\n" +
                super.toString() + "\n" +
                "Point Size: " + pointSize;
    }
}
