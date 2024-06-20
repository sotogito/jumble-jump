package alice_in_wonderland.main.domain.charactor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeyTest {
    private Key key;

    @BeforeEach
    void setUp() {
        key = new Key();
    }

    @Test
    void changeOpenState() {
        key.changeOpenState();
        assertTrue(key.getOpenState());
    }

    @Test
    void getSize() {
        assertEquals(3,key.getSize());
    }
}