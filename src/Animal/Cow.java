package Animal;

public class Cow extends Pet implements KillForFood {
    private static int dayTodayResurs = 3;

    public Cow(String name, int health, int speed, int weight) {
        super(name, health, speed, weight);
        setDayTodayResurs(dayTodayResurs);
    }

    @Override
    public int killPet() {
        return 0;
    }
}
