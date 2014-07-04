package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.ResizingArrayStack;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class Deque<Item> implements Iterable<Item> {
	private ResizingArrayStack<Item> q;
	private int N; // array size

	// construct an empty deque
	public Deque() {
		q = new ResizingArrayStack<Item>();	// q = (Item[]) new Object[2];
	}   
	
	// is the deque empty?
	public boolean isEmpty() {
		return N == 0;
	}       
	
	// return the number of items on the deque
	public int size() {
		return N;
	}     
	
	// insert the item at the front
	public void addFirst(Item item) {
		if (item == null) throw new NullPointerException();
		else {
			ResizingArrayStack<Item> addFirstS = new ResizingArrayStack<Item>();
			for (int i = 0; i < N; i++) addFirstS.push(q.pop()); // push to the last, pop the last
			addFirstS.push(item);
			for (int i = 0; i < (N + 1); i++) q.push(addFirstS.pop());
			N++;
			addFirstS = null;
		}
	}
	         
	
	// insert the item at the end
	public void addLast(Item item) {
		if (item == null) throw new NullPointerException();
		else {
			N++;
			q.push(item);
			}
	}     
	
	// delete and return the item at the front
	public Item removeFirst() {
		if (q.size() == 0) throw new java.util.NoSuchElementException();
		else {
			ResizingArrayStack<Item> removeLastS = new ResizingArrayStack<Item>();
			for (int i = 0; i < N; i++) removeLastS.push(q.pop());
			Item item = removeLastS.pop();
			for (int i = 0; i < (N - 1); i++) q.push(removeLastS.pop());
			N--;
			removeLastS = null;
			return item;
		}
	}
	
	// delete and return the item at the end
	public Item removeLast() {
		if (q.size() == 0) throw new java.util.NoSuchElementException();
		else {
			N--;
			return q.pop();
		}
	}   
	
	// return an iterator over items in order from front to end
	public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class DequeIterator implements Iterator<Item> {
        private int i;

        public DequeIterator() {
        	i = N;
        }
        public boolean hasNext() {
        	return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        @SuppressWarnings("unchecked")
		public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            ResizingArrayStack<String> nextS = new ResizingArrayStack<>();
            for (int j = i; j < N; j++) {
            	nextS.push((String) q.pop());
            }
            Item item = q.peek();
            for (int j = i; j < N; j++) {
            	q.push((Item) nextS.pop());
            }
            --i;
            nextS = null;
            return item;
        }
        
    }
    
	// unit testing
	public static void main(String[] args) {
        Deque<String> q = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (item.startsWith("+")) q.addFirst(item.substring(1)); // add first
            else if (item.startsWith("#")) q.addLast(item.substring(1)); // add last
            else if (item.equals("-") && !q.isEmpty()) { // remove first
            	StdOut.print(q.removeFirst() + " ");
            }
            else if (item.equals("--") && !q.isEmpty()) { // remove last
            	StdOut.print(q.removeLast() + " ");
            }
        }
        StdOut.println("(" + q.size() + " left on queue)");
    }
	   

}
