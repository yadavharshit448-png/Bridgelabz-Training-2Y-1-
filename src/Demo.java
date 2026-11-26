import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Demo {


    public static void main(String[] args){
        Queue<Integer> q = new ArrayBlockingQueue<>(3);
        q.add(3);
        q.add(3);
        q.add(5);
        System.out.println(q);
        System.out.println("afer this offer method will try to add the element but the capacity is only 3 so it will handle the exception and continue working ");
        q.offer(6);
        System.out.println(q);
        System.out.println("now removing using remove() method ");
        q.remove();
        q.remove();
        q.remove();
        System.out.println("queue is empty if we try to remove the element it will throw exception so we use poll() method to handle the exception ");
        q.poll();
        System.out.println(q);







    }
}
