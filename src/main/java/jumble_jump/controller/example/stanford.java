package jumble_jump.controller.example;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.List;
import java.util.Properties;

public class stanford {
    public static void main(String[] args) {
        // NLP 파이프라인 설정
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // 처리할 텍스트
        String text = "Stanford NLP is a great tool for natural language processing.";

        // NLP 파이프라인에 텍스트를 전달하여 처리
        CoreDocument document = new CoreDocument(text);
        pipeline.annotate(document);

        // 결과 출력
        document.sentences().forEach(sentence -> {
            sentence.tokens().forEach(token -> {
                String word = token.word();
                String lemma = token.lemma();
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                System.out.println("Word: " + word + ", Lemma: " + lemma + ", POS: " + pos);
            });
        });









        // 입력 문장
        String text2 = "Returning the user list size.";

        // 문장을 NLP 파이프라인에 전달
        CoreDocument doc = new CoreDocument(text2);
        pipeline.annotate(doc);

        // 결과를 저장할 StringBuilder
        StringBuilder result = new StringBuilder();

        // 각 단어 처리
        List<CoreLabel> tokens = doc.tokens();
        for (CoreLabel token : tokens) {
            String word = token.word();          // 원래 단어
            String lemma = token.lemma();        // 원형 (Lemma)
            String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class); // 품사 태그

            // 동사이고 'be' 동사가 아닌 경우만 원형으로 변환
            if (pos.startsWith("VB") && !lemma.equals("be")) {
                result.append(lemma);  // 원형을 추가
            } else {
                result.append(word);   // 원래 단어를 추가
            }
            result.append(" ");  // 단어 사이에 공백 추가
        }

        // 결과 출력
        System.out.println("Original: " + text2);
        System.out.println("Processed: " + result.toString().trim());
    }
}
