package alice_in_wonderland.main.domain.command;

import alice_in_wonderland.main.controller.WonderlandDevelop;
import alice_in_wonderland.main.domain.charactor.Alice;
import alice_in_wonderland.main.domain.charactor.Door;
import alice_in_wonderland.main.domain.charactor.Key;
import alice_in_wonderland.main.domain.number.NumberMaker;
import alice_in_wonderland.main.domain.number.RandomNumberMaker;


import alice_in_wonderland.main.util.message.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.*;

class InputManagerTest {

    NumberMaker numberMaker = new RandomNumberMaker();
    Alice alice = new Alice(numberMaker);
    Door door = new Door(numberMaker);
    Key key = new Key();

    InputManager inputManager = new InputManager(alice, door, key);


    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "!@#$", "잘못된입력"})
    void 목록에_없는_명령_작성(String input) {
        assertThatThrownBy(() -> inputManager.registerCommand(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INPUT_ERROR);
    }


}