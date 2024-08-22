package jumble_jump.util;

import java.util.ArrayList;
import java.util.List;

public class NLPConstants {

    public static List<String> verbsPosTags =
            new ArrayList<>(List.of("VB", "VBD", "VBG", "VBN", "VBP", "VBZ"));

    public static List<String> nounsPosTags =
            new ArrayList<>(List.of("NN", "NNS", "NNP", "NNPS"));

    public static List<String> adjectivesPosTags =
            new ArrayList<>(List.of("JJ", "JJR", "JJS"));

    public static String prepositionsPosTag = "IN";

    public static String passiveTypeTage = "VBN";

}
