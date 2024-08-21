package jumble_jump.service;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import jumble_jump.domain.*;
import jumble_jump.util.ExclusionWords;
import jumble_jump.util.NLPConstants;
import jumble_jump.repository.TranslationEntryRepository;
import jumble_jump.util.ReplaceWords;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

/**
 * 단수 복수
 *반환하기 - return - get으로 수정
 *
 */
public class NLPProcessingService {

    private final TranslationEntryRepository translationEntryRepository;
    private final EnglishPosEntry englishPosEntry;
    private final MethodName methodName;
    private final WordReplacer wordReplacer;

    private List<Integer> preNouns = new ArrayList<>();
    private List<Integer> ppVerbs = new ArrayList<>();


    public NLPProcessingService(TranslationEntryRepository translationEntryRepository, EnglishPosEntry englishPosEntry, MethodName methodName) {
        this.translationEntryRepository = translationEntryRepository;
        this.englishPosEntry = englishPosEntry;
        this.methodName = methodName;
        wordReplacer = new WordReplacer();
    }

    public void handlePos(String english){
        CoreDocument tokenizeDocument = initCoreDocumentation(english); //note 문장 초기설정
        fullSentenceTokenize(tokenizeDocument); //note 전체 문장 품사 고려하여 토큰화

        setPreNounsDividingNouns(); //note 전치사 기준 명사 나누기

        setMethodNamePosToken(); //note 합치기
    }

    private void setMethodNamePosToken(){
        if(!englishPosEntry.isEmptyVerbsIndex()){
            changeInfinitiveVerb();
        }

        if(!englishPosEntry.isEmptyAdjective()){
            for(int index : englishPosEntry.getAdjectivesIndexList()){
                CoreLabel adjectives = englishPosEntry.getTokenByIndex(index);
                methodName.addMethodNameEntry(adjectives.word());
            }
        }

        if(!preNouns.isEmpty()){
            for(int index : preNouns){
                CoreLabel preNoun = englishPosEntry.getTokenByIndex(index);
                methodName.addMethodNameEntry(preNoun.word());
            }
        }

        if(!ppVerbs.isEmpty()){
            for(int index : ppVerbs){
                CoreLabel verbs = englishPosEntry.getTokenByIndex(index);
                methodName.addMethodNameEntry(verbs.word());
            }
        }


        if(!englishPosEntry.isEmptyPreposition()){
            int index = englishPosEntry.getPrepositionIndex();
            CoreLabel preposition = englishPosEntry.getTokenByIndex(index);
            methodName.addMethodNameEntry(preposition.word());
        }

        if(!englishPosEntry.isEmptyNoun()){
            for(int index : englishPosEntry.getNounsIndexList()){
                CoreLabel noun = englishPosEntry.getTokenByIndex(index);
                methodName.addMethodNameEntry(noun.word());
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

            if(ReplaceWords.verbLemma.contains(lemma)){
                handleBooleanType(lemma);

                if(i < verbsIndexListSize -1){
                    handlePassiveType(i);
                }
                return;
            }
            methodName.addMethodNameEntry(WordReplacer.replaceOrdinaryVerb(lemma));
        }
    }

    private void handleBooleanType(String lemma){
        methodName.clearMethodNameEntry();
        Optional<String> replacedWord = WordReplacer.replaceBeHave(lemma);
        if(replacedWord.isEmpty()){
            return;
        }
        methodName.addMethodNameEntry(replacedWord.get());
    }

    private void handlePassiveType(int nowVerbsIndexListOrder) {
        boolean isPassiveType;
        int verbsIndexListSize = englishPosEntry.getVerbsIndexListSize();

        while (nowVerbsIndexListOrder < verbsIndexListSize - 1) {
            int nextVerbIndexFromToken = englishPosEntry.getVerbsIndexByIndex(nowVerbsIndexListOrder + 1);
            CoreLabel token = englishPosEntry.getTokenByIndex(nextVerbIndexFromToken);

            isPassiveType = token.tag().equals(NLPConstants.passiveTypeTage);

            if (isPassiveType) {
                ppVerbs.add(token.index());
                nowVerbsIndexListOrder++;
                continue;
            }
            break;
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

    }



    private void fullSentenceTokenize(CoreDocument tokenizeDocument){
        for(CoreLabel token : tokenizeDocument.tokens()){
            englishPosEntry.addToken(token);
            String pos = token.tag();
            String word = token.word();
            int index = token.index();

            if(NLPConstants.verbsPosTags.contains(pos)){
                englishPosEntry.addVerb(index);
            }else if(NLPConstants.nounsPosTags.contains(pos)){
                englishPosEntry.addNoun(index);
            }else if(NLPConstants.adjectivesPosTags.contains(pos)){
                englishPosEntry.addAdjective(index);
            } else if (NLPConstants.prepositionsPosTag.equals(pos)) {
                if(ExclusionWords.exclusionWord.contains(word)){
                    continue;
                }
                englishPosEntry.addPreposition(index);
            }
        }
    }

    private CoreDocument initCoreDocumentation(String english){
        Properties props = new Properties();

        props.setProperty("annotators", "tokenize,ssplit,pos,lemma");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        CoreDocument document = new CoreDocument(english);
        pipeline.annotate(document);
        return document;
    }






}
