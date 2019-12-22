package pattern;



public class Green implements Listener {
    int temp;
    boolean flag = true;

    @Override
    public void publish(int temp) {
        if (temp >= 100 && flag) {
            System.out.println("ALARM !!! GREEN");
            flag = false;
        } else if (temp < 100)
            flag = true;
    }
}
