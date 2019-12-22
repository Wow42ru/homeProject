package pattern;

public class Red implements Listener {
    int temp;
    boolean flag = true;

    @Override
    public void publish(int temp) {
        if (temp >= 600 && flag) {
            System.out.println("ALARM !!! RED");
            flag = false;
        } else if (temp < 600)
            flag = true;
    }
}
