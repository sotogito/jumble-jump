package jumble_jump.service;

import jumble_jump.domain.MethodName;
import jumble_jump.service.interfaces.CamelFormattingService;

import java.util.List;

public class CamelFormattingServiceImpl implements CamelFormattingService {
    private static final int CAPITALIZE_STAT_INDEX = 1;
    private static final int LOWERCASE_INDEX = 0;
    private static final int FIRST_CHARACTER_INDEX = 0;
    private static final int REMAINING_CHARACTERS_INDEX = 1;

    private final MethodName methodName;

    public CamelFormattingServiceImpl(MethodName methodName) {
        this.methodName = methodName;
    }

    @Override
    public void formatToCamelCase() {
        List<String> tokens = methodName.getMethodNameEntry();
        lowercaseFirstLetter(tokens);
        capitalize(tokens);
        joinCamelFormattedTokens();
    }

    @Override
    public void capitalize(List<String> tokens) {
        for (int i = CAPITALIZE_STAT_INDEX; i < tokens.size(); i++) {
            String token = tokens.get(i);
            String capitalizedToken = token.substring(FIRST_CHARACTER_INDEX, REMAINING_CHARACTERS_INDEX).toUpperCase()
                    + token.substring(REMAINING_CHARACTERS_INDEX);
            methodName.setCamelTypeMethodEntryByIndex(i, capitalizedToken);
        }
    }

    @Override
    public void lowercaseFirstLetter(List<String> tokens) {
        String firstLetter = tokens.get(LOWERCASE_INDEX);
        String lowerCaseFirstLetter = firstLetter.toLowerCase();
        methodName.setCamelTypeMethodEntryByIndex(LOWERCASE_INDEX, lowerCaseFirstLetter);
    }

    public void joinCamelFormattedTokens() {
        String camelTypeMethodName = String.join("", methodName.getMethodNameEntry());
        methodName.setCamelTypeMethodName(camelTypeMethodName);
    }

}
