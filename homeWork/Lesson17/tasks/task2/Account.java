package Lesson17.tasks.task2;

public class Account {
    private String number;
    private long balance;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public Account(String number, long balance) {
        this.number = number;
        this.balance = balance;
    }


}
