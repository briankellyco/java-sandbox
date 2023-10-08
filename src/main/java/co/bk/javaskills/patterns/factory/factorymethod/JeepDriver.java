package co.bk.javaskills.patterns.factory.factorymethod;

public class JeepDriver extends VehicleDriver {

	@Override
	public Vehicle getInstance() {
		return new Jeep();
	}
}
