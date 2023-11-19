import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        Deque<Character> stack = new ArrayDeque<>();
        String inp = scanner.nextLine();
        boolean isValid = true;

        for (int i = 0; i < inp.length(); i++) {
            char tempInp = inp.charAt(i);
            switch (tempInp){
                case '}', ')', ']' -> {
                    if (stack.isEmpty()) {
                        isValid = false;
                        break;
                    }

                    Character stackLastItem = stack.pollLast();

                    if (stackLastItem == null) {
                        isValid = false;
                        break;
                    }
                    if ((!stackLastItem.equals('{') && tempInp == '}') ||
                            (!stackLastItem.equals('(') && tempInp == ')') ||
                            (!stackLastItem.equals('[') && tempInp == ']')){

                        isValid = false;
                    }

                }
                case '{', '(', '[' -> {
                    stack.offerLast(tempInp);
                }
            }

            if (!isValid) {
                break;
            }
        }

        if (isValid && stack.isEmpty()) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}