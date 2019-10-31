package Animal.WildAnimals;

import Animal.Pet;
import Animal.WildAnimal;

public class Bear extends WildAnimal {
    public Bear(int weight, int speed, int damage) {
        super(weight, speed, damage);
    }


    @Override
    public Pet hunt(Pet petWilBeEaten, boolean goAway, WildAnimal HauntinglyAnimal) {
        if (goAway) {
            double chance = Math.random();
            if (chance > 0.6) {
                petWilBeEaten.setHealth(petWilBeEaten.getHealth() - getDamage());
                if (petWilBeEaten.getHealth() <= 0) {
                    petWilBeEaten = null;
                }
            }
        } else howMuchRunAway++;
        return petWilBeEaten;
    }
}

