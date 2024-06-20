package alice_in_wonderland.main.domain.command.execute;

import alice_in_wonderland.main.domain.charactor.Alice;
import alice_in_wonderland.main.domain.charactor.Key;
import alice_in_wonderland.main.domain.number.NumberMaker;
import alice_in_wonderland.main.util.message.ErrorMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OpenCommandTest {

    private Key key;
    private Alice alice;
    private OpenCommand openCommand;


    @BeforeEach
    void setUp() {
        key = mock(Key.class);
        alice = mock(Alice.class);

        openCommand = new OpenCommand(key, alice);
    }

    @Test
    @DisplayName("open 할 수 있을 때")
    void execute_success() {
        when(alice.aliceIsBiggerThanKey(key.getSize())).thenReturn(true);

        openCommand.execute();

        verify(key, times(1)).changeOpenState();
        verify(alice, times(1)).setAtOpenSize();
    }

    @Test
    @DisplayName("open 할 수 없을 때")
    void execute_fail() {
        when(alice.aliceIsBiggerThanKey(key.getSize())).thenReturn(false);

        assertThatThrownBy(() -> openCommand.execute())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.CANT_OPEN_WITH_KEY);

    }
}