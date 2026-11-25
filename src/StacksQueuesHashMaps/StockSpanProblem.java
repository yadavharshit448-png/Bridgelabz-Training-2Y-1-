package StacksQueuesHashMaps;

import java.util.Arrays;
import java.util.Stack;

/**
 * Problem 3 (Stacks & Queues): Stock Span Problem.
 * Span is the number of consecutive days (including today) the price was <= today's price.
 */
public class StockSpanProblem {
    public static int[] calculateSpan(int[] prices) {
        int n = prices.length;
        int[] span = new int[n];
        Stack<Integer> indexStack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!indexStack.isEmpty() && prices[indexStack.peek()] <= prices[i]) {
                indexStack.pop();
            }
            span[i] = indexStack.isEmpty() ? (i + 1) : (i - indexStack.peek());
            indexStack.push(i);
        }

        return span;
    }

    public static void main(String[] args) {
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        int[] spans = calculateSpan(prices);
        System.out.println("Prices: " + Arrays.toString(prices));
        System.out.println("Spans:  " + Arrays.toString(spans)); // [1, 1, 1, 2, 1, 4, 6]
    }
}
