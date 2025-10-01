package utils;

import java.nio.file.*;
import java.util.List;
import java.util.stream.*;

public class AocUtils {

    public static List<String> readLines(String filename) throws Exception {
        return Files.readAllLines(Paths.get(filename));
    }

    public static String readString(String filename) throws Exception {
        return Files.readString(Paths.get(filename));
    }

    public static Stream<String> readStream(String filename) throws Exception {
        return Files.lines(Paths.get(filename));
    }

    public static List<Integer> parseIntegers(String line, String delimiter) {
        return Stream.of(line.trim().split(delimiter))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static String getInputPath(String[] args, int day, boolean useTest) {
        if (args.length > 0) {
            return args[0];
        }
        String folder = useTest ? "test" : "input";
        return String.format("scripting/%s/day%02d.txt", folder, day);
    }
}
