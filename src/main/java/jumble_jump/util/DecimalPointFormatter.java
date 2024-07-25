package jumble_jump.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DecimalPointFormatter {

    public static Number format(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);

        if (bd.stripTrailingZeros().scale() <= 0) { //note 정수인지 확인
            return bd.longValue();
        }

        if (bd.scale() >= 10) { // note 무한소수인지 확인
            bd = bd.setScale(10, RoundingMode.HALF_UP);
        }

        return bd.doubleValue();
    }

    public static Number problemFormat(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);

        if (bd.stripTrailingZeros().scale() <= 0) { //note 정수인지 확인
            return bd.longValue();
        }
        return bd.doubleValue();
    }


}
