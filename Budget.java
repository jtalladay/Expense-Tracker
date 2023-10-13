import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


// Budget Object
// Constructor will set up number of categories, store in array of strings
// setup percent values for each category
public class Budget {

    private static int numCategories;

    public static ArrayList<String> categories = new ArrayList<>();

    public static void budgetSetup() {

        Scanner scanner = new Scanner(System.in);
        String userInput;

        System.out.print("First enter each category for the budget followed by enter key. ");

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
        // Display the contents of the ArrayList
        System.out.println("Budget Categories: ");
        for (String item : categories) {
            System.out.println(item);
        }

        return;
    }

}

