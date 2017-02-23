
public class PercolationStats {

 
 	int iterations;
 	Percolation p;
 	PercolationQuick pq;
 	double[] percents;
 	double[] times;
 	

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
