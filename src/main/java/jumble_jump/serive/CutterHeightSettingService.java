package jumble_jump.serive;

import jumble_jump.domain.Cutter;
import jumble_jump.domain.CutterController;
import jumble_jump.domain.CutterLength;
import jumble_jump.domain.RiceCake;
import jumble_jump.repository.Order;
import jumble_jump.repository.RiceCakes;

import java.util.List;

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

    public void setCutterHeight() {
        List<RiceCake> riceCakeList = order.getRiceCakes();
        int targetLength = order.getRiceCakeLength();
        start = 0;
        end = order.getMaxRiceCakeLength();

        int heightResult = findCutterHeight(riceCakeList, targetLength);
        setCutterHeightAsResult(heightResult);
    }

    private void setCutterHeightAsResult(int heightResult) {
        if (heightResult == NOTHING) {
            throw new IllegalArgumentException("계산할 수 없습니다.");
        }
        cutter.setHeight(heightResult);
    }

    private int findCutterHeight(List<RiceCake> riceCakeList, int target) {
        int result = NOTHING;

        while (isContinueCondition()) {
            mid = calculateMidPoint();
            int cutRiceCakeHeight = cutterController.calculateCutRiceCakeTotalHeight(riceCakeList, mid);
            CutterLength midCutterLength = cutterController.getCutterLengthStatus(target, cutRiceCakeHeight);

            if(whenCutterHeightTooShort(midCutterLength)){
                result = mid; //note 아래에사면 5일때 16이나옴
                continue;
            } else if (whenCutterHeightTooLong(midCutterLength)) {
                continue;
            }
            return mid;
        }
        return result;
    }

    private boolean whenCutterHeightTooShort(CutterLength midCutterLength) {
        if (midCutterLength.equals(CutterLength.SHORT)) {
            updateStartPoint(midCutterLength);
            return true;
        }
        return false;
    }

    private boolean whenCutterHeightTooLong(CutterLength midCutterLength) {
        if (midCutterLength.equals(CutterLength.LONG)) {
            updateEndPoint(midCutterLength);
            return true;
        }
        return false;
    }

    private void updateStartPoint(CutterLength midCutterLength) {
        start = cutterController.adjustingLength(midCutterLength, mid);
    }

    private void updateEndPoint(CutterLength midCutterLength) {
        end = cutterController.adjustingLength(midCutterLength, mid);
    }

    private boolean isContinueCondition() {
        return start <= end;
    }

    private int calculateMidPoint() {
        return (start + end) / 2;
    }

    //note 출력
    public int getResultCutterHeight() {
        return cutter.getHeight();
    }

}
