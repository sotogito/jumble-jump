package alice_in_wonderland.main.controller;

import alice_in_wonderland.main.domain.charactor.Alice;
import alice_in_wonderland.main.domain.charactor.Door;
import alice_in_wonderland.main.domain.charactor.Key;
import alice_in_wonderland.main.domain.manager.GameManager;
import alice_in_wonderland.main.domain.manager.InputMatcher;
import alice_in_wonderland.main.domain.number.NumberMaker;
import alice_in_wonderland.main.domain.number.RandomNumberMaker;
import alice_in_wonderland.main.util.message.ErrorMessage;
import alice_in_wonderland.main.util.message.GameMessage;
import alice_in_wonderland.main.view.Input;
import alice_in_wonderland.main.view.Output;

public class Wonderland {

    public void main(){
        Output.printGameIntro();

        Alice alice = createAlice();
        Door door = createDoor();
        Key key = createKey();
        System.out.println("처음"+alice);

        GameManager gameManager = new GameManager();


        try{
            while (!gameManager.isGameOver(key,door)){
                InputMatcher inputMatcher = createInputMatcher(gameManager,alice, door, key);
                gameManager.decreaseCount();

            }

            System.out.println("성공");
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        System.out.println("끝났다.");


    }

    //잘못 입력했을때 = 시도횟수 x
    //다른 오류 = 시도횟수o
    private InputMatcher createInputMatcher(GameManager gameManager,Alice alice, Door door, Key key) {
        while (!gameManager.isOverCount()){
            try{
                return new InputMatcher(getUserInput(),alice,door,key);
            }catch (IllegalArgumentException e) {
                if(e.getMessage().contains(ErrorMessage.INPUT_ERROR)){
                    System.out.println("줄지않아");
                    System.out.println(e.getMessage());
                    continue;
                }
                System.out.println("줄어");
                gameManager.decreaseCount();
                System.out.println(e.getMessage());
            }
        }
        throw new IllegalArgumentException("시도횟수가 끝났다! 엉엉엉엉엉");

    }




    private String getUserInput(){
        return Input.inputUser();
    }


    private Alice createAlice(){
        NumberMaker numberMaker = new RandomNumberMaker();
        return new Alice(numberMaker);
    }

    private Door createDoor(){
        NumberMaker numberMaker = new RandomNumberMaker();
        return new Door(numberMaker);
    }

    private Key createKey(){
        return new Key();
    }




}
