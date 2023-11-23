package maze;

public class Pair {
    final private int first;
    final private int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public boolean equals(Pair pair) {
        return (this.getFirst() == pair.getFirst() && this.getSecond() == pair.getSecond());
    }
}
