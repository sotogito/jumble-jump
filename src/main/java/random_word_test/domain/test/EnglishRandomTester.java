package random_word_test.domain.test;

import random_word_test.domain.voca.English;
import random_word_test.domain.voca.Korean;
import random_word_test.domain.voca.VocaPair;

import java.util.List;

public class EnglishRandomTester extends Tester{

    private List<English> englishList;

    public EnglishRandomTester(VocaPair vocaPair) {
        this.englishList = vocaPair.getAllEnglishWords();

    }

    @Override
    protected void shuffleList() {

    }

    @Override
    public void test() {
        System.out.println("EnglishRandomTester");
        System.out.println(englishList);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (English english : englishList) {
            builder.append(english).append("\n");
        }
        return builder.toString();
    }


}
