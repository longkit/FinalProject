

public class Wallet {
    private double balance;

    public Wallet() {
        this.balance = 0.0;
    }

    public double getBalance() {
        return balance;
    }

    public void addFunds(double amount) {
        this.balance += amount;
    }

    public void deduct(double amount) {
        this.balance -= amount;
    }
}
