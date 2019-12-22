package pattern;

import java.util.ArrayList;

public class Sensor {
    private ArrayList<Listener> listeners = new ArrayList<>();
    private int temperature = 30;


    public int getTemperature() {
        return temperature;
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    private void increaseTemp() {
        temperature = temperature + (int) (Math.random() * 10);
        System.out.println(temperature);
        notifyListeners();
    }

    private void decreaseTemp() {
        temperature = temperature - (int) (Math.random() * 10);
        System.out.println(temperature);
        notifyListeners();
    }

    private void notifyListeners() {
        for (Listener listener : listeners) {
            listener.publish(temperature);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Green green = new Green();
        Yellow yellow = new Yellow();
        Red red = new Red();
        Sensor sensor = new Sensor();
        sensor.addListener(green);
        sensor.addListener(yellow);
        sensor.addListener(red);
        while (true) {
            while (sensor.temperature < 600) {
                sensor.increaseTemp();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            while (sensor.getTemperature() > 50) {
                sensor.decreaseTemp();
                Thread.sleep(100);
            }
        }
    }
}
