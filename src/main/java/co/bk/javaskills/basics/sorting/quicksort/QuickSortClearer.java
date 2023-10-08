package co.bk.javaskills.basics.sorting.quicksort;

/**
 * Quick sort is a divide and conquer approach.
 * 
 * http://www.sparknotes.com/cs/recursion/examples/section3.rhtml
 * 
 * The basic step of sorting an array are as follows:
 * 1.   Select a pivot (middle or first value... doesn't really matter).
 * 2.	From both ends, swap elements and make all elements on the left less than the pivot and all elements on the right greater than the pivot.
 * 3.	Recursively sort left part and right part.
 * 
 */
public class QuickSortClearer {

	public static void main(String[] args) {
		int[] x = { 9, 3, 4, 220, 1, 3, 10, 5, 8 };
		printArray(x);

		int low = 0;
		int high = x.length - 1;

		quicksort(x, low, high);
		printArray(x);
	}
	
	public static void quicksort(int[] arr, int first, int last) {
		int i,j,pivot,temp;

		if (first < last) {
			pivot = first;
			i = first;
			j = last;

			while (i < j) {
				while(arr[i] <= arr[pivot] && i < last)
					i++;
				while(arr[j] > arr[pivot])
					j--;
				if (i < j) {
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp; 
				}
			}

			// Swap pivot number into its correct position e.g [9, 3, 4, 8, 1, 3, 5, 10, 220] to [5, 3, 4, 8, 1, 3, 9, 10, 220]
			temp = arr[pivot];
			arr[pivot] = arr[j];
			arr[j] = temp;
			quicksort(arr, first, j-1);	// Sort all array elements under pivot number e.g less than '9' here [5, 3, 4, 8, 1, 3, 9, 10, 220]
			quicksort(arr, j+1, last);	// Sort all array elements above pivot number e.g more than '9' here [5, 3, 4, 8, 1, 3, 9, 10, 220]
		}
	}
	
	public static void printArray(int[] x) {
		for (int a : x)
			System.out.print(a + " ");
		System.out.println();
	}
}
