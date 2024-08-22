package jumble_jump.repository;

import jumble_jump.domain.English;
import jumble_jump.domain.EnglishPosEntry;
import jumble_jump.domain.Korean;
import jumble_jump.domain.MethodName;

public class TranslationEntryRepository {
    private final Korean korean;
    private final English english;

    public TranslationEntryRepository(English english, Korean korean) {
        this.english = english;
        this.korean = korean;
    }

    public void setEnglish(String english) {
        this.english.setEnglish(english);
    }

    public void setKorean(String korean) {
        this.korean.setKoran(korean);
    }

    public String getEnglish() {
        return this.english.getEnglish();
    }

    public String getKorean() {
        return this.korean.getKoran();
    }

    @Override
    public String toString() {
        return getEnglish();
    }


}
