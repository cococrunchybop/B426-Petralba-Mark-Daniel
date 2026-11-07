package com.joysistvi.day22.activity.encap;

public class Highlighter extends Pen {

    String inkType;

    // parameterize constructor (special method)
    public Highlighter(String brand, String color, String inkType, double weightInGrams) {
        super(brand, color, weightInGrams);
        this.inkType = inkType;
    }


    // Shortcut to generate Getter and Setter: press alt + insert

    public String getInkType() {
        return inkType;
    }

    public void setInkType(String inkType) {
        this.inkType = inkType;
    }
    @Override
    public void write(){
        System.out.println("Writing smoothly with a ballpen.");
    }
    @Override
    public String toString() {
        return "Type: Highlighter\n" +
                super.toString() + "\n" +
                "Ink Type: " + inkType;
    }
}
