package LinkedListAssignment;

/**
 * 3. Circular Linked List: Task Scheduler
 */
public class TaskScheduler {
    static class TaskNode {
        int taskId;
        String taskName;
        int priority;
        String dueDate;
        TaskNode next;

        TaskNode(int taskId, String taskName, int priority, String dueDate) {
            this.taskId = taskId;
            this.taskName = taskName;
            this.priority = priority;
            this.dueDate = dueDate;
        }

        @Override
        public String toString() {
            return String.format("ID=%d, Name=%s, Priority=%d, Due=%s", taskId, taskName, priority, dueDate);
        }
    }

    private TaskNode head;
    private TaskNode tail;
    private TaskNode currentTask;

    public void addAtBeginning(int id, String name, int priority, String dueDate) {
        TaskNode node = new TaskNode(id, name, priority, dueDate);
        if (head == null) {
            head = tail = currentTask = node;
            node.next = head;
            return;
        }
        node.next = head;
        head = node;
        tail.next = head;
    }

    public void addAtEnd(int id, String name, int priority, String dueDate) {
        TaskNode node = new TaskNode(id, name, priority, dueDate);
        if (head == null) {
            head = tail = currentTask = node;
            node.next = head;
            return;
        }
        tail.next = node;
        tail = node;
        tail.next = head;
    }

    public void addAtPosition(int position, int id, String name, int priority, String dueDate) {
        if (position <= 1 || head == null) {
            addAtBeginning(id, name, priority, dueDate);
            return;
        }
        TaskNode curr = head;
        for (int i = 1; i < position - 1 && curr.next != head; i++) {
            curr = curr.next;
        }
        if (curr == tail) {
            addAtEnd(id, name, priority, dueDate);
            return;
        }
        TaskNode node = new TaskNode(id, name, priority, dueDate);
        node.next = curr.next;
        curr.next = node;
    }

    public boolean removeByTaskId(int taskId) {
        if (head == null) {
            return false;
        }
        TaskNode curr = head;
        TaskNode prev = tail;
        do {
            if (curr.taskId == taskId) {
                if (head == tail) {
                    head = tail = currentTask = null;
                } else {
                    prev.next = curr.next;
                    if (curr == head) {
                        head = curr.next;
                    }
                    if (curr == tail) {
                        tail = prev;
                    }
                    if (curr == currentTask) {
                        currentTask = curr.next;
                    }
                }
                curr.next = null;
                return true;
            }
            prev = curr;
            curr = curr.next;
        } while (curr != head);
        return false;
    }

    public TaskNode viewCurrentAndAdvance() {
        if (currentTask == null) {
            return null;
        }
        TaskNode task = currentTask;
        currentTask = currentTask.next;
        return task;
    }

    public void displayAll() {
        if (head == null) {
            System.out.println("No tasks scheduled.");
            return;
        }
        System.out.println("All Tasks:");
        TaskNode curr = head;
        do {
            System.out.println("  " + curr);
            curr = curr.next;
        } while (curr != head);
    }

    public void searchByPriority(int priority) {
        System.out.println("Tasks with priority " + priority + ":");
        if (head == null) {
            return;
        }
        TaskNode curr = head;
        boolean found = false;
        do {
            if (curr.priority == priority) {
                System.out.println("  " + curr);
                found = true;
            }
            curr = curr.next;
        } while (curr != head);
        if (!found) {
            System.out.println("  None found.");
        }
    }

    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();
        scheduler.addAtEnd(1, "Design DB", 1, "2026-11-26");
        scheduler.addAtEnd(2, "Build API", 2, "2026-11-27");
        scheduler.addAtBeginning(3, "Setup Repo", 1, "2026-11-25");
        scheduler.addAtPosition(2, 4, "Write Tests", 3, "2026-11-28");

        scheduler.displayAll();
        System.out.println("Current -> Next: " + scheduler.viewCurrentAndAdvance());
        System.out.println("Current -> Next: " + scheduler.viewCurrentAndAdvance());
        scheduler.searchByPriority(1);
        scheduler.removeByTaskId(2);
        scheduler.displayAll();
    }
}
