package database;

public class FinanceDetails {
    String date, details;
    int amount;

    public FinanceDetails(String date, String details, int amount) {
        this.date = date;
        this.details = details;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
