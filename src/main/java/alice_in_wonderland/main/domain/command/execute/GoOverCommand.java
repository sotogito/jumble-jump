package alice_in_wonderland.main.domain.command.execute;

import alice_in_wonderland.main.domain.charactor.Alice;
import alice_in_wonderland.main.domain.charactor.Door;
import alice_in_wonderland.main.domain.charactor.Key;
import alice_in_wonderland.main.domain.command.Command;
import alice_in_wonderland.main.domain.manager.GameManager;
import alice_in_wonderland.main.util.message.ErrorMessage;

public class GoOverCommand implements Command {
    private final Door door;
    private final Key key;
    private final Alice alice;

    //TODO 넘겨주는 인수가 많은데 door+key+alice 클래스를 하나 생성해야하나?
    public GoOverCommand(Door door, Key key,Alice alice) {
        this.door = door;
        this.key = key;
        this.alice = alice;
    }

    public boolean isOverGame(){
        return door.getGoOverState() && key.getOpenState();
    }

    @Override
    public void execute() {
        if (checkState()){
            return;
        }
        throw new IllegalArgumentException(ErrorMessage.CANT_GO_OVER_NOT_YET_OPEN);
    }

    private boolean checkState(){
        if(key.getOpenState()) {
            if (alice.isSmallerThanDoor(door.getSize())) {
                door.changeGoOverState();
                alice.setAtGoOverSize();
                return true;
            }
        }
        return false;
    }

}
