package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

/**	need to implement double-linked list	**/
public class Deque<Item> implements Iterable<Item> {
	private int N;	// size of stack
	private Node first, end;	// top of stack and end of stack

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
        private Node previous;
        private Node(Item item, Node previous, Node next) {
        	this.item = item;
        	this.previous = previous;
        	this.next = next;
        }
    }
    
	// construct an empty deque
	public Deque() {
		this.first = null;
		this.end = null;
		this.N = 0;
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
	        Node oldfirst = first;
	        first = new Node(item, null, oldfirst);
	        
	        if (isEmpty()) {
	        	end = first;
	        }
	        else {
		        oldfirst.previous = first;
	        }
	        
	        N++;
		}
	}
	         
	
	// insert the item at the end
	public void addLast(Item item) {
		if (item == null) throw new NullPointerException();
		else {
			Node oldend = end;
			end = new Node(item, oldend, null);
			
	        
	        if (isEmpty()) {
	        	first = end;
	        }
	        else {
	        	oldend.next = end;
	        }
			
			N++;
			}
	}     
	
	// delete and return the item at the front
	public Item removeFirst() {
		if (isEmpty()) throw new java.util.NoSuchElementException();
		else {
	        Item item = first.item;        // save item to return
	        first = first.next;            // delete first node
	        
	        if (first != null) {
	        	first.previous = null;
	        }
	        N--;
	        if (isEmpty()) {
	        	end = null;
	        }
	        return item;                   // return the saved item
		}
	}
	
	// delete and return the item at the end
	public Item removeLast() {
		if (isEmpty()) throw new java.util.NoSuchElementException();
		else {
			Item item = end.item;
			end = end.previous;
			
			if (end != null) {
				end.next = null;
			}
			N--;
			
			if (isEmpty()) {
				first = null;
			}
			return item;
		}
	}   
	
	// return an iterator over items in order from front to end
	public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext()  { return current != null;                     }
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
