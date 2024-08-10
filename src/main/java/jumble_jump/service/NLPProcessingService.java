package jumble_jump.service;

import org.apache.lucene.analysis.en.EnglishAnalyzer;

public class NLPProcessingService {

    private final EngilshTranslationService translationService;
    private final NLPProcessingService nlpService;
    private final CamelFormattinfService camelFormattinfService;

    public NLPProcessingService(
            CamelFormattinfService camelFormattinfService, EngilshTranslationService translationService, NLPProcessingService nlpService) {
        this.camelFormattinfService = camelFormattinfService;
        this.translationService = translationService;
        this.nlpService = nlpService;
    }
}
