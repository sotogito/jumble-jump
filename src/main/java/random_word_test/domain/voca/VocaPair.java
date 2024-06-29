package random_word_test.domain.voca;

import random_word_test.util.message.ErrorMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class VocaPair {

    private final TreeMap<English,Korean> vocas =new TreeMap<>();

    public VocaPair(List<String> vocaPairs) {

        // 엔터 별로 나누기 BufferedReader
        //: -> 별로 나누기
        // 왼쪽은 English 객체 생성
        // 오른쪽은 Korean 객체 생성
        //vaca에 put
        // List.copyOf(voca); -> 불변으로
        addEntries(vocaPairs);
    }

    public void addEntry(String input) {
        String[] parts = input.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_VOCA_ENTRY);
        }

        String word = parts[0].trim();
        String[] definitionsArray = parts[1].split(",");

        List<String> definitions = new ArrayList<>();
        for (String def : definitionsArray) {
            definitions.add(def.trim());
        }

        English english = new English(word);
        if (vocas.containsKey(english)) { //note 영어 단어가 존재 하는지 확인
            Korean existingKorean = vocas.get(english); //note 존재하면 그 뜻을 가져옴
            for (String def : definitions) {
                existingKorean.addDefinition(def); //note 존재하는 것에 새로운 뜻을 더함
            }
            return;
        }
        Korean korean = new Korean(definitions);
        vocas.put(english, korean);
    }

    public void addEntries(List<String> entries) {
        for (String entry : entries) {
            addEntry(entry);
        }
    }

    public void printVocabulary() {
        for (Map.Entry<English, Korean> entry : vocas.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }









}
