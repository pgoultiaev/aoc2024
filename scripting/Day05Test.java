import utils.AocUtils;

public class Day05Test {
    
    private static final String TEST_INPUT = """
47|53
97|13
97|61
97|47
75|29
61|13
75|53
29|13
97|29
53|29
61|53
97|53
61|29
47|13
75|47
97|75
47|61
75|61
47|29
75|13
53|13

75,47,61,53,29
97,61,53,29,13
75,29,13
75,97,47,61,53
61,13,29
97,13,75,29,47
""";

    public static void main(String... args) throws Exception {
        testPartOne();
        testPartTwo();
        testWithFile();
        System.out.println("All tests passed!");
    }

    static void testPartOne() {
        int result = Day05.partOne(TEST_INPUT);
        assertEqual(result, 143, "Part One with example input");
    }

    static void testPartTwo() {
        int result = Day05.partTwo(TEST_INPUT);
        assertEqual(result, 123, "Part Two with example input");
    }

    static void testWithFile() throws Exception {
        String fileInput = AocUtils.readString("scripting/test/day05.txt");
        int result = Day05.partOne(fileInput);
        assertEqual(result, 143, "Part One with test file");
    }

    static void assertEqual(int actual, int expected, String testName) {
        if (actual != expected) {
            throw new AssertionError(
                String.format("%s failed: expected %d but got %d", testName, expected, actual)
            );
        }
        System.out.println("✓ " + testName);
    }
}
