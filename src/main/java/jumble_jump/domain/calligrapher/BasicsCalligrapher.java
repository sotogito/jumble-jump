package jumble_jump.domain.calligrapher;

import com.github.lalyos.jfiglet.FigletFont;

import java.io.IOException;

public class BasicsCalligrapher implements Calligrapher {
    public final static int basicFontNumber = 0;

    @Override
    public String write(String fontName, String printout) {
        try{
            return FigletFont.convertOneLine(printout);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
