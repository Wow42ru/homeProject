package Animal.WildAnimals;

import Animal.Pet;
import Animal.WildAnimal;


public class Wolf extends WildAnimal {
    public Wolf(int weight, int speed, int damage) {
        super(weight, speed, damage);
    }


    public Pet hunt(Pet petWilBeEaten, boolean goAway, WildAnimal hauntinglyAnimal) {
        if (goAway) {
            double chance = Math.random();
            if (chance > 0.6) {
                petWilBeEaten.setHealth(petWilBeEaten.getHealth() - getDamage());
                if (petWilBeEaten.getHealth() <= 0) {
                    petWilBeEaten = null;
                }
            }
        } else canHuntingOrNot(hauntinglyAnimal);
        return petWilBeEaten;
    }
}
