import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// Major issue, how can i save a list of categories in the budget object that
// can be used in the expensetracker object

 /* STILL TO DO
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

        System.out.println("Expense Tracker Program");
        mainMenu();


    }//end of main

     /*
    main menu:
            1) Setup New Budget
            2) Restore Budget
            3) Expense Adding Mode
            4) Restore previous expense tracker
    */
     public static void mainMenu(){
         int selection = 0;
         while(selection != 5) {
             System.out.println("Main Menu: \n 1) Setup New Budget \n 2) Restore Budget \n " +
                     "3) Expense Adding Mode \n 4) Restore previous expense tracker \n " +
                      "5) QUIT");
             selection = userInput.nextInt();

             switch (selection) {
                 case 1:
                     budgetSetup();
                     break;
                 case 2:
                     System.out.println("Restoring previously used budget... ");
                     break;
                 case 3:
                     System.out.println("Entering expense mode... ");
                     expenseAdder();
                     break;
                 case 4:
                     System.out.println("Resotring previously used expense tracker...");
                     break;
                 case 5:
                     break;
             }
             //in the while loop still
         }
         return;
     }//end of mainMenu
/*
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

*/
     public static void expenseAdder(){

         int selection = 0;

         while (selection != 4) {
             System.out.println("Expense Adder Menu: \n 1) Add Expense \n 2) View total expenses\n " +
                     "3) Clear totals\n 4) Quit \n ");
             selection = userInput.nextInt();

             switch (selection) {
                 case 1:
                     System.out.println("Enter the date in the format yyyy-MM-dd ");
                     String date = userInput.nextLine();
                     //System.out.println("Enter the category from the list: \n" + Arrays.toString(Budget.categories));
                     System.out.println("Enter the category from the list: ");
                     String category = userInput.nextLine();
                     System.out.println("Enter the amount to add to the total: ");
                     double amount = userInput.nextDouble();
                     userInput.nextLine(); // Consume the newline character
                     expenses.add(new Expense(id, date, category, amount));
                     id++;
                     break;

                 case 2:
                     System.out.println(expenses);
                     System.out.println("Total = " + numberFormatter.format(total));
                     break;

                 case 3:
                     clearTotal();
                     break;
                 case 4:
                     System.out.println("Quitting expense adder mode...");
                     break;
             }

         }
     }// end of expenseAdder

     public static void budgetSetup(){
//  is this where load existing budget will happen? yes
// do you want to set up a new budget or restore a saved budget from file
         // change to say create new or load existing.
         System.out.println("New budget? 0 for no 1 for yes: ");
         int input = userInput.nextInt();

         switch(input){
            case 0:
                break;
            case 1:
                Budget budget = new Budget();
                budget.budgetSetup();
                break;
        }
        return;
     }

    public static void clearTotal() {
        ExpenseTracker.total = 0;
    }

}
