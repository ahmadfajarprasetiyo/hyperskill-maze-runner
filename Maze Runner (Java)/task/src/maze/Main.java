package maze;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int ACTION_EXIT = 0;
        final int ACTION_GENERATE_MAZE = 1;
        final int ACTION_LOAD_MAZE = 2;
        final int ACTION_SAVE_MAZE = 3;
        final int ACTION_DISPLAY_MAZE = 4;
        final int ACTION_FIND_ESCAPE = 5;

        Scanner scanner = new Scanner(System.in);
        Maze maze = null;

        int action;

        do{

            System.out.println("=== Menu ===");
            System.out.println("1. Generate a new maze");
            System.out.println("2. Load a maze");

            if(maze != null) {
                System.out.println("3. Save the maze");
                System.out.println("4. Display the maze");
                System.out.println("5. Find the escape");
            }

            System.out.println("0. Exit");

            action = scanner.nextInt();

            switch (action){
                case ACTION_GENERATE_MAZE -> {
                    System.out.println("Enter the size of a new maze");
                    int sizeMaze = scanner.nextInt();

                    maze = new Maze(sizeMaze,sizeMaze);
                    maze.generateMaze();
                    maze.printMaze();
                }
                case ACTION_LOAD_MAZE -> {
                    maze = new Maze();
                    maze.loadMaze();
                }
                case ACTION_SAVE_MAZE -> {
                    if(maze != null) {
                        maze.saveMaze();
                    }
                }
                case ACTION_DISPLAY_MAZE -> {
                    if(maze != null) {
                        maze.printMaze();
                    }
                }
                case ACTION_FIND_ESCAPE -> {
                    if(maze != null) {
                        maze.findTheEscape();
                        maze.printMaze();
                    }
                }
                case ACTION_EXIT -> System.out.println("Bye!");
                default -> System.out.println("Incorrect option. Please try again");
            }

            System.out.println();
        }while (action != ACTION_EXIT);


    }
}
