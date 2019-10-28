package Animal;

public abstract class WildAnimal extends Animal  {
    int Damage;
    public int getDamage() {
        return Damage;
    }
    public void setDamage(int damage) {
        Damage = damage;
    }

    WildAnimal(int weight, int speed,int damage) {
        super(weight, speed);
        setDamage(damage);
    }
}
