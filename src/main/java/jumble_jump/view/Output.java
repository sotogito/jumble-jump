package jumble_jump.view;

import jumble_jump.domain.Map;
import jumble_jump.domain.UserMoveManager;

import java.util.List;
import java.util.stream.Collectors;

public class Output {

    public static void printError(String error) {
        System.out.print("[ERROR] " + error);
    }

    public static void printMap(Map map){
        System.out.println();
        System.out.println("당신의 map : ");

        StringBuilder result = new StringBuilder();

        for(List<Integer> line : map.getMap()){
            String joinedLine = line.stream()
                    .map(String::valueOf) // Integer.toString()을 사용해도 됩니다.
                    .collect(Collectors.joining(" "));
            result.append(joinedLine).append("\n");
        }
        System.out.println(result);
    }

    public static void printMoveCountResult(UserMoveManager userMoveManager){
        System.out.printf("총 이동한 point : %d개", userMoveManager.getMoveCount());

    }
}
