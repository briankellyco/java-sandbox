package co.bk.javaskills.basics.systeminout;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SystemInOutWait {

	public static void main(String[] args) {
		
	    String newLine = System.getProperty("line.separator");
	    System.out.println("Reading Strings from console");
	    
	      //You use System.in to get the Strings entered in console by user
	      try {
	    	  //You need to create BufferedReader which has System.in to get user input
	    	  BufferedReader br = new BufferedReader(new
	                              InputStreamReader(System.in));
	    	  String userInput;
	    	  System.out.println("Enter text...");
	    	  System.out.println("Enter 'quit' to quit.");
	    	  do {
	    		  //reader .readLine() would wait for the user to enter strings.
	    		  //keep and entering text and when you press enter, all those would be displayed
	    		  //so it is keeping it in buffer until the user types 'quit' and presses enter
	    		  userInput = (String) br.readLine();
	    		  System.out.println("You entered : " + userInput);
	    	  } while(!userInput.equals("quit"));
	      }
	      catch(Exception e){
	      }
		
	}

}
