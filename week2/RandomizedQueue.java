package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.LinkedStack;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;


/**	stack: LIFO, push to the last, pop the lase
 * 	queue: FIFO, push to the last, pop the first**/
public class RandomizedQueue<Item> implements Iterable<Item> {
    private int N;          // size of the stack
    private Item[] a;         // array of items
    
	// construct an empty randomized queue
	public RandomizedQueue() {
		a = (Item[]) new Object[2];
	}  
	
	// is the queue empty?
	public boolean isEmpty() {
		return N == 0;
	}          
	
	// return the number of items on the queue
	public int size() {
		return N;
	}   
	
	// add the item
	public void enqueue(Item item) {
		if (item == null) throw new NullPointerException();
		else {
			if (N == a.length) resize(2*a.length);    // double size of array if necessary
	        a[N++] = item;                            // add item
		}
	} 
	
	// delete and return a random item
	public Item dequeue() {
		if (isEmpty()) throw new java.util.NoSuchElementException();
		else {
			int index = StdRandom.uniform(0, N);
			Item item = a[index];
			
			if (index != N - 1) {
	            a[index] = a[N - 1];
	        }
			
	        a[N-1] = null;                              // to avoid loitering
	        N--;
	        // shrink size of array if necessary
	        if (N > 0 && N == a.length/4) resize(a.length/2);
	        return item;
		}
	}      
	
	// return (but do not delete) a random item
	public Item sample() {
		if (isEmpty()) throw new java.util.NoSuchElementException();
		else {
			int index = StdRandom.uniform(0, N);
			Item item = a[index];
			return item;
		}
	}        
	
    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= N;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }
    
	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}        
	
    // an iterator, doesn't implement remove() since it's optional
    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i;

        public RandomizedQueueIterator() {
            i = N;
        }

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return a[--i];
        }
    }
    
	// unit testing
	public static void main(String[] args) {
        RandomizedQueue<String> s = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-") && !item.equals("#")) s.enqueue(item);
            else if (item.equals("#")) StdOut.print(s.sample() + " ");
            else if (item.equals("-") && !s.isEmpty()) {
            	StdOut.print(s.dequeue() + " ");
            }
        }
        StdOut.println("(" + s.size() + " left on stack)");
	}
	
}
