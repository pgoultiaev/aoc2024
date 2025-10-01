import static java.lang.System.*;

import utils.AocUtils;
import java.util.regex.*;

public class Day03 {

    public static void main(String... args) throws Exception {
        String inputFile = args.length > 0 ? args[0] : "scripting/input/day03.txt";
        String input = AocUtils.readString(inputFile);
        out.println(partOne(input));
        out.println(partTwo(input));
    }

    public static int partOne(String input) {
        Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
        Matcher matcher = pattern.matcher(input);

        return matcher.results()
                .mapToInt(match -> Integer.parseInt(match.group(1)) * Integer.parseInt(match.group(2)))
                .sum();
    }

    public static int partTwo(String input) {
        Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)|do\\(\\)|don't\\(\\)");
        Matcher matcher = pattern.matcher(input);

        boolean enabled = true;
        int sum = 0;

        while (matcher.find()) {
            String instruction = matcher.group();
            if (instruction.equals("do()")) {
                enabled = true;
            } else if (instruction.equals("don't()")) {
                enabled = false;
            } else if (enabled) {
                sum += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
            }
        }
        return sum;
    }

}
