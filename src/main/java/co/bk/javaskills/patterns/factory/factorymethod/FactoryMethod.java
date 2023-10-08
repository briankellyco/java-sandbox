package co.bk.javaskills.patterns.factory.factorymethod;

/**
 * "FactoryMethod" pattern is characterised as:
 * <p>
 * Defining an interface for creating objects, but lets subclasses decide which class to 
 * instantiate and refers to the newly created object through a common interface.
 * <p>
 * FactoryMethod is very similar to "Factory" pattern except it relies on inheritance instead of a discriminator
 * to return desired types.
 * <p>
 * Factory methods deals with the problem of creating objects without specifying the class of object that will be created.
 * Programmers can focus on their calls to the interface methods without worrying about instantiating
 * the objects (car and jeep ).
 * <p>
 * Factory pattern: The factory produces IProduct-implementations
 * <p>
 * Examples: 
 * Classroom (primary/secondary) containing students (primary or secondary types)
 */
public class FactoryMethod {

	public static void main(String[] args) {
		
		handleVehicle(new CarDriver());
		handleVehicle(new JeepDriver());
	}
	
	static void handleVehicle(VehicleDriver driver){
		driver.driveVehicle();
		driver.cleanVehicle();
	}
	
}
