package random_word_test.domain.voca;

public class English implements Comparable<English> {
    private final String word;

    public English(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }





    @Override
    public int compareTo(English other) {
        return this.word.compareTo(other.word); // note TreeMap에 저장될 때 key의 순서를 지정함 = 사전순 대로
    }

    @Override
    public boolean equals(Object obj) { //note 영어가 같으면 같은 객체로 취급
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        English english = (English) obj;
        return word.equals(english.word);
    }

    @Override
    public int hashCode() {
        return word.hashCode();
    }

    @Override
    public String toString() {
        return word;
    }


}
