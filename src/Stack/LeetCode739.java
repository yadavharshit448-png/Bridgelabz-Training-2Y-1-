package Stack;

import java.util.Scanner;
import java.util.Stack;

public class LeetCode739 {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> st = new Stack<>();
        int ans[] = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            while (!st.isEmpty() && temperatures[i] > temperatures[st.peek()]) {
                int prev = st.pop();
                ans[prev] = i - prev;
            }
            st.push(i);
        }

        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of days: ");
        int n = sc.nextInt();

        int[] temperatures = new int[n];

        System.out.println("Enter temperatures:");
        for (int i = 0; i < n; i++) {
            temperatures[i] = sc.nextInt();
        }

        LeetCode739 solution = new LeetCode739();
        int[] result = solution.dailyTemperatures(temperatures);

        System.out.println("Days to wait for a warmer temperature:");
        for (int value : result) {
            System.out.print(value + " ");
        }

        sc.close();
    }
}
