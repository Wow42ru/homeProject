import Animal.Pet;

public class Human   implements HumansMethods {
    private int health;
    private int eatForDay;
    private int howMuchEat =3;

    @Override
    public int spendResources() {
        return howMuchEat;
    }

    @Override
    public Pet feed(Pet pet) {
        System.out.println("кормим " + pet.getName());
        pet.setHealth(pet.getHealth()+1);
        return pet;

    }

    @Override
    public boolean goAway() {
       double a= Math.random();
       if (a>0.6) {
           return false;
       }
        else return true;
    }

    @Override
    public Pet killForFood(Pet pet) {
        System.out.println(pet.getName()+" прости нас... у фермера есть интерфейс.");
        return null;
    }
}
