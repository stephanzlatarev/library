package library;

class Reservation {

	private BookTitle title;
	private LibraryUser user;

	Reservation(BookTitle title, LibraryUser user) {
		this.title = title;
		this.user = user;
	}

	public BookTitle getTitle() {
		return title;
	}

	public LibraryUser getLibraryUser() {
		return user;
	}

}