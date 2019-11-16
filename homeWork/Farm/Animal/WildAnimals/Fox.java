package Farm.Animal.WildAnimals;

import Farm.Animal.WildAnimal;

public class Fox extends WildAnimal implements Hunting {

    public Fox(int weight, int speed, int damage) {
        super(weight, speed, damage);
        super.setName("лиса");
    }
}

