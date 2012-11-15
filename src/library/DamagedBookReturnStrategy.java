package library;

import java.util.logging.Logger;

class DamagedBookReturnStrategy extends BookReturnStrategy {

	private Logger log = Logger.getLogger("RepairService");

	void returnBookCopy(BookCopy book) {
		log.info("Book copy " + book + " needs repair");
	}

}