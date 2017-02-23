import java.util.*;
import java.io.*;
public class Percolation {

    int[][] grid;               //The array that holds all cells. Cells that are closed are 0 and cells that are open are 1.
    int above;                  //The virtual top of the system. Any open cell connected to above is full.
    int below;                  //The virtual bottom of the system. If a full cell is connected to below, then the system percolates.
    int side;                   //The side length of the system. 
    WeightedQuickUnionUF uf;    //The UF data structure used to implement this problem.

    /** Constructor. 
    uf is initialized with 2 more elements than the size of the system to hold above and below. 
    above and below are put into the last two elements of uf's array, and hold the value of the index they are located at.
    **/
    public Percolation(int n) {
        this.side = n;
        this.grid = new int[n][n];
        this.uf = new WeightedQuickUnionUF(n*n + 2);
        this.below = this.side*this.side;
        this.above = this.side*this.side + 1;
    }

    /**
    Opens the cell at the given location, and connects that cell to any adjacent open cells.
    Also connects to above or below if it is in the top or bottom row of the system, respectively.
    **/    
    public void open(int x, int y) {
        this.grid[x][y] = 1;
        if(x == 0) {
            uf.union(this.side*x + y, this.below);
        }
        if(x == this.side - 1) {
            uf.union(this.side*x + y, this.above);
        }
        if(isOpen(x+1, y)) {
            uf.union(this.side*(x+1) + y, this.side*x + y);
        }
        if(isOpen(x, y+1)) {
            uf.union(this.side*x + y, this.side*x + y + 1);
        }
        if(isOpen(x-1, y)) {
            uf.union(this.side*(x-1) + y, this.side*x + y);
        }
        if(isOpen(x, y-1)) {
            uf.union(this.side*x + y, this.side*x + y - 1);
        }
    }


    /**
    Returns true if the given cell is open.
    **/
    public boolean isOpen(int x, int y) {
        if (x >= 0 && x < this.side && y >= 0 && y < this.side) {
            return grid[x][y] == 1;
        }
        return false;
    }


    /**
    Returns true if the given cell is full, i.e. if the given cell is connected to above.
    **/
    public boolean isFull(int x, int y) {
        if (x >= 0 && x < this.side && y >= 0 && y < this.side) {
            return uf.connected(this.above, this.side * x + y);
        }
        return false;
    }

    /**
    Returns true if the system percolates, i.e. if above is connected to below.
    **/
    public boolean percolates() {
        return uf.connected(this.below, this.above);
    }


    /**
    Takes in a side length n and many pairs of cell coordinates from an input file, and then prints 'Yes' to standard output if the 
    system percolates, 'No' otherwise.
    **/
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
