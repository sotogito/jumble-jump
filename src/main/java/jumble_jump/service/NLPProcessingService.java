package jumble_jump.service;

import edu.stanford.nlp.pipeline.CoreDocument;

public interface NLPProcessingService {
    void handlePos(String english);

    CoreDocument initCoreDocumentation(String english);
    void tokenizeFullSentence(CoreDocument tokenizeDocument);
    void setPreNounsDividingNouns();
    void setMethodNamePosToken();
}
