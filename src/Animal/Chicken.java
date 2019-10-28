package Animal;

public class Chicken extends Pet implements KillForFood {
    int dayTodayResurs =4;
    public Chicken(String name, int health, int speed, int weight) {
        super(name, health, speed, weight);
        setDayTodayResurs(dayTodayResurs);
    }

    @Override
    public int killPet() {
        return 0;
    }
}
