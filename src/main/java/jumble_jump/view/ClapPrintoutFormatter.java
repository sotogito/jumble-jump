package jumble_jump.view;

public class ClapPrintoutFormatter {
    private static final String CLAP_PRINTOUT = "짝";
    private static final String CLAP_FORMAT = "%s!";

    public static String format(int clapCount){
        return String.format(CLAP_FORMAT, CLAP_PRINTOUT.repeat(clapCount));
    }
}
