package library;

abstract class BookReturnStrategy {

	abstract void returnBookCopy(BookCopy copy);

	static BookReturnStrategy getStrategy(Library library, BookCopy book) {
		if (book.getStatus() == BookCopy.Status.Damaged) {
			return new DamagedBookReturnStrategy();
		} else if (library.getBooks().contains(book)) {
			return new LibraryBookReturnStrategy();
		} else {
			return new UnknownBookReturnStrategy();
		}
	}

}