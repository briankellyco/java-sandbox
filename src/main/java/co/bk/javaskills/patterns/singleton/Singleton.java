package co.bk.javaskills.patterns.singleton;

/**
 * Real world uses of singletons:
 * 1. Configuration settings
 * 2. Caches (JNDI etc)
 * 3. Threadpools
 * 
 */
public class Singleton {

	private static Singleton instance = null;
	
	//1. Prevents instatiation
	//2. A singleton cannot be subclassed as the constructor is declared private
	private Singleton() {
	}
	
	public static synchronized Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}
