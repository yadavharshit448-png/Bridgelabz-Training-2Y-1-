package StacksQueuesHashMaps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem 5 (Hash Maps): Two Sum Problem.
 * Returns indices of the two numbers that add up to target.
 */
public class TwoSumProblem {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indexByValue = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (indexByValue.containsKey(complement)) {
                return new int[]{indexByValue.get(complement), i};
            }
            indexByValue.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        System.out.println("Two Sum indices: " + Arrays.toString(twoSum(nums, target))); // [0, 1]
    }
}
