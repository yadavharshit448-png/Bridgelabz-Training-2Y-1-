package LinkedListAssignment;

/**
 * 2. Doubly Linked List: Movie Management System
 */
public class MovieManagementSystem {
    static class MovieNode {
        String title;
        String director;
        int year;
        double rating;
        MovieNode prev;
        MovieNode next;

        MovieNode(String title, String director, int year, double rating) {
            this.title = title;
            this.director = director;
            this.year = year;
            this.rating = rating;
        }

        @Override
        public String toString() {
            return String.format("%s (%d) - Dir: %s, Rating: %.1f", title, year, director, rating);
        }
    }

    private MovieNode head;
    private MovieNode tail;

    public void addAtBeginning(String title, String director, int year, double rating) {
        MovieNode node = new MovieNode(title, director, year, rating);
        if (head == null) {
            head = tail = node;
            return;
        }
        node.next = head;
        head.prev = node;
        head = node;
    }

    public void addAtEnd(String title, String director, int year, double rating) {
        MovieNode node = new MovieNode(title, director, year, rating);
        if (tail == null) {
            head = tail = node;
            return;
        }
        tail.next = node;
        node.prev = tail;
        tail = node;
    }

    public void addAtPosition(int position, String title, String director, int year, double rating) {
        if (position <= 1 || head == null) {
            addAtBeginning(title, director, year, rating);
            return;
        }
        MovieNode curr = head;
        for (int i = 1; i < position - 1 && curr.next != null; i++) {
            curr = curr.next;
        }
        if (curr.next == null) {
            addAtEnd(title, director, year, rating);
            return;
        }
        MovieNode node = new MovieNode(title, director, year, rating);
        node.next = curr.next;
        node.prev = curr;
        curr.next.prev = node;
        curr.next = node;
    }

    public boolean removeByTitle(String title) {
        MovieNode curr = head;
        while (curr != null) {
            if (curr.title.equalsIgnoreCase(title)) {
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
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public void searchByDirector(String director) {
        System.out.println("Movies by " + director + ":");
        MovieNode curr = head;
        boolean found = false;
        while (curr != null) {
            if (curr.director.equalsIgnoreCase(director)) {
                System.out.println("  " + curr);
                found = true;
            }
            curr = curr.next;
        }
        if (!found) {
            System.out.println("  None found.");
        }
    }

    public void searchByRating(double minRating) {
        System.out.println("Movies with rating >= " + minRating + ":");
        MovieNode curr = head;
        boolean found = false;
        while (curr != null) {
            if (curr.rating >= minRating) {
                System.out.println("  " + curr);
                found = true;
            }
            curr = curr.next;
        }
        if (!found) {
            System.out.println("  None found.");
        }
    }

    public boolean updateRating(String title, double newRating) {
        MovieNode curr = head;
        while (curr != null) {
            if (curr.title.equalsIgnoreCase(title)) {
                curr.rating = newRating;
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public void displayForward() {
        System.out.println("Movies (Forward):");
        MovieNode curr = head;
        while (curr != null) {
            System.out.println("  " + curr);
            curr = curr.next;
        }
    }

    public void displayReverse() {
        System.out.println("Movies (Reverse):");
        MovieNode curr = tail;
        while (curr != null) {
            System.out.println("  " + curr);
            curr = curr.prev;
        }
    }

    public static void main(String[] args) {
        MovieManagementSystem mms = new MovieManagementSystem();
        mms.addAtBeginning("Inception", "Christopher Nolan", 2010, 8.8);
        mms.addAtEnd("Interstellar", "Christopher Nolan", 2014, 8.7);
        mms.addAtPosition(2, "The Matrix", "Wachowskis", 1999, 8.7);

        mms.displayForward();
        mms.displayReverse();
        mms.searchByDirector("Christopher Nolan");
        mms.searchByRating(8.8);
        mms.updateRating("The Matrix", 8.9);
        mms.removeByTitle("Inception");
        mms.displayForward();
    }
}
