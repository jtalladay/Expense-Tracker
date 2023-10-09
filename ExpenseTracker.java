import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class ExpenseTracker {
    public static double total;
    public static int id = 1;

    static ArrayList<Expense> expenses = new ArrayList<>();
    public static String[] categories = new String[6];

    public static void main(String[] args){


        String selection = "";
        System.out.println("Expense Tracker Program");
        Scanner userInput = new Scanner(System.in);
        NumberFormat numberFormatter = NumberFormat.getCurrencyInstance();

        budgetSetup();
//in main:
// move this into a method expenseAdder
// add budgetSetup method
// main variables:

        while (!selection.equalsIgnoreCase("X")) {
            System.out.println("Make your selection: ");
            System.out.println("A: Add expense");
            System.out.println("B: View total expenses");
            System.out.println("C: Clear totals");
            selection = userInput.nextLine();

            System.out.println("You selected " + selection);


            switch (selection.toUpperCase()) {
                case "A":

                    System.out.println("Enter the date in the format yyyy-MM-dd ");
                    String date = userInput.nextLine();
                    System.out.println("Enter the category from list: \n" + Arrays.toString(categories));
                    String category = userInput.nextLine();
                    System.out.println("Enter the amount to add to the total: ");
                    double amount = userInput.nextDouble();

                    expenses.add(new Expense(id, date, category, amount));
                    id++;
                    break;

                case "B":
                    System.out.println(expenses);
                    System.out.println("Total = " + numberFormatter.format(total));
                    break;

                case "C":
                    clearTotal();
            }
        }
    }

    public static void clearTotal() {
        ExpenseTracker.total = 0;
    }

    public static void budgetSetup(){

        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter category followed by enter: (Max of 6)");

        int i = 0;
        while (i < categories.length) {
            categories[i] = userInput.nextLine();
            i++;
        }
        System.out.println("You entered: " + Arrays.toString(categories));

    }
}
