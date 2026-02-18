package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SolutionTest {

    private final Solution solution = new Solution();

    @ParameterizedTest
    @DisplayName("Basic test cases with expected area")
    @CsvSource(value = {
            "1,8,6,2,5,4,8,3,7; 49",
            "1,1; 1",
            "2,3; 2",
            "1,2,1; 2",
            "1,2,4,3; 4",
            "0,0; 0",
            "0,1; 0",
            "1,0; 0",
            "5; 0",
            "; 0"
    }, delimiter = ';')
    void testMaxAreaWithCsvSource(String heightStr, int expected) {
        int[] height = parseArray(heightStr);
        assertEquals(expected, solution.calculatemaxArea(height),
                () -> String.format("Failed for input: [%s]", heightStr));
    }


    @ParameterizedTest
    @DisplayName("Edge and stress test cases")
    @MethodSource("provideStressCases")
    void testMaxAreaWithMethodSource(int[] height, int expected, String description) {
        assertEquals(expected, solution.calculatemaxArea(height), description);
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> provideStressCases() {
        return Stream.of(
                Arguments.of(new int[]{}, 0, "Empty array"),
                Arguments.of(new int[]{100}, 0, "Single element"),
                Arguments.of(new int[]{0, 0, 0, 0}, 0, "All zeros"),
                Arguments.of(new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1}, 25, "Decreasing sequence"),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 25, "Increasing sequence"),
                Arguments.of(new int[]{1, 100, 1, 100, 1}, 200, "Peaks at even indices"),
                Arguments.of(new int[]{1, 1, 1, 1, 100, 100, 1, 1, 1, 1}, 100, "Two high walls in middle"),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1}, 50, "Symmetric peak"),
                Arguments.of(new int[]{1000000, 1, 1000000}, 2000000, "Large values, wide gap")
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"null"})
    void testNullThrowsException(String ignored) {
        assertThrows(IllegalArgumentException.class, () -> solution.calculatemaxArea(null));
    }

    private static int[] parseArray(String str) {
        if (str == null || str.trim().isEmpty()) {
            return new int[0];
        }
        String[] parts = str.trim().split("\\s*,\\s*");
        int[] result = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            result[i] = Integer.parseInt(parts[i]);
        }
        return result;
    }
}