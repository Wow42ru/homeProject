package multithreading;

import java.util.concurrent.LinkedBlockingDeque;

public class Kitchen {
    LinkedBlockingDeque<Order> orderKlient = new LinkedBlockingDeque<>(5);
    LinkedBlockingDeque<Order> ordersOfficiant;

    public LinkedBlockingDeque<Order> getOrderKlient() {
        return orderKlient;
    }

    public void setOrderKlient(LinkedBlockingDeque<Order> orderKlient) {
        this.orderKlient = orderKlient;
    }

    public LinkedBlockingDeque<Order> getOrdersOfficiant() {
        return ordersOfficiant;
    }

    public void setOrdersOfficiant(LinkedBlockingDeque<Order> ordersOfficiant) {
        this.ordersOfficiant = ordersOfficiant;
    }

    public LinkedBlockingDeque<Order> getOrdersKocker() {
        return ordersKocker;
    }

    public void setOrdersKocker(LinkedBlockingDeque<Order> ordersKocker) {
        this.ordersKocker = ordersKocker;
    }

    LinkedBlockingDeque<Order> ordersKocker ;

    public static void main(String[] args) {
        LinkedBlockingDeque<Order> orderKlient = new LinkedBlockingDeque<>(5);
        LinkedBlockingDeque<Order> ordersOfficiant = new LinkedBlockingDeque<>(5);
        LinkedBlockingDeque<Order> ordersKocker = new LinkedBlockingDeque<>(5);
    }
}


class Order {

    public Order(String positionName) {
        this.positionName = positionName;
    }

    String positionName;
}


class Klient implements Runnable {
    public LinkedBlockingDeque<Order> getOrderKlient() {
        return orderKlient;
    }

    LinkedBlockingDeque<Order> orderKlient = null;

    public Klient(LinkedBlockingDeque<Order> orderKlient) {
        this.orderKlient = orderKlient;
    }

    public void newOrder(LinkedBlockingDeque<Order> order) {
        Order newOrder = new Order("Test Order");
        order.add(newOrder);
    }

    @Override
    public void run() {
        while (true) {
            newOrder(orderKlient);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Officiant implements Runnable {
    LinkedBlockingDeque<Order> ordersOfficiant = null;

    public Officiant(LinkedBlockingDeque<Order> ordersOfficiant) {
        this.ordersOfficiant = ordersOfficiant;
    }

    public LinkedBlockingDeque<Order> getOrdersOfficiant() {
        return ordersOfficiant;
    }

    public void newOrder(LinkedBlockingDeque<Order> order) throws InterruptedException {
        ordersOfficiant.add(order.take());
    }
    @Override
    public void run() {
        while (true) {
        //    newOrder(getOrderKlient);
        }
    }
}

class Cocker implements Runnable {
    LinkedBlockingDeque<Order> zakaz = new LinkedBlockingDeque<>(5);// в таком виде очередь ограничена maxInteger

    @Override
    public void run() {

    }
}