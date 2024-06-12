package alice_in_wonderland.main.domain.charactor;

import alice_in_wonderland.main.domain.number.NumberMaker;

public class Alice {
    private final NumberMaker numberMaker;

    private int size = 0;


    public Alice(NumberMaker numberMaker) {
        this.numberMaker = numberMaker;
    }


}
