package random_word_test.domain.voca;

import java.util.Map;
import java.util.TreeMap;

public class VocaPair {

    private final TreeMap<English,Korean> voca;

    public VocaPair(String inputVocaPair) {

        // 엔터 별로 나누기 BufferedReader
        //: -> 별로 나누기
        // 왼쪽은 English 객체 생성
        // 오른쪽은 Korean 객체 생성
        //vaca에 put
        // List.copyOf(voca); -> 불변으로
        this.voca = new TreeMap<>();
    }
}
