///usr/bin/env jbang "$0" "$@" ; exit $?

import static java.lang.System.*;

import java.nio.file.*;
import java.util.List;
import java.util.stream.*;

public class Day01 {

    public static void main(String... args) throws Exception {
        List<List<Integer>> lists = readfile("scripting/testDay01.txt");
        out.println(partOne(lists.get(0), lists.get(1)));
        out.println(partTwo(lists.get(0), lists.get(1)));
    }

    // Read a file and parse them into pairs of ints
    public static List<List<Integer>> readfile(String filename) throws Exception {
        Path path = Paths.get(filename);

        List<int[]> pairs = Files.lines(path)
                .map(line -> line.trim().split("   "))
                .map(pair -> new int[] { Integer.parseInt(pair[0]), Integer.parseInt(pair[1]) })
                .collect(Collectors.toList());

        // extract the first and second columns into separate lists and sort them
        List<Integer> first = pairs.stream().map(pair -> pair[0]).sorted().collect(Collectors.toList());
        List<Integer> second = pairs.stream().map(pair -> pair[1]).sorted().collect(Collectors.toList());

        // return both lists in a list
        return List.of(first, second);
    }

    // Take the first and second lists and for each index, calculate the difference
    // between the two ints and sum the total differences
    public static int partOne(List<Integer> first, List<Integer> second) {
        return IntStream.range(0, first.size()).map(i -> Math.abs(first.get(i) - second.get(i))).sum();
    }

    // Take the first and second lists as input. For the second list, make a map per
    // int as key and its occurrence as value. For each int in the first list, check
    // if it exists in the map. If it does, multiply it by the value of the map.
    // Finally, sum all the multiplied values.
    public static int partTwo(List<Integer> first, List<Integer> second) {
        var occurrenceMap = second.stream().collect(Collectors.toMap(i -> i, i -> 1, Integer::sum));
        return first.stream().filter(occurrenceMap::containsKey).mapToInt(i -> i * occurrenceMap.get(i)).sum();
    }

}
