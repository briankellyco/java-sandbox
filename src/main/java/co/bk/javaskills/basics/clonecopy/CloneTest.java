package co.bk.javaskills.basics.clonecopy;

import java.util.ArrayList;
import java.util.List;

import co.bk.javaskills.support.Author;
import co.bk.javaskills.support.Book_DeepCopy;
import co.bk.javaskills.support.Book_ShallowCopy;

/**
 * Illustrates differences between "shallow" and "deep" copying (cloning) of objects.
 * <p>
 * "Deep" creates new object references for object data types.
 * 
 * @author brian kelly
 */
public class CloneTest {

	public static void main (String[] args) {
		
        /**
         * Shallow copy
         */
		List<Author> authors = new ArrayList<Author>();
		authors.add(new Author("Johnson"));
		authors.add(new Author("Vlissides"));
		authors.add(new Author("Helm"));
		authors.add(new Author("Gamma"));
		
		Book_ShallowCopy bookS = new Book_ShallowCopy("GangOfFour", authors);
		System.out.println("Shallow copy: Original Object: BookTitle=" + bookS.getTitle() + " & Authors=" + printAuthors(bookS.getAuthors()));
		
		//Clone object
		Book_ShallowCopy bookSclone = (Book_ShallowCopy) bookS.clone();
		System.out.println("Shallow copy: Cloned Object: BookTitle=" + bookSclone.getTitle() + " & Authors=" + printAuthors(bookSclone.getAuthors()));
		
		//Update original object
		bookS.setTitle("GangOfFive");
		bookS.getAuthors().add(new Author("Young"));
		System.out.println("Shallow copy: Post update: Original Object: BookTitle=" + bookS.getTitle() + " & Authors=" + printAuthors(bookS.getAuthors()));
		System.out.println("Shallow copy: Post update: Cloned Object: BookTitle=" + bookSclone.getTitle() + " & Authors=" + printAuthors(bookSclone.getAuthors()));
		
		/**
		 * Deep Copy
		 */
		List<Author> authorsAlt = new ArrayList<Author>();
		authorsAlt.add(new Author("Johnson"));
		authorsAlt.add(new Author("Vlissides"));
		authorsAlt.add(new Author("Helm"));
		authorsAlt.add(new Author("Gamma"));
		
		Book_DeepCopy bookD = new Book_DeepCopy("GangOfFour", authorsAlt);
		System.out.println("Deep copy: Original Object: BookTitle=" + bookD.getTitle() + " & Authors=" + printAuthors(bookD.getAuthors()));
		
		//Clone object
		Book_DeepCopy bookDclone = (Book_DeepCopy) bookD.clone();
		System.out.println("Deep copy: Cloned Object: BookTitle=" + bookDclone.getTitle() + " & Authors=" + printAuthors(bookDclone.getAuthors()));
		
		//Update original object
		bookD.setTitle("GangOfFive");
		bookD.getAuthors().add(new Author("Crosbie"));
		System.out.println("Deep copy: Post update: Original Object: BookTitle=" + bookD.getTitle() + " & Authors=" + printAuthors(bookD.getAuthors()));
		System.out.println("Deep copy: Post update: Cloned Object: BookTitle=" + bookDclone.getTitle() + " & Authors=" + printAuthors(bookDclone.getAuthors()));

	}
	
	
	
	private static String printAuthors(List<Author> authors) {
		StringBuilder sb = new StringBuilder();
		for (Author author: authors) {
			sb.append(" " + author.getName());
		}
		return sb.toString();
	}
}
