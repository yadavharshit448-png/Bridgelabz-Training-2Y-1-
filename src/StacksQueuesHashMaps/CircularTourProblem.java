package StacksQueuesHashMaps;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Problem 5 (Stacks & Queues): Circular Tour Problem (Gas Station / Petrol Pump).
 * Determines the starting pump index to complete a circular tour, or -1 if impossible.
 */
public class CircularTourProblem {
    public static int findStartingPump(int[] petrol, int[] distance) {
        int n = petrol.length;
        if (n == 0 || n != distance.length) {
            return -1;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        int start = 0;
        int currentSurplus = 0;
        int totalSurplus = 0;

        for (int i = 0; i < n; i++) {
            int net = petrol[i] - distance[i];
            totalSurplus += net;
            currentSurplus += net;
            queue.offer(i);

            while (currentSurplus < 0 && !queue.isEmpty()) {
                int removed = queue.poll();
                currentSurplus -= (petrol[removed] - distance[removed]);
                start = i + 1;
            }
        }

        return totalSurplus >= 0 ? start % n : -1;
    }

    public static void main(String[] args) {
        int[] petrol = {4, 6, 7, 4};
        int[] distance = {6, 5, 3, 5};
        int start = findStartingPump(petrol, distance);
        System.out.println("Starting pump index: " + start); // 1

        int[] petrol2 = {1, 2, 3};
        int[] distance2 = {3, 4, 5};
        System.out.println("Starting pump index (impossible): " + findStartingPump(petrol2, distance2)); // -1
    }
}
