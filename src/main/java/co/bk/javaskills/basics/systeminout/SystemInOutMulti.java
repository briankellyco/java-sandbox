package co.bk.javaskills.basics.systeminout;

import java.util.Scanner;

public class SystemInOutMulti {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		int numberOfLinesToRead = new Integer(s.nextLine());
		String[] result = new String[numberOfLinesToRead];
		String line = "";
		for(int i = 0; i < numberOfLinesToRead; i++) {
		    result[i] = s.nextLine();
		}
		System.out.println("size=" + result.length);
	}

}
