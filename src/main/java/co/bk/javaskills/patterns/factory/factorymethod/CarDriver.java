package co.bk.javaskills.patterns.factory.factorymethod;

public class CarDriver extends VehicleDriver {

	@Override
	public Vehicle getInstance() {
		return new Car();
	}
}
