package jumble_jump.controller.example;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import jumble_jump.domain.Parts;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ApplyPassiveTideNLP {
    /**
     * 문장을 아래와 같이 분리
     * 1. 동사
     * 2. 명사
     * 3. 형용사
     * 4. 전치사
     *
     * 단, 해당 단어는 제외하고 추가 - that, if
     */

    private static Properties props = new Properties();


    private static List<CoreLabel> tokens = new ArrayList<>();

    private static List<String> verbs = new ArrayList<>();
    private static List<String> nouns = new ArrayList<>();
    private static List<String> adjectives = new ArrayList<>();
    private static List<String> prepositions = new ArrayList<>();

    public static void main(String[] args) {
        // note 문장 분리해서 순서대로 토큰화
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        CoreDocument document = new CoreDocument("Confirm that the payment is complete");
        pipeline.annotate(document);

        for(CoreLabel token : document.tokens()){
            tokens.add(token);
            String pos = token.tag();
            String word = token.word();

            if(Parts.verbsPosTags.contains(pos)){
                verbs.add(word);
            }else if(Parts.nounsPosTags.contains(pos)){
                nouns.add(word);
            }else if(Parts.adjectivesPosTags.contains(pos)){
                adjectives.add(word);
            } else if (Parts.prepositionsPosTag.equals(pos)) {
                prepositions.add(word);
            }
        }







    }


}
