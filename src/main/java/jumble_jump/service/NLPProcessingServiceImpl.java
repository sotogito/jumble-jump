package jumble_jump.service;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import jumble_jump.domain.*;
import jumble_jump.service.interfaces.NLPProcessingService;
import jumble_jump.util.ExclusionWords;
import jumble_jump.util.NLPConstants;
import jumble_jump.util.ReplaceWords;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class NLPProcessingServiceImpl implements NLPProcessingService {
    public static final String ANNOTATORS_KEY = "annotators";
    public static final String ANNOTATORS = "tokenize,ssplit,pos,lemma";

    private final EnglishPosEntry englishPosEntry;
    private final MethodName methodName;
    private final WordReplacer wordReplacer;

    private final List<Integer> preNouns = new ArrayList<>();
    private final List<Integer> ppVerbs = new ArrayList<>();

    public NLPProcessingServiceImpl(EnglishPosEntry englishPosEntry, MethodName methodName) {
        this.englishPosEntry = englishPosEntry;
        this.methodName = methodName;
        wordReplacer = new WordReplacer();
    }

    @Override
    public void handlePos(String english) {
        CoreDocument tokenizeDocument = initCoreDocumentation(english); //note 문장 초기설정
        tokenizeFullSentence(tokenizeDocument); //note 전체 문장 품사 고려하여 토큰화
        setPreNounsDividingNouns(); //note 전치사 기준 명사 나누기
        setMethodNamePosToken(); //note 합치기
    }

    @Override
    public CoreDocument initCoreDocumentation(String english) {
        Properties props = new Properties();

        props.setProperty(ANNOTATORS_KEY, ANNOTATORS);
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        CoreDocument document = new CoreDocument(english);
        pipeline.annotate(document);
        return document;
    }

    @Override
    public void tokenizeFullSentence(CoreDocument tokenizeDocument) {
        for (CoreLabel token : tokenizeDocument.tokens()) {
            englishPosEntry.addToken(token);
            String pos = token.tag();
            String word = token.word();
            int index = token.index();

            updateByPos(pos, word, index);
        }
    }

    private void updateByPos(String pos, String word, int index) {
        if (NLPConstants.verbsPosTags.contains(pos)) {
            englishPosEntry.addVerb(index);
        } else if (NLPConstants.nounsPosTags.contains(pos)) {
            englishPosEntry.addNoun(index);
        } else if (NLPConstants.adjectivesPosTags.contains(pos)) {
            englishPosEntry.addAdjective(index);
        } else if (NLPConstants.prepositionsPosTag.equals(pos)) {
            if (ExclusionWords.exclusionWord.contains(word)) {
                return;
            }
            englishPosEntry.addPreposition(index);
        }
    }

    @Override
    public void setPreNounsDividingNouns() {
        List<Integer> remainingNouns = new ArrayList<>();

        if (!englishPosEntry.isEmptyPreposition()) {
            updatePreNounsByPreposition(remainingNouns);
            return;
        }
        preNouns.addAll(englishPosEntry.getNounsIndexList());
        englishPosEntry.setNounsIndex(remainingNouns);
    }

    private void updatePreNounsByPreposition(List<Integer> remainingNouns) {
        int prepositionIndex = englishPosEntry.getPrepositionIndex();
        for (Integer nounIndex : englishPosEntry.getNounsIndexList()) {
            if (nounIndex < prepositionIndex) {
                preNouns.add(nounIndex);
                continue;
            }
            remainingNouns.add(nounIndex);
        }
        englishPosEntry.setNounsIndex(remainingNouns);
    }

    @Override
    public void setMethodNamePosToken() {
        if (!englishPosEntry.isEmptyVerbsIndex()) {
            changeInfinitiveVerb();
        }

        if (!englishPosEntry.isEmptyAdjective()) {
            for (int index : englishPosEntry.getAdjectivesIndexList()) {
                CoreLabel adjectives = englishPosEntry.getTokenByIndex(index);
                methodName.addMethodNameEntry(adjectives.word());
            }
        }

        if (!preNouns.isEmpty()) {
            for (int index : preNouns) {
                CoreLabel preNoun = englishPosEntry.getTokenByIndex(index);
                methodName.addMethodNameEntry(preNoun.word());
            }
        }

        if (!ppVerbs.isEmpty()) {
            for (int index : ppVerbs) {
                CoreLabel verbs = englishPosEntry.getTokenByIndex(index);
                methodName.addMethodNameEntry(verbs.word());
            }
        }


        if (!englishPosEntry.isEmptyPreposition()) {
            int index = englishPosEntry.getPrepositionIndex();
            CoreLabel preposition = englishPosEntry.getTokenByIndex(index);
            methodName.addMethodNameEntry(preposition.word());
        }

        if (!englishPosEntry.isEmptyNoun()) {
            for (int index : englishPosEntry.getNounsIndexList()) {
                CoreLabel noun = englishPosEntry.getTokenByIndex(index);
                methodName.addMethodNameEntry(noun.word());
            }
        }

    }

    private void changeInfinitiveVerb() {
        int verbsIndexListSize = englishPosEntry.getVerbsIndexListSize();

        for (int i = 0; i < verbsIndexListSize; i++) {
            int verbIndexFromToken = englishPosEntry.getVerbsIndexByIndex(i);
            CoreLabel token = englishPosEntry.getTokenByIndex(verbIndexFromToken);
            String lemma = token.lemma();

            if (isBooleanPassiveType(i, verbsIndexListSize, lemma)) {
                return;
            }
            methodName.addMethodNameEntry(wordReplacer.replaceOrdinaryVerb(lemma));
        }
    }

    private boolean isBooleanPassiveType(int i, int verbsIndexListSize, String lemma) {
        if (ReplaceWords.verbLemma.contains(lemma)) {
            updateMethodNameForAuxiliaryVerb(lemma);

            if (i < verbsIndexListSize - 1) {
                collectPassiveVerbs(i);
            }
            return true;
        }
        return false;
    }

    private void updateMethodNameForAuxiliaryVerb(String lemma) {
        methodName.clearMethodNameEntry();
        Optional<String> replacedWord = wordReplacer.replaceBeHave(lemma);
        if (replacedWord.isEmpty()) {
            return;
        }
        methodName.addMethodNameEntry(replacedWord.get());
    }

    private void collectPassiveVerbs(int nowVerbsIndexListOrder) {
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

}
