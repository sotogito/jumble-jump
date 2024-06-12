package alice_in_wonderland.main.domain.charactor;

import alice_in_wonderland.main.domain.gamedata.Item;
import alice_in_wonderland.main.domain.number.NumberMaker;

public class Alice {
    private final NumberMaker numberMaker;

    private int size = 0;


    public Alice(NumberMaker numberMaker) {
        this.numberMaker = numberMaker;
    }
    /*

    public void updateSize(Item item){
        if(item == Item.DRINK){
            size += numberMaker.generate();
        } else if (item == Item.MUSHROOM) {
            size -= numberMaker.generate();
        }
    }

    public setFirstSize
     */


}
