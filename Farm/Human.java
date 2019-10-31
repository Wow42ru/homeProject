public class Human   implements HumansMethods {
    private int health;
    private int eatForDay;
    private int howMuchEat;

    @Override
    public int spendResources() {
        return howMuchEat;
    }

    @Override
    public int feed(int heath, String name) {
        System.out.println("кормим " + name);
        return 1+heath;
        // TODO: 28.10.2019 Добавить проверку на полноту здоровья 
    }

    @Override
    public boolean goAway() {
       double a= Math.random();
       if (a>0.6)
        return false;
        else return true;
        // TODO: 29.10.2019 Можно сделать зависимость успеха от силы животоного
    }
}
