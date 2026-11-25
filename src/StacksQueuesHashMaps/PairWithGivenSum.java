package StacksQueuesHashMaps;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem 2 (Hash Maps): Check for a Pair with Given Sum in an Array.
 */
public class PairWithGivenSum {
    public static boolean hasPairWithSum(int[] arr, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];
            if (seen.containsKey(complement)) {
                return true;
            }
            seen.put(arr[i], i);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {8, 7, 2, 5, 3, 1};
        System.out.println("Target 10 exists: " + hasPairWithSum(arr, 10)); // true (8+2, 7+3)
        System.out.println("Target 20 exists: " + hasPairWithSum(arr, 20)); // false
    }
}
