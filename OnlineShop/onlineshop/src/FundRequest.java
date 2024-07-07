

public class FundRequest  {
    private Customer customer;
    private double amount;

    public FundRequest(Customer customer, double amount) {
        this.customer = customer;
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getAmount() {
        return amount;
    }
}
