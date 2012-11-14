package library;

import java.util.Date;

class Loan {

	private BookCopy copy;
	private LibraryUser user;
	private Date dueDate;

	Loan(BookCopy copy, LibraryUser user, Date dueDate) {
		this.copy = copy;
		this.user = user;
		this.dueDate = dueDate;
	}

	public BookCopy getCopy() {
		return copy;
	}

	public LibraryUser getLibraryUser() {
		return user;
	}

	public Date getDueDate() {
		return dueDate;
	}

}