package library;

import java.util.ArrayList;
import java.util.Collection;

import tdl.SearchResults;
import tdl.SearchSession;
import tdl.TheDigitalLibrary;

class TheDigitalLibraryUtils {

	static SearchSession openSearch(String keyword) {
		return TheDigitalLibrary.getLibrary().openSearchSession();
	}

	static Collection<String> getFirstPageOfHeaders(SearchSession session, String keyword) {
		SearchResults search = TheDigitalLibrary.getLibrary().startSearch(session, keyword, 20);

		return readPageOfHeaders(search);
	}

	static Collection<String> fetchNextPageOfHeaders(SearchSession session) {
		SearchResults search = TheDigitalLibrary.getLibrary().fetchNextPage(session);

		return readPageOfHeaders(search);
	}

	static Collection<String> readPageOfHeaders(SearchResults search) {
		ArrayList<String> page = new ArrayList<String>();

		while (search.hasNextBookTitle()) {
			page.add(search.getBookTitle());
		}

		return page;
	}

	static void closeSearch(SearchSession session) {
		if (session != null) {
			TheDigitalLibrary.getLibrary().closeSearchSession(session);
		}
	}

	static BookTitle constructBookTitle(String bookHeading) {
		int index = bookHeading.lastIndexOf(" by ");
		String title = bookHeading.substring(0, index);
		String author = bookHeading.substring(index + 4);

		return new BookTitle(author, title);
	}

}