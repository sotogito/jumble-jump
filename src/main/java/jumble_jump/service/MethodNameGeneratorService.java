package jumble_jump.service;


import jumble_jump.domain.MethodName;
import jumble_jump.service.interfaces.CamelFormattingService;
import jumble_jump.service.interfaces.EnglishTranslationService;
import jumble_jump.service.interfaces.NLPProcessingService;

public class MethodNameGeneratorService {
    private final EnglishTranslationService translationService;
    private final NLPProcessingService nlpService;
    private final CamelFormattingService camelFormattingService;
    private final MethodName methodName;

    public MethodNameGeneratorService(
            EnglishTranslationService translationService, NLPProcessingService nlpService, CamelFormattingService camelFormattingService, MethodName methodName) {
        this.camelFormattingService = camelFormattingService;
        this.translationService = translationService;
        this.nlpService = nlpService;
        this.methodName = methodName;
    }

    public void generate(String inputKorean) throws Exception {
        String english = translationService.translate(inputKorean);
        nlpService.handlePos(english);
        camelFormattingService.formatToCamelCase();
    }

    public String getCamelCaseMethodName() {
        return methodName.getCamelTypeMethodName();
    }

}
