package database;

public class IncomeAndExpense {
    private String date;
    private int income;
    private int expenses;

    public String getDate() {
        return date;
    }

    public int getIncome() {
        return income;
    }

    public int getExpenses() {
        return expenses;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }

    public IncomeAndExpense(String date, int income, int expenses) {
        this.date = date;
        this.income = income;
        this.expenses = expenses;
    }

}

