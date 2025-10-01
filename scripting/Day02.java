///usr/bin/env jbang "$0" "$@" ; exit $?

import static java.lang.System.*;

import utils.AocUtils;

public class Day02 {

    public static void main(String... args) throws Exception {
        String inputFile = args.length > 0 ? args[0] : "scripting/input/day02.txt";
        out.println(partOne(inputFile));
        out.println(partTwo(inputFile));
    }

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

    public static int partOne(String filename) {
        try (java.util.stream.Stream<String> lines = AocUtils.readStream(filename)) {
            return (int) lines.filter(Day02::isLineValid).count();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int partTwo(String filename) {
        try (java.util.stream.Stream<String> lines = AocUtils.readStream(filename)) {
            return (int) lines.filter(Day02::isLineValidWithOneRemoval).count();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
