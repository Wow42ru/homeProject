package Animal.Pets;

import Animal.Pet;

public class Chicken extends Pet {
    int dayTodayResources = 4;

    public Chicken(String name, int health, int speed, int weight) {
        super(name, health, speed, weight);
        setDayTodayresources(dayTodayResources);
    }

}