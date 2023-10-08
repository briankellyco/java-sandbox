package co.bk.javaskills.basics.sorting.bubble;

public class BubbleSortWhile {

	public static void main(String[] args) {

		//create an int array we want to sort using bubble so
		int numbers[] = new int[]{5,90,35,45,150,3};

		bubbleSort(numbers);

	}

	private static void bubbleSort(int[] numbers) {

		boolean flag = true;   // set flag to true to begin first pass
		int temp;   //holding variable

		while (flag) {
			flag = false;    //set flag to false awaiting a possible swap
			
			for(int j=0;j < numbers.length-1; j++){
				if (numbers[j] < numbers[j+1] ) {   // change to > for ascending sort
					temp = numbers[j];                //swap elements
					numbers[j] = numbers[ j+1 ];
					numbers[j+1] = temp;
					flag = true;              //shows a swap occurred  
				} 
			} 
		} 
	} 


}
