package jumble_jump.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AscendingSortMachine {

    public static List<Integer> sort(List<Integer> numberList) {
        for (int i = 0; i < numberList.size() - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < numberList.size(); j++) {
                if (numberList.get(j) < numberList.get(minIndex)) {
                    minIndex = j;
                }
            }

            int smallerNum = numberList.get(minIndex);
            int biggerNum = numberList.get(i);

            numberList.set(i, smallerNum);
            numberList.set(minIndex, biggerNum);
        }

        return numberList;
    }
}
