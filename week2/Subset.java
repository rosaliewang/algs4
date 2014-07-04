package week2;

import java.util.Iterator;

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
        Iterator<String> iterator = queue.iterator();
        for (int i = 0; i < k; i++) {
            String item = iterator.next();
            StdOut.println(item);
        }
	}
}
