package co.bk.javaskills.basics.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Sort a list of objects using java.support.Comparator.
 * 
 * @author brian kelly
 */
public class ComparableSort {

	public static void main(String[] args) {
	
		ComparableSort csort = new ComparableSort();
		
		
		List list = new ArrayList();
        list.add(csort.new InternalFeature(2244,"water"));
        list.add(csort.new InternalFeature(3433,"water"));
        list.add(csort.new InternalFeature(4323,"paths"));
        list.add(csort.new InternalFeature(5533,"quarries"));
        
        System.out.println("Before sort......");
        csort.print(list);
        Collections.sort(list);
        
        System.out.println("After sort......");
        csort.print(list);
		
	}
	

	public void print(List list) {
		Iterator it = list.iterator();
		while(it.hasNext()) {
			InternalFeature feat = (InternalFeature) it.next();
			System.out.println(feat);
		}
	}
	
	  /*
	   * Represents an object on a layer of a map (roads, water, lakes, paths...)
	   */
	  class InternalFeature implements Comparable<InternalFeature> {
		  
		  String layername = null;
		  int id;
		  
		  public InternalFeature(int id, String layername) {
			  this.id = id;
			  this.layername = layername;
		  }
		  
		  public String getLayername() {
			return layername;
		}

		public void setLayername(String layername) {
			this.layername = layername;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
		public String toString() {
			return "layername=" + this.layername;
		}
		
	    public int compareTo(InternalFeature feature) {
	    	return this.getLayername().compareTo(feature.getLayername());
	    }
		
	  }

}
