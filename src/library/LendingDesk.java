package library;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * The lending desk of the library handles the book lending and return procedures
 */
public class LendingDesk {

	private Library library;
	private MailService mailService;

	private HashMap<BookCopy, Loan> loans = new HashMap<BookCopy, Loan>();
	private ArrayList<Reservation> reservations = new ArrayList<Reservation>();

	public LendingDesk(Library library, MailService mailService) {
		this.library = library;
		this.mailService = mailService;

		library.desk = this;
	}

	public void reserveCopy(BookTitle title, LibraryUser user) {
		reservations.add(new Reservation(title, user));
	}

	void processAvailableCopy(BookCopy copy) {
		Reservation processedReservation = null;

		for (Reservation reservation: reservations) {
			if (reservation.getTitle().equals(copy.getTitle())) {
				processedReservation = reservation;
				break;
			}
		}

		if (processedReservation != null) {
			// lend the copy to the waiting user
			Calendar dueDate = Calendar.getInstance();
			dueDate.add(Calendar.MONTH, 1);

			copy.setStatus(BookCopy.Status.Lent);
			loans.put(copy, new Loan(copy, processedReservation.getLibraryUser(), dueDate.getTime()));

			// send the copy via mail service
			mailService.deliverBookTo(copy, processedReservation.getLibraryUser());

			// set reservation to completed
			reservations.remove(processedReservation);
		}
	}

	/**
	 * Lends a book copy to a library user for a month
	 *
	 * @param book the book copy to lend
	 * @param user the library user who lends the book
	 */
	public BookCopy lendCopy(BookTitle title, LibraryUser user) {
		for (BookCopy copy: library.getBooks()) {
			if (copy.getTitle().equals(title) && (copy.getStatus() == BookCopy.Status.Available)) {
				Calendar dueDate = Calendar.getInstance();
				dueDate.add(Calendar.MONTH, 1);

				copy.setStatus(BookCopy.Status.Lent);
				loans.put(copy, new Loan(copy, user, dueDate.getTime()));
				return copy;
			}
		}

		// no copy of the book title is available
		return null;
	}

	/**
	 * Returns a book copy to the library
	 *
	 * @param book the lent book copy which is returned
	 */
	public void returnCopy(BookCopy book) {
		if (loans.containsKey(book)) {
			loans.remove(book);
			book.setStatus(BookCopy.Status.Available);
		}
	}

}