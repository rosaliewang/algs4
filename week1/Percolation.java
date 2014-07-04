package week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int N;
	private boolean[][] id; // true, open; false, closed
	private WeightedQuickUnionUF wUf;
	
	/** create N-by-N grid, with all sites blocked
	 * **/
	public Percolation(int N) {
        if (N <= 0) {
        	throw new IllegalArgumentException("Illegal input.");
        }
		this.N = N;
		wUf = new WeightedQuickUnionUF(N * N + 2); // upper and lower
		id = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				id[i][j] = false;
			}
		}
	}
	
    /** open site (row i, column j) if it is not already**/
    public void open(int i, int j) {
    	id[i - 1][j - 1] = true;
		if (i == 1) {
			wUf.union(convertCoordinates(i, j), 0);
		}
		if (i == N) {
			wUf.union(convertCoordinates(i, j), N * N + 1);
		}
    	// right
    	if (isValid(i, j + 1)) {
    		if (isOpen(i, j + 1)) {
    			wUf.union(convertCoordinates(i, j), convertCoordinates(i, j + 1));
   			}
   		}
   		// left
 		if (isValid(i, j - 1)) {
   			if (isOpen(i, j - 1)) {
   				wUf.union(convertCoordinates(i, j), convertCoordinates(i, j - 1));
   			}
   		}
   		// up
   		if (isValid(i - 1, j)) {
   			if (isOpen(i - 1, j)) {
   				wUf.union(convertCoordinates(i, j), convertCoordinates(i - 1, j));			
   			}
    	}
    	// down
    	if (isValid(i + 1, j)) {
    		if (isOpen(i + 1, j)) {
        		wUf.union(convertCoordinates(i, j), convertCoordinates(i + 1, j));	
    		}
    	}
  	}

    /**is site (row i, column j) open? **/
    public boolean isOpen(int i, int j) {
    	if (isValid(i, j))
    		return (id[i - 1][j - 1]);
    	else throw new IndexOutOfBoundsException();
    	
    }

    /**is site(row i, column j) full? **/
    public boolean isFull(int i, int j) {
    	if (0 < i && i <= N && 0 < j && j <= N)
    		return wUf.connected(0, convertCoordinates(i, j));
    	else throw new IndexOutOfBoundsException();
    }

    
    /**does the system percolate? **/
    public boolean percolates() {
    	return wUf.connected(0, N * N + 1);
    }
  
    private int convertCoordinates(int i, int j) {
    	int pos =  N * (i - 1) + j;
    	return pos;
    }
    
    private boolean isValid(int i, int j) {
    	if (i <= 0 || i > N || j <= 0 || j > N) return false;
    	else return true;
    }

}
