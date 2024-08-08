package jumble_jump.util.parser;

import jumble_jump.domain.Map;
import jumble_jump.domain.Point;

import java.util.ArrayList;
import java.util.List;

public class UserStartLocalDataParser {

    public static List<Integer> parse(String input, Map map){
        List<Integer> result = new ArrayList<>();
        String[] tokens = input.split(" ");
        for (String token : tokens) {
            try {
                result.add(Integer.parseInt(token.trim()));
            }catch (NumberFormatException e){
                throw new IllegalArgumentException("숫자로 입력해주세요\n");
            }
        }

        validateValue(result);
        validatePoint(result.get(0),result.get(1),map);
        return result;
    }

    public static void validatePoint(int x, int y,Map map){
        if(!map.isWithinMap(new Point(x, y))){
            throw new IllegalArgumentException("x,y 좌표는 맵의 크기를 벗어날 수 없어요.\n");
        }
    }

    public static void validateValue(List<Integer> result){
        if(result.size() != 3){
            throw new IllegalArgumentException("x, y, 방향 3가지를 입력해주세요.");
        }
    }

}
