public class Accounts {

    private double balance;
    private double APR;

    public Accounts(double APR){
        this.APR = APR;
    }

    public Accounts(double APR, double balance){
        this.APR = APR;
        this.balance = balance;
    }



    public double getBalance() {
        return balance;
    }

    public double getAPR() {
        return APR;
    }

    public void deposit(double amount) {
        if (amount >= 0) {
            balance += amount;
        } else {
            System.out.println("Cannot deposit negative amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount >= 0) {
            balance -= amount;
            if (balance < 0) {
                balance = 0;
            }
        }else {
            System.out.println("Cannot withdraw negative amount!");
        }
    }

}
