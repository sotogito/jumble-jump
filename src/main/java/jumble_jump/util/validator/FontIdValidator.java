package jumble_jump.util.validator;

import jumble_jump.domain.Fonts;

import java.awt.*;

public class FontIdValidator {

    public static void validateExistId(int input, Fonts fonts){
        if(!fonts.idExistFontId(input)){
            throw new IllegalArgumentException("입력된 폰트는 존재하지 않아요\n");
        }
    }
}
