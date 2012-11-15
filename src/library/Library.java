package library;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class Library {

	private Catalog catalog;
	private Collection<BookCopy> books;

	private BookAvailabilityListener listener;

	public Library() {
		this.catalog = new Catalog();
		this.books = new HashSet<BookCopy>();
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void addBookCopy(BookCopy book) {
		book.setStatus(BookCopy.Status.Available);
		books.add(book);
		catalog.addTitle(book.getTitle());

		if (listener != null) {
			listener.onAvailableCopy(book);
		}
	}

	public Collection<BookCopy> getBooks() {
		return Collections.unmodifiableCollection(books);
	}

	public void registerListener(BookAvailabilityListener listener) {
		this.listener = listener;
	}

}