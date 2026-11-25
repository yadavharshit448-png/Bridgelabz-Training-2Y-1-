package Stack;

class Stack{


    int top;
    int[] arr;
    int size;

    public Stack(int size){
        this.top =-1;
        this.arr = new int[size];

    }
    void push(int data){
        if(top==arr.length-1){
            System.out.println("Stack Overflow");
            return;
        }
        arr[++top] = data;
    }
    void display(){
        if(top==-1){
            System.out.println("Stack Underflow");
            return;
        }
        for(int i=top;i>=0;i--){
            System.out.println(arr[i]);
        }
    }
    void pop(){
        if(top==-1){
            System.out.println("Stack Underflow");
            return;
        }
        System.out.println(arr[top--]);
    }
}





public class StackWithArray {


    public static void main(String[] args) {
        Stack stack = new Stack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);

        stack.push(4);
        stack.push(5);
        stack.display();
        stack.pop();
        stack.display();

    }
}
