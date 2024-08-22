package jumble_jump.service;


import jumble_jump.service.interfaces.CamelFormattingService;
import jumble_jump.service.interfaces.EnglishTranslationService;
import jumble_jump.service.interfaces.NLPProcessingService;

public class MethodNameGeneratorService {
    //todo 선언된 서비스들 인터페이스로
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
