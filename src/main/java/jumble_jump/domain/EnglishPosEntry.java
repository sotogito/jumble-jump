package jumble_jump.domain;

import edu.stanford.nlp.ling.CoreLabel;

import java.util.ArrayList;
import java.util.List;

public class EnglishPosEntry {

    private static List<CoreLabel> tokens = new ArrayList<>();

    private static List<Integer> verbs = new ArrayList<>();
    private static List<Integer> nouns = new ArrayList<>();
    private static List<Integer> adjectives = new ArrayList<>();
    private static List<Integer> prepositions = new ArrayList<>();
}
