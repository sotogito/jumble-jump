package alice_in_wonderland.main.domain.command.execute;

import alice_in_wonderland.main.domain.charactor.Alice;
import alice_in_wonderland.main.domain.charactor.Door;
import alice_in_wonderland.main.domain.charactor.Key;
import alice_in_wonderland.main.util.message.ErrorMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GoOverCommandTest {
    private Door door;
    private Key key;
    private Alice alice;
    private GoOverCommand goOverCommand;

    @BeforeEach
    void setUp() {
        door = mock(Door.class);
        key = mock(Key.class);
        alice = mock(Alice.class);

        goOverCommand = new GoOverCommand(door, key, alice);
    }


    @Test
    void isOverGame() {
        when(door.getGoOverState()).thenReturn(true);
        when(key.getOpenState()).thenReturn(true);

        assertTrue(goOverCommand.isOverGame());
    }

    @Test
    @DisplayName("go over이 가능할 때")
    void execute_success() {
        when(key.getOpenState()).thenReturn(true);
        when(alice.isSmallerThanDoor(door.getSize())).thenReturn(true);

        goOverCommand.execute();

        verify(door, times(1)).changeGoOverState();
        verify(alice, times(1)).setAtGoOverSize();
    }

    @Test
    @DisplayName("go over이 불가능할 때")
    void execute_fail() {
        when(key.getOpenState()).thenReturn(false);
        when(alice.isSmallerThanDoor(door.getSize())).thenReturn(true);

        assertThatThrownBy(() -> goOverCommand.execute())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.CANT_GO_OVER_NOT_YET_OPEN);

    }


}