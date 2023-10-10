import java.util.Arrays;
import java.util.Scanner;


// Budget Object
// Constructor will set up number of categories, store in array of strings
// setup percent values for each category
public class Budget {


    private static int numberCategories;
    public static String[] categories;

    public Budget(int cat) {
        numberCategories = cat;
        String[] categories = new String[numberCategories];
    }
}

