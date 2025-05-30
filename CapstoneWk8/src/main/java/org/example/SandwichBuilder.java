package org.example;

import java.util.ArrayList;
import java.util.List;

public class SandwichBuilder {
    private Sandwich currentSandwich;
    private List<PremiumTopping> availableMeats = new ArrayList<>();
    private List<PremiumTopping> availableCheeses = new ArrayList<>();
    private List<RegularTopping> availableRegularToppings = new ArrayList<>();

    public SandwichBuilder() {
        initializeAvailableToppings();
    }

    public void startNewSandwich(BreadType breadType, Size size, boolean isToasted) {
        this.currentSandwich = new Sandwich(breadType, size, isToasted);
    }

    public void addTopping(Topping topping) {
        if (currentSandwich != null) {
            currentSandwich.addTopping(topping);
        }
    }

    public Sandwich getCurrentSandwich() {
        return currentSandwich;
    }

    private void initializeAvailableToppings() {
        // Meats
        availableMeats.add(new PremiumTopping("Steak", false, ToppingType.MEAT));
        availableMeats.add(new PremiumTopping("Ham", false, ToppingType.MEAT));
        availableMeats.add(new PremiumTopping("Salami", false, ToppingType.MEAT));
        availableMeats.add(new PremiumTopping("Roast Beef", false, ToppingType.MEAT));
        availableMeats.add(new PremiumTopping("Chicken", false, ToppingType.MEAT));
        availableMeats.add(new PremiumTopping("Bacon", false, ToppingType.MEAT));

        // Cheeses
        availableCheeses.add(new PremiumTopping("American", false, ToppingType.CHEESE));
        availableCheeses.add(new PremiumTopping("Provolone", false, ToppingType.CHEESE));
        availableCheeses.add(new PremiumTopping("Cheddar", false, ToppingType.CHEESE));
        availableCheeses.add(new PremiumTopping("Swiss", false, ToppingType.CHEESE));

        // Regular toppings
        availableRegularToppings.add(new RegularTopping("Lettuce", false));
        availableRegularToppings.add(new RegularTopping("Peppers", false));
        availableRegularToppings.add(new RegularTopping("Onions", false));
        availableRegularToppings.add(new RegularTopping("Tomatoes", false));
        availableRegularToppings.add(new RegularTopping("Jalapenos", false));
        availableRegularToppings.add(new RegularTopping("Cucumbers", false));
        availableRegularToppings.add(new RegularTopping("Pickles", false));
        availableRegularToppings.add(new RegularTopping("Guacamole", false));
        availableRegularToppings.add(new RegularTopping("Mushrooms", false));
        availableRegularToppings.add(new RegularTopping("Mayo", false));
        availableRegularToppings.add(new RegularTopping("Mustard", false));
        availableRegularToppings.add(new RegularTopping("Ketchup", false));
        availableRegularToppings.add(new RegularTopping("Ranch", false));
        availableRegularToppings.add(new RegularTopping("Thousand Island", false));
        availableRegularToppings.add(new RegularTopping("Vinaigrette", false));
        availableRegularToppings.add(new RegularTopping("Aju", false));


    }

    public List<PremiumTopping> getAvailableMeats() {
        return availableMeats;
    }
    public List<PremiumTopping> getAvailableCheeses() {
        return availableCheeses;
    }
    public List<RegularTopping> getAvailableRegularToppings() {
        return availableRegularToppings;
    }
}
