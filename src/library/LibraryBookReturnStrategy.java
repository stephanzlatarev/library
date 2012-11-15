package library;

class LibraryBookReturnStrategy extends BookReturnStrategy {

	void returnBookCopy(BookCopy book) {
		book.setStatus(BookCopy.Status.Available);
	}

}