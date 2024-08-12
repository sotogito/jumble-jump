package jumble_jump.controller.example;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

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

    public static void main(String[] args) {
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        CoreDocument document = new CoreDocument("Confirm that the payment is complete");
        pipeline.annotate(document);

        for(CoreLabel token : document.tokens()){
            tokens.add(token);
        }

        for (CoreLabel token : tokens) {
            System.out.println(token.index());
            System.out.println(token.word());

        }
    }
}
