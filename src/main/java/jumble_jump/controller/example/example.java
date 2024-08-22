package jumble_jump.controller.example;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

import java.io.InputStream;

public class example {
    public static void main(String[] args) {
        try (InputStream modelIn = example.class.getResourceAsStream("/opennlp-en-ud-ewt-sentence-1.0-1.9.3.bin")) {
            if (modelIn == null) {
                throw new IllegalArgumentException("파일을 찾을 수 없습니다.");
            }

            SentenceModel model = new SentenceModel(modelIn);
            SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);

            String text = "This is a test. Let's see if it works. qwe";
            String[] sentences = sentenceDetector.sentDetect(text);

            for (String sentence : sentences) {
                System.out.println(sentence);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
