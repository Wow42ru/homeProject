import Animal.Chicken;
import Animal.Cow;
import Animal.Rabbit;

public class Main {
    public static void main(String[] args) {
        Farm farm = new Farm(20);
        Human fermer = new Human();
        farm.addFarmer(fermer);
        Cow cow =new Cow("Бурёнка",15,5,20);
        Rabbit rabbit = new Rabbit("Пушистик",5,20,5);
        Chicken chicken1 = new Chicken("Ряба",5,10,5);
        Chicken chicken2 = new Chicken("Несушка",5,10,5);
        farm.addPet(chicken1, chicken2,cow,rabbit);
farm.dayOnFarmGone();
    }
}
// На ферме есть фермер , пока что один

