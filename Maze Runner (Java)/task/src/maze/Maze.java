package maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

class Maze {
    int[][] maze;
    int rows;
    int cols;

    Pair entrance;
    Pair exit;

    Random random;

    Maze() {
        this(0,0);
    }
    Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        random = new Random(System.nanoTime());
    }

    void generateMaze (){
        List<Pair> collectionCoordinate = new ArrayList<>();
        maze = new int[this.rows][this.cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j] = 1;
            }
        }

        int seedRows = random.nextInt(this.rows);
        int seedCols = random.nextInt(this.cols);

        if(seedRows % 2 == 0) {
            if(seedRows > 0) {
                seedRows--;
            } else {
                seedRows++;
            }
        }

        if(seedCols % 2 == 0) {
            if(seedCols > 0) {
                seedCols--;
            } else {
                seedCols++;
            }
        }

        maze[seedRows][seedCols] = 0;
        insertNeighborCell(collectionCoordinate, seedRows, seedCols);

        while (!collectionCoordinate.isEmpty()) {
            Collections.shuffle(collectionCoordinate, random);

            Pair coordinate = collectionCoordinate.get(0);
            collectionCoordinate.remove(0);

            maze[coordinate.getFirst()][coordinate.getSecond()] = 0;
            fillNeighborCell(coordinate.getFirst(), coordinate.getSecond());
            insertNeighborCell(collectionCoordinate, coordinate.getFirst(), coordinate.getSecond());
        }


        fillAllBorder();
        makeEntranceAndExit();


    }

    void insertNeighborCell(List<Pair> collectionCoordinate, int rows, int cols) {
        int[] dRows = new int[]{0,-2,0,2};
        int[] dCols = new int[]{-2,0,2,0};

        for(int i = 0; i < 4; i++) {
            if(isValidCoordinate(rows+dRows[i], cols+dCols[i]) && (maze[rows+dRows[i]][cols+dCols[i]] == 1)) {
                collectionCoordinate.add(new Pair(rows+dRows[i], cols+dCols[i]));
            }
        }
    }

    void fillNeighborCell(int rows, int cols) {
        int[] dRows = new int[]{0,-2,0,2};
        int[] dCols = new int[]{-2,0,2,0};
        int[] dFillRows = new int[]{0,-1,0,1};
        int[] dFillCols = new int[]{-1,0,1,0};

        for(int i = 0; i < 4; i++) {
            if(isValidCoordinate(rows+dRows[i], cols+dCols[i]) && (maze[rows+dRows[i]][cols+dCols[i]] == 0)) {
                maze[rows+dFillRows[i]][cols+dFillCols[i]] = 0;
                break;
            }
        }
    }

    boolean isValidCoordinate(int rows, int cols) {
        return ((rows >= 0) && (rows < this.rows) && (cols >= 0) && (cols < this.cols));
    }

    void findEntranceAndExit() {
        for(int i = 0; i < this.rows - 1; i++) {
            if(maze[i][0] == 0) {
                entrance = new Pair(i, 0);
                break;
            }
        }

        for(int i = 0; i < this.rows - 1; i++) {
            if(maze[i][this.cols-1] == 0) {
                exit = new Pair(i, this.cols - 1);
                break;
            }
        }
    }
    void makeEntranceAndExit() {
        int entranceIndex = 0;
        for(int i = 1; i < this.rows - 1; i++) {
            if(maze[i][1] == 0) {
                maze[i][0] = 0;
                entranceIndex = i;
                entrance = new Pair(i, 0);
                break;
            }
        }

        for(int i = 1; i < this.rows - 1; i++) {
            if(i == entranceIndex) {
                continue;
            }

            if(maze[i][this.cols-2] == 0) {
                maze[i][this.cols-1] = 0;
                exit = new Pair(i, this.cols - 1);
                break;
            }
        }
    }

    void fillAllBorder() {

        for(int i = 0; i < this.rows; i++) {
            maze[i][0] = 1;
            maze[i][this.cols-1] = 1;
        }

        for(int i = 0; i < this.cols; i++) {
            maze[0][i] = 1;
            maze[this.rows-1][i] = 1;
        }


    }

    void loadMaze() {

        Scanner scannerSystemIn = new Scanner(System.in);
        String fileName = scannerSystemIn.nextLine();

        File file = new File(fileName);

        try (Scanner scanner = new Scanner(file)) {
            int sizeMaze = scanner.nextInt();
            int[][] mazeLoad = new int[sizeMaze][sizeMaze];
            for(int i = 0; i < sizeMaze; i++) {
                for(int j = 0; j < sizeMaze; j++) {
                    mazeLoad[i][j] = scanner.nextInt();
                }
            }

            this.rows = sizeMaze;
            this.cols = sizeMaze;
            this.maze = mazeLoad;
        } catch (FileNotFoundException e) {
            System.out.println("The file " + fileName + " does not exist");
        } catch (Exception e) {
            System.out.println("Cannot load the maze. It has an invalid format");
        }

        findEntranceAndExit();
    }

    void saveMaze() {

        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();

        File file = new File(fileName);

        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.println(this.rows);
            for (int[] ints : this.maze) {
                for (int anInt : ints) {
                    printWriter.print(anInt);
                    printWriter.print(" ");
                }
                printWriter.println();
            }
        } catch (IOException e) {
            System.out.printf("An exception occurred %s", e.getMessage());
        }
    }

    void findTheEscape() {
        maze[entrance.getFirst()][entrance.getSecond()] = 2;
        this.DFS(entrance.getFirst(), entrance.getSecond(), exit.getFirst(), exit.getSecond());
    }

    boolean DFS(int row, int col, int exitRow, int exitCol) {
        int[] dRows = new int[]{0,-1,0,1};
        int[] dCols = new int[]{-1,0,1,0};

        if (row == exitRow && col == exitCol) {
            maze[row][col] = 2;
            return true;
        }

        for (int i = 0; i < 4; i++) {
            int rowTemp = row+dRows[i];
            int colTemp = col+dCols[i];

            if (!this.isValidCoordinate(rowTemp, colTemp)) {
                continue;
            }

            if (maze[rowTemp][colTemp] == 0) {
                maze[rowTemp][colTemp] = 10;
                boolean res = this.DFS(rowTemp, colTemp, exitRow, exitCol);

                if (res) {
                    maze[rowTemp][colTemp] = 2;
                    return true;
                }
            }
        }

        return false;


    }

    void printMaze() {
        for (int[] ints : this.maze) {
            for (int anInt : ints) {
                switch (anInt) {
                    case 0,10 -> System.out.print("  ");
                    case 1,11 -> Maze.printWall();
                    case 2 -> System.out.print("//");
                }
            }
            System.out.println(); // Move to the next line after each row
        }
    }

    private static void printWall() {
        System.out.print("██");
    }
}
