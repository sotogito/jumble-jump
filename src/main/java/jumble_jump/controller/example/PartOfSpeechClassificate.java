package jumble_jump.controller.example;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.CoreAnnotations.*;
/**
 * be 동사, can, has, should가 들어가면 다른 동사는 삭제하고
 * that, if 삭제
 * 관사 삭제
 * 동사는 원형으로
 * 수동태의 경우 be동사를 is로 변경하고 그 뒤에 p.p
 * Check if your email is verified ->  is email(x) -> isVerfiedEmail
 * 조립 순서는 [동사+명사+현용사+전치사+명사]
 * has인경우 동사원형 말고 그냥 has
 *
 * your은 user로 변경
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PartOfSpeechClassificate {
/*
    // 수동태 검사를 위한 메서드
    private boolean isPassiveVoice(String sentence) {
        return sentence.contains("has been") || sentence.contains("is being") || sentence.contains("was") || sentence.contains("were");
    }

    // 수동태 문장을 처리하는 메서드
    private String handlePassiveVoice(String sentence) {
        if (sentence.contains("has been")) {
            return sentence.replace("has been", "is");
        } else if (sentence.contains("is being")) {
            return sentence.replace("is being", "is");
        } else if (sentence.contains("was")) {
            return sentence.replace("was", "is");
        } else if (sentence.contains("were")) {
            return sentence.replace("were", "is");
        }
        return sentence;
    }

    public List<String> generateMethodName(String sentence) {
        // 수동태인지 먼저 확인
        boolean isPassive = isPassiveVoice(sentence);

        if (isPassive) {
            sentence = handlePassiveVoice(sentence); // 수동태를 처리
        }

        // Stanford NLP 설정
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // 문장을 NLP 파이프라인에 전달하여 처리
        CoreDocument document = new CoreDocument(sentence);
        pipeline.annotate(document);

        List<String> verbs = new ArrayList<>();
        List<String> nouns = new ArrayList<>();
        List<String> adjectives = new ArrayList<>();
        List<String> prepositions = new ArrayList<>();

        String mainVerb = null;
        String pastParticiple = null;

        for (CoreLabel token : document.tokens()) {
            String word = token.word();
            String lemma = token.lemma();
            String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);

            // 불필요한 단어 삭제 (관사, that, if 등)
            if (pos.equals("DT") || word.equalsIgnoreCase("that") || word.equalsIgnoreCase("if")) {
                continue;
            }

            // 2인칭 대명사 -> User로 변경
            if (pos.equals("PRP") && (word.equalsIgnoreCase("you") || word.equalsIgnoreCase("your"))) {
                nouns.add("User");
                continue;
            }

            // 동사 처리: be, can, has, should -> 다른 동사 삭제하고 이 동사만 사용
            if (pos.startsWith("VB")) {
                if (lemma.equals("be")) {
                    mainVerb = "is";
                    verbs.clear();
                    verbs.add(mainVerb);
                    continue;
                } else if (lemma.equals("can") || lemma.equals("have") || lemma.equals("should")) {
                    mainVerb = lemma;
                    verbs.clear();
                    verbs.add(mainVerb);
                    continue;
                } else if (mainVerb == null) {
                    mainVerb = lemma;
                    verbs.add(mainVerb);
                }
                continue;
            }

            // 명사 처리
            if (pos.startsWith("NN")) {
                nouns.add(word);
                continue;
            }

            // 형용사 처리
            if (pos.startsWith("JJ")) {
                adjectives.add(word);
                continue;
            }

            // 전치사 처리
            if (pos.startsWith("IN")) {
                prepositions.add(word);
                continue;
            }

            // 수동태 처리 후 동사에 과거분사를 붙이는 로직
            if (isPassive && pos.equals("VBN")) {
                pastParticiple = lemma;
            }
        }

        if (isPassive && pastParticiple != null) {
            verbs.clear();
            verbs.add(mainVerb);
            verbs.add(pastParticiple);
        }

        // 최종 결과 조합: 동사 + 명사 + 형용사 + 전치사 + 명사 순서
        List<String> result = new ArrayList<>();
        result.addAll(verbs);
        result.addAll(nouns);
        result.addAll(adjectives);
        result.addAll(prepositions);

        return result;
    }


    public static void main(String[] args) {
        PartOfSpeechClassificate generator = new PartOfSpeechClassificate();

        // 예제 1: 수동태 문장
        List<String> methodNameParts1 = generator.generateMethodName("Checking to see if an email has been verified");
        System.out.println("Generated Method Name Parts: " + methodNameParts1);

        // 예제 2: 일반 문장
        List<String> methodNameParts2 = generator.generateMethodName("Verify that the user is an admin");
        System.out.println("Generated Method Name Parts: " + methodNameParts2);
    }

 */


    public List<String> generateMethodName(String sentence) {
        // Stanford NLP 설정
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // 문장을 NLP 파이프라인에 전달하여 처리
        CoreDocument document = new CoreDocument(sentence);
        pipeline.annotate(document);

        List<String> verbs = new ArrayList<>();
        List<String> nouns = new ArrayList<>();
        List<String> adjectives = new ArrayList<>();
        List<String> prepositions = new ArrayList<>();
        List<String> objects = new ArrayList<>();

        String mainVerb = null;
        String auxiliaryVerb = null;

        for (CoreLabel token : document.tokens()) {
            String word = token.word();
            String lemma = token.lemma();
            String pos = token.get(PartOfSpeechAnnotation.class);




             //* 무언가를 삭제한다기보다
             //* 1. 문장 돌면서 고나사 삭제함
             //* 2. 원래 문장에서 메서드 순서 지켜서 반환
             //* 피해야하는 것들 listd에 넣어서 관리하기

            // 관사 및 불필요한 단어 삭제
            if (pos.equals("DT") || word.equalsIgnoreCase("that") || word.equalsIgnoreCase("if")) { //todo 리스트에 아예 저장해야할듯
                continue;
            }

            // 2인칭 대명사 -> User로 변경
            if (pos.equals("PRP") && (word.equalsIgnoreCase("you") || word.equalsIgnoreCase("your"))) {
                nouns.add("User");
                continue;
            }

            if (pos.startsWith("VB")) {
                if (lemma.equals("be")) {
                    mainVerb = "is";
                    verbs.clear(); // 다른 동사 삭제
                } else if (lemma.equals("can") || lemma.equals("have") || lemma.equals("should")) {
                    if(lemma.equals("have")){
                        mainVerb = "has";
                        verbs.clear();
                        continue;
                    }
                    mainVerb = lemma; // 우선 동사 설정
                    verbs.clear(); // 다른 동사 삭제
                } else if (mainVerb == null) {
                    verbs.add(lemma); // 우선 동사가 없을 때만 동사 추가
                }
                continue;
            }
            // 명사 처리
            if (pos.startsWith("NN")) {
                nouns.add(word);
                continue;
            }

            // 형용사 처리
            if (pos.startsWith("JJ")) {
                adjectives.add(word);
                continue;
            }

            // 전치사 처리
            if (pos.startsWith("IN")) {
                prepositions.add(word);
                continue;
            }
        }

        // 명사 중 첫 번째는 명사 리스트에, 나머지는 objects 리스트로 이동
        if (nouns.size() > 1) {
            objects.addAll(nouns.subList(1, nouns.size()));
            nouns = nouns.subList(0, 1);
        }

        // 최종 결과 조합: 동사 + 명사 + 형용사 + 전치사 + 목적어 순서
        List<String> result = new ArrayList<>();
        if (mainVerb != null) {
            result.add(mainVerb);
        } else {
            result.addAll(verbs); // 메인 동사가 없을 경우 추가된 동사 사용
        }
        result.addAll(nouns);
        result.addAll(adjectives);
        result.addAll(prepositions);
        result.addAll(objects);

        return result;
    }


    public static void main(String[] args) {
        PartOfSpeechClassificate generator = new PartOfSpeechClassificate();
        List<String> methodNameParts = generator.generateMethodName("the status have changed book of an order");
        /**
         * Confirm that the payment is complete -> [is, payment, complete] 수동태 아닌건 잘됨
         * Determining if an update is needed -> [is, update] 근데 수동태면 이상해짐
         *
         * 동사 + 명사 합치는 로직을 구현한다음에
         * 수통태면 그냥 동사 구분하는 다계에서 바로 합치고 넘겨야할듯
         *
         */
        System.out.println("Generated Method Name Parts: " + methodNameParts);
    }



}
