package com.example;

public class Solution {
    public int calculatemaxArea(int[] height) {
        if (height == null) {
            throw new IllegalArgumentException("Height array cannot be null");
        }
        if (height.length < 2) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, area);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

}
