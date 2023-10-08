package co.bk.javaskills.basics.sorting.mergesort;

/**
 * Merge sort is a "divide and conquer" algorithm.
 *
 * Most implementations produce a stable sort, meaning that the implementation preserves the
 * input order of equal elements in the sorted output.
 *
 * Merge sort is an O(n log n) comparison-based sorting algorithm
 *
 * Merge sort operates by:
 * 1. Divide the unsorted list into n sublists, each containing 1 element.
 * 2. Repeatedly merge sublists to produce new sorted sublists until there is only 1 sublist remaining.
 *    This will be the sorted list.
 *
 * SEE http://newtechnobuzzz.blogspot.ie/2014/07/why-quicksort-is-better-than-mergesort.html#.VuFJbHzhA8p
 */
public class MergeSort {

	public static void main(String[] args) {
		int[] x = { 9, 3, 4, 220, 1, 3, 10, 5, 8 };
		printArray(x);

		int low = 0;
		int high = x.length - 1;

		mergesort(x, low, high);
		printArray(x);
	}

	public static void merge(int a[], int low, int mid, int high) {
		int b[] = new int[a.length];
		int i = low, j = mid + 1, k = 0;

		while (i <= mid && j <= high) {
			if (a[i] <= a[j])
				b[k++] = a[i++];
			else
				b[k++] = a[j++];
		}
		while (i <= mid)
			b[k++] = a[i++];

		while (j <= high)
			b[k++] = a[j++];

		k--;
		while (k >= 0) {
			a[low + k] = b[k];
			k--;
		}
	}

	public static void mergesort(int a[], int low, int high) {

		if (low < high) {
			int m = (high + low)/2;
			mergesort(a, low, m);
			mergesort(a, m + 1, high);
			merge(a, low, m, high);
		}
	}

	
	public static void printArray(int[] x) {
		for (int a : x)
			System.out.print(a + " ");
		System.out.println();
	}
}
