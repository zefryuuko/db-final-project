package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Finance extends DBConnect{
    // Class definition
    private static class Income {
        private String date;
        private int income;

        public String getDate() {
            return date;
        }

        public int getIncome() {
            return income;
        }

        public Income(String date, int income) {
            this.date = date;
            this.income = income;
        }
    }

    private static class Expense {
        private String date;
        private int expenses;

        public String getDate() {
            return date;
        }

        public int getExpenses() {
            return expenses;
        }

        public Expense(String date, int expenses) {
            this.date = date;
            this.expenses = expenses;
        }
    }

    // Get all table data
    public static ArrayList<IncomeAndExpense> getFinanceDetails() {
        ArrayList<IncomeAndExpense> result = new ArrayList<>();
        // Add incomes to result ArrayList
        for (Income income : getIncome()) {
            result.add(new IncomeAndExpense(income.getDate(), income.getIncome(), 0));
        }

        // Add purchase expenses to ArrayList
        for(Expense expense : getPurchaseExpenses()) {
            boolean isFound = false;
            for (IncomeAndExpense incomeAndExpense : result) {
                if (expense.getDate().equals(incomeAndExpense.getDate())) {
                    incomeAndExpense.setExpenses(incomeAndExpense.getExpenses() + expense.getExpenses());
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {
                result.add(new IncomeAndExpense(expense.getDate(), 0, expense.getExpenses()));
            }
        }

        // Add staff salary expenses to ArrayList
        for(Expense expense : getSalaryExpenses()) {
            boolean isFound = false;
            for (IncomeAndExpense incomeAndExpense : result) {
                if (expense.getDate().equals(incomeAndExpense.getDate())) {
                    incomeAndExpense.setExpenses(incomeAndExpense.getExpenses() + expense.getExpenses());
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {
                result.add(new IncomeAndExpense(expense.getDate(), 0, expense.getExpenses()));
            }
        }

        return result;
    }

    public static ArrayList<FinanceDetails> getFinanceDetailsByDate(String date) {
        ArrayList<FinanceDetails> result = new ArrayList<>();
        for (FinanceDetails income : getIncomeOnDate(date)) {
            result.add(income);
        }

        for (FinanceDetails expenses : getPurchaseExpensesOnDate(date)) {
            result.add(expenses);
        }

        for (FinanceDetails expenses : getSalaryExpensesOnDate(date)) {
            result.add(expenses);
        }
        return result;
    }

    // Get Income
    private static ArrayList<Income> getIncome() {
        ArrayList<Income> result = new ArrayList<>();

        try {
            String query = "SELECT DATE(s.sales_datetime) as date, SUM(i.item_price) as income FROM Sales s LEFT JOIN SalesDetails d ON s.sales_id = d.sales_id LEFT JOIN Item i ON d.item_id = i.item_id GROUP BY DATE(s.sales_datetime);";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Income row = new Income(rs.getDate("date").toString(), rs.getInt("income"));
                result.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }

        return result;
    }

    private static ArrayList<FinanceDetails> getIncomeOnDate(String date) {
        ArrayList<FinanceDetails> result = new ArrayList<>();

        try {
            String query = "SELECT DATE(s.sales_datetime) as date, SUM(i.item_price) as income FROM Sales s LEFT JOIN SalesDetails d ON s.sales_id = d.sales_id LEFT JOIN Item i ON d.item_id = i.item_id WHERE DATE(s.sales_datetime) = ? GROUP BY s.sales_id;";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, date);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                result.add(new FinanceDetails(date, "Sales Income", rs.getInt("income")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }

        return result;
    }

    // Get Expenses
    private static ArrayList<Expense> getPurchaseExpenses() {
        ArrayList<Expense> result = new ArrayList<>();

        try {
            String query = "SELECT DATE(purchase_date) as date, SUM(purchase_price_total) as expenses FROM PurchaseHistory GROUP BY DATE(purchase_date);";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Expense row = new Expense(rs.getDate("date").toString(), rs.getInt("expenses"));
                result.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }

        return result;
    }

    private static ArrayList<FinanceDetails> getPurchaseExpensesOnDate(String date) {
        ArrayList<FinanceDetails> result = new ArrayList<>();

        try {
            String query = "SELECT DATE(purchase_date) as date, SUM(purchase_price_total) as expenses FROM PurchaseHistory WHERE DATE(purchase_date) = ? GROUP BY DATE(purchase_date);";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, date);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                result.add(new FinanceDetails(date, "Purchases Expense", rs.getInt("expenses")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }

        return result;
    }

    private static ArrayList<Expense> getSalaryExpenses() {
        ArrayList<Expense> result = new ArrayList<>();

        try {
            String query = "SELECT DATE(payment_date) as date, SUM(payment_amount) as expenses FROM SalaryPaymentHistory GROUP BY DATE(payment_date);";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Expense row = new Expense(rs.getDate("date").toString(), rs.getInt("expenses"));
                result.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }

        return result;
    }

    private static ArrayList<FinanceDetails> getSalaryExpensesOnDate(String date) {
        ArrayList<FinanceDetails> result = new ArrayList<>();

        try {
            String query = "SELECT DATE(payment_date) as date, SUM(payment_amount) as expenses FROM SalaryPaymentHistory WHERE DATE(payment_date) = ? GROUP BY DATE(payment_date);";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, date);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                result.add(new FinanceDetails(date, "Salary Expense", rs.getInt("expenses")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }

        return result;
    }

}
