package co.bk.javaskills.basics.enums;

public class EnumTest {

	public static void main(String[] args) {
		
		for ( TaxType tt : TaxType.values() ){
			System.out.println("TaxType name: " + tt.name() + ", alias via toString=" + tt);
		}
	}

}
