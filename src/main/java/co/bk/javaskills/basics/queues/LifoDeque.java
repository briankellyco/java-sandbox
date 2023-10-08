package co.bk.javaskills.basics.queues;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

/**
 * LIFO (last in, first out) aka "Stack".
 * 
 */
public class LifoDeque {

	public static void main(String[] args) {
		
		char arr[] = {3,1,4,1,5,9,2,6,5,3,5,8,9};
		Deque<Integer> lifo = new LinkedList<Integer>();
		
		for (int i=0; i<arr.length; i++) {
			lifo.addFirst(Integer.valueOf(arr[i]));
		}
		
		/*
		 * Interact with the stack via the console. push and pop numbers :)
		 */
		try {

    	  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	  String userInput;
    	  System.out.println("Enter 'push' or 'pop' to add or retrieve items from LIFO stack");
    	  System.out.println("Enter 'quit' to quit.");
    	  do {
    		  userInput = (String) br.readLine();
    		  
    		  String data = null;
    		  Integer iObj = null;
    		  if (userInput.startsWith("push")) {
    			  data = userInput.substring(4);
    			  if (data != null && !data.equalsIgnoreCase("")) {

    				  try {
    					  iObj = Integer.valueOf(data);
	    				  lifo.addFirst(iObj); //"push" equivalent	  
	    				  System.out.println("input " + data + " pushed to stack");
    				  } catch (NumberFormatException e) {
    					  System.out.println("input was not numeric!");
    				  }
    			  }
    		  } else if (userInput.startsWith("pop")) {
    			  iObj = lifo.removeFirst(); //"pop" equivalent
    			  System.out.println(iObj.toString() + " popped from stack");
    		  } else if (userInput.startsWith("quit")) {
    			  continue;
    		  } else {
    			  System.out.println("Input not recognised");
    		  }
    		 
    	  } while(!userInput.equals("quit"));
    	  
      }
      catch(Exception e){
      }

	}

}
