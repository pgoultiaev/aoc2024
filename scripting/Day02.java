///usr/bin/env jbang "$0" "$@" ; exit $?

import static java.lang.System.*;

public class Day02 {

    public static void main(String... args) throws Exception {
        out.println(partOne());
        out.println(partTwo());
    }

    // Validates if every successor number is within 3 of its predecessor (either
    // direction)
    public static boolean isLineValid(String line) {
        String[] parts = line.trim().split("\\s+");
        java.util.List<Integer> numbers = java.util.Arrays.stream(parts)
                .map(Integer::parseInt)
                .toList();
        boolean allIncreasing = java.util.stream.IntStream.range(0, numbers.size() - 1)
                .allMatch(i -> numbers.get(i + 1) > numbers.get(i) && numbers.get(i + 1) - numbers.get(i) <= 3);
        boolean allDecreasing = java.util.stream.IntStream.range(0, numbers.size() - 1)
                .allMatch(i -> numbers.get(i + 1) < numbers.get(i) && numbers.get(i) - numbers.get(i + 1) <= 3);
        return allIncreasing || allDecreasing;
    }

    // Checks if the line can be made valid by removing one successor (any number
    // except the first)
    public static boolean isLineValidWithOneRemoval(String line) {
        return isLineValid(line) || java.util.stream.IntStream.range(0, line.trim().split("\\s+").length)
                .anyMatch(i -> {
                    String[] parts = line.trim().split("\\s+");
                    String modifiedLine = String.join(" ", java.util.Arrays.copyOfRange(parts, 0, i))
                            + " "
                            + String.join(" ", java.util.Arrays.copyOfRange(parts, i + 1, parts.length));

                    return isLineValid(modifiedLine);
                });
    }

    public static int partOne() {
        String filename = "scripting/testDay02.txt";
        try (java.util.stream.Stream<String> lines = java.nio.file.Files.lines(java.nio.file.Paths.get(filename))) {
            return (int) lines.filter(Day02::isLineValid).count();
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int partTwo() {
        String filename = "scripting/testDay02.txt";
        try (java.util.stream.Stream<String> lines = java.nio.file.Files.lines(java.nio.file.Paths.get(filename))) {
            return (int) lines.filter(Day02::isLineValidWithOneRemoval).count();
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
