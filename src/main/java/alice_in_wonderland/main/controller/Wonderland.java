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
        Output.printCharacterSize(key,door,alice);

        GameManager gameManager = new GameManager();


        try{
            while (!gameManager.isGameOver(key,door)){
                InputMatcher inputMatcher = createInputMatcher(gameManager,alice, door, key);
                gameManager.decreaseCount();
                Output.printAttemptCount(gameManager);
                Output.printCharacterSize(key,door,alice);
            }
            System.out.println("여기는 성공했을때만 ?");
        }catch (IllegalArgumentException e){
            //시도횟수 통과 못했을땐가?
            System.out.println("여기는 실패했을때만 ?");
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
                    System.out.println("\n"+e.getMessage());
                    continue;
                }
                gameManager.decreaseCount();
                System.out.println("\n"+e.getMessage());
                Output.printAttemptCount(gameManager);
            }
        }
        throw new IllegalArgumentException(); //시도 횟수를 초과했을 때
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
