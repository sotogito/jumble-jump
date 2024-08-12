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

    private static List<Integer> verbs = new ArrayList<>();
    private static List<Integer> nouns = new ArrayList<>();
    private static List<Integer> adjectives = new ArrayList<>();
    private static List<Integer> prepositions = new ArrayList<>();

    private static List<String> result = new ArrayList<>();

    public static void main(String[] args) {
        // note 문장 분리해서 순서대로 토큰화
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        CoreDocument document = new CoreDocument("Check if your email is verified");
        pipeline.annotate(document);

        for(CoreLabel token : document.tokens()){
            tokens.add(token);
            String pos = token.tag();
            String word = token.word();
            int index = token.index();

            if(Parts.verbsPosTags.contains(pos)){
                verbs.add(index);
            }else if(Parts.nounsPosTags.contains(pos)){
                nouns.add(index);
            }else if(Parts.adjectivesPosTags.contains(pos)){
                adjectives.add(index);
            } else if (Parts.prepositionsPosTag.equals(pos)) {
                if(Parts.exclusionWord.contains(word)){
                    continue;
                }
                prepositions.add(index);
            }

        }


            /**
             * 1. 수동태 동사원형 확인
             * 2. 맞다면 has,have를 제외한 나머지 다 is로
             * 3. 바로 뒤에 pp가 있다면 그것도 추가
             *
             * - be동사는 is, has로
             * -
             */


        for(int i = 0; i < verbs.size(); i++){
            int index = verbs.get(i)-1;
            CoreLabel nowVerbToken = tokens.get(index);
            String lemma = nowVerbToken.lemma();

            //note be동사일때



            if(lemma.equals("be") || lemma.equals("have")){
                result.clear();

                if(lemma.equals("be")){
                    result.add("is");
                }else if(lemma.equals("have")){
                    result.add("has");
                }

                if(i < verbs.size() - 1){
                    CoreLabel token = tokens.get(verbs.get(i));
                    if(token.tag().equals("VBN")){
                        result.add(token.word());
                    }
                }
                break;
            }

            result.add(lemma);


        }




        System.out.println(tokens);
        System.out.println(verbs);
        System.out.println(nouns);
        System.out.println(adjectives);
        System.out.println(prepositions);
        System.out.println(result);










    }


}
