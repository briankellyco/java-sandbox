package co.bk.javaskills.patterns.factory.factorymethod;

/**
 * Factory method approach would work just as well with this as a concrete class if that was
 * what was preferred (where getInstance() returned an object).
 * 
 * @see http://en.wikipedia.org/wiki/Factory_method_pattern
 */
public abstract class VehicleDriver {
	
	//A sub-class object is instantiated and returned
	public abstract Vehicle getInstance();
	
	public void driveVehicle(){
		getInstance().drive();
	}
	
	public void cleanVehicle(){
		getInstance().clean();
	}
}
