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

        List<Integer> array = order.getArray();
        List<RiceCake> riceCakeList = order.getSortedRiceCakes();
        int targetLength = order.getRiceCakeLength();
        int start = 0;
        int end = array.size()-1;

        int heightResult = findCutterHeight(riceCakeList,array,targetLength,start,end);

        if(heightResult == -1){
            throw new IllegalArgumentException("계산할 수 없습니다.");
        }
        cutter.setHeight(heightResult);
    }

    private int findCutterHeight(
            List<RiceCake> riceCakeList, List<Integer> array, int target, int start, int end){
        if (start > end) {
            return -1;  // 정상적인 기저 조건: 더 이상 탐색할 수 없을 때
        }




        int mid = (start + end) / 2;
        int midHeight = array.get(mid);
        int cutRiceCakeHeight = calculateCutRiceCake(riceCakeList,midHeight);

        System.out.println(mid);
        System.out.println(midHeight);
        System.out.println(cutRiceCakeHeight);

        CutterLength midCutterLength = cutterController.calculate(target, cutRiceCakeHeight);



        if (midCutterLength.equals(CutterLength.SAME)){
            return midHeight;

        } else if (midCutterLength.equals(CutterLength.LONG)){
            int startPoint = mid + (midCutterLength.getValue());
            return findCutterHeight(riceCakeList,array, target,startPoint, end); //짧게

        }else if (midCutterLength.equals(CutterLength.SHORT)){
            int endPoint = mid + (midCutterLength.getValue());
            return findCutterHeight(riceCakeList, array, target, start, endPoint);
        }

        return -1;
    }

    private int calculateCutRiceCake(List<RiceCake> riceCakeList, int midCutterHeight){
        int result = 0;

        for (RiceCake riceCake : riceCakeList) {
            int riceCakeHeight = riceCake.getHeight();

            if(riceCakeHeight > midCutterHeight){
                result += (riceCakeHeight - midCutterHeight);
            }
        }
        return result;

    }




    //note 출력

    public int getResultCutterHeight(){
        return cutter.getHeight();
    }

}
