package org.example;

public class PremiumTopping extends Topping {
    private ToppingType type;

    public PremiumTopping(String name, boolean isExtra, ToppingType type) {
        super(name, isExtra);
        this.type = type;
    }

    public ToppingType getType() {
        return type; }


    }

