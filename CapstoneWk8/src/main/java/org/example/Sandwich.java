package org.example;

import java.util.ArrayList;
import java.util.List;

public class Sandwich implements PriceInterface {
    private BreadType breadType;
    private Size size;
    private List<Topping> toppings = new ArrayList<>();
    private boolean isToasted;

    public Sandwich(BreadType breadType, Size size, boolean isToasted) {
        this.breadType = breadType;
        this.size = size;
        this.isToasted = isToasted;
    }

    public BreadType getBreadType() {
        return breadType;
    }
    public Size getSize() {
        return size;
    }
    public List<Topping> getToppings() {
        return toppings;
    }
    public boolean isToasted() {
        return isToasted;
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    @Override
    public double calculatePrice() {
        double price = getBasePrice();

        for (Topping topping : toppings) {
            if (topping instanceof PremiumTopping premium) {
                price += getPremiumToppingPrice(premium);
            }
            // Regular toppings are free
        }
        return price;
    }

    private double getBasePrice() {
        switch (size) {
            case FOUR_INCH:
                return 5.50;
            case EIGHT_INCH:
                return 7.00;
            case TWELVE_INCH:
                return 8.50;
            default:
                throw new IllegalArgumentException("Unknown size: " + size);
        }

    }

    private double getPremiumToppingPrice(PremiumTopping topping) {
        double basePrice;

        switch (topping.getType()) {
            case MEAT:
                switch (size) {
                    case FOUR_INCH:
                        basePrice = 1.00;
                        break;
                    case EIGHT_INCH:
                        basePrice = 2.00;
                        break;
                    case TWELVE_INCH:
                        basePrice = 3.00;
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown size: " + size);
                }
                break;
            case CHEESE:
                switch (size) {
                    case FOUR_INCH:
                        basePrice = 0.75;
                        break;
                    case EIGHT_INCH:
                        basePrice = 1.50;
                        break;
                    case TWELVE_INCH:
                        basePrice = 2.25;
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown size: " + size);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown topping type: " + topping.getType());
        }

        if (topping.isExtra()) {
            switch (topping.getType()) {
                case MEAT:
                    switch (size) {
                        case FOUR_INCH:
                            basePrice += 0.50;
                            break;
                        case EIGHT_INCH:
                            basePrice += 1.00;
                            break;
                        case TWELVE_INCH:
                            basePrice += 1.50;
                            break;
                    }
                    break;
                case CHEESE:
                    switch (size) {
                        case FOUR_INCH:
                            basePrice += 0.30;
                            break;
                        case EIGHT_INCH:
                            basePrice += 0.60;
                            break;
                        case TWELVE_INCH:
                            basePrice += 0.90;
                            break;
                    }
                    break;
            }
        }

        return basePrice;
    }
}
