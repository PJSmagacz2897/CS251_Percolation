# CS251_Percolation

"Percolation is a scientific model that is used to analyze the connectivity of systems. For example, it can be used to analyze if a porous 
landscape with water on the surface will eventually allow the water to drain through to the bottom. It can also be used to analyze if oil 
would be able to reach the surface in a similar manner. The idea of the model is to analyze what conditions are necessary for the system 
to percolate (i.e. let the water or oil through).

This project applies the Union-Find data structure to solve this problem. The system will be represented as a N-by-N grid where each 
cell can be in one of 3 states: blocked, open or full. A grid where percolation has been achieved will have a path of full cells from the 
surface to the bottom." (Based on an assignment developed by Bob Sedgewick and Kevin Wayne. Copyright © 2008.)

This project makes use of stdlib.jar, QuickUnionUF.java and WeightedUnionUF.java, written by Robert Sedgewick and Kevin Wayne. A detailed 
API for stdlib.jar can be found at http://introcs.cs.princeton.edu/java/stdlib/ and and links to the API's for QuickUnionUF.java and 
WeightedUnionUF.java can be found at http://algs4.cs.princeton.edu/15uf/.

Assignment given by Xavier Tricoche in Purdue's CS251, Spring 2017.
