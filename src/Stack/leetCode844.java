package Stack;

import java.util.Scanner;
import java.util.Stack;

public class leetCode844 {

    static class Solution {

        public static String str(String s) {
            Stack<Character> st = new Stack<>();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);

                if (ch == '#') {
                    if (!st.isEmpty()) {
                        st.pop();
                    }
                } else {
                    st.push(ch);
                }
            }

            while (!st.isEmpty()) {
                sb.append(st.pop());
            }

            return sb.reverse().toString();
        }

        public boolean backspaceCompare(String s, String t) {
            String s1 = str(s);
            String s2 = str(t);

            return s1.equals(s2);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first string: ");
        String s = sc.nextLine();

        System.out.print("Enter second string: ");
        String t = sc.nextLine();

        Solution sol = new Solution();
        boolean result = sol.backspaceCompare(s, t);

        System.out.println("Result: " + result);

        sc.close();
    }
}
