package jumble_jump.domain.calligrapher;


import com.github.lalyos.jfiglet.FigletFont;

import java.io.IOException;
import java.io.InputStream;

public class VariousCalligrapher implements Calligrapher {

    private static final String FONT_PATH = "/fonts/ours/%s%s";
    private static final String EXTENSION_PATH = ".flf";

    @Override
    public String write(String fontName, String printout) {
        try{
            String path = String.format(FONT_PATH, fontName, EXTENSION_PATH);
            InputStream fontStream = VariousCalligrapher.class.getResourceAsStream(path);
            return FigletFont.convertOneLine(fontStream, printout);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
