package LinkedList;

public class DisplayList {


    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    static Node Head;
    public static void display(Node Head){
        Node temp = Head;
        while (temp != null) {
            System.out.print(temp.data+"->");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {

        Node first = new Node(10);
        Node second = new Node(20);
        Node third = new Node(30);

        // Connecting nodes
        first.next = second;
        second.next = third;
        Head = first;

        display(Head);


    }


}
