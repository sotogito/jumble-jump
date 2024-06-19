package alice_in_wonderland.main.domain.charactor;

import alice_in_wonderland.main.domain.number.NumberMaker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class AliceTest {
    NumberMaker numberMaker;
    Alice alice;

    @BeforeEach
    void setUp() {
        numberMaker = mock(NumberMaker.class); //numberMaker 인터페이스의 가짜 객체 생성
        when(numberMaker.generate(anyInt(), anyInt())).thenReturn(7); //숫자 생성 메서드 호출 시 7을 반환하도록 설정
        alice = new Alice(numberMaker); //그 값을 앨리스에게 부여
    }


    @Test
    void growSize() {
    }

    @Test
    void reduceSize() {
    }

    @Test
    void aliceIsBiggerThanKey() {
    }

    @Test
    void isSmallerThanDoor() {
    }

    @Test
    void setAtOpenSize() {
    }

    @Test
    void setAtGoOverSize() {
    }

    @Test
    void getAtOpenSize() {
    }

    @Test
    void getAtGoOverSize() {
    }

    @Test
    void testToString() {
    }
}