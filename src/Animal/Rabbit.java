package Animal;

public class Rabbit extends Pet {
    public Rabbit(String name, int health, int speed, int weight) {
        super(name, health, speed, weight);
    }
    @Override
    public int killPet() {
        return 0;
    }
}
