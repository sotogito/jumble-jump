package jumble_jump.domain;

import edu.stanford.nlp.ling.CoreLabel;
import jumble_jump.util.ReplaceWords;

import java.util.Optional;

public class WordReplacer {
    public Optional<String> replaceBeHave(String word) {
        if (word.equals(ReplaceWords.be)) {
            return Optional.of("is");
        } else if (word.equals(ReplaceWords.have)) {
            return Optional.of("have");
        }
        return Optional.empty();
    }

    public String replaceOrdinaryVerb(String word) {
        if (word.equals(ReplaceWords.returnS)) {
            return "get";
        }
        return word;
    }

}
