package LinkedListAssignment;

/**
 * 9. Circular Linked List: Online Ticket Reservation System
 */
public class OnlineTicketReservation {
    static class TicketNode {
        int ticketId;
        String customerName;
        String movieName;
        String seatNumber;
        String bookingTime;
        TicketNode next;

        TicketNode(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
            this.ticketId = ticketId;
            this.customerName = customerName;
            this.movieName = movieName;
            this.seatNumber = seatNumber;
            this.bookingTime = bookingTime;
        }

        @Override
        public String toString() {
            return String.format("Ticket#%d: %s | Movie=%s | Seat=%s | Time=%s",
                    ticketId, customerName, movieName, seatNumber, bookingTime);
        }
    }

    private TicketNode head;
    private TicketNode tail;
    private int count;

    public void addTicket(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        TicketNode node = new TicketNode(ticketId, customerName, movieName, seatNumber, bookingTime);
        if (head == null) {
            head = tail = node;
            node.next = head;
        } else {
            tail.next = node;
            tail = node;
            tail.next = head;
        }
        count++;
    }

    public boolean removeTicket(int ticketId) {
        if (head == null) {
            return false;
        }
        TicketNode curr = head;
        TicketNode prev = tail;
        do {
            if (curr.ticketId == ticketId) {
                if (head == tail) {
                    head = tail = null;
                } else {
                    prev.next = curr.next;
                    if (curr == head) {
                        head = curr.next;
                    }
                    if (curr == tail) {
                        tail = prev;
                    }
                }
                curr.next = null;
                count--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        } while (curr != head);
        return false;
    }

    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }
        System.out.println("Booked Tickets:");
        TicketNode curr = head;
        do {
            System.out.println("  " + curr);
            curr = curr.next;
        } while (curr != head);
    }

    public void searchByCustomerOrMovie(String query) {
        System.out.println("Search tickets for '" + query + "':");
        if (head == null) {
            return;
        }
        TicketNode curr = head;
        boolean found = false;
        do {
            if (curr.customerName.equalsIgnoreCase(query) || curr.movieName.equalsIgnoreCase(query)) {
                System.out.println("  " + curr);
                found = true;
            }
            curr = curr.next;
        } while (curr != head);
        if (!found) {
            System.out.println("  None found.");
        }
    }

    public int totalBookedTickets() {
        return count;
    }

    public static void main(String[] args) {
        OnlineTicketReservation system = new OnlineTicketReservation();
        system.addTicket(501, "Alice", "Inception", "A1", "10:00 AM");
        system.addTicket(502, "Bob", "Interstellar", "B4", "10:15 AM");
        system.addTicket(503, "Charlie", "Inception", "A2", "10:30 AM");

        system.displayTickets();
        System.out.println("Total booked: " + system.totalBookedTickets());
        system.searchByCustomerOrMovie("Inception");
        system.removeTicket(502);
        System.out.println("Total booked after cancellation: " + system.totalBookedTickets());
        system.displayTickets();
    }
}
