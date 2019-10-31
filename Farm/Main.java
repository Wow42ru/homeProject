import Animal.Pets.Chicken;
import Animal.Pets.Cow;
import Animal.Pets.Rabbit;
import Animal.WildAnimals.Bear;
import Animal.WildAnimals.Fox;
import Animal.WildAnimals.Wolf;

public class Main {
    public static int getDaysOnFarm() {
        return daysOnFarm;
    }

    public static void setDaysOnFarm(int daysOnFarm) {
        Main.daysOnFarm = daysOnFarm;
    }

    static int daysOnFarm = 0;

    public static void main(String[] args) {

        Farm farm = new Farm(20);
        Human farmer = new Human();
        farm.addFarmer(farmer);
        Cow cow = new Cow("Бурёнка", 20, 5, 5);
        Rabbit rabbit = new Rabbit("Пушистик", 10, 11, 5);
        Chicken chicken1 = new Chicken("Ряба", 4, 7, 5);
        Chicken chicken2 = new Chicken("Несушка", 4, 7, 5);
        farm.addPet(chicken1, chicken2, cow, rabbit);
        Wolf wolf1 = new Wolf(8, 6, 5);
        Wolf wolf2 = new Wolf(8, 6, 5);
        Wolf wolf3 = new Wolf(8, 6, 5);
        Bear bear = new Bear(30, 8, 10);
        Fox fox = new Fox(6, 10, 5);
        farm.addWildAnimals(wolf1, wolf2, wolf3, bear, fox);
        farm.dayOnFarmGone();
        for (int i = 0; i < 10000; i++) {
            farm.dayOnFarmGone();
            daysOnFarm++;
        }
    }
}
// На ферме есть фермер , пока что один

