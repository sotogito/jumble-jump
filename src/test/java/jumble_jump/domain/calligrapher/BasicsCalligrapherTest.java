package jumble_jump.domain.calligrapher;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicsCalligrapherTest {
    Calligrapher calligrapher = new BasicsCalligrapher();

    @Test
    void 출력물_확인(){
        System.out.println(calligrapher.write("basices","hello"));
    }


}