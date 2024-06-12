package alice_in_wonderland.main.domain.charactor;

import alice_in_wonderland.main.domain.gamedata.Item;
import alice_in_wonderland.main.domain.number.NumberMaker;

public class Alice {
    private final int MIN_SIZE = 1;
    private final int MAX_SIZE = 9;
    private final NumberMaker numberMaker;

    private int size = 0;


    public Alice(NumberMaker numberMaker) {
        this.numberMaker = numberMaker;
        size = setFirstSize();
    }


    //근데 앨리스의 크기가 넘어가지 않을 정도의 숫자여야함
    public void updateSize(Item item){
        if(item == Item.DRINK){ //커짐
            size += numberMaker.generate(MIN_SIZE, maxSizeAtDrink());
        } else if (item == Item.MUSHROOM) { //작아짐
            size -= numberMaker.generate(MIN_SIZE, maxSizeAtMushroom());
        }
    }

    private int setFirstSize(){
        int  FIRST_MIN_SIZE = 6;
        int FIRST_MAX_SIZE = 9;
        return numberMaker.generate(FIRST_MIN_SIZE, FIRST_MAX_SIZE);
    }

    private int maxSizeAtDrink(){
        return size = MIN_SIZE;
    }
    private int maxSizeAtMushroom(){
        return MAX_SIZE - size;
    }



}
