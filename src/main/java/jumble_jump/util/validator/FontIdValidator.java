package jumble_jump.util.validator;

import jumble_jump.domain.Fonts;
import jumble_jump.util.message.ErrorMessage;

import java.awt.*;

public class FontIdValidator {

    public static void validateExistId(int input, Fonts fonts){
        if(!fonts.idExistFontId(input)){
            throw new IllegalArgumentException(ErrorMessage.CANT_FIND_FONT_BY_FONT_ID);
        }
    }
}
