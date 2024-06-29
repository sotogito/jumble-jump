package random_word_test.view;

import random_word_test.util.message.ServiceMessage;

public class Output {
    public static void printError(String error) {
        System.out.print("[NOTICE] " + error);
    }

    public static void printInputVoca() {
        System.out.print(ServiceMessage.INPUT_VOCA.getFormatingMessage());
    }

}
