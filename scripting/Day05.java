import static java.lang.System.*;

import utils.AocUtils;
import java.util.*;
import java.util.stream.*;

public class Day05 {

    public static void main(String... args) throws Exception {
        String inputFile = args.length > 0 ? args[0] : "scripting/input/day05.txt";
        String input = AocUtils.readString(inputFile);
        out.println(partOne(input));
        out.println(partTwo(input));
    }

    public static int partOne(String input) {
        var parsed = parseInput(input);
        Comparator<Integer> pageOrder = buildPageComparator(parsed.rules);
        
        return parsed.updates.stream()
                .filter(update -> isCorrectlyOrdered(update, pageOrder))
                .mapToInt(Day05::getMiddlePage)
                .sum();
    }

    public static int partTwo(String input) {
        var parsed = parseInput(input);
        Comparator<Integer> pageOrder = buildPageComparator(parsed.rules);
        
        return parsed.updates.stream()
                .filter(update -> !isCorrectlyOrdered(update, pageOrder))
                .map(update -> update.stream().sorted(pageOrder).collect(Collectors.toList()))
                .mapToInt(Day05::getMiddlePage)
                .sum();
    }

    static boolean isCorrectlyOrdered(List<Integer> update, Comparator<Integer> comparator) {
        List<Integer> sorted = update.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
        return update.equals(sorted);
    }

    static int getMiddlePage(List<Integer> update) {
        return update.get(update.size() / 2);
    }

    static Comparator<Integer> buildPageComparator(List<Rule> rules) {
        Map<Integer, Set<Integer>> mustComeBefore = new HashMap<>();
        
        for (Rule rule : rules) {
            mustComeBefore.computeIfAbsent(rule.before, k -> new HashSet<>()).add(rule.after);
        }
        
        return (a, b) -> {
            if (mustComeBefore.getOrDefault(a, Set.of()).contains(b)) {
                return -1;
            } else if (mustComeBefore.getOrDefault(b, Set.of()).contains(a)) {
                return 1;
            }
            return 0;
        };
    }

    static ParsedInput parseInput(String input) {
        String[] sections = input.split("\n\n");
        
        List<Rule> rules = sections[0].lines()
                .map(line -> line.split("\\|"))
                .map(parts -> new Rule(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])))
                .collect(Collectors.toList());
        
        List<List<Integer>> updates = sections[1].lines()
                .map(line -> Arrays.stream(line.split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
        
        return new ParsedInput(rules, updates);
    }

    record Rule(int before, int after) {}
    record ParsedInput(List<Rule> rules, List<List<Integer>> updates) {}
}
