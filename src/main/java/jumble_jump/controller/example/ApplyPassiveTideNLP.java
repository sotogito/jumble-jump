package jumble_jump.controller.example;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import jumble_jump.util.ExclusionWords;
import jumble_jump.util.NLPConstants;

import java.util.ArrayList;
import java.util.List;
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

    private static int preposition = -1;
    private static List<Integer> prepositions = new ArrayList<>();

    private static List<String> result = new ArrayList<>();

    public static void main(String[] args) {
        // note 문장 분리해서 순서대로 토큰화
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        CoreDocument document = new CoreDocument("Changing the status have changed book of an order");
        pipeline.annotate(document);

        for(CoreLabel token : document.tokens()){
            tokens.add(token);
            String pos = token.tag();
            String word = token.word();
            int index = token.index();

            if(NLPConstants.verbsPosTags.contains(pos)){
                verbs.add(index);
            }else if(NLPConstants.nounsPosTags.contains(pos)){
                nouns.add(index);
            }else if(NLPConstants.adjectivesPosTags.contains(pos)){
                adjectives.add(index);
            } else if (NLPConstants.prepositionsPosTag.equals(pos)) {
                if(ExclusionWords.exclusionWord.contains(word)){
                    continue;
                }
                preposition = index;
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

        List<Integer> preNouns = new ArrayList<>();
        List<Integer> remainingNouns = new ArrayList<>();

        if (!prepositions.isEmpty()) {
            int adjectivesIndex = prepositions.get(0) - 1;


            for (int nounIndex : nouns) {
                if (nounIndex - 1 < adjectivesIndex) {
                    preNouns.add(nounIndex); // 조건을 만족하는 명사 인덱스 저장
                } else {
                    remainingNouns.add(nounIndex); // 나머지 명사 인덱스 저장
                }
            }
            nouns = remainingNouns; // 남은 명사 인덱스로 리스트 업데이트
        } else if (prepositions.isEmpty()) {
            preNouns.addAll(nouns);
            nouns = remainingNouns;
        }


        for(int i = 0; i < verbs.size(); i++){
            int index = verbs.get(i)-1;
            CoreLabel nowVerbToken = tokens.get(index);
            String lemma = nowVerbToken.lemma();

            //note boolean 메서드형
            if(lemma.equals("be") || lemma.equals("have")){
                result.clear();

                if(lemma.equals("be")){
                    result.add("is");
                }else if(lemma.equals("have")){
                    result.add("has");
                }

                //note 수동태의 경우
                if(i < verbs.size() - 1){
                    CoreLabel token = tokens.get(verbs.get(i));
                    if(token.tag().equals("VBN")){
                        result.add(token.word());
                    }
                }
                break;
            }
            //note 일반 메서드형
            result.add(lemma);
        }

        /**
         * 전치사 앞에 명사 뒤에 명사
         */
        for(Integer i : preNouns){
            result.add(tokens.get(i-1).word());
        }

        if(!adjectives.isEmpty()){
            result.add(tokens.get(adjectives.get(0)-1).word());
        }


       if(!prepositions.isEmpty()){
           result.add(tokens.get(prepositions.get(0)-1).word());
       }


        for(Integer i : nouns){
            result.add(tokens.get(i-1).word());
        }









        System.out.println(tokens);
        System.out.println(verbs+"v");
        System.out.println(nouns+"n");
        System.out.println(preNouns+"pn");
        System.out.println(adjectives+"형");
        System.out.println(prepositions+"전");
        System.out.println(result);










    }


}
