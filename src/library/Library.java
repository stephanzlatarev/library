package library;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class Library implements Subject {

	private Catalog catalog;
	private Collection<BookCopy> books;
	private Collection<Observer> observers;

	public Library() {
		this.catalog = new Catalog();
		this.books = new HashSet<BookCopy>();
		this.observers = new HashSet<Observer>();
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void addBookCopy(BookCopy book) {
		book.setStatus(BookCopy.Status.Available);
		books.add(book);
		catalog.addTitle(book.getTitle());

		notifyObservers(book);
	}

	public Collection<BookCopy> getBooks() {
		return Collections.unmodifiableCollection(books);
	}

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void unregisterObserver(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers(BookCopy copy) {
		for (Observer observer: observers) {
			observer.notifyOnAvailableCopy(copy);
			break;
		}
	}

}