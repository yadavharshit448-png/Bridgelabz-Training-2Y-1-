package Stack;

import java.util.Stack;

public class MonoTonicIncStack {

    public static void main(String[] args) {
        int arr[] = {1,2,35443534,3,6,3,73434,};

        Stack<Integer> st = new Stack<>();
        for(int value :arr){
            while(!st.isEmpty() && st.peek()< value){
                st.pop();
            }
            st.push(value);
        }
        System.out.println("decreasing stack :" +st);

    }
}
