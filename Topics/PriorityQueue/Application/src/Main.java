import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue<Integer> queue = createQueue(scanner);

        printElement(queue, scanner.nextInt());
    }

    public static Queue<Integer> createQueue(Scanner scanner) {
        Queue<Integer> queue = new PriorityQueue();
        queue.add(scanner.nextInt());
        queue.add(scanner.nextInt());
        queue.add(scanner.nextInt());
        queue.add(scanner.nextInt());

        return queue;
    }

    public static void printElement(Queue<Integer> queue, int n) {
        // write your code here
        for (int i = 1; i < n; i++) {
            queue.poll();
        }

        System.out.println(queue.poll());
    }    
}