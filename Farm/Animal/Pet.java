package Animal;

public abstract class Pet extends Animal  {
    private String name;
    private int health;
    private int resources ;
    private int dayTodayResources  = 0;

    public void setDayTodayresources (int dayTodayResources ) {
        this.dayTodayResources  = dayTodayResources ;
    }

    public int getDayTodayResources () {
        return dayTodayResources ;
    }

    @Override
    public String toString() {
        return "Pets{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", resurs=" + resources  +
                ", dayTodayResurs=" + dayTodayResources  +
                '}';
    }

    public Pet(String name, int health, int speed, int weight) {
        super(speed, weight);
        setName(name);
        setResources(health);
        setHealth(health);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getResources() {
        return resources ;
    }

    private void setResources(int resources) {
        this.resources  = resources;
    }

}
