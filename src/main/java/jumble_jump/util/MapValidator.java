package jumble_jump.util;

public class MapValidator {

    public static void validateMapSIze(int size){
        if (size < 4 || size > 10) {
            throw new IllegalArgumentException("맵의 사이즈는 4~10까지 입력 가능합니다.\n");
        }
    }
}
