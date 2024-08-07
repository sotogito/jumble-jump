package jumble_jump.domain;

import jumble_jump.domain.type.MapType;

import java.util.ArrayList;
import java.util.List;

public class Map {

    private int length,with;
    private final List<List<Integer>> map = new ArrayList<>();

    public Map(int length, int with) {
        this.length = length;
        this.with = with;
    }

    public void addMapLine(List<Integer> line) {
        this.map.add(line);
    }

    public boolean isCanMoveLand(Point point){
        int x = point.getX();
        int y = point.getY();
        return map.get(x).get(y) == MapType.LAND.getValue();
    }

    public boolean isWithinMap(Point point){
        int x = point.getX();
        int y = point.getY();

        return x >= 0 && x < with && y >= 0 && y < length;

    }
}
