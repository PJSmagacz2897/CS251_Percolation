import java.io.*;
public class PercolationVisualizer {

       public static void main(String[] args) {
        int n = StdIn.readInt();
        Percolation p = new Percolation(n);
        Out outputFile = "visualMatrix.txt"
        String output = n + "\n\n";
        while (!StdIn.isEmpty()) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            p.open(x,y);
            for(int i = p.side - 1; i >= 0; i--) {
                for(int j = 0; j < p.side; j++) {
                 	if(p.uf.connected(p.above, p.side*i + j)) output += "2 ";
                 	else output += p.grid[i][j] + " ";
                }
                output += "\n";
            }
            output += "\n";
        }
        StdOut.print(output);
        outputFile.println(output);
    }
}
