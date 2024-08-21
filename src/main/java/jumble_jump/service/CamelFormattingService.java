package jumble_jump.service;

import jumble_jump.domain.MethodName;

import java.util.List;

public class CamelFormattingService {
    private final MethodName methodName;

    private final int CAPITALIZE_STAT_INDEX = 1;
    private final int LOWERCASE_INDEX = 0;

    public CamelFormattingService(MethodName methodName) {
        this.methodName = methodName;
    }

    public void formatToCamelCase() {
        List<String> tokens = methodName.getMethodNameEntry();

        lowercaseFirstLetter(tokens);
        capitalize(tokens);
    }

    private void capitalize(List<String> tokens){
        for (int i = CAPITALIZE_STAT_INDEX; i < tokens.size(); i++) {
            String token = tokens.get(i);
            String capitalizedToken = token.substring(0, 1).toUpperCase() + token.substring(1);

            methodName.setCamelTypeMethodName(i, capitalizedToken);
        }
    }

    private void lowercaseFirstLetter(List<String> tokens){
        String firstLetter = tokens.get(LOWERCASE_INDEX);
        String lowerCaseFirstLetter = firstLetter.toLowerCase();

        methodName.setCamelTypeMethodName(LOWERCASE_INDEX,lowerCaseFirstLetter);
    }

}
