package pattern;

public class Yellow implements Listener {
    int temp;
    boolean flag = true;

    @Override
    public void publish(int temp) {
        if (temp >= 300 && flag) {
            System.out.println("ALARM !!! YELLOW");
            flag = false;
        } else if (temp < 300)
            flag = true;
    }
}
