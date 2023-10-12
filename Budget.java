import java.util.Arrays;
import java.util.Scanner;


// Budget Object
// Constructor will set up number of categories, store in array of strings
// setup percent values for each category
public class Budget {

    public static void budgetSetup() {
        Scanner userInput = new Scanner(System.in);

        System.out.print("Enter the number of categories: ");
        int numCategories = userInput.nextInt();
        userInput.nextLine(); // Consume the newline character

        String[] stringArray = new String[numCategories];

        for (int i = 0; i < numCategories; i++) {
            System.out.print("Enter string " + (i + 1) + ": ");
            stringArray[i] = userInput.nextLine();
        }

        // Now, you have an array of strings in stringArray.

        // Print the entered strings
        System.out.println("You entered the following categories:");
        for (String str : stringArray) {
            System.out.println(str);
        }

        //userInput.close(); // Close the scanner when done.
    }
}

