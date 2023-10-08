package co.bk.javaskills.patterns.factory.factorymethod;

public class Jeep implements Vehicle {

	public void drive() {
		System.out.println("Jeep drives offroad.");
	}
	
	public void clean() {
		System.out.println("Jeep goes to river.");
	}
}
