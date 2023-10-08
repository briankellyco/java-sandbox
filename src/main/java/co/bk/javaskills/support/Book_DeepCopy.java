package co.bk.javaskills.support;


import java.io.Serializable;
import java.util.List;

import co.bk.javaskills.basics.clonecopy.DeepCopy;


public class Book_DeepCopy implements Serializable, Cloneable {

	private static final long serialVersionUID = 5274322557288890881L;
	
	private String title = "";
	private List authors = null;
	
	public Book_DeepCopy(String title, List<Author> authors){
		this.title = title;
		this.authors = authors;
	}
	
	/**
	 * Utility constructor. Helps clone a new object from an existing one.
	 */
	public Book_DeepCopy(Book_DeepCopy original){
		this.title = original.getTitle();
		this.authors = DeepCopy.cloneList( original.getAuthors());
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List getAuthors() {
		return authors;
	}

	public void setAuthors(List authors) {
		this.authors = authors;
	}
	
    public Object clone() {
        
    	//Deep copy: primitive data types copied and object data types have new references created.
        return new Book_DeepCopy( this );
    }
	  
}
