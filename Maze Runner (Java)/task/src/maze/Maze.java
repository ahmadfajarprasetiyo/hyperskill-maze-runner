package maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class Maze {
    int[][] maze;
    int rows;
    int cols;

    Random random;

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

    void makeEntranceAndExit() {
        int entranceIndex = 0;
        for(int i = 1; i < this.rows - 1; i++) {
            if(maze[i][1] == 0) {
                maze[i][0] = 0;
                entranceIndex = i;
                break;
            }
        }

        for(int i = 1; i < this.rows - 1; i++) {
            if(i == entranceIndex) {
                continue;
            }

            if(maze[i][this.cols-2] == 0) {
                maze[i][this.cols-1] = 0;
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

    void printMaze() {
        for (int[] ints : this.maze) {
            for (int anInt : ints) {
                if (anInt == 1) {
                    Maze.printWall();
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println(); // Move to the next line after each row
        }
    }

    private static void printWall() {
        System.out.print("██");
    }
}
