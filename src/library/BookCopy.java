package library;

/**
 * An instance of a book copy of a given title.
 */
public class BookCopy {

	public enum Status {
		Available, // Book copy is in library, ready to be lent
		Lent,      // Book copy is lent to a library user
		Damaged    // Book copy is damaged
	};

	private BookTitle title;
	private Status status;

	/**
	 *  A new book copy of a given title
	 *
	 * @param title the title of the book
	 */
	public BookCopy(BookTitle title) {
		this.title = title;
	}

	/**
	 * @return the book title
	 */
	public BookTitle getTitle() {
		return title;
	}

	/**
	 * @return the availability status of the book copy
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 *  Changes the availability status of the book copy 
	 *
	 * @param status the up-to-date status of the book copy
	 */
	void setStatus(Status status) {
		this.status = status;
	}

}