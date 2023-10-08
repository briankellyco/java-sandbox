package co.bk.javaskills.basics.queues;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Queues typically, but do not necessarily, order elements in a FIFO (first-in-first-out) manner
 * <p>
 * The Queue interface for LIFO (instead of LinkedList) provides advantage to change the concrete class
 * without having to modify how the queue is handled.
 */
public class FifoQueue {

	public static void main(String[] args) {
        char arr[] = {3,1,4,1,5,9,2,6,5,3,5,8,9};
        Queue<Integer> fifo = new LinkedList<Integer>();

        for (int i=0; i<arr.length; i++) {
        	fifo.add (new Integer (arr[i]));
        }
            
        System.out.print (fifo.remove() + ".");
        while (!fifo.isEmpty()) {
            System.out.print (fifo.remove());
        }
        System.out.println();

	}

}
