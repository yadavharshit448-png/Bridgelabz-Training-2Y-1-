package Stack;

import java.util.Scanner;

public class leetCode2390 {

    static class Solution {
        public String removeStars(String s) {
            java.util.Stack<Character> st = new java.util.Stack<>();

            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);

                if (ch == '*') {
                    if (!st.isEmpty()) {
                        st.pop();
                    }
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
        String result = sol.removeStars(s);

        System.out.println("Result: " + result);

        sc.close();
    }
}
