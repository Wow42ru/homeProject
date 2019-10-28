public class Human   implements HumansMethods {
    private int health;
    private int eatForDay;
    private int howMuchEat;

    @Override
    public int spendResurse() {
        return howMuchEat;
    }

    @Override
    public int feed(int heath) {
        
        return 1+heath;
        // TODO: 28.10.2019 Добавить проверку на полноту здоровья 
    }
}
