package co.bk.javaskills.basics.comparison;

/**
 * Equivalence checks
 * 
 * @author brian kelly
 */
public class EqualsUtil {

	
	public static void main(String[] args) {
		
		Integer iOne = Integer.valueOf(24);
		Integer iTwo = Integer.valueOf(24);
		
		/*
		 * Java provides two equality checks in java.lang.Object class.
		 * 1. equals() - may be overriden
		 * 2. hashCode() - quicker way of checking, creating a hash of internal data structures
		 * An object with an overriden equals() MUST have a hashCode() method.
		 */
		boolean bEqual = EqualsUtil.checkEquivalence(iOne, iTwo);
		System.out.println("objects equal? " + bEqual);
		
	}
	
	  public static boolean checkEquivalence( Object obj1, Object obj2) {
		  if (obj1 != null && obj2 != null && (( obj1 == obj2 ) || obj1.equals( obj2 ))){
			  return true;
		  } else if ( obj1 == null && obj2 == null ) {
			  return true;
	      }
		  return false;
	  }
	  
	  /**
	   * @param a
	   * @param b
	   * @return 0 if objects are same and 1 or -1 if objects are different
	   */
	  public static int compareTo(Object a, Object b){
		  int hashA = a.hashCode();
		  int hashB = b.hashCode();
		  return ( hashA < hashB ? -1 : ( hashA == hashB ? 0 : 1 ) );
	  }
}
