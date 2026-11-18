package Stack;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("enter the size of stack");
        int n = sc.nextInt();

        StackOps2 st = new StackOps2(n);

        int choice;

        do {
            System.out.println("\n1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Display");
            System.out.println("5. Exit");
            System.out.println("enter your choice");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("enter the data");
                    int data = sc.nextInt();
                    st.push(data);
                    break;

                case 2:
                    st.pop();
                    break;

                case 3:
                    st.peek();
                    break;

                case 4:
                    st.display();
                    break;

                case 5:
                    System.out.println("exiting...");
                    break;

                default:
                    System.out.println("invalid choice");
            }

        } while (choice != 5);

        sc.close();
    }
}
