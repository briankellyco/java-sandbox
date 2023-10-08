package co.bk.javaskills.basics.enums;

/**
 * Type-safe enumerations. Implicitly final.
 * 
 * If an enum is a member of a class, it's implicitly static.
 * 
 * The == and equals() are equivalent for an enum, with added advantage == will
 * never throw a nullpointer exception.
 * 
 */
public enum TaxType {

	IT("IT", false, true),
	VAT("VAT", true, false),
	PAYE("PAYE", false, true);
	
	private final String abbreviation;
	
	private final boolean bMultipleRegistrations;
	
	private final boolean bAllowedToRegister;
	
	/**
	 * Constructor.
	 * 
	 * @param tax abbreviation
	 * @param bMultipleRegistrations
	 * @param bAllowedToRegister
	 */
	TaxType(String abbr, boolean bMultipleRegistrations, 
			boolean bAllowedToRegister) {
		this.abbreviation = abbr;
		this.bMultipleRegistrations = bMultipleRegistrations;
		this.bAllowedToRegister = bAllowedToRegister;
		
	}
		
	public static TaxType fromValue(String v) {
		for (TaxType tt : TaxType.values()) {
			if (tt.abbreviation.equals(v)) {
				return tt;
			}
		}		
		throw new IllegalArgumentException(v.toString());
	}
	
	public String getTaxAbbreviation() {
		return abbreviation;
	}
	
	/**
	 * Method provides a user friendly text/label for each enum type. It overrides the 
	 * default ENUM value returned.
	 * <p>
	 * The value returned changes depending on how it is accessed:
	 * <ul>
	 * <li>Using EL: ${myEnumValue} in a JSP will return the "name" value of the enum. For example the enum: <br/>
	 * VAT("VAT", false, true)<br/><br/> would return "VAT" in the jsp page. 
	 * <li>Using JSTL: <c:out value="${myEnumValue}"/> in a JSP will return the "toString()" value.
	 *</ul> 
	 */
	public String toString() {
		if (this == IT) {
			return "Income Tax";
		} else if (this == VAT) {
			return "VAT Tax";
		} else if (this == PAYE) {
			return "PAYE Tax";
		} else {
			return null;
		}
	}
}
