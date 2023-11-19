import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.Deque;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < size; i++) {
            int temp = scanner.nextInt();
            stack.offerLast(temp);
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pollLast());
        }
    }
}
