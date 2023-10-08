package co.bk.javaskills.patterns.factory.abstractfactory;

/**
 * 
 * Abstract Factory Pattern: A factory-factory produces IFactories, which in turn produces IProducts.
 * <p>
 * Advantages:
 * 1. It isolates the concrete classes that are generated.  The names of actual implementing classes need not to be known on the client side.
 *    With the isolation, you can change the implementation from one factory to another.
 * 
 * Reasons for using:
 * 1. You often don't just want to create a single object (as with Factory method) rather you want to create a collection of related objects.
 * 
 * @see http://howtodoinjava.com/2012/10/29/abstract-factory-pattern-in-java/
 */
public class AbstractFactory {
	
	private Computer comp;
	
	public static void main(String[] args) {
	
		AbstractFactory type = new AbstractFactory();
		Computer computer = type.getComputer("Server");
	 	System.out.println("Monitor: "+computer.getMonitor().getSpecification());
	 	System.out.println("RAM: "+computer.getRAM().getSpecification());
	 	System.out.println("Processor: "+computer.getProcessor().getSpecification());
	} 	 
	 	 	
	/**
	 * Returns a computer for a type
	 *
	 * @param computerType String, PC / Workstation / Server
	 * @return Computer
	 */
	public Computer getComputer(String computerType) {
	
		if (computerType.equals("PC"))
			comp = new PC();
		else if(computerType.equals("Workstation"))
			comp = new Workstation();
		else if(computerType.equals("Server"))
			comp = new Server();
		return comp;
	}
}
