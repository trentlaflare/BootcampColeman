package org.example;

public class Drink implements PriceInterface {
    private DrinkSize size;

    public Drink(DrinkSize size) {
        this.size = size;
    }

    public DrinkSize getSize() { return size; }

    @Override
    public double calculatePrice() {
        switch (size) {
            case SMALL:
                return 2.00;
            case MEDIUM:
                return 2.50;
            case LARGE:
                return 3.00;
            default:
                throw new IllegalArgumentException("Unknown drink size: " + size);
        }
    }
}
