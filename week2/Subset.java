package week2;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;


public class Subset {
	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
	    RandomizedQueue<String> queue = new RandomizedQueue<String>();
	    while (!StdIn.isEmpty()) {
	        String str = StdIn.readString();
	        queue.enqueue(str);
	    }
        for (int i = 0; i < k; i++) {
            if (!queue.isEmpty()) {
            	StdOut.println(queue.dequeue());
            }
        }
	}
}
