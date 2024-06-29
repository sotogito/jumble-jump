package random_word_test.view;


import java.util.Scanner;

public class Input {
    private static Scanner scanner= new Scanner(System.in);

    public static String inputVocaEntries(){
        return scanner.nextLine();
    }
}
