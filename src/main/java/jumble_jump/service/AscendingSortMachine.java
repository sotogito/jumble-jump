package jumble_jump.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AscendingSortMachine {

    public static List<Integer> sort(List<Integer> numberList) {
        for (int i = 0; i < numberList.size() - 1; i++) {

            int minIndex = i;


            for (int j = i + 1; j < numberList.size(); j++) {
                if (canSort(numberList, minIndex, j)) {
                    minIndex = j;
                }
            }

            swipe(numberList, i, minIndex);
        }
        return numberList;
    }

    private static boolean canSort(List<Integer> numberList, int minIndex, int j) {
        return numberList.get(j) < numberList.get(minIndex);
    }

    private static void swipe(List<Integer> numberList, int nowIndex, int minIndex) {
        int smallerNum = numberList.get(minIndex);
        int biggerNum = numberList.get(nowIndex);

        numberList.set(nowIndex, smallerNum);
        numberList.set(minIndex, biggerNum);
    }

}
