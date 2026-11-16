package LinkedList;

import java.util.Scanner;





public class InsertionAtStart {

    public static Node Insert(int data) {
        Node newNode =  new Node(data);
        if(Head==null){
            Head = newNode;
            return Head;
        }
        newNode.next = Head;
        Head = newNode;
        return Head;

    }
     public static void display(Node Head){
        Node temp = Head;
         while (temp != null) {
             System.out.print(temp.data+"->>");
             temp = temp.next;
         }
         System.out.println("null");
     }
    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }


    }
    public static Node Head;





    public static void main(String[] args) {
        Node first = new Node(10);
        Node second = new Node(20);
        Node third = new Node(30);

        first.next = second;
        second.next = third;

       Head = first;


        System.out.println("inserting a element  at start:");

       Scanner sc = new Scanner(System.in);
       int data = sc.nextInt();

       display(Insert(data));



    }
}
