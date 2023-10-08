package co.bk.javaskills.basics.clonecopy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Concepts: 
 * 	1. shallow copy vs deep copy
 * 		http://en.wikipedia.org/wiki/Shallow_copy#Shallow_copy
 * 
 * @author brian kelly
 * @author http://javatechniques.com/blog/faster-deep-copies-of-java-objects/
 */
public class DeepCopy {
	
	private static Class[] paramTypes = new Class[]{};
	private static Object[] paramValues  = new Object[]{};
	  
	//Prevent class from being instantiated
	private DeepCopy(){
	}
	  
	/**
	 * Two approaches to "deep copy" exist.
	 * 1. An object implements a "clone" method that creates a new object reference:  	
	 *      public Object clone() {
     * 			return new LineFeature( this );
     * 		}
     * 2. Serialization is used to write the whole object graph to a persistent store and then read back.
     *    The original object is retained but the object reference changes. 
     *    Approach is useful when an object does not offer a "clone" method. 
     *    Represents a fail-over way of doing the job in "cloneList" method.
	 *  
	 * @param list to be cloned
	 * @return cloned list
	 */
	public static List cloneList(List list) {
		  
		List rList = new ArrayList(list.size());
	    try {
	    	
	      Object o = null;
	      for (int i=0; i < list.size(); i++) {
	    	  o = list.get(i);
	    	  rList.add( o.getClass().getMethod("clone", paramTypes).invoke(o, paramValues));
	      }
	    } catch (Exception e) {
	    	rList = (List) copy(list);
	    }
	    return rList;
	}
	
	
	/**
	 * Deep copy alternative approach. The whole object graph is written into a persistent store and read back.
	 * <p>
     * Returns a copy of the object, or null if the object cannot be serialized.
	 * 
	 * @param obj The object to DeepCopy (must implement Serializable)
	 * @return A new version of the Object
	 */
	public static Object copy(Object obj) {
		
		 Object rObject = null;
		 
		 if (obj == null){
			 return null;
		 }

		 try {
	    	//Write the object out to a byte array
			FastByteArrayOutputStream fbos = new FastByteArrayOutputStream();
	        ObjectOutputStream out = new ObjectOutputStream(fbos);
	    	out.writeObject( obj );
	    	out.flush();
	    	out.close();

	    	//Make an input stream from the byte array and read a copy of the object back in.
	    	ObjectInputStream in = new ObjectInputStream(fbos.getInputStream());
	    	rObject = in.readObject();

	    	in.close();
	    	fbos.close();
		 } catch (IOException ioe){
			 ioe.printStackTrace();
		 } catch (ClassNotFoundException cnfe) {
			 cnfe.printStackTrace();
		 }
		 return rObject;
	}
	  
	  

}
