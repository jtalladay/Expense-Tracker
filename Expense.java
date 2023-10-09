import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Expense {


    private int id;
    private String category;
    private String date;
    private double amount;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    NumberFormat numberFormatter = NumberFormat.getCurrencyInstance();


    public Expense(int id, String date, String category, double amount){
        this.id = id;
        this.date = simpleDateFormat.format(new Date());
        this.category = category;
        this.amount = amount;
        ExpenseTracker.total += amount;

    }

    @Override
    public String toString() {
        return "ID: " + id + " Date: " + date + " Category: " + category +
                                                    " Amount: " + numberFormatter.format(amount) + "\n";
    }


}
