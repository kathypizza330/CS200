import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class ArrayHeapSort {

	private int[] A;

	public ArrayHeapSort(int[] A){
		this.A = A;
		buildHeap();
	}

	private void heapify(int i, int size){
	// left and right subtrees are already heaps
	// need to bubble element i in place top down
		int max = i;  
	    int left = 2*i + 1;  // left = 2*i + 1
	    int right = 2*i + 2;  // right = 2*i + 2
	 
	    if (left < size && A[left] > A[max]){
	        max = left;
	    }
	 
	    if (right < size && A[right] > A[max]){
	        max = right;
	    }
	 
	    if (max!= i)
	    {
	    	//System.out.println("here?");
	    	int temp = A[i];
	    	A[i] = A[max];
	    	A[max] = temp;
	 
	        heapify(max,size);
	    }
	}

	// implement the buildHeap algorithm described in the lecture notes 
	private void buildHeap(){
		for (int i = A.length / 2 - 1; i >= 0; i--)
	        heapify(i,A.length);
	}

	// implement the in place heapSort algorithm described in the lecture notes
	public void heapSort(){
		for (int i=A.length-1; i>=0; i--)
	    {
	        int temp = A[0];
	        A[0] = A[i];
	        A[i] = temp;
	        heapify(0,i);
	    }
	}

	// return the heap content
	public String toString(){
		return Arrays.toString(A);
	}

	public static void main(String[] args) throws FileNotFoundException{
		// scanner for input file
		Scanner scan = new Scanner( new File (args[0]));
		// first int in input: number of ints to sort following 
		int n = Integer.parseInt(scan.next());
		int[] A = new int[n];
		for(int i = 0; i<n; i++)
			A[i] = Integer.parseInt(scan.next());
		System.out.println("    in: " + Arrays.toString(A));
		ArrayHeapSort ahs = new ArrayHeapSort(A);
		System.out.println("  heap: " + ahs);
		ahs.heapSort();
		System.out.println("sorted: " + ahs);

	}
}
