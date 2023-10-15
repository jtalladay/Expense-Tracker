import java.io.*;
import java.text.NumberFormat;
import java.util.*;


// Budget Object
// Constructor will set up number of categories, store in array of strings
// setup percent values for each category
public class Budget {

    private static int numCategories;

    public static ArrayList<String> categories = new ArrayList<>();
    public static ArrayList<Double> doubleList = new ArrayList<>();
    public static HashMap<String, Double> budget = new HashMap<>();
    public static double monthlyIncome;

    public static void budgetSetup() {

        Scanner scanner = new Scanner(System.in);
        String userInput;
        System.out.println("First enter your monthly income, followed by the enter key. ");
        monthlyIncome = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character


        System.out.println("First enter each category for the budget followed by enter key. ");

        while (true) {
            // Prompt the user for input
            System.out.print("Enter a new category ('q' when done): ");
            userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("q")) {
                break; // Exit the loop if the user enters 'q'
            }

            // Add the user's input to the ArrayList
            categories.add(userInput);
        }
        //enter budget percentages. Total must be equal to 1
        //System.out.println("Now, enter the percentage for each budget category: ");
        //System.out.println("Numbers must be given in format 0.xx and total must equal 1.00 ");
        // Iterate through the category list and prompt the user for double values for each item

        System.out.println("Now, enter a value for each category.");
        System.out.println("The value can be in decimal format for a percentage of your \n" +
                "monthly income, or you can enter the total dollar amount for the month.");

        for (String item : categories) {
            double doubleValue;

            System.out.print("Enter a value for '" + item + "': ");
            doubleValue = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character

            // Store the double value in the HashMap using the item name as the key
            budget.put(item, doubleValue);
        }

        // Display the Budget Hashmap
        System.out.println("Monthly Budget Summary with percentages:");
        NumberFormat percentFormatter = NumberFormat.getPercentInstance(Locale.US);
        NumberFormat numberFormatter = NumberFormat.getCurrencyInstance();
        for (String key : budget.keySet()) {
            if(budget.get(key) < 1.0)
                System.out.println(key + " -> " + percentFormatter.format(budget.get(key)));
            else {
                System.out.println(key + " -> " + numberFormatter.format(budget.get(key)));
            }
        }
        System.out.println("Monthly Budget Summary with updated values:");
        for (String key : budget.keySet()) {
            if (budget.get(key) < 1.0) {
                double monthlyValue = budget.get(key) * monthlyIncome;
                budget.put(key, monthlyValue);
            }
        }
        for (String key : budget.keySet()) {
            System.out.println(key + " -> " + numberFormatter.format(budget.get(key)));
        }


        return;
    }// end of budget setup method





    public static void saveBudgetToFile(){
        try (PrintWriter writer = new PrintWriter(new FileWriter("Budget.txt"))) {
            for (String key : budget.keySet()) {
                double value = budget.get(key);
                writer.println(key + "," + value);
            }
            System.out.println("HashMap has been saved to Budget.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }// end of save budget method

    public static void loadBudgetFromFile(String filename){

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String key = parts[0];
                    double value = Double.parseDouble(parts[1]);
                    budget.put(key, value);
                }
            }
            System.out.println("Budget has been loaded from " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Display the loaded HashMap
        for (String key : budget.keySet()) {
            System.out.println(key + " -> " + budget.get(key));
        }
    }// end of load budget method

}

