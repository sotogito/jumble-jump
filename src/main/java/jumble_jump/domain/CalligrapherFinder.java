package jumble_jump.domain;

import jumble_jump.domain.calligrapher.BasicsCalligrapher;
import jumble_jump.domain.calligrapher.Calligrapher;
import jumble_jump.domain.calligrapher.VariousCalligrapher;

public class CalligrapherFinder {
    private final Fonts fonts;
    private final String endStatus = Status.END.getStatus();
    //private boolean isEnd;

    public CalligrapherFinder(Fonts fonts) {
        this.fonts = fonts;
    }

    public boolean isEnd(String input){
        return input.equals(endStatus);
    }

    public String getCalligrapy(int fontId, String printout){
        String fontName = fonts.getFontName(fontId);
        Calligrapher calligrapher = getCalligrapher(fontId);

        return calligrapher.write(fontName, printout);
    }

    private Calligrapher getCalligrapher(int fontId){
        if (fontId == BasicsCalligrapher.basicFontNumber){
            return new BasicsCalligrapher();
        }
        return new VariousCalligrapher();
    }

}
