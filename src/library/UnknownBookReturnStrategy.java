package library;

class UnknownBookReturnStrategy extends BookReturnStrategy {

	void returnBookCopy(BookCopy book) {
		throw new IllegalArgumentException("Not a book of ours!");
	}

}