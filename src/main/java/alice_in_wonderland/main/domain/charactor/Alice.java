package alice_in_wonderland.main.domain.charactor;

import alice_in_wonderland.main.domain.gamedata.Item;
import alice_in_wonderland.main.domain.number.NumberMaker;

public class Alice {
    private final int MIN_SIZE = 1;
    private final int MAX_SIZE = 9;
    private final NumberMaker numberMaker;

    private int size = 0;
    private int atOpenSize = -1;
    private int atGoOverSize = -1;
    private boolean isAtOpenSizeSet = false;   // 플래그를 추가하여 값이 설정되었는지 추적
    private boolean isAtGoOverSizeSet = false;


    public Alice(NumberMaker numberMaker) {
        this.numberMaker = numberMaker;
        //size = 2;
        size = setFirstSize();
    }


    //근데 앨리스의 크기가 넘어가지 않을 정도의 숫자여야함
    public void updateSize(Item item){
        if(item == Item.DRINK && size < MAX_SIZE){ //커짐
            size += numberMaker.generate(MIN_SIZE, maxSizeAtDrink());
            return;
        } else if (item == Item.MUSHROOM && size > MIN_SIZE) { //작아짐
            size -= numberMaker.generate(MIN_SIZE, maxSizeAtMushroom());
            return;
        }
        isCantChangeSize(item);
    }

    private int maxSizeAtDrink(){
        return MAX_SIZE - size;
    }
    private int maxSizeAtMushroom(){
        return size-MIN_SIZE;
    }

    public void isCantChangeSize(Item item) {
        if (item == Item.DRINK && size == MAX_SIZE) {
            throw new IllegalArgumentException("더이상 커질 수 없어");
        } else if (item == Item.MUSHROOM && size == MIN_SIZE) {
            throw new IllegalArgumentException("더이상 작아질 수 없어");
        }
    }

    public boolean isBiggerThanKey(int keySize){
        if (size <= keySize){
            throw new IllegalArgumentException("키가 너무 무거워서 들수 없어!");
        }
        return true;
    }

    public boolean isSmallerThanDoor(int doorSize){
        if (size > doorSize){
            throw new IllegalArgumentException("문을 넘어가기에는 내가 너무 커");
        }
        if (size < doorSize-1){
            throw new IllegalArgumentException("문을 넘어가기에는 내가 너무 작아");
        }
        return size == doorSize || size == doorSize-1;
    }

    public void setAtOpenSize() {
        if (!isAtOpenSizeSet) {
            atOpenSize = size;
            isAtOpenSizeSet = true;
        }
    }
    public int getAtOpenSize() {
        return atOpenSize;
    }
    public void setAtGoOverSize() {
        if (!isAtGoOverSizeSet) {
            atGoOverSize = size;
            isAtGoOverSizeSet = true;
        }
    }
    public int getAtGoOverSize(){
        return atGoOverSize;
    }




    private int setFirstSize(){
        int  FIRST_MIN_SIZE = 6;
        int FIRST_MAX_SIZE = 9;
        return numberMaker.generate(FIRST_MIN_SIZE, FIRST_MAX_SIZE);
    }

    @Override
    public String toString() {
        return "앨리스의 크기 : "+size;
    }




}
