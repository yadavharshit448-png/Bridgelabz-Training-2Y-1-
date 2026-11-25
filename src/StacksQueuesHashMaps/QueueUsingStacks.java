package StacksQueuesHashMaps;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Problem 1 (Stacks & Queues): Implement a Queue Using Two Stacks.
 */
public class QueueUsingStacks<T> {
    private final Stack<T> enqueueStack = new Stack<>();
    private final Stack<T> dequeueStack = new Stack<>();

    public void enqueue(T item) {
        enqueueStack.push(item);
    }

    public T dequeue() {
        shiftStacksIfNeeded();
        if (dequeueStack.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return dequeueStack.pop();
    }

    public T peek() {
        shiftStacksIfNeeded();
        if (dequeueStack.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return dequeueStack.peek();
    }

    public boolean isEmpty() {
        return enqueueStack.isEmpty() && dequeueStack.isEmpty();
    }

    private void shiftStacksIfNeeded() {
        if (dequeueStack.isEmpty()) {
            while (!enqueueStack.isEmpty()) {
                dequeueStack.push(enqueueStack.pop());
            }
        }
    }

    public static void main(String[] args) {
        QueueUsingStacks<Integer> queue = new QueueUsingStacks<>();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        System.out.println("Peek: " + queue.peek());       // 10
        System.out.println("Dequeue: " + queue.dequeue()); // 10
        queue.enqueue(40);
        System.out.println("Dequeue: " + queue.dequeue()); // 20
        System.out.println("Dequeue: " + queue.dequeue()); // 30
        System.out.println("Dequeue: " + queue.dequeue()); // 40
        System.out.println("Is empty: " + queue.isEmpty()); // true
    }
}
