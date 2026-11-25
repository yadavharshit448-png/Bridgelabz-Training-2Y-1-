package Stack;

public class
StackOps2 {

    int ar[];
    int top = 0;

    public StackOps2(int size) {
        ar = new int[size];
    }

    public void push(int data) {

        if (top == ar.length) {
            System.out.println("stack is full");
            return;
        }

        ar[top] = data;
        top++;
        System.out.println("data pushed");
    }

    public void display() {
        if (top == 0) {
            System.out.println("stack is empty");
            return;
        }

        for (int i = top - 1; i >= 0; i--) {
            System.out.println(ar[i]);
        }
    }

    public void pop() {
        if (top == 0) {
            System.out.println("stack is empty");
            return;
        }

        top--;
        int data = ar[top];
        ar[top] = 0;
        System.out.println("popped data: " + data);
    }

    public void peek() {
        if (top == 0) {
            System.out.println("stack is empty");
            return;
        }

        int data = ar[top - 1];
        System.out.println("top data: " + data);
    }
}
