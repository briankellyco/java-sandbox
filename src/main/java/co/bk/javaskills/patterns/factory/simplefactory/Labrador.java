package co.bk.javaskills.patterns.factory.simplefactory;

/**
 * The common parent class can be an abstract class or an interface.
 */
public class Labrador implements Dog {

	public void talk() {
		System.out.println("Labrador says \"what time is dinner?\"");
	}
}
