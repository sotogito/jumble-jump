package jumble_jump.repository;

import jumble_jump.domain.English;
import jumble_jump.domain.EnglishPosEntry;
import jumble_jump.domain.Korean;

public class TranslationEntryRepository {
    private final Korean korean;
    private final English english;
    private final EnglishPosEntry englishPosEntry;

    public TranslationEntryRepository(English english, Korean korean, EnglishPosEntry englishEntry) {
        this.english = english;
        this.korean = korean;
        this.englishPosEntry = englishEntry;
    }

    public void setEnglish(String english) {
        this.english.setEnglish(english);
    }
}
