package jumble_jump.domain;

import jumble_jump.util.message.ErrorMessage;

import java.util.HashMap;
import java.util.Map;

public class Fonts {

    private Map<Integer,String> fonts = new HashMap<>();

    public Fonts(Map<Integer, String> fonts) {
        this.fonts = fonts;
    }

    //TODO optional 활용
    public String getFontName(int fontId) {
        try{
            return fonts.get(fontId);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(ErrorMessage.CANT_FIND_FONT);
        }
    }

    public boolean idExistFontId(int id) {
        return fonts.containsKey(id);
    }

    public Map<Integer,String> getFonts() {
        return fonts;
    }

}
