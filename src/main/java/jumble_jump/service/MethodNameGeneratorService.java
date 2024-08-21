package jumble_jump.service;

import jumble_jump.repository.TranslationEntryRepository;

public class MethodNameGeneratorService {
    private final EnglishTranslationService translationService;
    private final NLPProcessingService nlpService;
    private final CamelFormattingService camelFormattingService;

    public MethodNameGeneratorService(
            EnglishTranslationService translationService, NLPProcessingService nlpService, CamelFormattingService camelFormattingService) {
        this.camelFormattingService = camelFormattingService;
        this.translationService = translationService;
        this.nlpService = nlpService;

    }

    public void generate(String inputKorean) throws Exception {
        String english = translationService.translate(inputKorean);
        nlpService.handlePos(english);
        camelFormattingService.formatToCamelCase();
    }


}
