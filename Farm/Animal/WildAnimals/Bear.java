package Animal.WildAnimals;

import Animal.WildAnimal;

public class Bear extends WildAnimal {
    public Bear(int weight, int speed, int damage) {
        super(weight, speed, damage);
        super.setName("медведь");
    }
}

