/*************************************************************************
 * Name:	Yuchen Wang
 * Email:	yuchen.wang2012@gmail.com
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new BySlope();   // YOUR DEFINITION HERE

	private class BySlope implements Comparator<Point> {
    	public int compare(Point p1, Point p2) {
    		double slope1 = slopeTo(p1);
    		double slope2 = slopeTo(p2);
    		if (slope1 == slope2) return 0;
    		else if (slope1 < slope2) return -1;
    		else return +1;
		}
	}
    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
    	double slope;
    	if (this.y == that.y && this.x != that.x) slope = 0.0; // horizonal
    	else if (this.x == that.x && this.y != that.y) slope = Double.POSITIVE_INFINITY;
    	else if (this.x == that.x && this.y == that.y) slope = Double.NEGATIVE_INFINITY;
    	else slope = (double)(this.y - that.y) / (this.x - that.x);
    	return slope;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
    	if (this.x == that.x && this.y == that.y) return 0;
    	else if (this.y < that.y) return -1;
    	else if (this.y == that.y && this.x < that.x) return -1;
    	else return +1;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    	while (!StdIn.isEmpty()) {
	    	int x1 = StdIn.readInt();
	    	int y1 = StdIn.readInt();
	    	int x2 = StdIn.readInt();
	    	int y2 = StdIn.readInt();
	    	Point p1 = new Point(x1, y1);
	    	Point p2 = new Point(x2, y2);
	    	
	    	StdOut.println("slope from p1 (" + p1.x + ", " + p1.y + ") to (" + p2.x + ", " + p2.y + ") is " + p1.slopeTo(p2));
	    	StdOut.println("compare by coordinates: " + p1.compareTo(p2));
	    	StdOut.println("compare by slope: " + p1.SLOPE_ORDER.compare(p1, p2));
    	}
    }
}
