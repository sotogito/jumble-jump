package alice_in_wonderland.main.controller;

import alice_in_wonderland.main.util.message.GameMessage;
import alice_in_wonderland.main.view.Output;

public class Wonderland {

    public void main(){
        GameMessage gameMessage = new GameMessage();
        Output.printGameIntro(gameMessage);
    }
}
