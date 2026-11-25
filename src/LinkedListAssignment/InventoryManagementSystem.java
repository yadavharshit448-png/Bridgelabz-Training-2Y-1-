package LinkedListAssignment;

/**
 * 4. Singly Linked List: Inventory Management System
 */
public class InventoryManagementSystem {
    static class ItemNode {
        String itemName;
        int itemId;
        int quantity;
        double price;
        ItemNode next;

        ItemNode(String itemName, int itemId, int quantity, double price) {
            this.itemName = itemName;
            this.itemId = itemId;
            this.quantity = quantity;
            this.price = price;
        }

        @Override
        public String toString() {
            return String.format("ID=%d, Name=%s, Qty=%d, Price=%.2f", itemId, itemName, quantity, price);
        }
    }

    private ItemNode head;

    public void addAtBeginning(String name, int id, int qty, double price) {
        ItemNode node = new ItemNode(name, id, qty, price);
        node.next = head;
        head = node;
    }

    public void addAtEnd(String name, int id, int qty, double price) {
        ItemNode node = new ItemNode(name, id, qty, price);
        if (head == null) {
            head = node;
            return;
        }
        ItemNode curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = node;
    }

    public void addAtPosition(int position, String name, int id, int qty, double price) {
        if (position <= 1 || head == null) {
            addAtBeginning(name, id, qty, price);
            return;
        }
        ItemNode curr = head;
        for (int i = 1; i < position - 1 && curr.next != null; i++) {
            curr = curr.next;
        }
        ItemNode node = new ItemNode(name, id, qty, price);
        node.next = curr.next;
        curr.next = node;
    }

    public boolean removeByItemId(int itemId) {
        if (head == null) {
            return false;
        }
        if (head.itemId == itemId) {
            ItemNode temp = head;
            head = head.next;
            temp.next = null;
            return true;
        }
        ItemNode curr = head;
        while (curr.next != null && curr.next.itemId != itemId) {
            curr = curr.next;
        }
        if (curr.next == null) {
            return false;
        }
        ItemNode target = curr.next;
        curr.next = target.next;
        target.next = null;
        return true;
    }

    public boolean updateQuantity(int itemId, int newQty) {
        ItemNode item = searchById(itemId);
        if (item == null) {
            return false;
        }
        item.quantity = newQty;
        return true;
    }

    public ItemNode searchById(int itemId) {
        ItemNode curr = head;
        while (curr != null) {
            if (curr.itemId == itemId) {
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }

    public ItemNode searchByName(String name) {
        ItemNode curr = head;
        while (curr != null) {
            if (curr.itemName.equalsIgnoreCase(name)) {
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }

    public double calculateTotalValue() {
        double total = 0;
        ItemNode curr = head;
        while (curr != null) {
            total += curr.price * curr.quantity;
            curr = curr.next;
        }
        return total;
    }

    public void sortBy(String field, boolean ascending) {
        head = mergeSort(head, field, ascending);
    }

    private ItemNode mergeSort(ItemNode node, String field, boolean ascending) {
        if (node == null || node.next == null) {
            return node;
        }
        ItemNode middle = getMiddle(node);
        ItemNode rightHalf = middle.next;
        middle.next = null;

        ItemNode left = mergeSort(node, field, ascending);
        ItemNode right = mergeSort(rightHalf, field, ascending);
        return merge(left, right, field, ascending);
    }

    private ItemNode getMiddle(ItemNode node) {
        ItemNode slow = node;
        ItemNode fast = node.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ItemNode merge(ItemNode a, ItemNode b, String field, boolean ascending) {
        ItemNode dummy = new ItemNode("", 0, 0, 0);
        ItemNode tail = dummy;
        while (a != null && b != null) {
            int cmp = field.equalsIgnoreCase("price")
                    ? Double.compare(a.price, b.price)
                    : a.itemName.compareToIgnoreCase(b.itemName);
            boolean takeA = ascending ? (cmp <= 0) : (cmp >= 0);
            if (takeA) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        tail.next = (a != null) ? a : b;
        return dummy.next;
    }

    public void displayAll() {
        ItemNode curr = head;
        while (curr != null) {
            System.out.println("  " + curr);
            curr = curr.next;
        }
    }

    public static void main(String[] args) {
        InventoryManagementSystem ims = new InventoryManagementSystem();
        ims.addAtEnd("Keyboard", 101, 5, 45.00);
        ims.addAtBeginning("Mouse", 102, 10, 25.00);
        ims.addAtPosition(2, "Monitor", 103, 2, 220.00);

        System.out.println("Inventory:");
        ims.displayAll();
        System.out.printf("Total value: %.2f%n", ims.calculateTotalValue());

        ims.sortBy("price", true);
        System.out.println("Sorted by price (asc):");
        ims.displayAll();

        ims.updateQuantity(102, 12);
        System.out.println("Search Monitor: " + ims.searchByName("Monitor"));
        ims.removeByItemId(101);
        System.out.println("After removing 101:");
        ims.displayAll();
    }
}
