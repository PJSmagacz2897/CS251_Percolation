
public class PercolationStats {

 
 	int iterations;            //The number of times the code runs
 	Percolation p;             //Used for fast percolations
 	PercolationQuick pq;       //Used for slow percolations
 	double[] percents;         //array holding the percentages of open cells needed for a system to percolate for each iteration
 	double[] times;            //array holding the runtime of each iteration
 	
  /**
  Creates a num by num system and opens random cells until the system percolates. This is repreated times times. Each iteration's 
  percentage of open cells and runtime are stored in this.percents[i] and this.times[i] respectively. Uses either p or pq depending on 
  whether speed is 'fast' or 'slow', respectively.
  **/
 	public PercolationStats(int num, int times, String speed) {
     	if(num >= 0 && times >= 0) {
         	this.iterations = times;
         	this.percents = new double[iterations];
         	this.times = new double[iterations];
         	int numOpened = 0;
         	for(int i = 0; i < this.iterations; i++) {
             	numOpened = 0;
             	Stopwatch timer = new Stopwatch();
             	if(speed.equals("fast")) {
              		this.p = new Percolation(num);
             		while(numOpened < num || !p.percolates()) {
             			int x = (int) StdRandom.uniform(0, num);
             			int y = (int) StdRandom.uniform(0, num);
                 		if(!p.isOpen(x,y)) {
                 			p.open(x,y);
                   	  	numOpened++;
                 		}	
                	}
             	}
             	else {
                 	this.pq = new PercolationQuick(num);
             		while(numOpened < num || !pq.percolates()) {
             			int x = (int) StdRandom.uniform(0, num);
             			int y = (int) StdRandom.uniform(0, num);
                 		if(!pq.isOpen(x,y)) {
                 			pq.open(x,y);
                   	  		numOpened++;
                 		}	
                	}
             	}
             this.percents[i] = (double) numOpened / (num * num);
             this.times[i] = timer.elapsedTime();
            }
     	}
 	}
 
 /**
 Takes in num, time, and speed from the command line. Outputs total runtime, the average percentage of cells needed to percolate,
 the average runtime, and the standard deviations for percentage and runtime to standard output.
 **/
 	public static void main (String[] args) {
     	if(args.length == 3) {
          int time = Integer.parseInt(args[0]);
         	int num = Integer.parseInt(args[1]);
         	String speed = args[2];
         	Stopwatch timerTotal = new Stopwatch();
         	PercolationStats ps = new PercolationStats(num, time, speed);
         	double runtimeTotal = timerTotal.elapsedTime();
         	double meanThreshold = StdStats.mean(ps.percents);
         	double meanTime = StdStats.mean(ps.times);
         	double sddThreshold = StdStats.stddev(ps.percents);
         	double sddTime = StdStats.stddev(ps.times);
         	StdOut.println("**OUTPUT BELOW**");
         	StdOut.println("mean threshold=" + meanThreshold);
         	StdOut.println("std dev=" + sddThreshold);
         	StdOut.println("time=" + runtimeTotal);
         	StdOut.println("mean time=" + meanTime);
         	StdOut.println("stddev time=" + sddTime);
     	} 
 	}
}
