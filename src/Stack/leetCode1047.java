package Stack;

import java.util.Scanner;

public class leetCode1047 {

    static class Solution {
        public String removeDuplicates(String s) {

            java.util.Stack<Character> st = new java.util.Stack<>();

            for (int i = 0; i < s.length(); i++) {

                char ch = s.charAt(i);

                if (st.isEmpty()) {
                    st.push(ch);
                } else if (st.peek() == ch) {
                    st.pop();
                } else {
                    st.push(ch);
                }
            }

            StringBuilder sb = new StringBuilder();

            while (!st.isEmpty()) {
                sb.append(st.pop());
            }

            return sb.reverse().toString();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the string:");
        String s = sc.nextLine();

        Solution sol = new Solution();
        String result = sol.removeDuplicates(s);

        System.out.println("Result: " + result);

        sc.close();
    }
}
