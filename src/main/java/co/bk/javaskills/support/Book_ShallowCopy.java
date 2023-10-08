package co.bk.javaskills.support;


import java.io.Serializable;
import java.util.List;


public class Book_ShallowCopy implements Serializable, Cloneable {

	private static final long serialVersionUID = -6742738207410890430L;

	private String title = "";
	private List authors = null;
	
	public Book_ShallowCopy(String title, List<Author> authors){
		this.title = title;
		this.authors = authors;
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
        
    	//Shallow copy: only fields of primitive data type are copied while object references are not copied.
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
	  
}
