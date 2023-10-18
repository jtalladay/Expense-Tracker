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
            System.out.printf("%-10s $%.2f%n", key, budget.get(key));
        }


        return;
    }// end of budget setup method

    public static void saveBudgetToFile(){
        try (PrintWriter writer = new PrintWriter(new FileWriter("Budget.txt"))) {
            writer.println("Monthly Income: " + monthlyIncome);
            writer.printf("%-10s %-10s%n", "Category", "Amount");
            //writer.println("Category    Amount");
            for (String key : budget.keySet()) {
                double value = budget.get(key);
                writer.printf("%s,%f%n", key, value);
            }
            System.out.println("Budget has been saved to Budget.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }// end of save budget method

    public static boolean loadBudgetFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isHeader = true; // To skip the header lines
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    if (line.trim().isEmpty()) {
                        continue; // Skip empty header lines
                    }
                    isHeader = false; // Skip the first non-empty line
                    monthlyIncome = Double.parseDouble(line.replaceAll("[^\\d.]", "")); // Update monthlyIncome
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String category = parts[0].trim();
                    double amount = Double.parseDouble(parts[1].trim());
                    budget.put(category, amount);
                }
            }

            for (String key : budget.keySet()) {
                System.out.printf("%-10s $%.2f%n", key, budget.get(key));
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}

