package jumble_jump.domain;

import java.util.List;

public class CutterControlUnit implements CutterController {

    @Override
    public int calculateCutRiceCakeTotalHeight(List<RiceCake> riceCakeList, int midCutterHeight) {
        int result = 0;

        for (RiceCake riceCake : riceCakeList) {
            if (riceCake.canCut(midCutterHeight)) {
                result += (riceCake.getHeightOfCuttingWithCutter(midCutterHeight));
            }
        }
        return result;
    }

    @Override
    public CutterLength getCutterLengthStatus(int target, int result) {
        if (target < result) {
            return CutterLength.SHORT;
        } else if (target > result) {
            return CutterLength.LONG;
        }
        return CutterLength.SAME;
    }

    @Override
    public int adjustingLength(CutterLength midCutterLength, int mid) {
        return mid + (midCutterLength.getValue());
    }

}
