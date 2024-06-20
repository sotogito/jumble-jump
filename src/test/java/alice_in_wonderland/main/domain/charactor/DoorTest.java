package alice_in_wonderland.main.domain.charactor;

import alice_in_wonderland.main.domain.number.NumberMaker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DoorTest {

    private NumberMaker numberMaker;
    private Door door;

    @BeforeEach
    void setUp() {
        numberMaker = mock(NumberMaker.class);
        when(numberMaker.generate(2,5)).thenReturn(3);
        door = new Door(numberMaker);
    }

    @Test
    void changeGoOverState() {
        door.changeGoOverState();
        assertTrue(door.getGoOverState());
    }

    @Test
    void getSize() {
        assertEquals(3, door.getSize());
    }

}