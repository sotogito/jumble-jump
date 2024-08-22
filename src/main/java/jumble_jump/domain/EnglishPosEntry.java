package jumble_jump.domain;

import edu.stanford.nlp.ling.CoreLabel;

import java.util.ArrayList;
import java.util.List;

public class EnglishPosEntry {

    private List<CoreLabel> tokens = new ArrayList<>();

    private List<Integer> verbsIndex = new ArrayList<>();
    private List<Integer> nounsIndex = new ArrayList<>();
    private List<Integer> adjectivesIndex = new ArrayList<>();
    private List<Integer> prepositionsIndex = new ArrayList<>();

    public EnglishPosEntry() {
    }

    public int getVerbsIndexByIndex(int index) {
        return verbsIndex.get(index);
    }

    public List<Integer> getVerbsIndex() {
        return verbsIndex;
    }

    public int getVerbsIndexListSize(){
        return verbsIndex.size();
    }

    public CoreLabel getTokenByIndex(int index) {
        int indexFromTokenList = index-1;
        return tokens.get(indexFromTokenList);

        /*
        for(CoreLabel token : tokens) {
            if(token.index() == index){
                return token;
            }
        }

         */
    }

    public void addToken(CoreLabel token) {
        tokens.add(token);
    }

    public void addVerb(int verbIndex) {
        verbsIndex.add(verbIndex);
    }

    public void addNoun(int nounIndex) {
        nounsIndex.add(nounIndex);
    }

    public void addAdjective(int adjectiveIndex) {
        adjectivesIndex.add(adjectiveIndex);
    }

    public void addPreposition(int prepositionIndex) {
        prepositionsIndex.add(prepositionIndex);
    }

    public boolean isEmptyVerbsIndex() {
        return verbsIndex.isEmpty();
    }

    public boolean isEmptyPreposition(){
        return prepositionsIndex.isEmpty();
    }

    public boolean isEmptyAdjective(){
        return adjectivesIndex.isEmpty();
    }

    public boolean isEmptyNoun(){
        return nounsIndex.isEmpty();
    }

    public boolean isEmptyVerb() {
        return verbsIndex.isEmpty();
    }

    public void setNounsIndex(List<Integer> nounsIndex){
        this.nounsIndex = nounsIndex;
    }

    public int getPrepositionIndex(){
        return prepositionsIndex.get(0);
    }

    public List<Integer> getNounsIndexList() {
        return nounsIndex;
    }

    public List<Integer> getAdjectivesIndexList() {
        return adjectivesIndex;
    }

    public CoreLabel getCoreLabelTokenByOrder(int order) {
        for (CoreLabel token : tokens) {
            if(token.index() == order){
                return token;
            }
        }
        return null;
    }

}
