# Advent of Code 2024 in Java

Based on the [vscode-remote-try-java](https://github.com/microsoft/vscode-remote-try-java) template

## Project Structure

```
scripting/
├── utils/
│   └── AocUtils.java          # Shared utilities (file reading, parsing)
├── input/
│   └── dayXX.txt              # Real puzzle inputs
├── test/
│   └── dayXX.txt              # Example/test inputs from puzzle descriptions
├── Day01.java                 # Solution files
├── Day02.java
├── ...
└── DayTemplate.java           # Template for new days
```

## Running Solutions

### With Real Input (default)

From the repo root directory:

```bash
$ java scripting/Day01.java
```

### With Test Input

```bash
$ java scripting/Day01.java scripting/test/day01.txt
```

### With Custom Input File

```bash
$ java scripting/Day01.java path/to/input.txt
```

## Creating a New Day

1. Copy `DayTemplate.java` to `DayXX.java` (replace XX with day number)
2. Replace `XX` in the file with the actual day number
3. Add your puzzle input to `scripting/input/dayXX.txt`
4. Add test input to `scripting/test/dayXX.txt`
5. Implement `partOne()` and `partTwo()` methods
6. Run with test input first to verify: `java scripting/DayXX.java scripting/test/dayXX.txt`
7. Run with real input: `java scripting/DayXX.java`

## Utilities

The `AocUtils` class provides common file reading utilities:

- `readLines(filename)` - Read file as `List<String>`
- `readString(filename)` - Read entire file as `String`
- `readStream(filename)` - Stream lines from file
- `parseIntegers(line, delimiter)` - Parse delimited integers
