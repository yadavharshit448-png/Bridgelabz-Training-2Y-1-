package LinkedList;

public class Searching {

    static class Node{
        int data;
        Node next;
        Node(int data){
            this.data = data;
            this.next = null;
        }
    }
    static Node head;

    public static void display(DisplayList.Node Head){
        DisplayList.Node temp = Head;
        while (temp != null) {
            System.out.print(temp.data+"->");
            temp = temp.next;
        }
        System.out.println("null");
    }
    public static  void main (String [] args){

        Node first = new Node(1);
        Node second = new Node(2);
        Node third
    }



}
