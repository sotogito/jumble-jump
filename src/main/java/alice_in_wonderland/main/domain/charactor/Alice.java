package alice_in_wonderland.main.domain.charactor;

import alice_in_wonderland.main.domain.states.Item;
import alice_in_wonderland.main.domain.number.NumberMaker;
import alice_in_wonderland.main.util.message.ErrorMessage;
import alice_in_wonderland.main.util.message.GameMessage;

public class Alice {
    private final int MIN_SIZE = 1;
    private final int MAX_SIZE = 9;
    private final NumberMaker numberMaker;

    private int size = 0;
    private int atOpenSize = -1;
    private int atGoOverSize = -1;
    private boolean isAtOpenSizeSet = false;
    private boolean isAtGoOverSizeSet = false;

    public Alice(NumberMaker numberMaker) {
        this.numberMaker = numberMaker;
        size = setFirstSize();
    }


    public void growSize(){
        if (size < MAX_SIZE){
            size += numberMaker.generate(MIN_SIZE, maxSizeAtDrink());
            return;
        }
        throw new IllegalArgumentException(ErrorMessage.ALICE_SIZE_LIMIT_MAX);
    }

    public void reduceSize(){
        if (size > MIN_SIZE){
            size -= numberMaker.generate(MIN_SIZE, maxSizeAtMushroom());
            return;
        }
        throw new IllegalArgumentException(ErrorMessage.ALICE_SIZE_LIMIT_MIN);
    }


    public boolean aliceIsBiggerThanKey(int keySize){
        return size > keySize;
    }

    private int maxSizeAtDrink() {
        return MAX_SIZE - size;
    }

    private int maxSizeAtMushroom() {
        return size - MIN_SIZE;
    }


    public boolean isSmallerThanDoor(int doorSize) {
        if (size > doorSize) {
            throw new IllegalArgumentException(ErrorMessage.CANT_GO_OVER_BIG);
        }
        if (size < doorSize - 1) {
            throw new IllegalArgumentException(ErrorMessage.CANT_GO_OVER_SMALL);
        }
        return size == doorSize || size == doorSize - 1;
    }

    public void setAtOpenSize() {
        if (!isAtOpenSizeSet) {
            atOpenSize = size;
            isAtOpenSizeSet = true;
        }
    }

    public void setAtGoOverSize() {
        if (!isAtGoOverSizeSet) {
            atGoOverSize = size;
            isAtGoOverSizeSet = true;
        }
    }

    public int getAtOpenSize() {
        return atOpenSize;
    }

    public int getAtGoOverSize() {
        return atGoOverSize;
    }

    private int setFirstSize() {
        int FIRST_MIN_SIZE = 6;
        int FIRST_MAX_SIZE = 9;
        return numberMaker.generate(FIRST_MIN_SIZE, FIRST_MAX_SIZE);
    }

    @Override
    public String toString() {
        String name = "앨리스";
        return String.format(GameMessage.SIZE, name, size);
    }

}
