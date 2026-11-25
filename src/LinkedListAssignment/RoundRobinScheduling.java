package LinkedListAssignment;

import java.util.ArrayList;
import java.util.List;

/**
 * 6. Circular Linked List: Round Robin Scheduling Algorithm
 */
public class RoundRobinScheduling {
    static class ProcessNode {
        int processId;
        int burstTime;
        int remainingTime;
        int priority;
        int waitingTime;
        int turnaroundTime;
        ProcessNode next;

        ProcessNode(int processId, int burstTime, int priority) {
            this.processId = processId;
            this.burstTime = burstTime;
            this.remainingTime = burstTime;
            this.priority = priority;
        }

        @Override
        public String toString() {
            return String.format("P%d(rem=%d, prio=%d)", processId, remainingTime, priority);
        }
    }

    private ProcessNode head;
    private ProcessNode tail;

    public void addProcess(int processId, int burstTime, int priority) {
        ProcessNode node = new ProcessNode(processId, burstTime, priority);
        if (head == null) {
            head = tail = node;
            node.next = head;
            return;
        }
        tail.next = node;
        tail = node;
        tail.next = head;
    }

    private void removeProcess(ProcessNode prev, ProcessNode target) {
        if (head == tail && target == head) {
            head = tail = null;
        } else {
            prev.next = target.next;
            if (target == head) {
                head = target.next;
            }
            if (target == tail) {
                tail = prev;
            }
        }
        target.next = null;
    }

    public void displayQueue() {
        if (head == null) {
            System.out.println("  Queue: [empty]");
            return;
        }
        StringBuilder sb = new StringBuilder("  Queue: [");
        ProcessNode curr = head;
        do {
            sb.append(curr);
            curr = curr.next;
            if (curr != head) {
                sb.append(", ");
            }
        } while (curr != head);
        sb.append("]");
        System.out.println(sb);
    }

    public void simulate(int quantum) {
        if (head == null || quantum <= 0) {
            return;
        }
        List<ProcessNode> completed = new ArrayList<>();
        int currentTime = 0;
        int round = 1;
        ProcessNode curr = head;
        ProcessNode prev = tail;

        while (head != null) {
            int execTime = Math.min(quantum, curr.remainingTime);
            curr.remainingTime -= execTime;
            currentTime += execTime;

            if (curr.remainingTime == 0) {
                curr.turnaroundTime = currentTime;
                curr.waitingTime = curr.turnaroundTime - curr.burstTime;
                completed.add(curr);

                ProcessNode nextNode = curr.next;
                removeProcess(prev, curr);
                curr = (head == null) ? null : nextNode;
            } else {
                prev = curr;
                curr = curr.next;
            }

            if (head != null && curr == head) {
                System.out.println("After round " + round++ + ":");
                displayQueue();
            }
        }

        double totalWait = 0;
        double totalTurnaround = 0;
        System.out.println("Completed processes:");
        for (ProcessNode p : completed) {
            totalWait += p.waitingTime;
            totalTurnaround += p.turnaroundTime;
            System.out.printf("  P%d: Waiting=%d, Turnaround=%d%n", p.processId, p.waitingTime, p.turnaroundTime);
        }
        System.out.printf("Average Waiting Time: %.2f%n", totalWait / completed.size());
        System.out.printf("Average Turnaround Time: %.2f%n", totalTurnaround / completed.size());
    }

    public static void main(String[] args) {
        RoundRobinScheduling rr = new RoundRobinScheduling();
        rr.addProcess(1, 10, 1);
        rr.addProcess(2, 4, 2);
        rr.addProcess(3, 6, 1);

        System.out.println("Initial Queue:");
        rr.displayQueue();
        rr.simulate(3);
    }
}
