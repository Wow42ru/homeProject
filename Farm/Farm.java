import Animal.Pet;
import Animal.WildAnimal;

import java.util.Arrays;

public class Farm {

    private Pet[] pets = new Pet[5];
    private WildAnimal[] wildAnimals = new WildAnimal[5];
    private int resources;
    private int freePlace;
    private int freePlaceWild;
    private int randomThisTurn;
    private Human farmer;

    public int getFreePlace() {
        return freePlace;
    }

    public void setFreePlace(int freePlace) {
        if (freePlace == 0) {
            System.out.println("Диким животным больше нечего есть, экосистема скоро падёт! ");
        } else this.freePlace = freePlace;

    }

    public int getFreePlaceWild() {
        return freePlaceWild;
    }

    public void setFreePlaceWild(int freePlaceWild) {
        if (freePlaceWild == 0) {
            System.out.println("Хищников больше нет! ");
        } else this.freePlaceWild = freePlaceWild;
    }

    private int randomPet() {
        for (int i = 0; i < pets.length; i++) {
            for (int j = 0; j < pets.length * 100; j++) {
                randomThisTurn = (int) (Math.random() * pets.length);
                if (pets[randomThisTurn] != null)
                    return randomThisTurn;
            }
        }
        for (int j = 0; j < pets.length; j++) {
            if (pets[j] != null) {
                randomThisTurn = j;
                return randomThisTurn;
            }
        }

        System.out.println("Скотина померла, горе-фермеру осталось жить всего " + Math.abs(resources / farmer.spendResources()) + " дня");
        resources=Math.abs(resources / farmer.spendResources())+Main.getDaysOnFarm();
        System.out.println("Фермер продержался "+ resources+ " дней");
        System.exit(0);
        return 0;
    }

    private void hounting() {
        int a = randomPet();
        int b = randomWildAnimal();
        Object[] object1;
        object1 = (Object[]) wildAnimals[b].hunt(pets[a], farmer.goAway(), wildAnimals[b]);
        wildAnimals[b] = (WildAnimal) object1[0];
        pets[a] = (Pet) object1[1];
    }

    private int randomWildAnimal() {
        int randomWildAnimal;
        for (int i = 0; i < wildAnimals.length; i++) {
            for (int j = 0; j < wildAnimals.length * 100; j++) {
                randomWildAnimal = (int) (Math.random() * wildAnimals.length);
                if (wildAnimals[randomWildAnimal] != null)
                    return randomWildAnimal;
            }
        }
        for (int j = 0; j < wildAnimals.length; j++) {
            if (wildAnimals[j] != null) {
                randomWildAnimal = j;
                return randomWildAnimal;
            }
        }
        System.out.println("Дикие животные мертвы ");
        System.exit(0);
        return 0;
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
                    break;
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
                System.out.println(freePlace + "животных на ферме");
                numberOfNewWildAnimals++;
                if (numberOfNewWildAnimals == newWildAnimals.length) {
                    break;
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

    public void addFarmer(Human newFarmer) {
        farmer = newFarmer;
    }

    private void spendEat() {
        System.out.printf("На ферме %d запасов \n", resources);
        resources -= farmer.spendResources();
        System.out.printf("Фермер решил поесть, теперь на  ферме %d запасов \n", resources);
    }

    private boolean getDayResurse() {
        int a = 0;//проверяем есть ли ресурсы
        for (Pet pet : pets) {
            if (pet != null) {
                resources += pet.getDayTodayResources();
                a++;
            }
        }
        return a == 0;
    }

    public void dayOnFarmGone() {

        spendEat();// фермер тратит ресурсы
        hounting();// Дикие звери охотятся
        for (int i = 0; i < pets.length; i++) {
            if (pets[i] != null)
                pets[i] = farmer.feed(pets[i]);
        }

        if (getDayResurse()) //собираем ресурсы и проверяем, не пора ли забивать кроликов
            for (Pet pet : pets) {
                if (pet != null)
                    farmer.killForFood(pet);
            }
        System.out.println("день прошел, на ферме " + resources + " запасов.");
        System.out.println("На ферме живут:");
        for (Pet v : pets)
            if (v != null)
                System.out.println(v);// перечисляем живую скотину
    }

    @Override
    public String toString() {
        return "Farm{" +
                "pets=" + Arrays.toString(pets) +
                '}';
    }
}


