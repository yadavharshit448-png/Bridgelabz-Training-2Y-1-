package LinkedListAssignment;

import java.util.ArrayList;
import java.util.List;

/**
 * 7. Singly Linked List: Social Media Friend Connections
 */
public class SocialMediaFriendConnections {
    static class UserNode {
        int userId;
        String name;
        int age;
        List<Integer> friendIds;
        UserNode next;

        UserNode(int userId, String name, int age) {
            this.userId = userId;
            this.name = name;
            this.age = age;
            this.friendIds = new ArrayList<>();
        }

        @Override
        public String toString() {
            return String.format("User(ID=%d, Name=%s, Age=%d, Friends=%s)", userId, name, age, friendIds);
        }
    }

    private UserNode head;

    public void addUser(int userId, String name, int age) {
        UserNode node = new UserNode(userId, name, age);
        node.next = head;
        head = node;
    }

    public UserNode searchById(int userId) {
        UserNode curr = head;
        while (curr != null) {
            if (curr.userId == userId) {
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }

    public UserNode searchByName(String name) {
        UserNode curr = head;
        while (curr != null) {
            if (curr.name.equalsIgnoreCase(name)) {
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }

    public boolean addFriendConnection(int userId1, int userId2) {
        if (userId1 == userId2) {
            return false;
        }
        UserNode u1 = searchById(userId1);
        UserNode u2 = searchById(userId2);
        if (u1 == null || u2 == null) {
            return false;
        }
        if (!u1.friendIds.contains(userId2)) {
            u1.friendIds.add(userId2);
        }
        if (!u2.friendIds.contains(userId1)) {
            u2.friendIds.add(userId1);
        }
        return true;
    }

    public boolean removeFriendConnection(int userId1, int userId2) {
        UserNode u1 = searchById(userId1);
        UserNode u2 = searchById(userId2);
        if (u1 == null || u2 == null) {
            return false;
        }
        boolean removed1 = u1.friendIds.remove(Integer.valueOf(userId2));
        boolean removed2 = u2.friendIds.remove(Integer.valueOf(userId1));
        return removed1 || removed2;
    }

    public List<Integer> findMutualFriends(int userId1, int userId2) {
        List<Integer> mutual = new ArrayList<>();
        UserNode u1 = searchById(userId1);
        UserNode u2 = searchById(userId2);
        if (u1 == null || u2 == null) {
            return mutual;
        }
        for (int fid : u1.friendIds) {
            if (u2.friendIds.contains(fid)) {
                mutual.add(fid);
            }
        }
        return mutual;
    }

    public void displayFriends(int userId) {
        UserNode user = searchById(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        System.out.print("Friends of " + user.name + ": ");
        for (int fid : user.friendIds) {
            UserNode friend = searchById(fid);
            System.out.print((friend != null ? friend.name : fid) + " ");
        }
        System.out.println();
    }

    public void countFriendsForAll() {
        System.out.println("Friend counts:");
        UserNode curr = head;
        while (curr != null) {
            System.out.println("  " + curr.name + ": " + curr.friendIds.size());
            curr = curr.next;
        }
    }

    public static void main(String[] args) {
        SocialMediaFriendConnections sm = new SocialMediaFriendConnections();
        sm.addUser(1, "Alice", 22);
        sm.addUser(2, "Bob", 23);
        sm.addUser(3, "Charlie", 21);
        sm.addUser(4, "Diana", 24);

        sm.addFriendConnection(1, 2);
        sm.addFriendConnection(1, 3);
        sm.addFriendConnection(2, 3);
        sm.addFriendConnection(2, 4);

        sm.displayFriends(1);
        System.out.println("Mutual friends (1 & 2): " + sm.findMutualFriends(1, 2));
        sm.countFriendsForAll();
        sm.removeFriendConnection(1, 3);
        sm.displayFriends(1);
    }
}
