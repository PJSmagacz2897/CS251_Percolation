import java.util.*;
import java.io.*;
public class Percolation {

    int[][] grid;
    int top = 0;
    int bottom;
    int side;
    WeightedQuickUnionUF uf;


    public Percolation(int n) {
        this.side = n;
        this.grid = new int[n][n];
        this.uf = new WeightedQuickUnionUF(n*n + 2);
        this.bottom = this.side*this.side;
        this.top = this.side*this.side + 1;
    }


    public void open(int x, int y) {
        this.grid[x][y] = 1;
        if(x == 0) {
            uf.union(this.side*x + y, this.bottom);
        }
        if(x == this.side - 1) {
            uf.union(this.side*x + y, this.top);
        }
        if(isOpen(x+1, y)) {
            uf.union(this.side*(x+1) + y, this.side*x + y);
        }
        if(isOpen(x-1, y)) {
            uf.union(this.side*(x-1) + y, this.side*x + y);
        }
        if(isOpen(x, y+1)) {
            uf.union(this.side*x + y, this.side*x + y + 1);
        }
        if(isOpen(x, y-1)) {
            uf.union(this.side*x + y, this.side*x + y - 1);
        }
    }


    public boolean isOpen(int x, int y) {
        if (x >= 0 && x < this.side && y >= 0 && y < this.side) {
            return grid[x][y] == 1;
        }
        return false;
    }


    public boolean isFull(int x, int y) {
        if (x >= 0 && x < this.side && y >= 0 && y < this.side) {
            return uf.connected(top, this.side * x + y);
        }
        return false;
    }


    public boolean percolates() {
        return uf.connected(this.bottom, this.top);
    }


    public static void main(String[] args) {
        int n = StdIn.readInt();
        Percolation p = new Percolation(n);
        while (!StdIn.isEmpty()) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            p.open(x,y);
        }
        if(p.percolates()) StdOut.println("Yes");
        else StdOut.println("No");
    }
}
