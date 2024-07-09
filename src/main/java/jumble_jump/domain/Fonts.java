package jumble_jump.domain;

import java.util.HashMap;
import java.util.Map;

public class Fonts {

    private Map<Integer,String> fonts = new HashMap<>();

    public Fonts(Map<Integer, String> fonts) {
        this.fonts = fonts;
    }

    public String getFontName(int fontId) {
        return fonts.get(fontId);
    }

}
