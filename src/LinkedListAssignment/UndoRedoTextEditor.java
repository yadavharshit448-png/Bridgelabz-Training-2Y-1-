package LinkedListAssignment;

/**
 * 8. Doubly Linked List: Undo/Redo Functionality for Text Editor
 */
public class UndoRedoTextEditor {
    static class StateNode {
        String content;
        StateNode prev;
        StateNode next;

        StateNode(String content) {
            this.content = content;
        }
    }

    private final int maxHistory;
    private StateNode head;
    private StateNode tail;
    private StateNode current;
    private int size;

    public UndoRedoTextEditor(int maxHistory) {
        this.maxHistory = maxHistory;
        StateNode initial = new StateNode("");
        head = tail = current = initial;
        size = 1;
    }

    public void addState(String newContent) {
        // Discard any redo history ahead of current
        if (current.next != null) {
            StateNode toClear = current.next;
            current.next = null;
            while (toClear != null) {
                StateNode next = toClear.next;
                toClear.prev = null;
                toClear.next = null;
                size--;
                toClear = next;
            }
            tail = current;
        }

        StateNode node = new StateNode(newContent);
        tail.next = node;
        node.prev = tail;
        tail = node;
        current = node;
        size++;

        while (size > maxHistory) {
            StateNode oldHead = head;
            head = head.next;
            head.prev = null;
            oldHead.next = null;
            size--;
        }
    }

    public boolean undo() {
        if (current.prev == null) {
            return false;
        }
        current = current.prev;
        return true;
    }

    public boolean redo() {
        if (current.next == null) {
            return false;
        }
        current = current.next;
        return true;
    }

    public String getCurrentText() {
        return current.content;
    }

    public static void main(String[] args) {
        UndoRedoTextEditor editor = new UndoRedoTextEditor(10);
        editor.addState("Hello");
        editor.addState("Hello World");
        editor.addState("Hello World!");

        System.out.println("Current: " + editor.getCurrentText());
        editor.undo();
        System.out.println("After undo: " + editor.getCurrentText());
        editor.undo();
        System.out.println("After 2nd undo: " + editor.getCurrentText());
        editor.redo();
        System.out.println("After redo: " + editor.getCurrentText());
        editor.addState("Hello Java");
        System.out.println("After new edit: " + editor.getCurrentText());
        System.out.println("Can redo after new edit: " + editor.redo());
    }
}
