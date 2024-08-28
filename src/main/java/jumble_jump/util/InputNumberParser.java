package jumble_jump.util;

import jumble_jump.domain.NumberImpl;
import jumble_jump.domain.NumberT;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InputNumberParser {
    private final static int MIN = 1;
    private final static int MAX = 99999;

    private final static int MIN_LIST_SIZE = 1;
    private final static int MAX_LIST_SIZE = 99999;

    public static List<Integer> getNumberList(String input) {
        List<Integer> result = new ArrayList<>();

        String[] splitWord = input.split(" ");
        //note 오름차순 정렬
        for (String s : splitWord) {
            int number = changeNumberFromString(s);
            validateNumberRange(number);

            result.add(number);
        }

        validateDuplication(result);
        validateNumberCount(result);
        return result;
    }


    private static int changeNumberFromString(String s) {
        try{
            return Integer.parseInt(s);
        }catch (NumberFormatException e){
            throw new NumberFormatException();
        }
    }

    private static void validateNumberRange(int number){
        if (number < MIN || number > MAX) {
            throw new IllegalArgumentException("Number must be between " + MIN + " and " + MAX);
        }
    }

    private static void validateNumberCount(List<Integer> numbers) {
        int size = numbers.size();
        if (size < MIN_LIST_SIZE || size > MAX_LIST_SIZE) {
            throw new IllegalArgumentException("Number list size must be between " + MIN_LIST_SIZE + " and " + MAX_LIST_SIZE);
        }
    }

    private static void validateDuplication(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if(uniqueNumbers.size() != numbers.size()){
            throw new IllegalArgumentException("중복되는 숫자가 존재합니다.\n");
        }
    }
}
