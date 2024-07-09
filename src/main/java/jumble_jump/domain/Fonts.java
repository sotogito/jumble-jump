package jumble_jump.domain;

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
            throw new IllegalArgumentException("폰트를 찾을 수 없어요.\n");
        }
    }

    public boolean idExistFontId(int id) {
        return fonts.containsKey(id);
    }

    public Map<Integer,String> getFonts() {
        return fonts;
    }

}
