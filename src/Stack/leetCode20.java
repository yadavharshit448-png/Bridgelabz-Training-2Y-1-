package Stack;

import java.util.Scanner;
import java.util.Stack;

public class leetCode20 {

    public boolean isValid(String s) {

        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (ch == '(' || ch == '{' || ch == '[') {
                st.push(ch);
            } else {

                if (st.isEmpty()) {
                    return false;
                }

                char tmp = st.pop();

                if (ch == ')' && tmp != '(') {
                    return false;
                }

                if (ch == '}' && tmp != '{') {
                    return false;
                }

                if (ch == ']' && tmp != '[') {
                    return false;
                }
            }
        }

        return st.isEmpty();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter brackets: ");
        String input = sc.nextLine();

        leetCode20 obj = new leetCode20();
        boolean result = obj.isValid(input);

        System.out.println(result);

        sc.close();
    }
}
