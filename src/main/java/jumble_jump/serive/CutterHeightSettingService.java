package jumble_jump.serive;

import jumble_jump.domain.Cutter;
import jumble_jump.domain.CutterController;
import jumble_jump.domain.CutterLength;
import jumble_jump.domain.RiceCake;
import jumble_jump.repository.Order;
import jumble_jump.repository.RiceCakes;

import java.util.List;

public class CutterHeightSettingService {
    /**
     * 1. 이진탐색 + 재귀로 찾기
     */

    private final Order order;
    private final Cutter cutter;

    private final CutterController cutterController;

    public CutterHeightSettingService(Cutter cutter, Order order, CutterController cutterController) {
        this.cutter = cutter;
        this.order = order;
        this.cutterController = cutterController;
    }

    public void setCutterHeight(){
        /**
         * 정렬
         * 재귀
         */

        List<RiceCake> riceCakeList = order.getSortedRiceCakes();
        int targetLength = order.getRiceCakeLength();
        int start = 0;
        int end = order.getRiceCakeCount()-1;

        int heightResult = findCutterHeight(riceCakeList,targetLength,start,end);

        cutter.setHeight(heightResult);
    }

    private int findCutterHeight(List<RiceCake> array, int target, int start, int end){
        int mid = (start + end) / 2;
        int midHeight = array.get(mid).getHeight();


        CutterLength midCutterLength = cutterController.calculate(target, midHeight);

        if (midCutterLength.equals(CutterLength.SAME)){
            return midHeight;

        } else if (midCutterLength.equals(CutterLength.LONG)){
            int endPoint = mid + (midCutterLength.getValue());
            return findCutterHeight(array, target, start, endPoint);

        }else if (midCutterLength.equals(CutterLength.SHORT)){
            int startPoint = mid + (midCutterLength.getValue());
            return findCutterHeight(array, target, startPoint, end);
        }

        throw new IllegalArgumentException("계산할 수 없습니다.");
    }

}
