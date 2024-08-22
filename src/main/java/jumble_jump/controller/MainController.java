package jumble_jump.controller;

import jumble_jump.domain.English;
import jumble_jump.domain.EnglishPosEntry;
import jumble_jump.domain.Korean;
import jumble_jump.domain.MethodName;
import jumble_jump.repository.TranslationEntryRepository;
import jumble_jump.service.CamelFormattingServiceImpl;
import jumble_jump.service.EnglishTranslationServiceImpl;
import jumble_jump.service.MethodNameGeneratorService;
import jumble_jump.service.NLPProcessingServiceImpl;
import jumble_jump.service.interfaces.CamelFormattingService;
import jumble_jump.service.interfaces.EnglishTranslationService;
import jumble_jump.service.interfaces.NLPProcessingService;
import jumble_jump.view.Input;
import jumble_jump.view.Output;
import jumble_jump.util.ErrorMessage;

public class MainController {

    private MethodNameGeneratorService methodNameGeneratorService;

    public void main(){
        TranslationEntryRepository translationEntryRepository = new TranslationEntryRepository(new English(), new Korean());
        EnglishPosEntry englishPosEntry = new EnglishPosEntry();
        MethodName methodName = new MethodName();

        methodNameGeneratorService = createMethodNameGeneratorService(translationEntryRepository, englishPosEntry, methodName);
        generateMethod();

        printCamelCaseMethodName();
    }

    private void generateMethod(){
        while (true){
            try{
                methodNameGeneratorService.generate(Input.inputKorean());
                break;
            }catch (IllegalArgumentException e){
                Output.printError(e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                Output.printError(ErrorMessage.UNEXPECTED_ERROR.name());
                throw new RuntimeException(e);
            }
        }
    }

    private MethodNameGeneratorService createMethodNameGeneratorService(
            TranslationEntryRepository translationEntryRepository, EnglishPosEntry englishPosEntry, MethodName methodName){

        EnglishTranslationService englishTranslationService = createEnglishTranslationService(translationEntryRepository);
        NLPProcessingService nlpProcessingService = createNLPProcessingService(englishPosEntry, methodName);
        CamelFormattingService camelFormattingService = createCamelFormattingService(methodName);

        return new MethodNameGeneratorService(
                englishTranslationService,nlpProcessingService,camelFormattingService,methodName);
    }

    private EnglishTranslationService createEnglishTranslationService(TranslationEntryRepository translationEntryRepository){
        return new EnglishTranslationServiceImpl(translationEntryRepository);
    }

    private NLPProcessingService createNLPProcessingService(EnglishPosEntry englishPosEntry, MethodName methodName){
        return new NLPProcessingServiceImpl(englishPosEntry,methodName);
    }

    private CamelFormattingService createCamelFormattingService(MethodName methodName){
        return new CamelFormattingServiceImpl(methodName);
    }

    private void printCamelCaseMethodName(){
        Output.printCamelCaseMethodName(methodNameGeneratorService.getCamelCaseMethodName());
    }

}
