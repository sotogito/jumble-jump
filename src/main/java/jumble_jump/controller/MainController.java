package jumble_jump.controller;

import jumble_jump.domain.English;
import jumble_jump.domain.EnglishPosEntry;
import jumble_jump.domain.Korean;
import jumble_jump.repository.TranslationEntryRepository;
import jumble_jump.service.CamelFormattingService;
import jumble_jump.service.EnglishTranslationService;
import jumble_jump.service.MethodNameGeneratorService;
import jumble_jump.service.NLPProcessingService;
import jumble_jump.view.Input;

public class MainController {

    public void main(){
        TranslationEntryRepository translationEntryRepository = new TranslationEntryRepository(
                new English(), new Korean(), new EnglishPosEntry());
        MethodNameGeneratorService methodNameGeneratorService = createMethodNameGeneratorService(translationEntryRepository);




        generateMethod(methodNameGeneratorService);

        System.out.println(translationEntryRepository.getKorean());
        System.out.println(translationEntryRepository.getEnglish());

    }

    private void generateMethod(MethodNameGeneratorService methodNameGeneratorService){
        while (true){
            try{
                String korean = Input.inputKorean();
                methodNameGeneratorService.generate(korean);
                break;

            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private MethodNameGeneratorService createMethodNameGeneratorService(TranslationEntryRepository translationEntryRepository){
        EnglishTranslationService englishTranslationService = createEnglishTranslationService(translationEntryRepository);
        NLPProcessingService nlpProcessingService = createNLPProcessingService();
        CamelFormattingService camelFormattingService = createCamelFormattingService();

        return new MethodNameGeneratorService(englishTranslationService,nlpProcessingService,camelFormattingService);
    }

    private EnglishTranslationService createEnglishTranslationService(TranslationEntryRepository translationEntryRepository){
        return new EnglishTranslationService(translationEntryRepository);
    }

    private NLPProcessingService createNLPProcessingService(){
        return new NLPProcessingService();
    }

    private CamelFormattingService createCamelFormattingService(){
        return new CamelFormattingService();
    }

}
