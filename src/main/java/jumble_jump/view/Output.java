package jumble_jump.view;

import jumble_jump.serive.CutterHeightSettingService;

public class Output {
    public static void printCutterHeightResult(CutterHeightSettingService cutterHeightSettingService){
        System.out.printf("결과 : %d", cutterHeightSettingService.getResultCutterHeight());

    }
}
