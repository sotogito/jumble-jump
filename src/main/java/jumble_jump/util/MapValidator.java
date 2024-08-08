package jumble_jump.util;

import jumble_jump.domain.type.MapType;

import java.util.List;

public class MapValidator {

    public static void validateMapSIze(int size){
        if (size < 4 || size > 10) {
            throw new IllegalArgumentException("맵의 사이즈는 4~10까지 입력 가능합니다.\n");
        }
    }

    public static void validateMapSIzeByInputMapTypes(int size, List<Integer> inputMapTypes){
        if(size != inputMapTypes.size()){
            throw new IllegalArgumentException("맵의 사이즈 크기만큼 입력해주세요.\n");
        }
    }

    public static void validateMapTypes(List<Integer> inputMapTypes){
        for (Integer inputMapType : inputMapTypes) {
            MapType.fromValue(inputMapType);
        }
    }


}
