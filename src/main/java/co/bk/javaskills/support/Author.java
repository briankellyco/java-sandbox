package co.bk.javaskills.support;

import java.io.Serializable;

//Author may have many books
public class Author implements Serializable {

	private String name;

	public Author(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
