package jumble_jump.controller;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

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
    }
}
