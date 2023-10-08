package co.bk.javaskills.patterns.factory.simplefactory;

/**
 * "Factory" pattern is responsible for creating instances of your classes.
 *
 * Factories are basically smart constructors.
 * <p>
 * Three "factory" patterns exist:
 * 1. SimpleFactory (this being an example).
 * 2. Factory Method (inheritance determines what objects are instantiated).
 * 3. Abstract Factory
 * <p>
 * This factory implementation is the simplest and most intuitive factory type possible. 
 * <p>
 * Factories enable complex object initialisation to be centralised and does not expose that logic.
 * Only when inheritance is involved (a class implementing an interface, or derived class, implements a factory method) is it an example of the factory method pattern.
 * <p>
 * Advantages: Loose coupling provided
 * <p>
 * Drawbacks: Inflexible in that a new dog definition means the factory needs to be modified. Violates the "open close principle" which
 * states "the design and writing of the code should be done in a way that new functionality should be added with minimum changes in 
 * the existing code". Further info: http://www.oodesign.com/open-close-principle.html
 * 
 * @author brian kelly
 */
public class Factory {

	public static void main(String[] args) {
		Dog d = Factory.getInstance("small");
		d.talk();
		
		d = Factory.getInstance("medium");
		d.talk();
		
		d = Factory.getInstance("large");
		d.talk();
	}

	public static Dog getInstance(String size) {
		
		if (size != null && !"".equalsIgnoreCase(size)) {
			
			if ("small".equalsIgnoreCase(size)) {
				return new Terrier();
			} else if ("medium".equalsIgnoreCase(size)) {
				return new Spaniel();
			} else if ("large".equalsIgnoreCase(size)) {
				return new Labrador();
			}
		}
		return null;
	}
}
