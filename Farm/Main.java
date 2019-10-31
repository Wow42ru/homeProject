import Animal.Pets.Chicken;
import Animal.Pets.Cow;
import Animal.Pets.Rabbit;
import Animal.WildAnimals.Bear;
import Animal.WildAnimals.Fox;
import Animal.WildAnimals.Wolf;

public class Main {
    public static void main(String[] args) {
        Farm farm = new Farm(20);
        Human farmer = new Human();
        farm.addFarmer(farmer);
        Cow cow =new Cow("Бурёнка",15,5,20);
        Rabbit rabbit = new Rabbit("Пушистик",5,20,5);
        Chicken chicken1 = new Chicken("Ряба",5,10,5);
        Chicken chicken2 = new Chicken("Несушка",5,10,5);
        farm.addPet(chicken1, chicken2,cow,rabbit);
        Wolf wolf1 = new Wolf(8,10,8);
        Wolf wolf2 = new Wolf(8,10,8);
        Wolf wolf3 = new Wolf(8,10,8);
        Bear bear = new Bear(30,10,15);
        Fox fox = new Fox(6,20,5);
        farm.addWildAnimals(wolf1,wolf2,wolf3,bear,fox);
farm.dayOnFarmGone();
    }
}
// На ферме есть фермер , пока что один

