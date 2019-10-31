package Animal;

public abstract class Pet extends Animal  {

    private int health;
    private int resources ;
    private int dayTodayResources  = 0;
    final  int  maxHealth;

    public void setDayTodayresources (int dayTodayResources ) {
        this.dayTodayResources  = dayTodayResources ;
    }

    public int getDayTodayResources () {
        return dayTodayResources ;
    }

    @Override
    public String toString() {
        return "Pets{" +
                "name='" + getName() + '\'' +
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
        maxHealth = (health);
    }



    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {

        if (health>=maxHealth){
            return;
        }
        this.health = health;
    }

    public int getResources() {
        return resources ;
    }

    private void setResources(int resources) {
        this.resources  = resources;
    }

}
