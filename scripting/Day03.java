///usr/bin/env jbang "$0" "$@" ; exit $?

import static java.lang.System.*;

import java.nio.file.*;
import java.util.regex.*;

public class Day03 {

    public static void main(String... args) throws Exception {
        String inputPartOne = readfile("scripting/testDay03.txt");
        out.println(partOne(inputPartOne));
        String inputPartTwo = readfile("scripting/testDay03Part2.txt");
        out.println(partTwo(inputPartTwo));
    }

    public static String readfile(String filename) throws Exception {
        return Files.readString(Paths.get(filename));
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
