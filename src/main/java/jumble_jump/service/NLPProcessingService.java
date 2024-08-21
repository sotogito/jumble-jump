package jumble_jump.service;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import jumble_jump.domain.EnglishPosEntry;
import jumble_jump.domain.MethodName;
import jumble_jump.domain.Parts;
import jumble_jump.repository.TranslationEntryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class NLPProcessingService {

    private final TranslationEntryRepository translationEntryRepository;
    private final EnglishPosEntry englishPosEntry;
    //private final MethodName methodName;
    private List<Integer> preNouns = new ArrayList<>();


    public NLPProcessingService(TranslationEntryRepository translationEntryRepository, EnglishPosEntry englishPosEntry) {
        this.translationEntryRepository = translationEntryRepository;
        this.englishPosEntry = englishPosEntry;
    }

    public void handlePos(){
        CoreDocument tokenizeDocument = initCoreDocumentation(); //note 문장 초기설정
        fullSentenceTokenize(tokenizeDocument); //note 전체 문장 품사 고려하여 토큰화

        setPreNounsDividingNouns(); //note 전치사 기준 명사 나누기

        setMethodNamePosToken(); //note 합치기
    }

    private void setMethodNamePosToken(){
        /**
         * 동사 -> pre명사 -> 형용사 -> 전치사 -> 명사
         */
        if(!englishPosEntry.isEmptyVerbsIndex()){
            changeInfinitiveVerb();
        }

        if(englishPosEntry.isEmptyPreposition()){ //note 전치사가 없으면 preNouns하고 형용사만 고려해도 됨
            if(!preNouns.isEmpty()){
                for(int index : preNouns){
                    CoreLabel preNoun = englishPosEntry.getTokenByIndex(index);
                    translationEntryRepository.addMethodNameEntry(preNoun.word());
                }
            }

            if(!englishPosEntry.isEmptyAdjective()){
                for(int index : englishPosEntry.getAdjectivesIndexList()){
                    CoreLabel adjectives = englishPosEntry.getTokenByIndex(index);
                    translationEntryRepository.addMethodNameEntry(adjectives.word());
                }
            }
            return;
        }


        if(!englishPosEntry.isEmptyPreposition()){
            int index = englishPosEntry.getPrepositionIndex();
            CoreLabel preposition = englishPosEntry.getTokenByIndex(index);
            translationEntryRepository.addMethodNameEntry(preposition.word());
        }

        if(!englishPosEntry.isEmptyNoun()){
            for(int index : englishPosEntry.getNounsIndexList()){
                CoreLabel noun = englishPosEntry.getTokenByIndex(index);
                translationEntryRepository.addMethodNameEntry(noun.word());
            }
        }

    }



    //todo 단수 복수 고려
    private void changeInfinitiveVerb(){
        int verbsIndexListSize = englishPosEntry.getVerbsIndexListSize();

        for(int i = 0; i < verbsIndexListSize; i++){
            int verbIndexFromToken = englishPosEntry.getVerbsIndexByIndex(i);
            CoreLabel token = englishPosEntry.getTokenByIndex(verbIndexFromToken);
            String lemma = token.lemma();

            if(Parts.verbLemma.contains(lemma)){
                handleBooleanType(lemma);
                //todo 수동태인경우, be동사 뒤에 집어 넣어야됨
                //fixme 대문자로 넣어야됨
                if(i < verbsIndexListSize -1){
                    handlePassiveType(i);
                }
                return;
            }
            translationEntryRepository.addMethodNameEntry(lemma);
        }
    }

    private void handleBooleanType(String lemma){
        translationEntryRepository.clearMethodNameEntry();

        //note 동사원형이 be,have일 경우 넣기
        if(lemma.equals("be")){
            translationEntryRepository.addMethodNameEntry("is");
        }else if(lemma.equals("have")){
            translationEntryRepository.addMethodNameEntry("have");
        }
    }

    private void handlePassiveType(int nowVerbsIndexListOrder){
        int nextVerbIndexFromToken = englishPosEntry.getVerbsIndexByIndex(nowVerbsIndexListOrder+1);
        CoreLabel token = englishPosEntry.getTokenByIndex(nextVerbIndexFromToken);

        if(token.tag().equals(Parts.passiveTypeTage)){
            translationEntryRepository.addMethodNameEntry(token.word()); //note p.p 형태 그래도 넣음
        }
    }




    private void setPreNounsDividingNouns(){
        List<Integer> remainingNouns = new ArrayList<>();

        if(englishPosEntry.isEmptyPreposition()){ //note 전치사가 없으면 실행 preNouns 생성하기
            preNouns.addAll(englishPosEntry.getNounsIndexList());
            englishPosEntry.setNounsIndex(remainingNouns);
            return;
        }

        int prepositionIndex = englishPosEntry.getPrepositionIndex();
        for(Integer nounIndex : englishPosEntry.getNounsIndexList()){
            if(nounIndex < prepositionIndex){ //note 전치사 앞 명사
                preNouns.add(nounIndex);
                continue;
            }
            remainingNouns.add(nounIndex);
        }
        englishPosEntry.setNounsIndex(remainingNouns);




        /*

        if(!englishPosEntry.isEmptyPreposition()){
            int prepositionIndex = englishPosEntry.getPrepositionIndex();

            for(Integer nounIndex : englishPosEntry.getNounsIndexList()){
                if(nounIndex < prepositionIndex){ //note 전치사 앞 명사
                    preNouns.add(nounIndex);
                    continue;
                }
                remainingNouns.add(nounIndex);
            }
            englishPosEntry.setNounsIndex(remainingNouns);
            return;
        }

         */
        //fixme 굳이 뺴지 않고 전치사 유무로 pre부터 넣을지 말지 결정
        //preNouns.addAll(englishPosEntry.getNounsIndexList());
        //englishPosEntry.setNounsIndex(remainingNouns);
    }



    private void fullSentenceTokenize(CoreDocument tokenizeDocument){
        for(CoreLabel token : tokenizeDocument.tokens()){
            englishPosEntry.addToken(token);
            String pos = token.tag();
            String word = token.word();
            int index = token.index();

            if(Parts.verbsPosTags.contains(pos)){
                englishPosEntry.addVerb(index);
            }else if(Parts.nounsPosTags.contains(pos)){
                englishPosEntry.addNoun(index);
            }else if(Parts.adjectivesPosTags.contains(pos)){
                englishPosEntry.addAdjective(index);
            } else if (Parts.prepositionsPosTag.equals(pos)) {
                if(Parts.exclusionWord.contains(word)){
                    continue;
                }
                englishPosEntry.addPreposition(index);
            }
        }
    }

    private CoreDocument initCoreDocumentation(){
        String english = translationEntryRepository.getEnglish(); //fixme 테스트를 위해서 인수로 받는게 좋은건가

        Properties props = new Properties();

        props.setProperty("annotators", "tokenize,ssplit,pos,lemma");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        CoreDocument document = new CoreDocument(english);
        pipeline.annotate(document);
        return document;
    }






}
