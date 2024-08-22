package jumble_jump.service.interfaces;

import jumble_jump.domain.MethodName;

import java.util.List;

public interface CamelFormattingService {
    void formatToCamelCase();
    void capitalize(List<String> tokens);
    void lowercaseFirstLetter(List<String> tokens);
}
