package StacksQueuesHashMaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Problem 1 (Hash Maps): Find all subarrays whose elements sum up to zero.
 */
public class ZeroSumSubarrays {
    public static List<int[]> findZeroSumSubarrays(int[] arr) {
        List<int[]> result = new ArrayList<>();
        Map<Integer, List<Integer>> prefixSumIndices = new HashMap<>();

        // Prefix sum 0 at index -1 handles subarrays starting from index 0
        prefixSumIndices.computeIfAbsent(0, k -> new ArrayList<>()).add(-1);

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (prefixSumIndices.containsKey(sum)) {
                for (int prevIndex : prefixSumIndices.get(sum)) {
                    result.add(new int[]{prevIndex + 1, i});
                }
            }
            prefixSumIndices.computeIfAbsent(sum, k -> new ArrayList<>()).add(i);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {6, 3, -1, -3, 4, -2, 2, 4, 6, -12, -7};
        List<int[]> subarrays = findZeroSumSubarrays(arr);

        System.out.println("Zero-sum subarrays (start..end):");
        for (int[] range : subarrays) {
            System.out.println("[" + range[0] + ", " + range[1] + "]");
        }
    }
}
