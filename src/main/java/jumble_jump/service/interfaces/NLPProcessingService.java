package jumble_jump.service.interfaces;

import edu.stanford.nlp.pipeline.CoreDocument;

public interface NLPProcessingService {
    void handlePos(String english);

    CoreDocument initCoreDocumentation(String english);

    void tokenizeFullSentence(CoreDocument tokenizeDocument);

    void setPreNounsDividingNouns();

    void setMethodNamePosToken();

}
