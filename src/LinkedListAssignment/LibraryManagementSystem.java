package LinkedListAssignment;

/**
 * 5. Doubly Linked List: Library Management System
 */
public class LibraryManagementSystem {
    static class BookNode {
        String title;
        String author;
        String genre;
        int bookId;
        boolean available;
        BookNode prev;
        BookNode next;

        BookNode(String title, String author, String genre, int bookId, boolean available) {
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.bookId = bookId;
            this.available = available;
        }

        @Override
        public String toString() {
            return String.format("ID=%d, Title=%s, Author=%s, Genre=%s, Available=%b",
                    bookId, title, author, genre, available);
        }
    }

    private BookNode head;
    private BookNode tail;
    private int count;

    public void addAtBeginning(String title, String author, String genre, int bookId, boolean available) {
        BookNode node = new BookNode(title, author, genre, bookId, available);
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        count++;
    }

    public void addAtEnd(String title, String author, String genre, int bookId, boolean available) {
        BookNode node = new BookNode(title, author, genre, bookId, available);
        if (tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        count++;
    }

    public void addAtPosition(int position, String title, String author, String genre, int bookId, boolean available) {
        if (position <= 1 || head == null) {
            addAtBeginning(title, author, genre, bookId, available);
            return;
        }
        BookNode curr = head;
        for (int i = 1; i < position - 1 && curr.next != null; i++) {
            curr = curr.next;
        }
        if (curr.next == null) {
            addAtEnd(title, author, genre, bookId, available);
            return;
        }
        BookNode node = new BookNode(title, author, genre, bookId, available);
        node.next = curr.next;
        node.prev = curr;
        curr.next.prev = node;
        curr.next = node;
        count++;
    }

    public boolean removeByBookId(int bookId) {
        BookNode curr = head;
        while (curr != null) {
            if (curr.bookId == bookId) {
                if (curr == head) {
                    head = curr.next;
                } else {
                    curr.prev.next = curr.next;
                }
                if (curr == tail) {
                    tail = curr.prev;
                } else {
                    curr.next.prev = curr.prev;
                }
                curr.prev = null;
                curr.next = null;
                count--;
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public void searchByTitleOrAuthor(String query) {
        System.out.println("Search results for '" + query + "':");
        BookNode curr = head;
        boolean found = false;
        while (curr != null) {
            if (curr.title.equalsIgnoreCase(query) || curr.author.equalsIgnoreCase(query)) {
                System.out.println("  " + curr);
                found = true;
            }
            curr = curr.next;
        }
        if (!found) {
            System.out.println("  None found.");
        }
    }

    public boolean updateAvailability(int bookId, boolean available) {
        BookNode curr = head;
        while (curr != null) {
            if (curr.bookId == bookId) {
                curr.available = available;
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public void displayForward() {
        System.out.println("Library Books (Forward):");
        BookNode curr = head;
        while (curr != null) {
            System.out.println("  " + curr);
            curr = curr.next;
        }
    }

    public void displayReverse() {
        System.out.println("Library Books (Reverse):");
        BookNode curr = tail;
        while (curr != null) {
            System.out.println("  " + curr);
            curr = curr.prev;
        }
    }

    public int totalBooks() {
        return count;
    }

    public static void main(String[] args) {
        LibraryManagementSystem lib = new LibraryManagementSystem();
        lib.addAtEnd("Clean Code", "Robert Martin", "Software", 1, true);
        lib.addAtBeginning("Effective Java", "Joshua Bloch", "Java", 2, true);
        lib.addAtPosition(2, "Design Patterns", "GoF", "Software", 3, false);

        lib.displayForward();
        lib.displayReverse();
        System.out.println("Total books: " + lib.totalBooks());

        lib.searchByTitleOrAuthor("Joshua Bloch");
        lib.updateAvailability(3, true);
        lib.removeByBookId(1);
        System.out.println("Total books after removal: " + lib.totalBooks());
    }
}
