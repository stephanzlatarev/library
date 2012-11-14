package library;

/**
 * A title of a book from the library's catalog.
 * Copies of the title are represented with <code>BookCopy</code>
 */
public class BookTitle {

	private String author;
	private String title;

	/**
	 *  A new book with a given author and title
	 * 
	 * @param author the author of the book
	 * @param title the title of the book
	 */
	public BookTitle(String author, String title) {
		this.author = author;
		this.title = title;
	}

	/**
	 * @return the author of the book
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @return the title of the book
	 */
	public String getTitle() {
		return title;
	}

	@Override
	public int hashCode() {
		return (author + title).hashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof BookTitle) {
			BookTitle book = (BookTitle) object;
			return this.title.equals(book.title) && this.author.equals(book.author);
		} else {
			return false;
		}
	}

}