import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue<Integer> queue = new PriorityQueue<>(new IntegerComparator());

        int start = scanner.nextInt();
        int end = scanner.nextInt();

        for (int i = start; i <= end; i++) {
            // add elements to a queue here
            if (PrimeChecker.isPrime(i)) {
                queue.offer(i);
            }
        }

        if (queue.isEmpty()) {
            System.out.println("The queue is empty.");
        } else if (queue.size() == 1) {
            System.out.printf("There aren't enough elements in a queue. You added only %d.\n", queue.poll());
        } else if (queue.size() == 2) {
            System.out.printf("There aren't enough elements in a queue. You added two: %d and %d.\n", queue.poll(), queue.poll());
        } else {
            for (int i = 0; i < 3; i++) {
                System.out.println(queue.poll());
            }
        }
    }
}

class IntegerComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer i1, Integer i2) {
        // write your code here
        int result = i1.compareTo(i2);
        if (result != 0) {
            result = result * -1;
        }

        return result;
    }
}

class PrimeChecker {

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

}