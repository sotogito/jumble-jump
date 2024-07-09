package jumble_jump.domain.calligrapher;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VariousCalligrapherTest {

    Calligrapher calligrapher = new VariousCalligrapher();

    @Test
    void 출력물_확인(){
        String fontName = "block";
        String printout = "hello";

        System.out.println(calligrapher.write(fontName, printout));
    }

}