package jumble_jump.domain.type;

import java.util.ArrayList;
import java.util.List;

public enum Game369Number {
    THREE(3),
    SIX(6),
    NINE(9);

    private final int number;

    Game369Number(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public static List<Integer> get369NumberList() {
        List<Integer> result = new ArrayList<>();

        for (Game369Number game369Number : Game369Number.values()) {
            result.add(game369Number.getNumber());
        }
        return result;
    }

}
