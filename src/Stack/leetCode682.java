package Stack;

import java.util.Scanner;
import java.util.Stack;

public class leetCode682 {
    class Solution {
        public int calPoints(String[] operations) {

            Stack<Integer> st = new Stack<>();

            for (int i = 0; i < operations.length; i++) {

                String numbers = operations[i];

                if (numbers.equals("+")) {

                    int val1 = st.pop();
                    int val2 = st.pop();

                    int newVal = val1 + val2;

                    st.push(val2);
                    st.push(val1);
                    st.push(newVal);

                }
                else if (numbers.equals("D")) {

                    st.push(2 * st.peek());

                }
                else if (numbers.equals("C")) {

                    st.pop();

                }
                else {

                    st.push(Integer.parseInt(numbers));

                }
            }

            int totalSum = 0;

            while (!st.isEmpty()) {
                totalSum += st.pop();
            }

            return totalSum;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of operations: ");
        int n = sc.nextInt();

        String[] operations = new String[n];

        System.out.println("Enter operations:");
        for (int i = 0; i < n; i++) {
            operations[i] = sc.next();
        }

        leetCode682 obj = new leetCode682();
        Solution sol = obj.new Solution();

        int result = sol.calPoints(operations);
        System.out.println("Total score: " + result);

        sc.close();
    }
}
