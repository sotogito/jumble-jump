package jumble_jump.serive;

import jumble_jump.domain.Cutter;
import jumble_jump.domain.CutterController;
import jumble_jump.domain.CutterLength;
import jumble_jump.domain.RiceCake;
import jumble_jump.repository.Order;
import jumble_jump.repository.RiceCakes;

import java.util.List;

/**
 * 1. 데이터 선언하기
 * 2. 재귀 돌리기
 */
public class CutterHeightSettingService {
    private final Order order;
    private final Cutter cutter;
    private final CutterController cutterController;


    private static final int NOTHING = -1;

    private int mid;
    private int start;
    private int end;

    public CutterHeightSettingService(Cutter cutter, Order order, CutterController cutterController) {
        this.cutter = cutter;
        this.order = order;
        this.cutterController = cutterController;
    }

    public void setCutterHeight(){
        List<RiceCake> riceCakeList = order.getSortedRiceCakes();
        int targetLength = order.getRiceCakeLength();
        int start = 0;
        int end = order.getMaxRiceCakeLength();

        int heightResult = findCutterHeight(riceCakeList,targetLength,start,end);
        if(heightResult == NOTHING){
            throw new IllegalArgumentException("계산할 수 없습니다.");
        }
        cutter.setHeight(heightResult);
    }

    private int findCutterHeight(List<RiceCake> riceCakeList, int target, int start, int end){
        if (isUnderlyingCondition(start, end)) {
            return NOTHING;
        }

        int mid = (start + end) / 2;
        int cutRiceCakeHeight = cutterController.calculateCutRiceCakeTotalHeight(riceCakeList,mid);
        CutterLength midCutterLength = cutterController.getCutterLengthStatus(target, cutRiceCakeHeight);

        return adjustingCutterLength(midCutterLength,riceCakeList,target,start,end,mid);
    }

    private int adjustingCutterLength(
            CutterLength midCutterLength, List<RiceCake> riceCakeList, int target, int start, int end, int mid){
        if (midCutterLength.equals(CutterLength.LONG)){
            int startPoint = cutterController.adjustingLength(midCutterLength,mid);
            return findCutterHeight(riceCakeList, target,startPoint, end); //todo 이부부느메서드로 변경

        } else if (midCutterLength.equals(CutterLength.SHORT)){
            int endPoint = cutterController.adjustingLength(midCutterLength,mid);
            return findCutterHeight(riceCakeList, target, start, endPoint);
        }

        return mid;
    }



    private boolean isUnderlyingCondition(int start, int end) {
        return start > end;
    }


    //note 출력

    public int getResultCutterHeight(){
        return cutter.getHeight();
    }

}
