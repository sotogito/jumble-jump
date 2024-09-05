package jumble_jump.domain;

public class CutterControlUnit implements CutterController{
    @Override
    public CutterLength calculate(int target, int result) {
        if (target < result) {
            return CutterLength.LONG;
        } else if (target > result) {
            return CutterLength.SHORT;
        }
        return CutterLength.SAME;
    }
}
