package random_word_test.domain.test;

import random_word_test.domain.voca.English;
import random_word_test.domain.voca.Korean;
import random_word_test.domain.voca.VocaPair;

import java.util.List;

public class KoreanRandomTester extends Tester{

    private List<Korean> koreanList;

    public KoreanRandomTester(VocaPair vocaPair) {
        this.koreanList = vocaPair.getAllKoreanDefinitions();
    }

    @Override
    protected void shuffleList() {

    }

    @Override
    public void test() {

        System.out.println("KoreanRandomTester");
        for (Korean korean : koreanList) {
            System.out.println(korean);
            System.out.println();
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Korean korean : koreanList) {
            builder.append(korean).append("\n");
        }
        return builder.toString();
    }
}
