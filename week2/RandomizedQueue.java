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
    private LinkedStack<Item> s;

    private class Node {
        private Item item;
        private Node next;
    }
    
	// construct an empty randomized queue
	public RandomizedQueue() {
		s = new LinkedStack<Item>();
		
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
			s.push(item);
			N++;
		}
	} 
	
	// delete and return a random item
	public Item dequeue() {
		if (s.size() == 0) throw new java.util.NoSuchElementException();
		else {
			LinkedStack<Item> dequeueS = new LinkedStack<Item>();
			int index = StdRandom.uniform(0, N);
			for (int i = 0; i < index; i++) dequeueS.push(s.pop());
			Item item = s.pop();
			for (int i = 0; i < index; i++) s.push(dequeueS.pop());
			dequeueS = null;
			N--;
			return item;
		}
	}      
	
	// return (but do not delete) a random item
	public Item sample() {
		LinkedStack<Item> dequeueS = new LinkedStack<Item>();
		int index = StdRandom.uniform(0, N);
		for (int i = 0; i < index; i++) dequeueS.push(s.pop());
		Item item = s.peek();
		for (int i = 0; i < index; i++) s.push(dequeueS.pop());
		dequeueS = null;
		return item;
	}        
	
	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}        
	
    // an iterator, doesn't implement remove() since it's optional
    private class RandomizedQueueIterator implements Iterator<Item> {
    	@SuppressWarnings("unchecked")
		private Node current = (Node) s.peek();
        public boolean hasNext()  { return N != 0;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
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
