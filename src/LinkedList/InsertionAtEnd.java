package LinkedList;

import java.util.Scanner;

public class InsertionAtEnd {
    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    static Node tail;
    static Node head;

    public static void insertAtEnd(int data){
        Node newNode = new Node(data);

        if(head==null){
            newNode=head;
            return ;
        }
        Node temp = head;
        while(temp.next!=null){
            temp = temp.next;
        }
        temp.next = newNode;


    }


    public static void display(Node head){
        Node temp = head;
    while(temp!=null){
        System.out.println(temp.data+" -> ");
        temp= temp.next;
    }
        System.out.println("null");

    }
    public static void main(String[]args){
        Node first = new Node(10);
        Node second = new Node(20);
        Node third  = new Node(30);

        first.next = second;
        second.next = third;
        head = first;
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the last element");
        int data = sc.nextInt();

        insertAtEnd(data);
        display(head);


    }

}
