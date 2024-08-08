package jumble_jump.domain;

import jumble_jump.domain.type.MapType;
import jumble_jump.util.MapValidator;

import java.util.ArrayList;
import java.util.List;

public class Map {

    private final int length;
    private final int with;
    private final List<List<Integer>> map = new ArrayList<>();

    public Map(int size) {
        MapValidator.validateMapSIze(size);
        this.length = size;
        this.with = size;
    }

    public void addMapLine(List<Integer> line) {
        MapValidator.validateMapTypes(line);
        MapValidator.validateMapSIzeByInputMapTypes(length,line);
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

    public int getMapSize(){
        return length;
    }

    public List<List<Integer>> getMap(){
        return map;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(length).append("x").append(with).append("\n");
        for (List<Integer> line : map) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }

}
