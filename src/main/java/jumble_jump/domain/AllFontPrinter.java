package jumble_jump.domain;

import jumble_jump.domain.calligrapher.Calligrapher;

import java.awt.*;
import java.security.KeyStore;
import java.util.Map;

public class AllFontPrinter {
    private final String FONT_PRINT_EXAMPLE ="AbCdEf";
    private final int INDEX_ADJUSTMENT_NUMBER =1;
    private final String PRINT_FONTS_FORMAT = """
            %d :
             %s
            """;

    public String print(CalligrapherFinder finder, Fonts fonts){
        StringBuilder result = new StringBuilder();

        for(Map.Entry<Integer, String> entry : fonts.getFonts().entrySet()){
            int printFontIndex = entry.getKey()+INDEX_ADJUSTMENT_NUMBER;
            String printout = finder.getCalligrapy(entry.getKey(),entry.getValue());
            result.append(
                    String.format(PRINT_FONTS_FORMAT,printFontIndex,printout)
            );

        }
        return result.toString();
    }

}
