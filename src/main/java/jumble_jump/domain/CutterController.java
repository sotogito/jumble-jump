package jumble_jump.domain;

import java.util.List;

public interface CutterController {
    CutterLength getCutterLengthStatus(int target, int result);

    int adjustingLength(CutterLength midCutterLength, int mid);

    int calculateCutRiceCakeTotalHeight(List<RiceCake> riceCakeList, int midCutterHeight);

}
