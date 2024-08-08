package jumble_jump.util.parser;

import java.util.ArrayList;
import java.util.List;

public class MapLineParser {

    public static List<Integer> parse(String input){
        List<Integer> result = new ArrayList<>();
        String[] tokens = input.split(" ");

        for (String token : tokens) {
            try {
                result.add(Integer.parseInt(token.trim()));
            }catch (NumberFormatException e){
                throw new IllegalArgumentException("숫자로 입력해주세요\n");
            }
        }
        return result;
    }

}
