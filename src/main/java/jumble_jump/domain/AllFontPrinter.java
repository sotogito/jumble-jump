package jumble_jump.domain;

import jumble_jump.domain.calligrapher.Calligrapher;

import java.awt.*;
import java.security.KeyStore;
import java.util.Map;

public class AllFontPrinter {
    private final String FONT_PRINT_EXAMPLE ="AbCdEf";

    public String print(CalligrapherFinder finder, Fonts fonts){
        StringBuilder result = new StringBuilder();

        for(Map.Entry<Integer, String> entry : fonts.getFonts().entrySet()){
            String printout = finder.getCalligrapy(entry.getKey(),entry.getValue());
            result.append(entry.getKey()+1).append(":")
                    .append("\n")
                    .append(printout)
                    .append("\n");
        }
        return result.toString();
    }
}
