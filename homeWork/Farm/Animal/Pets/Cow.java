package Farm.Animal.Pets;

import Farm.Animal.Pet;

public class Cow extends Pet {
    private static int dayTodayResources = 3;

    public Cow(String name, int health, int speed, int weight) {
        super(name, health, speed, weight);
        setDayTodayresources(dayTodayResources);
    }

}
