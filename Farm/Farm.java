import Animal.Pet;
import Animal.WildAnimal;

import java.util.Arrays;

public class Farm {
    private int resources;
    private int freePlace = 0;
    private int freePlaceWild = 0;
    Pet[] pets = new Pet[5];
    WildAnimal[] wildAnimals = new WildAnimal[5];
    private int HowMuchpetsStillAlive = pets.length;
    private int HowMuchwildAnStillAlive = wildAnimals.length;//еще не испуганы
    Human farmer;


    private Pet randomPet() {
        int a = 0;
        int b = pets.length;
        int randomPet = a + (int) Math.random() * b;
        if (pets[randomPet] == null)
            randomPet();
        return pets[randomPet];
    }
    private Pet Hounting(){
        return wildAnimals[randomWildAnimal()].hunt(randomPet(),farmer.goAway(), wildAnimals[randomWildAnimal()]);
    }
    private int randomWildAnimal() {
        int a = 0;
        int b = wildAnimals.length;
        int randomWildAnimal = a + (int) Math.random() * b;
        if (pets[randomWildAnimal] == null)
            randomWildAnimal();
        return randomWildAnimal;
    }

    public void addPet(Pet... newPet) {//
        int numberOfNewPet = 0;//для последовательного назначения места newBooks в массиве books
        somePet:
        for (int i = 0; freePlace < this.pets.length; i++) {
            if (this.pets[i] == null) {// проверка на свободное место
                this.pets[i] = newPet[numberOfNewPet];
                freePlace++;// "закрытие" индекса
                numberOfNewPet++;
                if (numberOfNewPet == newPet.length) {
                    numberOfNewPet = 0;// При вызове нового метода отсчёт ведётся с 0
                    break somePet;
                }
            }
        }
        if (freePlace == this.pets.length) {
            System.out.println("На ферме недостаточно места для нового животного");

        }
    }

    public void addWildAnimals(WildAnimal... newWildAnimals) {//
        int numberOfNewWildAnimals = 0;//для последовательного назначения места newBooks в массиве books
        someWildAnimals:
        for (int i = 0; freePlaceWild < this.wildAnimals.length; i++) {
            if (this.wildAnimals[i] == null) {// проверка на свободное место
                this.wildAnimals[i] = newWildAnimals[numberOfNewWildAnimals];
                freePlaceWild++;// "закрытие" индекса
                numberOfNewWildAnimals++;
                if (numberOfNewWildAnimals == newWildAnimals.length) {
                    numberOfNewWildAnimals = 0;// При вызове нового метода отсчёт ведётся с 0
                    break someWildAnimals;
                }
            }
        }
        if (freePlaceWild == this.wildAnimals.length) {
            System.out.println("В лесу больше нет места новым хищникам");

        }
    }

    public Farm(int resurse) {
        this.resources = resurse;
    }

    public int getResurse() {
        return resources;
    }

    public void addResurse(int AddResurse) {
        this.resources = AddResurse + this.resources;
    }

    public void addFarmer(Human newFarmer) {
        farmer = newFarmer;
    }

    private void spendEat() {
        resources = -farmer.spendResources();
    }

    private void getDayResurse() {
        for (int i = 0; i < freePlace - 1; i++)
            resources += pets[i].getDayTodayResources();
    }

    public void dayOnFarmGone() {
        spendEat();// фермер тратит ресурсы
        int a =
        Pet
        for (Pet v : pets) {// кормим
            if (v != null)
                farmer.feed(v.getHealth(), v.getName());
        }
        getDayResurse();//собираем ресурсы
        System.out.println("день прошел, на ферме" + resources + "запасов.");
        System.out.println("На ферме живут:");
        for (Pet v : pets)
            System.out.println(v);// перечисляем живую скотину
    }

    @Override
    public String toString() {
        return "Farm{" +
                "pets=" + Arrays.toString(pets) +
                '}';
    }
}


