package jumble_jump.view;

import jumble_jump.service.Game369Service;

public class Output {

    public static void print369GameResult(Game369Service game369Service) {
        System.out.println("결과!!출력한ㄷ!!다!1");
        for(String printout : game369Service.getTokenResult()){
            System.out.println(printout);
        }
    }

}
