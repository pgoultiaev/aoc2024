///usr/bin/env jbang "$0" "$@" ; exit $?

import static java.lang.System.*;

import utils.AocUtils;
import java.util.List;

public class DayXX {

    public static void main(String... args) throws Exception {
        String inputFile = args.length > 0 ? args[0] : "scripting/input/dayXX.txt";
        List<String> lines = AocUtils.readLines(inputFile);
        out.println(partOne(lines));
        out.println(partTwo(lines));
    }

    public static long partOne(List<String> lines) {
        return 0;
    }

    public static long partTwo(List<String> lines) {
        return 0;
    }
}
