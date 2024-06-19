package alice_in_wonderland.main.domain.charactor;

import alice_in_wonderland.main.domain.command.execute.GoOverCommand;
import alice_in_wonderland.main.domain.command.execute.OpenCommand;
import alice_in_wonderland.main.domain.number.NumberMaker;
import alice_in_wonderland.main.util.message.ErrorMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class AliceTest {
    NumberMaker numberMaker;
    Alice alice;
    Key key = new Key();

    /*
    1. 앨리스 크기가 커짐 확인
    2. 앨리스 크기가 작아짐 확인
     */

    @BeforeEach
    void setUp() {
        numberMaker = mock(NumberMaker.class); //numberMaker 인터페이스의 가짜 객체 생성
        when(numberMaker.generate(anyInt(), anyInt())).thenReturn(7); //숫자 생성 메서드 호출 시 7을 반환하도록 설정
        alice = new Alice(numberMaker); //그 값을 앨리스에게 부여
    }


    @Test
    @DisplayName("앨리스의 크기가 최대일 경우 더이상 커지지 못한다는 메시지 띄워야함")
    void growSize_크기_키움_불가능() {
        numberMaker = mock(NumberMaker.class);
        when(numberMaker.generate(anyInt(), anyInt())).thenReturn(9);
        alice = new Alice(numberMaker);

        assertThatThrownBy(() -> alice.growSize())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.ALICE_SIZE_LIMIT_MAX);
    }


    @Test
    @DisplayName("앨리스의 크기가 최소 경우 더이상 작아지지 못한다는 메시지 띄워야함")
    void reduceSize() {
        numberMaker = mock(NumberMaker.class);
        when(numberMaker.generate(anyInt(), anyInt())).thenReturn(1);
        alice = new Alice(numberMaker);

        assertThatThrownBy(() -> alice.reduceSize())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.ALICE_SIZE_LIMIT_MIN);

    }

    @Test
    @DisplayName("앨리스 크기가 더 크면 true")
    void aliceIsBiggerThanKey() {
        int keySize = 3;
        assertTrue(alice.aliceIsBiggerThanKey(keySize));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6}) //문의 크기 범위가 2~5이긴 한데...
    @DisplayName("앨리스 크기 > 문크기 = 넘어갈 수 없음")
    void isSmallerThanDoor_크기가_커서_넘어갈_수_없음(int doorSize) {
        assertThatThrownBy(() -> alice.isSmallerThanDoor(doorSize))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.CANT_GO_OVER_BIG);

    }

    @ParameterizedTest
    @ValueSource(ints = {9})
    @DisplayName("앨리스 크기 < 문크기 = 넘어갈 수 없음")
    void isSmallerThanDoor_크기가_작아서_넘어갈_수_없음(int doorSize) {
        assertThatThrownBy(() -> alice.isSmallerThanDoor(doorSize))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.CANT_GO_OVER_SMALL);
    }

    @ParameterizedTest
    @ValueSource(ints = {4,5})
    @DisplayName("앨리스가 문의 크기와 같거나 문보다 -1 작으면 넘어갈 수 있음")
    void isSmallerThanDoor_넘어갈_수_있음(int doorSize) {
        numberMaker = mock(NumberMaker.class);
        when(numberMaker.generate(anyInt(), anyInt())).thenReturn(4);
        alice = new Alice(numberMaker);

        assertTrue(alice.isSmallerThanDoor(doorSize));
    }



    @Test
    @DisplayName("앨리스가 문을 open하면 크기를 저장하고 그 크기를 get")
    void setAtOpenSize() {
        OpenCommand openCommand = new OpenCommand(key,alice); //key 크기 : 기본 3
        openCommand.execute();

        int expected = 7;
        int result = alice.getAtOpenSize();

        assertEquals(expected,result);
    }

    @Test
    @DisplayName("앨리스가 go over했을 때 크기 저장")
    void setAtGoOverSize() {
        //문 크기
        setAtOpenSize();
        numberMaker = mock(NumberMaker.class);
        when(numberMaker.generate(2, 5)).thenReturn(3);
        Door door = new Door(numberMaker);
        //앨리스 크기
        numberMaker = mock(NumberMaker.class); //numberMaker 인터페이스의 가짜 객체 생성
        when(numberMaker.generate(anyInt(), anyInt())).thenReturn(2); //숫자 생성 메서드 호출 시 7을 반환하도록 설정
        alice = new Alice(numberMaker);

        GoOverCommand goOverCommand = new GoOverCommand(door,key,alice);
        goOverCommand.execute();

        int expected = 2;
        int result = alice.getAtGoOverSize();

        assertEquals(expected,result);
        //성공시에는 예외를 던지면 안된다!!
    }

}