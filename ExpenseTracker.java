import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

 /* what are we trying to do.
 main menu:
 1) Setup New Budget
 2) Restore Budget
 3) Expense Adding Mode
 4) Restore previous expense tracker

 Setup New Budget
 Name new budget: "Home Budget"
 Enter what categories you want "Car, Grocery"
 Enter decimal value for percentages for each category
 exmaple:
 Car: (user enters) 0.25 (user presses enter)
 Grocery: (user enters) 0.35 (user presses enter)

 Back to main menu
 1) Setup New Budget
 2) Restore Budget
 3) Expense Adding Mode
 4) Restore previous expense tracker
 "3" enter

 Select what budget to use
 1) "Home Budget"
 "1" enter

 Expense Adder Mode
 Enter your selection:
 1) Add new expense
 2) View total expenses
 3) clear expenses
 "1" enter

Enter the date in the format yyyy-MM-dd
 "2023-12-25"
Enter the category from list:
Car, Grocery
"Car"
Enter Amount

repeats:
Enter your selection:
objects:
expense
budget
expense tracker

methods
mainMenu()
budgetSetup()
budgetLoad()
expenseAdder()
addExpense()
deleteExpense()
sumTotal()
categoryTotal()



  */
public class ExpenseTracker {
    public static double total;
    public static int id = 1;


    static ArrayList<Expense> expenses = new ArrayList<>();
     static NumberFormat numberFormatter = NumberFormat.getCurrencyInstance();
     static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args){


        String selection = "";
        System.out.println("Expense Tracker Program");
        mainMenu();


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
                    System.out.println("Enter the category from list: \n" + Arrays.toString(Budget.categories));
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
    public static void mainMenu(){
        System.out.println("Main Menu: \n 1) Setup Budget \n 2) Expense Adder \n ");
        int selection = userInput.nextInt();
        switch (selection){
            case 1:
                budgetSetup();
                break;
            case 2:
                System.out.println("Returning");
                break;
        }
    return;
    }
     public static void budgetSetup(){
//  is this where load existing budget will happen?

         System.out.println("Enter number of categories: ");
         int numberCategories = userInput.nextInt();
         System.out.println("Enter " + numberCategories + "categories followed by enter: ");
         Budget budget = new Budget(numberCategories);

/*
         int i = 0;
         while (i < categories.length) {
             categories[i] = userInput.nextLine();
             i++;
         }
         System.out.println("You entered: " + Arrays.toString(categories));
*/
     }

    public static void clearTotal() {
        ExpenseTracker.total = 0;
    }


}
