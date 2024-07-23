package jumble_jump.util;

public class DecimalPointFormatter {

    public static Number format(double value) {
        if (value == (long) value) {
            return (long) value;
        }
        return Math.round(value * 100.0) / 100.0;
    }

}
