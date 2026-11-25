package StacksQueuesHashMaps;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem 3 (Hash Maps): Longest Consecutive Sequence in an unsorted array.
 */
public class LongestConsecutiveSequence {
    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Map<Integer, Boolean> present = new HashMap<>();
        for (int num : nums) {
            present.put(num, true);
        }

        int longest = 0;
        for (int num : present.keySet()) {
            // Only start counting from the beginning of a sequence
            if (!present.containsKey(num - 1)) {
                int current = num;
                int streak = 1;
                while (present.containsKey(current + 1)) {
                    current++;
                    streak++;
                }
                longest = Math.max(longest, streak);
            }
        }

        return longest;
    }

    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println("Longest consecutive sequence length: " + longestConsecutive(nums)); // 4
    }
}
