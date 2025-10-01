///usr/bin/env jbang "$0" "$@" ; exit $?

import static java.lang.System.*;

import utils.AocUtils;
import java.util.List;
import java.util.function.*;
import java.util.stream.*;

public class Day04 {

    public static void main(String... args) throws Exception {
        String inputFile = args.length > 0 ? args[0] : "scripting/input/day04.txt";
        List<String> grid = AocUtils.readLines(inputFile);
        out.println(partOne(grid));
        out.println(partTwo(grid));
    }

    public static long partOne(List<String> grid) {
        int rows = grid.size();
        int cols = grid.get(0).length();
        
        record Direction(int dr, int dc) {}
        
        List<Direction> directions = List.of(
            new Direction(0, 1),   // right
            new Direction(0, -1),  // left
            new Direction(1, 0),   // down
            new Direction(-1, 0),  // up
            new Direction(1, 1),   // down-right
            new Direction(1, -1),  // down-left
            new Direction(-1, 1),  // up-right
            new Direction(-1, -1)  // up-left
        );
        
        Predicate<String> isXMAS = s -> s.equals("XMAS");
        
        BiFunction<Integer, Integer, Stream<String>> extractWindow = (row, col) -> 
            directions.stream()
                .map(dir -> extractSequence(grid, row, col, dir.dr(), dir.dc(), 4))
                .filter(s -> s.length() == 4);
        
        return IntStream.range(0, rows)
            .boxed()
            .flatMap(row -> IntStream.range(0, cols)
                .boxed()
                .flatMap(col -> extractWindow.apply(row, col)))
            .filter(isXMAS)
            .count();
    }

    private static String extractSequence(List<String> grid, int startRow, int startCol,
            int deltaRow, int deltaCol, int length) {
        StringBuilder sb = new StringBuilder();
        int row = startRow;
        int col = startCol;

        for (int i = 0; i < length; i++) {
            if (row < 0 || row >= grid.size() || col < 0 || col >= grid.get(0).length()) {
                break;
            }
            sb.append(grid.get(row).charAt(col));
            row += deltaRow;
            col += deltaCol;
        }

        return sb.toString();
    }

    public static long partTwo(List<String> grid) {
        int rows = grid.size();
        int cols = grid.get(0).length();

        Predicate<String> isMasOrSam = s -> s.equals("MAS") || s.equals("SAM");

        Function<Integer, Function<Integer, Boolean>> isValidXMas = row -> col -> {
            if (grid.get(row).charAt(col) != 'A') {
                return false;
            }

            String diagonal1 = extractSequence(grid, row - 1, col - 1, 1, 1, 3);
            String diagonal2 = extractSequence(grid, row - 1, col + 1, 1, -1, 3);

            return diagonal1.length() == 3 && diagonal2.length() == 3
                    && isMasOrSam.test(diagonal1) && isMasOrSam.test(diagonal2);
        };

        return IntStream.range(1, rows - 1)
                .boxed()
                .flatMap(row -> IntStream.range(1, cols - 1)
                        .filter(col -> isValidXMas.apply(row).apply(col))
                        .boxed())
                .count();
    }
}
