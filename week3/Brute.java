import java.util.Arrays;

public class Brute {
	
	public static void main(String[] args) {
		String filename = args[0];
		In in = new In(filename);
        int number = in.readInt();
        Point[] points = new Point[number];
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (int i = 0; i < number; i++) {
        	int x = in.readInt();
        	int y = in.readInt();
            points[i] = new Point(x, y);
            points[i].draw();
        }
        
        Arrays.sort(points);
		
		
        for (int i = 0; i < number; i++) {
        	Point p = points[i];
        	for (int j = i + 1; j < number; j++) {
        		Point q = points[j];
        		for (int l = j + 1; l < number; l++) {
        			Point r = points[l];
        			for (int m = l + 1; m < number; m++) {
        				Point s = points[m];
        				double slope1 = p.slopeTo(q);
        				double slope2 = p.slopeTo(r);
        				double slope3 = p.slopeTo(s);
        				if (slope1 == slope2 && slope1 == slope3 && slope2 == slope3) {
        					StdOut.printf("%s -> %s -> %s -> %s\n", p, q, r, s);
        					p.drawTo(s);
        				}
        			}
        		}
        	}
        }
	}
}
