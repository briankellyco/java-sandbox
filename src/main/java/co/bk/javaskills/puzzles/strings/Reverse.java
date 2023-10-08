package co.bk.javaskills.puzzles.strings;

public class Reverse {

	public static void main(String[] args) {
		
		/**
		 * Approaches to reverse a string:
		 * 1. Iteratively
		 * 2. Recursively
		 * 3. Using StringBuilder
		 */
		String str = "heh heh my my";
		
		reverseIteratively(str);
		reverseUsingStringBuilder(str);
		reverseUsingRecursion(str);
	}
	
	public static void reverseIteratively(String toReverse) {
		StringBuffer sb = new StringBuffer();
		int len = toReverse.length();
		
		//Appends characters
		for (int i = (len - 1); i >= 0; i--) {
			sb.append(toReverse.charAt(i));
		}
		System.out.println("reverseIteratively output: " + sb.toString());
	}
	
	public static void reverseUsingStringBuilder(String toReverse) {
		String output = new StringBuilder(toReverse).reverse().toString();
		System.out.println("reverseUsingStringBuilder output: " + output);
	}
	
	
	public static String reverseUsingRecursion(String str) {
		
	    if ((null == str) || (str.length()  <= 1)) {
	    	return str;
	    }
	    String output = reverseUsingRecursion(str.substring(1)) + str.charAt(0);
		System.out.println("reverseUsingRecursion output: " + output);
		return output;
	}
	
	
}
