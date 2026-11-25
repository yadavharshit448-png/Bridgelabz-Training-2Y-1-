package LinkedListAssignment;

/**
 * 1. Singly Linked List: Student Record Management
 */
public class StudentRecordManagement {
    static class StudentNode {
        int rollNumber;
        String name;
        int age;
        String grade;
        StudentNode next;

        StudentNode(int rollNumber, String name, int age, String grade) {
            this.rollNumber = rollNumber;
            this.name = name;
            this.age = age;
            this.grade = grade;
        }

        @Override
        public String toString() {
            return String.format("Roll=%d, Name=%s, Age=%d, Grade=%s", rollNumber, name, age, grade);
        }
    }

    private StudentNode head;

    public void addAtBeginning(int rollNumber, String name, int age, String grade) {
        StudentNode node = new StudentNode(rollNumber, name, age, grade);
        node.next = head;
        head = node;
    }

    public void addAtEnd(int rollNumber, String name, int age, String grade) {
        StudentNode node = new StudentNode(rollNumber, name, age, grade);
        if (head == null) {
            head = node;
            return;
        }
        StudentNode curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = node;
    }

    public void addAtPosition(int position, int rollNumber, String name, int age, String grade) {
        if (position <= 1 || head == null) {
            addAtBeginning(rollNumber, name, age, grade);
            return;
        }
        StudentNode curr = head;
        for (int i = 1; i < position - 1 && curr.next != null; i++) {
            curr = curr.next;
        }
        StudentNode node = new StudentNode(rollNumber, name, age, grade);
        node.next = curr.next;
        curr.next = node;
    }

    public boolean deleteByRollNumber(int rollNumber) {
        if (head == null) {
            return false;
        }
        if (head.rollNumber == rollNumber) {
            StudentNode temp = head;
            head = head.next;
            temp.next = null;
            return true;
        }
        StudentNode curr = head;
        while (curr.next != null && curr.next.rollNumber != rollNumber) {
            curr = curr.next;
        }
        if (curr.next == null) {
            return false;
        }
        StudentNode target = curr.next;
        curr.next = target.next;
        target.next = null;
        return true;
    }

    public StudentNode searchByRollNumber(int rollNumber) {
        StudentNode curr = head;
        while (curr != null) {
            if (curr.rollNumber == rollNumber) {
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }

    public boolean updateGrade(int rollNumber, String newGrade) {
        StudentNode student = searchByRollNumber(rollNumber);
        if (student == null) {
            return false;
        }
        student.grade = newGrade;
        return true;
    }

    public void displayAll() {
        if (head == null) {
            System.out.println("No student records.");
            return;
        }
        StudentNode curr = head;
        while (curr != null) {
            System.out.println("  " + curr);
            curr = curr.next;
        }
    }

    public static void main(String[] args) {
        StudentRecordManagement list = new StudentRecordManagement();
        list.addAtBeginning(101, "Alice", 20, "A");
        list.addAtEnd(103, "Charlie", 21, "B");
        list.addAtPosition(2, 102, "Bob", 20, "B+");

        System.out.println("All students:");
        list.displayAll();

        System.out.println("Search 102: " + list.searchByRollNumber(102));
        list.updateGrade(102, "A-");
        System.out.println("After updating 102 grade: " + list.searchByRollNumber(102));

        list.deleteByRollNumber(101);
        System.out.println("After deleting 101:");
        list.displayAll();
    }
}
