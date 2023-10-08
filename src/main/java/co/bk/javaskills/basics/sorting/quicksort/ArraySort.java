package co.bk.javaskills.basics.sorting.quicksort;

import java.util.Arrays;

/**
 * Sort  method uses a variation of Quick sort algorithm
 */
class ArraySort {

	public static void main(String args[]) {
		int data[] = { 4, -5, 2, 6, 1 };

		Arrays.sort(data);

		for (int c: data) {
			System.out.println(c);
		}
	}
}