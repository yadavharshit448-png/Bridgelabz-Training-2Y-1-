package Stack;

import java.util.Scanner;
import java.util.Stack;

public class leetcode1021 {

    static class Solution {
        public String removeOuterParentheses(String s) {
            Stack<Character> st = new Stack<>();
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);

                if (ch == '(') {
                    if (!st.isEmpty()) {
                        result.append(ch);
                    }
                    st.push(ch);
                } else {
                    st.pop();
                    if (!st.isEmpty()) {
                        result.append(ch);
                    }
                }
            }

            return result.toString();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter parentheses string: ");
        String input = sc.nextLine();

        Solution sol = new Solution();
        String result = sol.removeOuterParentheses(input);

        System.out.println("Result: " + result);

        sc.close();
    }
}
