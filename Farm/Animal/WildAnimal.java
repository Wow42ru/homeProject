package Animal;

import Animal.WildAnimals.Hunting;

public abstract class WildAnimal extends Animal implements Hunting {
   private int Damage;
   protected int howMuchRunAway=0;
    public int getDamage() {
        return Damage;
    }
    public void setDamage(int damage) {
        Damage = damage;
    }

    protected WildAnimal(int weight, int speed, int damage) {
        super(weight, speed);
        setDamage(damage);
    }
    protected WildAnimal  canHuntingOrNot(WildAnimal wildAnimal ){
         howMuchRunAway++;
          if (howMuchRunAway>=3)
              return null;
          return wildAnimal;
    }
}
