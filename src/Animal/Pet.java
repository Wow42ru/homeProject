package Animal;

public abstract class Pet extends Animal implements KillForFood {
    private String name;
    private int health;
    private int resurs;
    private int dayTodayResurs = 0;

    public void setDayTodayResurs(int dayTodayResurs) {
        this.dayTodayResurs = dayTodayResurs;
    }

    public int getDayTodayResurs() {
        return dayTodayResurs;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", resurs=" + resurs +
                ", dayTodayResurs=" + dayTodayResurs +
                '}';
    }

    public Pet(String name, int health, int speed, int weight) {
        super(speed, weight);
        setName(name);
        setResurs(health);
        setHealth(health);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getResurs() {
        return resurs;
    }

    public void setResurs(int resurs) {
        this.resurs = resurs;
    }


}
