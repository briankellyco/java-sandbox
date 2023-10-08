package co.bk.javaskills.basics.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Sort a list of objects using java.support.Comparator.
 * 
 * @author brian kelly
 */
public class ComparatorSort {

	
	public static void main(String[] args) {
		ComparatorSort csort = new ComparatorSort();
		List<InternalFeature> list = csort.buildAndSortFeatureList(csort);
		
		for (InternalFeature feat: list) {
			System.out.println("feature order before sort: " + feat.getLayername());
		}
		
		//Sort method 1
		sortUsingStaticComparator(list);
	
		//Sort method 2. Working fine.
		//sortUsingAnonymousComparator(list);
		
		//Sort method 3
		sortUsingComparatorSuppliedToListConstructor(list);
		
		for (InternalFeature feat: list) {
			System.out.println("feature order after sort: " + feat.getLayername());
		}
		
	}
	
	/**
	 * Option 1
	 * Statically defined comparator used to sort
	 */
	private static void sortUsingStaticComparator(List list) {
		Collections.sort(list, new FeatureComparator());
	}
	
	/**
	 * Option 2
	 * Anonymous comparator used to sort.
	 */
	private static void sortUsingAnonymousComparator(List list) {
		Collections.sort(list, new Comparator<InternalFeature>() {
			
		      public int compare(InternalFeature if1, InternalFeature if2) {
		          return if1.getLayername().compareTo(if2.getLayername());
		      }
		});
	}
	
	
	/**
	 * Option 3
	 * Initialising a SortedSet or SortedMap (like TreeSet) will also sort objects. 
	 * @param list
	 */
	public static void sortUsingComparatorSuppliedToListConstructor(List list) {
		
		Set<InternalFeature> sortedOnAddition = new TreeSet<InternalFeature>(new FeatureComparator());
		sortedOnAddition.add((InternalFeature) list.get(20));
		sortedOnAddition.add((InternalFeature) list.get(40));
		sortedOnAddition.add((InternalFeature) list.get(10));
		
		for (InternalFeature feat: sortedOnAddition) {
			System.out.println("feature order after putting features into SET object: " + feat.getLayername());
		}
	}
	
	public List buildAndSortFeatureList(ComparatorSort csort) {
		
		List list = new ArrayList();
		
		Random random = new Random();
		InternalFeature feature = null;
		for (int i=0; i<50; i++) {
			
			int id = random.nextInt();
			String layername = null;
			if (i % 5 == 0) {
				layername = "water";
			} else if (i % 8 == 0) {
				layername = "basketball courts";
			} else {
				layername = RandomStringUtils.randomAlphanumeric(20).toLowerCase();
			}
			feature = csort.new InternalFeature(id, layername);
			list.add(feature);
		}
		return list;
	}
	
	
	//Alternatively could used "implements Comparator" and cast objects
	static class FeatureComparator implements Comparator<InternalFeature> {
		
			public int compare(InternalFeature if1, InternalFeature if2) {
	          return if1.getLayername().compareTo(if2.getLayername());
	      }
	}
	  
	  /*
	   * Representing an object on a map. A lakes layer contains only lakes. A roads
	   * layer contains only roads....etc.
	   */
	  class InternalFeature {
		  
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
		
	  }
	  
}
