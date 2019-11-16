package Farm.Animal;


import Farm.Animal.WildAnimals.Hunting;

public abstract class WildAnimal extends Animal implements Hunting {
    private int Damage;
    protected int howMuchRunAway = 0;

    @Override
    public String toString() {
        return "WildAnimal{" +
                "Damage=" + Damage +
                ", howMuchRunAway=" + howMuchRunAway +
                '}';
    }

    public int getDamage() {
        return Damage;
    }

    public void setDamage(int damage) {
        Damage = damage;
    }

    public Object hunt(Pet petWilBeEaten, boolean goAway, WildAnimal hauntinglyAnimal) {
        System.out.println("Охота началась." + getName() + " Расчитывает на сытный ужин.");
        if (goAway) {
            double chance = Math.random() *( getDamage() + (petWilBeEaten.getSpeed() - hauntinglyAnimal.getSpeed()));
            if (chance > 0.8) {
                petWilBeEaten.setHealth(petWilBeEaten.getHealth() - getDamage());
                if (petWilBeEaten.getHealth() <= 0) {
                    System.out.println("Охота удалась... " + petWilBeEaten.getName() + " мы будем помнить тебя!");
                    petWilBeEaten = null;
                } else System.out.println(petWilBeEaten.getName() + "на волоске от смерти!");
            } else {
                System.out.println("Акела промахнулся");
            }
        } else hauntinglyAnimal = canHuntingOrNot(hauntinglyAnimal);
        Object[] object = new Object[2];
        object[1] = petWilBeEaten;
        object[0] = hauntinglyAnimal;
        return object;
    }

    protected WildAnimal(int weight, int speed, int damage) {
        super(weight, speed);
        setDamage(damage);
    }

    protected WildAnimal canHuntingOrNot(WildAnimal wildAnimal) {
        howMuchRunAway++;
        System.out.println(getName() + " убежал с пустыми лапами!");
        if (howMuchRunAway >= 3) {
            System.out.println(getName() + " убежал поджав хвост и больше не рискнёт сунуться на ферму, фермер молодец!");
            return null;
        }
        return wildAnimal;
    }
}
