package co.bk.javaskills.basics.sorting.bubble;

/**
 * A bubble sort repeatedly compares adjacent elements of an array. 
 * A bubble sort will continue until no swaps have occurred.
 * 
 * In bubble sort, we basically traverse the array from first
 * to array_length - 1 position and compare the element with the next one.
 * Element is swapped with the next element if the next element is greater.
 *
 * Bubble sort steps are as follows.
 *
 * 1. Compare array[0] & array[1]
 * 2. If array[0] > array [1] swap it.
 * 3. Compare array[1] & array[2]
 * 4. If array[1] > array[2] swap it.
 * ...
 * 5. Compare array[n-1] & array[n]
 * 6. if [n-1] > array[n] then swap it.
 *
 * After this step we will have largest element at the last index.
 *
 * Repeat the same steps for array[1] to array[n-1]
 * 
 * NOTE: Complexity of bubble sort is O(n2).
 *
 * O(N2) represents an algorithm whose performance is directly proportional to the square of
 * the size of the input data set. This is common with algorithms that involve nested iterations
 * over the data set. Deeper nested iterations will result in O(N3), O(N4) etc.
 *
 * SEE https://rob-bell.net/2009/06/a-beginners-guide-to-big-o-notation/
 *
 */
public class BubbleSort {

	public static void main(String[] args) {

		//create an int array we want to sort using bubble sort algorithm
		int numbers[] = new int[]{5,90,35,45,150,3};

		//print array before sorting using bubble sort algorithm
		System.out.println("Array Before Bubble Sort");
		for(int i=0; i < numbers.length; i++){
			System.out.print(numbers[i] + " ");
		}

		//sort an array using bubble sort algorithm
		bubbleSort(numbers);

		System.out.println("");

		//print array after sorting using bubble sort algorithm
		System.out.println("Array After Bubble Sort");
		for(int i=0; i < numbers.length; i++){
			System.out.print(numbers[i] + " ");
		}

	}

	private static void bubbleSort(int[] numbers) {

		int n = numbers.length;
		int temp = 0;

		for(int i=0; i < n; i++){
			for(int j=1; j < (n-i); j++){
				if(numbers[j-1] > numbers[j]){ /* For descending order use < */
					//swap the elements
					temp = numbers[j-1];
					numbers[j-1] = numbers[j];
					numbers[j] = temp;
				}
			}
		}

	}
}
