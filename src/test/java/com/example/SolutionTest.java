package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void exampleTest() {
        Solution sol = new Solution();
        int[] height = {1,8,6,2,5,4,8,3,7};
        assertEquals(49, sol.maxArea(height));
    }

    @Test
    void simpleTest() {
        Solution sol = new Solution();
        int[] height = {1,1};
        assertEquals(1, sol.maxArea(height));
    }

    @Test
    void increasingTest() {
        Solution sol = new Solution();
        int[] height = {1,2,3,4,5};
        assertEquals(6, sol.maxArea(height)); // min(2,5)*3 = 2*3=6? Actually: min(1,5)*4=4, but best is min(2,5)*3=6
    }

    @Test
    void singleElement() {
        Solution sol = new Solution();
        int[] height = {5};
        assertEquals(0, sol.maxArea(height)); // need at least 2 lines
    }
}