import java.io.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* STILL TO DO
Setup New Budget
Name new budget: "Home Budget"
print totals for each category independantly
have checks for when the expenses added are reaching the budgeted limit
need to print out negative if value has gone past limit
need to have a way to keep track of the month

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
         int selection = 0;
         while(selection != 5) {
             System.out.println("Main Menu: \n 1) Budget \n " +
                     "2) Expense Adding Mode \n 3) Save Expenses to text file \n " +
                      "4) Restore previous expense tracker  \n 5) QUIT");
             selection = userInput.nextInt();
             userInput.nextLine();

             switch (selection) {
                 case 1: // Set up budget
                     budgetSetup();
                     break;
                 case 2: // Enter expense adding mode
                     System.out.println("Entering expense mode... ");
                     expenseAdder();
                     break;
                 case 3: // Save expense list
                     System.out.println("Name of file to save: ");
                     String fileName = userInput.nextLine();
                     userInput.nextLine();
                     System.out.println("Saving current expense list to file...");
                     saveExpenseListToFile(expenses);
                 case 4: // Load expense list
                     System.out.println("Restoring previously used expense tracker...");
                     System.out.println("You selected to load Expense list from file.");
                     System.out.println("Enter the filename of the expense list to load: ");
                     String filename = userInput.nextLine();
                     userInput.nextLine();
                     if(loadExpenseListFromFile(filename)){
                         System.out.println("Expense list successfully loaded...");
                     }else{
                         System.out.println("File not loaded, try again...");
                     }
                     break;
                 case 5:
                     System.out.println("Quitting the application...");
                     break;
                 default:
                     System.out.println("Invalid input, please try again... ");
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

                 case 2: // need to work on this need to add totals per category

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
                boolean loadedSuccessfully = budget.loadBudgetFromFile(filename);
                if (loadedSuccessfully) {
                    System.out.println("Budget has been loaded successfully:");
                } else {
                    System.out.println("Failed to load the budget from the file.");
                }
                break;
             case 3:
                 System.out.println("You selected to save budget to file.");
                 budget.saveBudgetToFile();

         }
        return;
     }

     public static void saveExpenseListToFile(ArrayList<Expense> arrayList) {
         // Generate a unique filename based on the current timestamp
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
         String fileName = "ExpenseList_" + dateFormat.format(new Date()) + ".txt";

         try {
             // Create a BufferedWriter to write to the file
             BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

             // Loop through the ArrayList and write each element to the file
             for (Expense element : arrayList) {
                 writer.write(String.valueOf(element));
                 //writer.newLine(); // Add a newline character to separate elements
             }

             // Close the writer to release system resources
             writer.close();

             System.out.println("ArrayList has been successfully saved to " + fileName);
         } catch (IOException e) {
             System.err.println("Error saving ArrayList to file: " + e.getMessage());
         }
     }
     public static boolean loadExpenseListFromFile(String filename) {
         try {

             BufferedReader reader = new BufferedReader(new FileReader(filename));
             String line;
             String pattern = "ID: (\\d+) Date: (\\d{4}-\\d{2}-\\d{2}) Category: (\\w+) Amount: \\$([\\d,.]+)";

             Pattern expensePattern = Pattern.compile(pattern);

             while ((line = reader.readLine()) != null) {
                 Matcher matcher = expensePattern.matcher(line);
                 if (matcher.find()) {
                     int id = Integer.parseInt(matcher.group(1));
                     String date = matcher.group(2);
                     String category = matcher.group(3);
                     double amount = Double.parseDouble(matcher.group(4).replaceAll(",", "")); // Remove commas and convert to double

                     expenses.add(new Expense(id, date, category, amount));
                 }
             }

             reader.close();

             // Now, you have a list of Expense objects with the data from the file.
             for (Expense expense : expenses) {
                 System.out.println(expense);
             }
             return true;
         } catch (IOException e) {
             e.printStackTrace();
             return false;
         }

     } // end of load method
    public static void clearTotal() {
        ExpenseTracker.total = 0;
    }

}
