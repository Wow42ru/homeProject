import Animal.Pet;

import java.util.Arrays;

public class Farm {
    private int resurse;
    Human farmer;
    int freePlace = 0;
    Pet[] pets = new Pet[5];

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
            System.out.println("Библеотека перегружена");

        }
    }


    public Farm(int resurse) {
        this.resurse = resurse;
    }

    public int getResurse() {
        return resurse;
    }

    public void addResurse(int AddResurse) {
        this.resurse = AddResurse + this.resurse;
    }

    public void addFarmer(Human newFarmer) {
        farmer = newFarmer;
    }

    public void spendEat() {
        resurse = -farmer.spendResurse();
    }

    public void getDayResurse() {
        for (int i = 0; i < freePlace-1; i++)
            resurse += pets[i].getDayTodayResurs();
    }
    private void feedPet(){
        for (Pet i:pets) {
            farmer.feed(i.getHealth());
        }
    }
public void dayOnFarmGone(){
        spendEat();
// приходит враг
    //  кормит
    getDayResurse();
    System.out.println("день прошел, на ферме"+resurse +"запасов.");
    System.out.println("На ферме живут:");
    for (Pet v : pets)
        System.out.println(v);
}

    @Override
    public String toString() {
        return "Farm{" +
                "pets=" + Arrays.toString(pets) +
                '}';
    }
}


