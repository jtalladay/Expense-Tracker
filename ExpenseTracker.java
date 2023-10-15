import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;

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
 1) Budget
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
            1) Budget
            3) Expense Adding Mode
            4) Restore previous expense tracker
    */
     public static void mainMenu(){
         int selection = 1;
         while(selection < 5 && selection > 0) {
             System.out.println("Main Menu: \n 1) Budget \n " +
                     "2) Expense Adding Mode \n 3) Restore previous expense tracker \n " +
                      "4) Save Expenses to text file \n 5) QUIT");
             selection = userInput.nextInt();
             userInput.nextLine();

             switch (selection) {
                 case 1:
                     budgetSetup();
                     break;
                 case 2:
                     System.out.println("Entering expense mode... ");
                     expenseAdder();
                     break;
                 case 3:
                     System.out.println("Restoring previously used expense tracker...");
                     break;
                 case 4:
                     System.out.println("Name of file to save: ");
                     String fileName = userInput.nextLine();
                     userInput.nextLine();
                     System.out.println("Saving current expense list to file...");
                     saveArrayListToFile(expenses);
                 default:
                     System.out.println("Quitting the application...");
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
             userInput.nextLine();

             switch (selection) {
                 case 1:
                     System.out.println("Enter the date in the format yyyy-MM-dd ");
                     String date = userInput.nextLine();
                     System.out.println("Enter the category from the list: \n" + Budget.categories);
                     //System.out.println("Enter the category from the list: ");
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
         System.out.println("Budget Setup");
         Budget budget = new Budget();

         System.out.println("1) Setup new budget \n2) Restore saved budget from file \n" +
                  "3) Save budget to file for reuse");
         int input = userInput.nextInt();
         userInput.nextLine();

         switch(input){
            case 1:
                budget.budgetSetup();
                System.out.println("Budget setup complete. returning to main menu...");
                break;
            case 2:
                System.out.println("You selected to load budget from file.");
                System.out.println("Enter the filename of the budget to load: ");
                String filename = userInput.nextLine();
                userInput.nextLine();
                budget.loadBudgetFromFile(filename);
                break;
             case 3:
                 System.out.println("You selected to save budget to file.");
                 budget.saveBudgetToFile();

         }
        return;
     }

     public static void saveArrayListToFile(ArrayList<Expense> arrayList) {
         // Generate a unique filename based on the current timestamp
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
         String fileName = "ExpenseList_" + dateFormat.format(new Date()) + ".txt";

         try {
             // Create a BufferedWriter to write to the file
             BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

             // Loop through the ArrayList and write each element to the file
             for (Expense element : arrayList) {
                 writer.write(String.valueOf(element));
                 writer.newLine(); // Add a newline character to separate elements
             }

             // Close the writer to release system resources
             writer.close();

             System.out.println("ArrayList has been successfully saved to " + fileName);
         } catch (IOException e) {
             System.err.println("Error saving ArrayList to file: " + e.getMessage());
         }
     }
    public static void clearTotal() {
        ExpenseTracker.total = 0;
    }

}
