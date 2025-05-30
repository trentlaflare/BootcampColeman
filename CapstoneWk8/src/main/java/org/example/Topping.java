package org.example;

public abstract class Topping  {
    private String name;
    private boolean isExtra;

    public Topping(String name, boolean isExtra) {
        this.name = name;
        this.isExtra = isExtra;
    }


    public String getName() {
        return name;
    }
    public boolean isExtra() {
        return isExtra;
    }
}

